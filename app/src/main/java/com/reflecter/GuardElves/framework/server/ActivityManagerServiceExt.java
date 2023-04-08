package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.framework.clazz.ProcessRecord;
import com.reflecter.GuardElves.framework.clazz.ProcessStateRecord;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;
import com.reflecter.GuardElves.util.Logger;

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
        return ClassConst.ActivityManagerService;
    }

    @Override
    public String getServiceName() {
        return TAG;
    }

    public void forceStopPackage(String packageName, int userId) {
        call(MethodConst.forceStopPackage, packageName, userId);
        Logger.d(TAG, "forceStopPackage: " + packageName);
    }

    public int getProcessState(String processName, int uid) {
        ProcessRecord processRecord = new ProcessRecord(call(MethodConst.getProcessRecordLocked, processName, uid));
        if (processRecord.isNull()) {
            Logger.e(TAG, "uid:" + uid + " proc:" + processName + " processRecord no exist");
        }
        ProcessStateRecord processStateRecord = new ProcessStateRecord(processRecord.getState());
        return processStateRecord.getCurProcState();
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
