package com.reflecter.GuardElves.framework.clazz;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.FieldConst;

public class Wakelock extends AbstractReflectClass{

    public Wakelock(Object instance) {
        super(instance);
    }

    @Override
    public String getClassPath() {
        return ClassConst.WakeLock;
    }

    public String getTag() {
        return getObjectField(FieldConst.mTag);
    }
}
