package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.framework.server.base.AbstractServer;

public class UsageStatsServiceExt extends AbstractServer {

    private static volatile UsageStatsServiceExt sInstance;

    public static UsageStatsServiceExt getInstance() {
        if (sInstance == null) {
            synchronized (UsageStatsServiceExt.class) {
                if (sInstance == null) {
                    sInstance = new UsageStatsServiceExt();
                }
            }
        }
        return sInstance;
    }

    private UsageStatsServiceExt() { }

}
