package com.jackyang.android.support.exception;

/**
 * SpicaAndroidException
 * <p/>
 * Created by jackyang on 3/22/15.
 */
public class SpicaAndroidException extends RuntimeException {

    public SpicaAndroidException() {
    }

    public SpicaAndroidException(String message) {
        super(message);
    }

    public SpicaAndroidException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpicaAndroidException(Throwable cause) {
        super(cause);
    }
}
