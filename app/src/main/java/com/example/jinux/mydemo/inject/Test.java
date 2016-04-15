package com.example.jinux.mydemo.inject;

import com.example.jinux.mydemo.common.Utils;

import javax.inject.Inject;

/**
 * Created by baidu on 16/4/14.
 */
public class Test {
    @Inject
    public Test() {
        Utils.log("create Test");
    }
}
