package com.basic;

import com.frame.entity.Account;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xsy
 * @desc 方式相关
 **/
public class FeflectUtil {
    /**
     * 获取类的所有字段
     *
     * @param clazz
     * @return
     */
    public List<Field> findAllField(Class clazz) {
        final List<Field> res = new LinkedList<>();
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                res.add(field);
            }
        });
        return res;
    }

    @Test
    public void test1() {
        System.out.println(findAllField(Account.class));
    }
}
