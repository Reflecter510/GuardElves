package com.reflecter.GuardElves.xposed.ActivityManagerService;

import static android.app.usage.UsageEvents.Event.ACTIVITY_PAUSED;
import static android.app.usage.UsageEvents.Event.ACTIVITY_RESUMED;

import android.content.ComponentName;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.MethodConstants;
import com.reflecter.GuardElves.util.Logger;
import com.reflecter.GuardElves.xposed.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;

public class ActivitySwitchHook extends MethodHook {
    public static final String TAG = "ActivitySwitchHook";

    public ActivitySwitchHook(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public String getTargetClass() {
        return ClassConstants.ActivityManagerService;
    }

    @Override
    public String getTargetMethod() {
        return MethodConstants.updateActivityUsageStats;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[]{ClassConstants.ComponentName, int.class, int.class,
                ClassConstants.IBinder, ClassConstants.ComponentName};
    }

    @Override
    public XC_MethodHook getTargetHook() {
        return new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                // 获取方法参数
                Object[] args = param.args;

                // 获取切换事件
                int event = (int) args[2];
                if (event != ACTIVITY_PAUSED && event != ACTIVITY_RESUMED) {
                    return;
                }

                // 本次事件用户
                int userId = (int) args[1];
                // 本次事件包名
                String packageName = ((ComponentName) args[0]).getPackageName();
                if (packageName == null) {
                    return;
                }

                Logger.d(TAG, "front pkg: " + packageName + " event:" + event);
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
