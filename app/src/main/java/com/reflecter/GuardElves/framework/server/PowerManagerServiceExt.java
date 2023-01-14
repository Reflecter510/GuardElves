package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;
import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XposedHelpers;

public class PowerManagerServiceExt extends AbstractSystemService {
    public static final String TAG = "PowerManagerServiceExt";
    private static volatile PowerManagerServiceExt sInstance;

    public static PowerManagerServiceExt getInstance() {
        if (sInstance == null) {
            synchronized (PowerManagerServiceExt.class) {
                if (sInstance == null) {
                    sInstance = new PowerManagerServiceExt();
                }
            }
        }
        return sInstance;
    }

    private PowerManagerServiceExt() { }


    @Override
    public String getServiceName() {
        return TAG;
    }

    /**
     * 监听WakeLock的获取，并打印日志
     * 时机：获取完成之后
     * @param wakeLock
     */
    public void onWakeLockAcquired(Object wakeLock) {
        Logger.d(TAG, "" + wakeLock + " " + XposedHelpers.getObjectField(wakeLock, "mTag"));
    }
}
