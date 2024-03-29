package com.reflecter.GuardElves.framework.clazz;

import androidx.annotation.NonNull;

import com.reflecter.GuardElves.util.Logger;

import de.robv.android.xposed.XposedHelpers;

public abstract class AbstractReflectClass {
    private static final String TAG = "AbstractReflectClass";
    private Object mObject;

    public AbstractReflectClass() { }

    public AbstractReflectClass(Object instance) {
        mObject = instance;
    }

    public void setObject(Object o) {
        mObject = o;
    }

    public Object getObject() {
        return mObject;
    }

    public abstract String getClassPath();

    public boolean isNull() {
        return mObject == null;
    }

    public <T> T getObjectField(String fieldName) {
        if (mObject == null) {
            return null;
        }
        Object object = XposedHelpers.getObjectField(mObject, fieldName);
        return (T)object;
    }

    public int getIntField(String fieldName) {
        if (mObject == null) {
            return Integer.MIN_VALUE;
        }
        return XposedHelpers.getIntField(mObject, fieldName);
    }

    public void setIntField(String fieldName, int value) {
        if (mObject == null) {
            return ;
        }
        XposedHelpers.setIntField(mObject, fieldName, value);
    }

    public int getStaticIntField(String fieldName) {
        if (mObject == null) {
            return Integer.MIN_VALUE;
        }
        return XposedHelpers.getStaticIntField(mObject.getClass(), fieldName);
    }

    public boolean getBooleanField(String fieldName) {
        if (mObject == null) {
            return false;
        }
        return XposedHelpers.getBooleanField(mObject, fieldName);
    }

    public boolean getStaticBooleanField(String fieldName) {
        if (mObject == null) {
            return false;
        }
        return XposedHelpers.getStaticBooleanField(mObject.getClass(), fieldName);
    }

    public void setStaticBooleanField(String fieldName, boolean value) {
        if (mObject == null) {
            return ;
        }
        XposedHelpers.setStaticBooleanField(mObject.getClass(), fieldName, value);
    }

    public Object call(String methodName, Object... args) {
        if (mObject == null) {
            Logger.e(TAG, getClassPath() + "->" + methodName + ": object is null");
            return null;
        }
        return XposedHelpers.callMethod(mObject, methodName, args);
    }

    public void newInstance(ClassLoader classLoader, Object... args) {
        try {
            Object object = XposedHelpers.newInstance(XposedHelpers.findClass(getClassPath(), classLoader), args);
            setObject(object);
        } catch (Exception e) {
            Logger.e(TAG, "newInstance e=" + e);
        }
    }

    @NonNull
    @Override
    public String toString() {
        if (mObject != null) {
            return mObject.toString();
        }
        return super.toString();
    }
}
