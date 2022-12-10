package com.reflecter.GuardElves.xposed.DeviceIdleController;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.framework.server.ActivityManagerServiceExt;
import com.reflecter.GuardElves.xposed.base.MethodHook;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import de.robv.android.xposed.XC_MethodHook;

public class DumpHook extends MethodHook {

    public DumpHook(ClassLoader classLoader) {
        super(classLoader);
    }

    public DumpHook(ClassLoader classLoader, String targetClass) {
        super(classLoader, targetClass);
    }

    @Override
    public String getTargetClass() {
        return ClassConstants.DeviceIdleController;
    }

    @Override
    public String getTargetMethod() {
        return MethodConstants.dump;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[] {
                FileDescriptor.class, PrintWriter.class, String[].class
        };
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                String[] args = (String[]) param.args[2];
                if (args != null && args.length >= 2) {
                    if (args[0].equals("killApp")) {
                        String packageName = args[1];
                        PrintWriter pw = (PrintWriter) param.args[1];
                        ActivityManagerServiceExt.getInstance().killApp(packageName);
                        pw.println("killApp done");
                    }
                }
            }
        };
    }

    @Override
    public int getMinVersion() {
        return 0;
    }

    @Override
    public String successLog() {
        return null;
    }
}
