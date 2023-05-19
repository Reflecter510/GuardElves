package com.reflecter.GuardElves.hook.ThermalManagerService;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.framework.clazz.Temperature;
import com.reflecter.GuardElves.hook.base.MethodHook;
import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XC_MethodHook;

public class OnTemperatureChangedCallbackHook extends MethodHook {
    private static final String TAG = "OnTemperatureChangedCallbackHook";

    public OnTemperatureChangedCallbackHook(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public String getTargetClass() {
        return ClassConst.ThermalManagerService;
    }

    @Override
    public String getTargetMethod() {
        return MethodConst.onTemperatureChangedCallback;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[] {
                ClassConst.Temperature
        };
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Temperature temperature = new Temperature(param.args[0]);
                Logger.d(TAG, " type=" + temperature.getType());
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
