package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.framework.clazz.Temperature;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;

public class ThermalManagerServiceExt extends AbstractSystemService {
    public static final String TAG = "ThermalManagerSerivceExt";

    private static volatile ThermalManagerServiceExt sInstance;

    public static ThermalManagerServiceExt getInstance() {
        if (sInstance == null) {
            synchronized (ThermalManagerServiceExt.class) {
                if (sInstance == null) {
                    sInstance = new ThermalManagerServiceExt();
                }
            }
        }
        return sInstance;
    }

    private ThermalManagerServiceExt() { }

    @Override
    public String getClassPath() {
        return ClassConst.ThermalManagerService;
    }

    @Override
    public String getServiceName() {
        return TAG;
    }

    public void notifyTemperature(Temperature temperature) {
        doWithBootCheck(o -> {
            synchronized (mService.getClass()) {
                call(MethodConst.onTemperatureChangedCallback, temperature.getObject());
            }
        });
    }
}
