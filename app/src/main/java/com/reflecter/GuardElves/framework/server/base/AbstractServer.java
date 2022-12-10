package com.reflecter.GuardElves.framework.server.base;

import android.os.Build;

import com.reflecter.GuardElves.util.Logger;

import java.util.function.Consumer;

public abstract class AbstractServer implements IServerExt {
    protected Object mServer;

    public abstract String getServerName();

    @Override
    public void setServer(Object server) {
        mServer = server;
    }

    public boolean isServerBoot() {
        return mServer != null;
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
