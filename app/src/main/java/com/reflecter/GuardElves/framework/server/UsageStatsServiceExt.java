package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.framework.server.base.AbstractServer;

public class UsageStatsServiceExt extends AbstractServer {
    public static final String TAG = "UsageStatsServiceExt";

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

    @Override
    public String getServerName() {
        return TAG;
    }
}
