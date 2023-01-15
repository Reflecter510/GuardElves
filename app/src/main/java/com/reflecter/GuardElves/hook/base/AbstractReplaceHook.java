package com.reflecter.GuardElves.hook.base;

import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;

public class AbstractReplaceHook extends XC_MethodReplacement {

    protected Object replaceMethod(MethodHookParam param) throws Throwable {
        return null;
    }

    @Override
    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
        try {
            return replaceMethod(param);
        } catch (Throwable throwable) {
            Logger.printStackTrace(throwable);
        }
        return XposedBridge.invokeOriginalMethod(param.method, param.thisObject, param.args);
    }

}
