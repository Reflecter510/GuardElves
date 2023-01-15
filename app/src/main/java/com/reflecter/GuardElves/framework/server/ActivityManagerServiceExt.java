package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;
import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XposedHelpers;

public class ActivityManagerServiceExt extends AbstractSystemService {
    public static final String TAG = "ActivityManagerServiceExt";
    public final static int MAIN_USER = 0;

    private static volatile ActivityManagerServiceExt sInstance;
    private int mResumedUid;
    private String mResumedPackage;
    private int mLastResumedUid;
    private String mLastResumedPackage;

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
    public String getClassPath() {
        return ClassConstants.ActivityManagerService;
    }

    @Override
    public String getServiceName() {
        return TAG;
    }

    public void forceStopPackage(String packageName, int userId) {
        callMethod(MethodConstants.forceStopPackage, packageName, userId);
        Logger.d(TAG, "forceStopPackage: " + packageName);
    }

    public void notifyActivityResumed(int uid, String packageName) {
        // 如果同一个uid，但是pkg从非null切到null？
        mLastResumedUid = mResumedUid;
        mLastResumedPackage = mResumedPackage;
        mResumedUid = uid;
        mResumedPackage = packageName;
        if (uid != mLastResumedUid || !packageName.equals(mLastResumedPackage)) {
            Logger.d(TAG, "front pkg: " + packageName + " prev pkg:" + mLastResumedPackage);
            // notify app switch
        }
    }
}
