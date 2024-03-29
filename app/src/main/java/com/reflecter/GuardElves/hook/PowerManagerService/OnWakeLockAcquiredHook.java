package com.reflecter.GuardElves.hook.PowerManagerService;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.framework.clazz.Wakelock;
import com.reflecter.GuardElves.framework.server.PowerManagerServiceExt;
import com.reflecter.GuardElves.hook.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;

public class OnWakeLockAcquiredHook extends MethodHook {
    public OnWakeLockAcquiredHook(ClassLoader classLoader) {
        super(classLoader);
    }

    public OnWakeLockAcquiredHook(ClassLoader classLoader, String targetClass) {
        super(classLoader, targetClass);
    }

    @Override
    public String getTargetClass() {
        return ClassConst.PowerManagerService;
    }

    @Override
    public String getTargetMethod() {
        return MethodConst.notifyWakeLockAcquiredLocked;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[] {
                ClassConst.WakeLock
        };
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                PowerManagerServiceExt.getInstance().onWakeLockAcquired(new Wakelock(param.args[0]));
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
