package com.reflecter.GuardElves.framework.clazz;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.FieldConst;

public class ProcessRecord extends AbstractReflectClass{
    public ProcessRecord(Object instance) {
        super(instance);
    }

    @Override
    public String getClassPath() {
        return ClassConst.ProcessRecord;
    }

    public Object getState() {
        return getObjectField(FieldConst.mState);
    }
}
