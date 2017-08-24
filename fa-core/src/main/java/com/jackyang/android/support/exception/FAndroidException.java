package com.jackyang.android.support.exception;

/**
 * FAndroidException
 * <p/>
 * Created by jackyang on 3/22/15.
 */
public class FAndroidException extends RuntimeException {

    public FAndroidException() {
    }

    public FAndroidException(String message) {
        super(message);
    }

    public FAndroidException(String message, Throwable cause) {
        super(message, cause);
    }

    public FAndroidException(Throwable cause) {
        super(cause);
    }
}
