package com.reflecter.GuardElves.framework.server.base;

import android.os.Build;

import com.reflecter.GuardElves.util.Logger;

import java.util.function.Consumer;

public abstract class AbstractSystemService implements ISystemServiceExt {
    protected Object mService;

    public abstract String getServiceName();

    @Override
    public void setService(Object service) {
        mService = service;
    }

    public boolean isServiceBoot() {
        return mService != null;
    }

    public void callMethodWithBootCheck(Consumer<Object> consumer) {
        if (!isServiceBoot()) {
            Logger.e(getServiceName(), "callMethodWithBootCheck: service not boot");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            consumer.accept(null);
        } else {
            Logger.e(getServiceName(), "callMethodWithBootCheck: SDK < N");
        }
    }
}
