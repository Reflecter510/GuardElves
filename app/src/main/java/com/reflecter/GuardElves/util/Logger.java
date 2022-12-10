package com.reflecter.GuardElves.util;

import android.util.Log;

public class Logger {
    private final static String TAG = "GuardElves";
    private final static boolean sDebug = true;

    public static void i(String tag, String msg) {
        if (sDebug) Log.i(TAG, tag + ": " + msg);
    }

    public static void v(String tag, String msg) {
        if (sDebug) Log.v(TAG, tag + ": " + msg);
    }

    public static void w(String tag, String msg) {
        if (sDebug) Log.w(TAG, tag + ": " + msg);
    }

    public static void d(String tag, String msg) {
        if (sDebug) Log.d(TAG, tag + ": " + msg);
    }

    public static void e(String tag, String msg) {
        if (sDebug) Log.e(TAG, tag + ": " + msg);
    }

    /**
     * 打印调用堆栈.
     */
    public static void printStackTrace(Throwable throwable) {
        e("stack:", "---------------> ");
        e("stack:", throwable.getMessage());
        StackTraceElement[] stackElements = throwable.getStackTrace();
        for (StackTraceElement element : stackElements) {
            e("stack:", "at " + element.getClassName() + "." + element.getMethodName() +
                    "(" + element.getFileName() + ":" + element.getLineNumber() + ")");
        }
        e("stack:", " <---------------");
    }
}
