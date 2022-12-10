package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.FieldConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;

import de.robv.android.xposed.XposedHelpers;

public class DeviceIdleControllerExt extends AbstractSystemService {
    public static final String TAG = "DeviceIdleControllerExt";
    private static volatile DeviceIdleControllerExt sInstance;

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
    public String getServiceName() {
        return TAG;
    }

    public void stepIntoDeepIdle() {
        callMethodWithBootCheck(o -> {
            int STATE_LOCATING = XposedHelpers.getStaticIntField(mService.getClass(), "STATE_LOCATING");
            XposedHelpers.setIntField(mService, FieldConstants.mState, STATE_LOCATING);
            synchronized (mService.getClass()) {
                XposedHelpers.callMethod(mService, MethodConstants.stepIdleStateLocked, TAG);
            }
        });
    }
}
