package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;

public class UsageStatsServiceExt extends AbstractSystemService {
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
    public String getClassPath() {
        return ClassConstants.UsageStatsService;
    }

    @Override
    public String getServiceName() {
        return TAG;
    }
}
