package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;
import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XposedHelpers;

public class ActivityManagerServiceExt extends AbstractSystemService {
    public static final String TAG = "ActivityManagerServiceExt";
    public final static int MAIN_USER = 0;

    private static volatile ActivityManagerServiceExt sInstance;

    public static ActivityManagerServiceExt getInstance() {
        if (sInstance == null) {
            synchronized (ActivityManagerServiceExt.class) {
                if (sInstance == null) {
                    sInstance = new ActivityManagerServiceExt();
                }
            }
        }
        return sInstance;
    }

    private ActivityManagerServiceExt() { }

    @Override
    public String getServiceName() {
        return TAG;
    }

    public void forceStopPackage(String packageName, int userId) {
        callMethodWithBootCheck(o -> {
            XposedHelpers.callMethod(mService, MethodConstants.forceStopPackage, packageName, userId);
            Logger.d(TAG, "forceStopPackage: " + packageName + " success");
        });
    }
}
