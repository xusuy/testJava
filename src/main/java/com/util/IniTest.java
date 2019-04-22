package com.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;

/**
 * @author xsy
 * @create 2019-03-08 14:23
 * @desc 测试读取ini
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class IniTest {

    @Resource
    private IniProperties iniProperties;

    @Test
    public void testReadValue() {
        System.out.println("wx.jxhwx.api.key=" + iniProperties.getValue("wxconfig", "wx.jxhwx.api.key"));
        System.out.println("jxh.app.core.url=" + iniProperties.getValue("项目配置", "jxh.app.core.url"));
    }

//    @Test
    public void testReflect() throws Exception{
        //测试反射私有构造方法创建实例
        System.out.println(IniProperties.getIniPropertiesInstance());
        Constructor<IniProperties> declaredConstructor = IniProperties.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        System.out.println(declaredConstructor.newInstance());
    }

}
