package com.reflecter.GuardElves.framework.clazz;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.FieldConst;

public class ProcessStateRecord extends AbstractReflectClass{
    public ProcessStateRecord(Object instance) {
        super(instance);
    }

    @Override
    public String getClassPath() {
        return ClassConst.ProcessStateRecord;
    }

    public int getCurProcState() {
        return getIntField(FieldConst.mCurProcState);
    }
}
