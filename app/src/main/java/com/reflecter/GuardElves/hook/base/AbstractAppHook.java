package com.reflecter.GuardElves.hook.base;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * APP抽象Hook
 */
public abstract class AbstractAppHook {
    /**
     * 载入应用参数.
     */
    public final XC_LoadPackage.LoadPackageParam mPackageParam;

    /**
     * 类加载器
     */
    public ClassLoader mClassLoader;

    /**
     * 执行Hook.
     *
     * @param packageParam 载入应用参数
     */
    public AbstractAppHook(XC_LoadPackage.LoadPackageParam packageParam) {
        String targetPackageName = getTargetPackageName();
        if (targetPackageName == null) {
            log("Target packageName is null");
        }
        this.mPackageParam = packageParam;
        this.mClassLoader = packageParam.classLoader;
        String packageName = packageParam.packageName;
        if (packageName.equals(targetPackageName)) {
            // 尝试Hook可以防止软重启
            try {
                hook();
            } catch (Throwable throwable) {
                log("Hook failed: " + throwable.getMessage());
            }
        }
    }

    /**
     * @return 目标包名
     */
    public abstract String getTargetPackageName();

    /**
     * @return 目标应用名称
     */
    public abstract String getTargetAppName();

    /**
     * 真正的Hook实现
     */
    public abstract void hook();

    /**
     * 打印日志.
     *
     * @param msg 消息
     */
    public void log(String msg) {
        String targetAppName = getTargetAppName();
        if (targetAppName == null) {
            targetAppName = "null";
        }
        XposedBridge.log("NoActive(" + targetAppName + ") -> " + msg);
    }
}
