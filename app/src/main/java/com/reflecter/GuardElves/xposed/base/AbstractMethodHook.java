package com.reflecter.GuardElves.xposed.base;

import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XC_MethodHook;

public abstract class AbstractMethodHook extends XC_MethodHook {

    protected void beforeMethod(MethodHookParam param) throws Throwable {

    }

    protected void afterMethod(MethodHookParam param) throws Throwable {

    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        try {
            beforeMethod(param);
        } catch (Throwable throwable) {
            Logger.printStackTrace(throwable);
        }
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        try {
            afterMethod(param);
        } catch (Throwable throwable) {
            Logger.printStackTrace(throwable);
        }
    }
}
