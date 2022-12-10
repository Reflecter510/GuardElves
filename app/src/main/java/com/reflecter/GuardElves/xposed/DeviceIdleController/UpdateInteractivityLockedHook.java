package com.reflecter.GuardElves.xposed.DeviceIdleController;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.DeviceIdleControllerExt;
import com.reflecter.GuardElves.xposed.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;

public class UpdateInteractivityLockedHook extends MethodHook {
    public UpdateInteractivityLockedHook(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public String getTargetClass() {
        return ClassConstants.DeviceIdleController;
    }

    @Override
    public String getTargetMethod() {
        return MethodConstants.updateInteractivityLocked;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[0];
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                DeviceIdleControllerExt.getInstance().updateInteractivityLocked();
            }
        };
    }

    @Override
    public int getMinVersion() {
        return 0;
    }

    @Override
    public String successLog() {
        return null;
    }
}
