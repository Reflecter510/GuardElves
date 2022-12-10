package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.framework.server.base.AbstractServer;

public class AlarmManagerServiceExt extends AbstractServer {
    public static final String TAG = "AlarmManagerServiceExt";
    private static volatile AlarmManagerServiceExt sInstance;

    public static AlarmManagerServiceExt getInstance() {
        if (sInstance == null) {
            synchronized (AlarmManagerServiceExt.class) {
                if (sInstance == null) {
                    sInstance = new AlarmManagerServiceExt();
                }
            }
        }
        return sInstance;
    }

    private AlarmManagerServiceExt() { }

    @Override
    public String getServerName() {
        return TAG;
    }
}
