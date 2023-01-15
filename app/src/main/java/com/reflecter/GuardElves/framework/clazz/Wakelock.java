package com.reflecter.GuardElves.framework.clazz;

import com.reflecter.GuardElves.constants.ClassConstants;
import com.reflecter.GuardElves.constants.FieldConstants;

public class Wakelock extends AbstractReflectClass{

    public Wakelock(Object instance) {
        super(instance);
    }

    @Override
    public String getClassPath() {
        return ClassConstants.WakeLock;
    }

    public String getTag() {
        return getObjectField(FieldConstants.mTag);
    }
}
