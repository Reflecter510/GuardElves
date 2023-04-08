package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.FieldConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;

public class DeviceIdleControllerExt extends AbstractSystemService {
    public static final String TAG = "DeviceIdleControllerExt";
    private static volatile DeviceIdleControllerExt sInstance;
    private boolean mScreenOn = false;

    public static DeviceIdleControllerExt getInstance() {
        if (sInstance == null) {
            synchronized (DeviceIdleControllerExt.class) {
                if (sInstance == null) {
                    sInstance = new DeviceIdleControllerExt();
                }
            }
        }
        return sInstance;
    }

    private DeviceIdleControllerExt() { }

    @Override
    public String getClassPath() {
        return ClassConst.DeviceIdleController;
    }

    @Override
    public String getServiceName() {
        return TAG;
    }

    public void stepIntoDeepIdle() {
        doWithBootCheck(o -> {
            int STATE_LOCATING = getStaticIntField(FieldConst.STATE_LOCATING);
            setIntField(FieldConst.mState, STATE_LOCATING);
            synchronized (mService.getClass()) {
                call(MethodConst.stepIdleStateLocked, TAG);
            }
        });
    }

    public void updateInteractivityLocked() {
        mScreenOn = getBooleanField(FieldConst.mScreenOn);
        // todo 试验功能：灭屏直接进入DeepDoze
        if (!mScreenOn) {
            stepIntoDeepIdle();
        }
    }
}
