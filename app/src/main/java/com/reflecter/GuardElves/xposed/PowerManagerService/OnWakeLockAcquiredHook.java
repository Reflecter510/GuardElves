package com.reflecter.GuardElves.xposed.PowerManagerService;

import android.os.IBinder;
import android.os.PowerManager;
import android.os.WorkSource;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.PowerManagerServiceExt;
import com.reflecter.GuardElves.xposed.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class OnWakeLockAcquiredHook extends MethodHook {
    public OnWakeLockAcquiredHook(ClassLoader classLoader) {
        super(classLoader);
    }

    public OnWakeLockAcquiredHook(ClassLoader classLoader, String targetClass) {
        super(classLoader, targetClass);
    }

    @Override
    public String getTargetClass() {
        return ClassConstants.PowerManagerService;
    }

    @Override
    public String getTargetMethod() {
        return MethodConstants.notifyWakeLockAcquiredLocked;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[] {
                ClassConstants.WakeLock
        };
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                PowerManagerServiceExt.getInstance().onWakeLockAcquired(param.args[0]);
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
