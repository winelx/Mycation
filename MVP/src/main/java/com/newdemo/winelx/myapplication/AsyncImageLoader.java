package com.newdemo.winelx.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 10942 on 2018/1/21 0021.
 */

public class AsyncImageLoader {
    final static String TAG = "AsyncImageLoader";
    /**
     * /sdcard/Android/data/%package_name%/files/images
     */
    private static File PIC_PATH;

    private static HashMap<String, SoftReference<Drawable>> picCache;
    private BitmapFactory.Options opts = new BitmapFactory.Options();
    private ExecutorService excutorsService = Executors.newFixedThreadPool(1);
    private int width;
    private int height;
    private Context mContext;

    public AsyncImageLoader(Context context) {
        this.mContext = context;
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d(TAG, "Max memory is " + maxMemory + "M");

        PIC_PATH = new File(mContext.getExternalFilesDir("images")
                + File.separator);

        picCache = new HashMap<String, SoftReference<Drawable>>();
        opts.inSampleSize = 1;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
    }

    public void setThreads(int num) {
        this.excutorsService = Executors.newFixedThreadPool(num);
    }

    public interface ImageLoadCallback {
        public void imageLoaded(Drawable drawable, String imgUrl);
    }

    // 加载图片
    public Drawable loadDrawable(final String imgUrl,
                                 final ImageLoadCallback mCallback) {
        if (TextUtils.isEmpty(imgUrl)) {
            return null;
        }
        // 获取图片文件名称
        final String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
        final String imgUrlHashCode = imgUrl.hashCode() + "";
        // 如果有缓存则返回图片
        if (picCache.containsKey(imgUrlHashCode)) {
            Drawable imgDrawable = picCache.get(imgUrlHashCode).get();
            if (imgDrawable != null) {
                return imgDrawable;
            }
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mCallback.imageLoaded((Drawable) msg.obj, imgUrl);
            }
        };
        excutorsService.submit(new Runnable() {

            @Override
            public void run() {
                Drawable drawable = loadImageFromUrl(imgUrl, fileName);
                if (drawable != null) {// 获取到图片
                    if (width != 0) {
                        try {
                            drawable = zoomDrawable(drawable, width, height,
                                    fileName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        width = 0;
                        height = 0;
                    }
                    picCache.put(imgUrlHashCode, new SoftReference<Drawable>(
                            drawable));
                }
                handler.sendMessage(handler.obtainMessage(0, drawable));
            }
        });
        return null;
    }

    // 通过网络请求获取图片
    public Drawable loadImageFromUrl(String imgUrl, String fileName) {
        if (TextUtils.isEmpty(imgUrl)) {
            return null;
        }
        Drawable drawable = null;
        fileName = String.valueOf(fileName.hashCode()); // 将文件名hash
        if (!PIC_PATH.exists()) {// 判断存放图片的文件夹是否存在
            PIC_PATH.mkdirs();// 不存在则创建一个图片文件夹
        }
        File imgFile = new File(PIC_PATH, fileName);
        if (imgFile.exists()) {// 如果存在在SD卡中，则返回SD卡里的图片
            try {
                drawable = getDrawableFromSdcard(fileName);
            } catch (OutOfMemoryError outOfMemoryError) {
                outOfMemoryError.printStackTrace();
                return null;
            }
            return getDrawableFromSdcard(fileName);
        } else {// 如果内存和SD卡都不存在则网络请求图片
            URL url;
            BufferedInputStream inputStream = null;
            HttpURLConnection con = null;
            try {
                Log.d(TAG, "load imgUrl is:" + imgUrl);
                url = new URL(imgUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(15000);// 设置网络请求超时时间15秒
                con.setReadTimeout(15000);
                con.connect();

                InputStream is = con.getInputStream(); // 获取图片流
                inputStream = new BufferedInputStream(is); // 赋值
                saveImage(imgFile, inputStream); // 保存图片到本地SD卡
                is.close();// 关闭输入流

                // String imgCookie = con.getHeaderField(pos);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (inputStream != null)
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

        return drawable;
    }

    // 从本地SD卡获取图片
    private Drawable getDrawableFromSdcard(String fileName) {

        Bitmap source = BitmapFactory.decodeFile(PIC_PATH + File.separator
                + fileName, opts);
        if (source == null) {
            return null;
        } else {
            return new BitmapDrawable(source);
        }
    }

    // 保存图片到本地SD卡
    private void saveImage(File image, BufferedInputStream bis) {
        if (image != null && image.getParentFile() != null) {
            image.getParentFile().mkdirs();
        }
        FileOutputStream fos = null; // 定义文件输出流
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            fos = new FileOutputStream(image);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            ;
            fos.close();
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 取消所有正在执行的任务
    public void shutDownTread() {
        excutorsService.shutdownNow();
    }

    // 处理图片
    private Drawable zoomDrawable(Drawable drawable, int w, int h,
                                  String fileName) {
        // 取图片的真实大小
        int intrinsicw = drawable.getIntrinsicWidth();
        int intrinsich = drawable.getIntrinsicHeight();
        // 设置为true,表示解析Bitmap对象，该对象不占内存
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(PIC_PATH + File.separator + fileName, opts);

        // 设置为false,解析Bitmap对象加入到内存中
        opts.inJustDecodeBounds = false;
        int width = opts.outWidth;
        int height = opts.outHeight;
        drawable.setBounds(0, 0, width, height);

        int minW = Math.min(width, w);
        int minH = Math.min(height, h);

        float scaleWidth = ((float) w / intrinsicw);
        float scaleHeight = ((float) h / intrinsich);
        Matrix matrix = new Matrix();
        if (minW == width && minH == height) {
            scaleWidth = scaleHeight = mContext.getResources()
                    .getDisplayMetrics().density;
        } else if (minW == width && minH == h) {
            scaleWidth = scaleHeight;
        } else if (minW == w && minH == height) {
            scaleHeight = scaleWidth;
        } else {
            scaleWidth = scaleHeight = Math.min(scaleWidth, scaleHeight);
        }
        Bitmap oldbmp = drawableToBitmap(drawable, width, height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    // Drawable To Bitmap
    private Bitmap drawableToBitmap(Drawable drawable, int width, int height) {
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
}