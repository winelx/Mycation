package com.newdemo.winelx.greedao.Utils;

import com.newdemo.winelx.greedao.Bean.User;
import com.newdemo.winelx.greedao.db.BaseDbHelper;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class UserHelper  extends BaseDbHelper<User, Long> {
    public UserHelper(AbstractDao dao) {
        super(dao);
    }
}
