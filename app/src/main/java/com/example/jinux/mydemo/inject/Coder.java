package com.example.jinux.mydemo.inject;

import com.example.jinux.mydemo.common.Utils;

import javax.inject.Inject;

/**
 * Created by baidu on 16/4/14.
 */
public class Coder {
    private final Boss mBoss;

    private Coder(Boss boss) {
        Utils.log("create Coder");
        mBoss = boss;
    }

    public static Coder provideCoder(Boss boss) {
        return new Coder(boss);
    }
}
