package com.reflecter.GuardElves.hook.DeviceIdleController;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.framework.clazz.Temperature;
import com.reflecter.GuardElves.framework.server.ActivityManagerServiceExt;
import com.reflecter.GuardElves.framework.server.DeviceIdleControllerExt;
import com.reflecter.GuardElves.framework.server.ThermalManagerServiceExt;
import com.reflecter.GuardElves.hook.base.MethodHook;

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
        return ClassConst.DeviceIdleController;
    }

    @Override
    public String getTargetMethod() {
        return MethodConst.dump;
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
                PrintWriter pw = (PrintWriter) param.args[1];
                String[] args = (String[]) param.args[2];
                if (args != null && args.length == 1) {
                    switch (args[0]) {
                        case "deepIdle":
                            DeviceIdleControllerExt.getInstance().stepIntoDeepIdle();
                            pw.println("deepIdle done");
                            break;
                        case "thermalShutdown":
                            Temperature temperature = new Temperature(null);
                            temperature.newInstance(classLoader, 31.26510F, (int)3, "skin", (int)6);
                            ThermalManagerServiceExt.getInstance().notifyTemperature(temperature);
                            break;
                        default:
                            break;
                    }
                } else if (args != null && args.length >= 2) {
                    switch (args[0]) {
                        case "forceStopApp":
                            String packageName = args[1];
                            ActivityManagerServiceExt.getInstance().forceStopPackage(packageName, 0);
                            pw.println("forceStopApp done");
                            break;
                        case "getProcessState":
                            String processName = args[1];
                            String uid = args[2];
                            int state = ActivityManagerServiceExt.getInstance().getProcessState(processName, Integer.parseInt(uid));
                            pw.println("getProcessState: process:" + processName + " uid=" + uid + " state=" +  state);

                        case "thermalStatus":
                            int status = Integer.parseInt(args[1]);
                            Temperature temperature = new Temperature(null);
                            temperature.newInstance(classLoader, 31.26510F, (int)3, "skin", (int)status);
                            ThermalManagerServiceExt.getInstance().notifyTemperature(temperature);
                            break;
                        default:
                            break;
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
