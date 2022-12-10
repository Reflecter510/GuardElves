package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;

public class PowerManagerServiceExt extends AbstractSystemService {
    public static final String TAG = "PowerManagerServiceExt";
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


    @Override
    public String getServerName() {
        return TAG;
    }
}
