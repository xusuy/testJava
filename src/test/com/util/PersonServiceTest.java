package com.util;

import com.dao.PersonDao;
import com.domain.Person;
import com.service.PersonService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author xsy
 * @create 2018-12-21 15:40
 * @desc PersonService mock测试
 **/
public class PersonServiceTest {

    private PersonDao mockDao;
    private PersonService personService;

    @Before
    public void setUp() throws Exception {
        //模拟PersonDao对象
        mockDao = mock(PersonDao.class);
        //预期：能在DAO中找到Person并更新成功
        when(mockDao.getPerson(1)).thenReturn(new Person(1, "Person1"));
        when(mockDao.update(isA(Person.class))).thenReturn(true);

        personService = new PersonService(mockDao);
    }

    @Test
    public void testUpdate() throws Exception {
        boolean result = personService.update(1, "new name");
        //更新id为1的Person的名字，预期：能在DAO中找到Person并更新成功
        assertTrue("must true", result);
        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(1)).getPerson(eq(1));
        //验证是否执行过一次update
        verify(mockDao, times(1)).update(isA(Person.class));
    }

    @Test
    public void testUpdateNotFind() throws Exception {
        boolean result = personService.update(2, "new name");
        //更新id为2的Person的名字，预期：不能在DAO中找到Person，更新失败
        assertFalse("must false", result);
        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(1)).getPerson(eq(2));
        //验证是否执行过一次update
        verify(mockDao, never()).update(isA(Person.class));
    }
}
