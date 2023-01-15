package com.reflecter.GuardElves.framework.server;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.framework.server.base.AbstractSystemService;

public class AlarmManagerServiceExt extends AbstractSystemService {
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
    public String getClassPath() {
        return ClassConstants.AlarmManagerService;
    }

    @Override
    public String getServiceName() {
        return TAG;
    }
}
