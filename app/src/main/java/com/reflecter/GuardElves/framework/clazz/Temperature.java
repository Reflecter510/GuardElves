package com.reflecter.GuardElves.framework.clazz;

import com.reflecter.GuardElves.constants.ClassConst;
import com.reflecter.GuardElves.constants.FieldConst;

public class Temperature extends AbstractReflectClass {

    public Temperature(Object instance) {
        super(instance);
    }

    @Override
    public String getClassPath() {
        return ClassConst.Temperature;
    }

    public int getType() {
        return getIntField(FieldConst.mType);
    }
}
