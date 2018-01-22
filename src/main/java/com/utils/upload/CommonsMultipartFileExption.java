package com.utils.upload;

/**
 * Created by GSN on 2017/4/1.
 *
 *  CommonsMultipartFile实例为null异常
 */
public class CommonsMultipartFileExption extends Exception {

    public CommonsMultipartFileExption() {
    }

    public CommonsMultipartFileExption(String message) {
        super(message);
    }

    public CommonsMultipartFileExption(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonsMultipartFileExption(Throwable cause) {
        super(cause);
    }

    public CommonsMultipartFileExption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
