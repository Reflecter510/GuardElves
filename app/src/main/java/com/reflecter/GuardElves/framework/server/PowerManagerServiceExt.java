package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.framework.server.base.AbstractServer;

public class PowerManagerServiceExt extends AbstractServer {
    private static volatile PowerManagerServiceExt sInstance;

    public static PowerManagerServiceExt getInstance() {
        if (sInstance == null) {
            synchronized (PowerManagerServiceExt.class) {
                if (sInstance == null) {
                    sInstance = new PowerManagerServiceExt();
                }
            }
        }
        return sInstance;
    }

    private PowerManagerServiceExt() { }


}
