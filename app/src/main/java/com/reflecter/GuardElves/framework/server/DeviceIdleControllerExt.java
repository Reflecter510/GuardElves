package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.FieldConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;

import de.robv.android.xposed.XposedHelpers;

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
        return ClassConstants.DeviceIdleController;
    }

    @Override
    public String getServiceName() {
        return TAG;
    }

    public void stepIntoDeepIdle() {
        doWithBootCheck(o -> {
            int STATE_LOCATING = getStaticIntField(FieldConstants.STATE_LOCATING);
            setIntField(FieldConstants.mState, STATE_LOCATING);
            synchronized (mService.getClass()) {
                callMethod(MethodConstants.stepIdleStateLocked, TAG);
            }
        });
    }

    public void updateInteractivityLocked() {
        mScreenOn = getBooleanField(FieldConstants.mScreenOn);
        // todo 试验功能：灭屏直接进入DeepDoze
        if (!mScreenOn) {
            stepIntoDeepIdle();
        }
    }
}
