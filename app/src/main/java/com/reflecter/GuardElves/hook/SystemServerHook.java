package com.reflecter.GuardElves.hook;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.hook.ActivityManagerService.ActivityManagerServiceHook;
import com.reflecter.GuardElves.hook.ActivityManagerService.ActivitySwitchHook;
import com.reflecter.GuardElves.hook.DeviceIdleController.DumpHook;
import com.reflecter.GuardElves.hook.DeviceIdleController.UpdateInteractivityLockedHook;
import com.reflecter.GuardElves.hook.PowerManagerService.*;
import com.reflecter.GuardElves.hook.ThermalManagerService.OnTemperatureChangedCallbackHook;
import com.reflecter.GuardElves.hook.base.AbstractAppHook;

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
        for (String serverName : ClassConst.serverExts.keySet()) {
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
        new OnWakeLockReleasedHook(mClassLoader);

        // ThermalManagerService
        new OnTemperatureChangedCallbackHook(mClassLoader);
    }
}
