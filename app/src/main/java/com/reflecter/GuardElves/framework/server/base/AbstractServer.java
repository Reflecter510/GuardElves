package com.reflecter.GuardElves.framework.server.base;

public class AbstractServer implements IServerExt {
    protected Object mServer;

    @Override
    public void setServer(Object server) {
        mServer = server;
    }

    public boolean isServerBoot() {
        return mServer != null;
    }
}
