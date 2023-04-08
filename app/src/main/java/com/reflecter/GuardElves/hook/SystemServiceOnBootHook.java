package com.reflecter.GuardElves.hook;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.util.Logger;
import com.reflecter.GuardElves.hook.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;

public class SystemServiceOnBootHook extends MethodHook {
    private static final String TAG = "SystemServiceOnBootHook";
    private final String mTargetClass;

    public SystemServiceOnBootHook(ClassLoader classLoader, String targetClass) {
        super(classLoader, targetClass);
        mTargetClass = targetClass;
    }

    @Override
    public String getTargetClass() {
        return mTargetClass;
    }

    @Override
    public String getTargetMethod() {
        return MethodConst.onBootPhase;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[]{int.class};
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (ClassConst.serverExts.containsKey(getTargetClass())) {
                    ClassConst.serverExts.get(getTargetClass()).setService(param.thisObject);
                    Logger.d(TAG, "Hook onBootPhase: " + param.thisObject);
                }
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
