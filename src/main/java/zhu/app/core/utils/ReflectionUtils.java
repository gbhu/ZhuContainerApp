package zhu.app.core.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lenovo
 * date 2017/5/13.
 */
public class ReflectionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);

    private ReflectionUtils() {
    }

    public static List<Class<?>> getAssignedClass(Class<?> cls, List<Class<?>> clses) {
        ArrayList classes = new ArrayList();
        Iterator var3 = clses.iterator();

        while(var3.hasNext()) {
            Class c = (Class)var3.next();
            if(cls.isAssignableFrom(c) && !cls.equals(c)) {
                classes.add(c);
            }
        }

        return classes;
    }

    public static List<Class<?>> getClasses(Class<?> cls) throws ClassNotFoundException {
        String pk = cls.getPackage().getName();
        String path = pk.replace('.', '/');
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);
        return getClasses(new File(url.getFile()), pk, (String[])null);
    }

    public static List<Class<?>> getClasses(File dir, String pk, String[] outsides) throws ClassNotFoundException {
        LOGGER.debug("  Dir: {}, PK: {}", new Object[]{dir, pk});
        ArrayList classes = new ArrayList();
        if(!dir.exists()) {
            return classes;
        } else {
            String thisPk = StringUtils.isBlank(pk)?"":pk + ".";
            File[] var5 = dir.listFiles();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                File f = var5[var7];
                if(f.isDirectory()) {
                    classes.addAll(getClasses(f, thisPk + f.getName(), outsides));
                }

                String name = f.getName();
                if(name.endsWith(".class")) {
                    Class clazz = null;
                    String clazzName = thisPk + name.substring(0, name.length() - 6);
                    LOGGER.debug("Class: {}", clazzName);
                    if(outsides == null || outsides.length == 0 || !ArrayUtils.contains(outsides, clazzName)) {
                        try {
                            clazz = Class.forName(clazzName);
                        } catch (Throwable var13) {
                            LOGGER.error("实例化失败", var13);
                        }

                        if(clazz != null) {
                            classes.add(clazz);
                        }
                    }
                }
            }

            return classes;
        }
    }

    public static <T> T cloneInstance(T instance) {
        Class cls = instance.getClass();
        Object newIns = BeanUtils.instantiateClass(cls);
        BeanUtils.copyProperties(instance, newIns);
        return (T) newIns;
    }

    public static String getSimpleSurname(Class<?> clazz) {
        return clazz == null?null:StringUtils.substringBefore(clazz.getSimpleName(), "_$$_");
    }

    public static String getSurname(Class<?> clazz) {
        return clazz == null?null: StringUtils.substringBefore(clazz.getName(), "_$$_");
    }

    public static Object getFieldValue(Object object, Field field) {
        makeAccessible(field);
        Object result = null;

        try {
            result = field.get(object);
        } catch (IllegalAccessException var4) {
            LOGGER.error("不可能抛出的异常{}", var4.getMessage());
        }

        return result;
    }

    public static Object getFieldValue(Object object, String fieldName) {
        try {
            Field e = getDeclaredField(object, fieldName);
            if(e == null) {
                throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
            } else {
                return getFieldValue(object, e);
            }
        } catch (Exception var6) {
            String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

            try {
                Method ex = object.getClass().getMethod(methodName, new Class[0]);
                return ex.invoke(object, new Object[0]);
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var5) {
                LOGGER.error("Could not exec method [" + methodName + "] on target [" + object + "]", var5);
                return null;
            }
        }
    }

    public static <T> Class<T> getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if(!(genType instanceof ParameterizedType)) {
            LOGGER.warn(clazz.getSimpleName() + "\'s superclass not ParameterizedType");
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if(index < params.length && index >= 0) {
                if(!(params[index] instanceof Class)) {
                    LOGGER.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                    return Object.class;
                } else {
                    return (Class)params[index];
                }
            } else {
                LOGGER.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "\'s Parameterized Type: " + params.length);
                return Object.class;
            }
        }
    }

    public static <T> void setFieldValue(T object, Field field, Object value) {
        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException var4) {
            LOGGER.error("不可能抛出的异常:{}", var4.getMessage());
        }

    }

    public static <T> void setFieldValue(T object, String fieldName, Object value) {
        try {
            Field e = getDeclaredField(object, fieldName);
            if(e == null) {
                throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
            }

            setFieldValue(object, e, value);
        } catch (Exception var7) {
            String methodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

            try {
                Method ex = object.getClass().getMethod(methodName, new Class[]{value.getClass()});
                ex.invoke(object, new Object[]{value});
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var6) {
                LOGGER.error("Could not exec method [" + methodName + "] on target [" + object + "]", var6);
            }
        }

    }

    public static Field getDeclaredField(Object object, String fieldName) {
        Assert.notNull(object, "object不能为空");
        Assert.hasText(fieldName, "fieldName");
        Class superClass = object.getClass();

        while(superClass != Object.class) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException var4) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }

    public static List<Field> getDeclaredFields(Object object) {
        Assert.notNull(object, "object不能为空");
        ArrayList fields = new ArrayList();

        for(Class superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            Field[] f = superClass.getDeclaredFields();
            fields.addAll(Arrays.asList(f));
        }

        return fields;
    }

    protected static void makeAccessible(Field field) {
        if(!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }

    }
}
