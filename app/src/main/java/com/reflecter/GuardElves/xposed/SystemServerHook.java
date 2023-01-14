package com.reflecter.GuardElves.xposed;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.xposed.ActivityManagerService.ActivityManagerServiceHook;
import com.reflecter.GuardElves.xposed.ActivityManagerService.ActivitySwitchHook;
import com.reflecter.GuardElves.xposed.DeviceIdleController.DumpHook;
import com.reflecter.GuardElves.xposed.DeviceIdleController.UpdateInteractivityLockedHook;
import com.reflecter.GuardElves.xposed.PowerManagerService.OnWakeLockAcquiredHook;
import com.reflecter.GuardElves.xposed.base.AbstractAppHook;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SystemServerHook extends AbstractAppHook {
    public static final String PACKAGE_NAME = "android";
    public static final String APP_NAME = "Android";

    /**
     * 执行Hook.
     *
     * @param packageParam 载入应用参数
     */
    public SystemServerHook(XC_LoadPackage.LoadPackageParam packageParam) {
        super(packageParam);
    }

    @Override
    public String getTargetPackageName() {
        return PACKAGE_NAME;
    }

    @Override
    public String getTargetAppName() {
        return APP_NAME;
    }

    @Override
    public void hook() {
        for (String serverName : ClassConstants.serverExts.keySet()) {
            new SystemServiceOnBootHook(mClassLoader, serverName);
        }
        // ActivityManagerService
        new ActivityManagerServiceHook(mClassLoader);
        new ActivitySwitchHook(mClassLoader);

        // DeviceIdleController
        new UpdateInteractivityLockedHook(mClassLoader);
        new DumpHook(mClassLoader);

        // PowerManagerService
        new OnWakeLockAcquiredHook(mClassLoader);
    }
}
