package com.reflecter.GuardElves.xposed.ActivityManagerService;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.ActivityManagerServiceExt;
import com.reflecter.GuardElves.xposed.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;

public class ActivityManagerServiceHook extends MethodHook {
    public ActivityManagerServiceHook(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public String getTargetClass() {
        return ClassConstants.ActivityManagerService;
    }

    /**
     * todo 看一下这个方法的源码
     * @return
     */
    @Override
    public String getTargetMethod() {
        return MethodConstants.setSystemProcess;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[0];
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                ActivityManagerServiceExt.getInstance().setService(param.thisObject);
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
