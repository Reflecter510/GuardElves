package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.framework.server.base.AbstractServer;

public class DeviceIdleControllerExt extends AbstractServer {
    private static volatile DeviceIdleControllerExt sInstance;

    public static DeviceIdleControllerExt getInstance() {
        if (sInstance == null) {
            synchronized (DeviceIdleControllerExt.class) {
                if (sInstance == null) {
                    sInstance = new DeviceIdleControllerExt();
                }
            }
        }
        return sInstance;
    }

    private DeviceIdleControllerExt() { }
}
