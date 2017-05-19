package zhu.app.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo
 * date 2017/5/13.
 */
public class ModelUtils {
    public ModelUtils() {
    }

    public static void copyProperties(Object source, Object target, boolean ignoreNullSource, String... ignoreProperties) {
        copyProperties(source, target, ignoreNullSource, Arrays.asList(ignoreProperties));
    }

    public static void copyProperties(Object source, Object target, boolean ignoreNullSource, List<String> ignoreList) throws BeansException {
        if(source != null && target != null) {
            Class actualEditable = target.getClass();
            PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
            PropertyDescriptor[] var6 = targetPds;
            int var7 = targetPds.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                PropertyDescriptor targetPd = var6[var8];
                Method writeMethod = targetPd.getWriteMethod();
                if(writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                    PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                    if(sourcePd != null) {
                        Method readMethod = sourcePd.getReadMethod();
                        if(readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                            try {
                                if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                    readMethod.setAccessible(true);
                                }

                                Object ex = readMethod.invoke(source, new Object[0]);
                                if(ex != null || !ignoreNullSource) {
                                    if(!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                        writeMethod.setAccessible(true);
                                    }

                                    writeMethod.invoke(target, new Object[]{ex});
                                }
                            } catch (Throwable var14) {
                                throw new FatalBeanException("Could not copy property \'" + targetPd.getName() + "\' from source to target", var14);
                            }
                        }
                    }
                }
            }

        }
    }
}

