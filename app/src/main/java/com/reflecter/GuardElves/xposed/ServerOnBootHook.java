package com.reflecter.GuardElves.xposed;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.util.Logger;
import com.reflecter.GuardElves.xposed.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;

public class ServerOnBootHook extends MethodHook {
    private static final String TAG = "ServerOnBootHook";
    private final String mTargetClass;

    public ServerOnBootHook(ClassLoader classLoader, String targetClass) {
        super(classLoader, targetClass);
        mTargetClass = targetClass;
    }

    @Override
    public String getTargetClass() {
        return mTargetClass;
    }

    @Override
    public String getTargetMethod() {
        return MethodConstants.onBootPhase;
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
                if (ClassConstants.serverExts.containsKey(getTargetClass())) {
                    ClassConstants.serverExts.get(getTargetClass()).setServer(param.thisObject);
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
