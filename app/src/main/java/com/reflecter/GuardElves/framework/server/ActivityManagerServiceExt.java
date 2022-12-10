package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.base.AbstractServer;
import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XposedHelpers;

public class ActivityManagerServiceExt extends AbstractServer {
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

    public void killApp(String packageName) {
        if (!isServerBoot()) {
            Logger.e(TAG, "killApp: server not boot");
            return;
        }
        XposedHelpers.callMethod(mServer, MethodConstants.forceStopPackage, packageName, MAIN_USER);
        Logger.d(TAG, "killApp: " + packageName + " was killed");
    }
}
