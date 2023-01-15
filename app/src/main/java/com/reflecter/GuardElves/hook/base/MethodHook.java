package com.reflecter.GuardElves.hook.base;

import android.os.Build;

import com.reflecter.GuardElves.util.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * 方法Hook抽象类.
 */
public abstract class MethodHook {
    /**
     * 任何版本
     */
    public final int ANY_VERSION = -1;
    /**
     * 类加载器
     */
    public ClassLoader classLoader;

    public MethodHook(ClassLoader classLoader) {
        init(classLoader, null);
    }

    public MethodHook(ClassLoader classLoader, String targetClass) {
        init(classLoader, targetClass);
    }

    private void init(ClassLoader classLoader, String targetClass) {
        this.classLoader = classLoader;
        if (isToHook()) {
            try {
                hook(targetClass);
            } catch (Throwable throwable) {
                onError(throwable);
            }
        }
    }

    /**
     * @return 目标类
     */
    public abstract String getTargetClass();

    /**
     * @return 目标方法
     */
    public abstract String getTargetMethod();

    /**
     * @return 目标参数
     */
    public abstract Object[] getTargetParam();

    /**
     * @return Hook方法
     */
    public abstract XC_MethodHook getTargetHook();

    /**
     * @return 最低支持版本
     */
    public abstract int getMinVersion();

    /**
     * @return 成功日志
     */
    public abstract String successLog();

    /**
     * @return 忽略错误
     */
    public boolean isIgnoreError() {
        return false;
    }

    /**
     * Hook包装.
     */
    public void hook(String targetClass) {
        int minVersion = getMinVersion();
        String realTargetClass = targetClass == null ? getTargetClass() : targetClass;
        if (minVersion == ANY_VERSION || Build.VERSION.SDK_INT >= minVersion) {
            ArrayList<Object> param = new ArrayList<>(Arrays.asList(getTargetParam()));
            param.add(getTargetHook());
            String targetMethod = getTargetMethod();
            if (targetMethod == null) {
                XposedHelpers.findAndHookConstructor(realTargetClass, classLoader, param.toArray());
            } else {
                XposedHelpers.findAndHookMethod(realTargetClass, classLoader, getTargetMethod(), param.toArray());
            }
            onSuccess();
        }
    }

    /**
     * @return 是否Hook
     */
    public boolean isToHook() {
        return true;
    }

    /**
     * 调用原方法.
     *
     * @param param 方法参数
     * @return 原方法返回值
     * @throws Throwable 移除
     */
    public Object invokeOriginalMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
        return XposedBridge.invokeOriginalMethod(param.method, param.thisObject, param.args);
    }

    /**
     * 打印成功日志包装.
     */
    public void logSuccess() {
        String log = successLog();
        if (log == null) {
            return;
        }
        Logger.i(getTargetClass() + "." + getTargetMethod(), log);

    }

    /**
     * 成功后执行方法.
     */
    public void onSuccess() {
        logSuccess();
    }

    /**
     * 打印错误.
     *
     * @param throwable 异常
     */
    public void logError(Throwable throwable) {
        if (isIgnoreError()) {
            return;
        }
        Logger.e(getTargetClass() + "." + getTargetMethod(), " failed: " + throwable.getMessage());
    }

    /**
     * 错误后执行方法.
     *
     * @param throwable 异常
     */
    public void onError(Throwable throwable) {
        logError(throwable);
    }

    /**
     * 返回常量Hook.
     *
     * @param result 结果
     * @return 返回常量Hook
     */
    public XC_MethodReplacement constantResult(final Object result) {
        return XC_MethodReplacement.returnConstant(result);
    }

    public boolean runNoThrow(Runnable runnable) {
        try {
            runnable.run();
            return true;
        } catch (Throwable throwable) {
            return false;
        }
    }
}
