package com.reflecter.GuardElves.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class PackageHook implements IXposedHookLoadPackage {
    private final static String TAG = "PackageHook";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if (loadPackageParam.packageName == null) {
            return;
        }
        switch (loadPackageParam.packageName) {
            case SystemServerHook.PACKAGE_NAME:
                new SystemServerHook(loadPackageParam);
                break;
            default:
                break;
        }
    }
}
