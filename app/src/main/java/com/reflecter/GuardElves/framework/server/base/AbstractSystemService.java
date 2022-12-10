package com.reflecter.GuardElves.framework.server.base;

import android.os.Build;

import com.reflecter.GuardElves.util.Logger;

import java.util.function.Consumer;

public abstract class AbstractSystemService implements ISystemServiceExt {
    protected Object mService;

    public abstract String getServerName();

    @Override
    public void setService(Object service) {
        mService = service;
    }

    public boolean isServerBoot() {
        return mService != null;
    }

    public void callMethodWithBootCheck(Consumer<Object> consumer) {
        if (!isServerBoot()) {
            Logger.e(getServerName(), "callMethodWithBootCheck: server not boot");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            consumer.accept(null);
        } else {
            Logger.e(getServerName(), "callMethodWithBootCheck: SDK < N");
        }
    }
}
