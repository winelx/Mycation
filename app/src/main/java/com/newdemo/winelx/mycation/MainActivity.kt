package com.newdemo.winelx.mycation

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello
        //跳转
        jump.setOnClickListener {
            startActivityForResult<Main2Activity>(101, "name" to "张三", "age" to 27)

        }
        //切换
        hello.setOnClickListener {
            hello.setText("HelloWorld")
        }

        //提示
        toast.setOnClickListener {
            toast("this is a toast")
            longToast("this is a longtoast")
        }
        add.setOnClickListener {
            add(5, 3)
        }

    }

    fun add(a: Int, b: Int) {
        return hello.setText(a + b);
    }

    fun max(a: Int, b: Int): Int {
        if (a > b)
            return a
        else
            return b
    }
}



