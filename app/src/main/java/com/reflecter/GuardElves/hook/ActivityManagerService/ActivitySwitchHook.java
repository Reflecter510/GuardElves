package com.reflecter.GuardElves.hook.ActivityManagerService;

import static android.app.usage.UsageEvents.Event.ACTIVITY_RESUMED;

import android.content.pm.ActivityInfo;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.FieldConst;
import com.reflecter.GuardElves.constants.MethodConst;
import com.reflecter.GuardElves.framework.server.ActivityManagerServiceExt;
import com.reflecter.GuardElves.hook.base.MethodHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class ActivitySwitchHook extends MethodHook {
    public static final String TAG = "ActivitySwitchHook";

    public ActivitySwitchHook(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public String getTargetClass() {
        return ClassConst.ActivityTaskManagerService;
    }

    /**
     * TaskFragment#resumeTopActivity -> ActivityRecord#setState -> ATMS::AMS#updateActivityUsageStats
     * @return
     */
    @Override
    public String getTargetMethod() {
        return MethodConst.updateActivityUsageStats;
    }

    @Override
    public Object[] getTargetParam() {
        return new Object[] { ClassConst.ActivityRecord, int.class };
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
                int event = (int) args[1];
                if (event != ACTIVITY_RESUMED) {
                    return;
                }
                ActivityInfo activityInfo  = ((ActivityInfo)XposedHelpers.getObjectField(args[0], FieldConst.info));
                // 本次resume事件的包名和uid
                String packageName = activityInfo.packageName;
                int uid = activityInfo.applicationInfo.uid;

                ActivityManagerServiceExt.getInstance().notifyActivityResumed(uid, packageName);
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
