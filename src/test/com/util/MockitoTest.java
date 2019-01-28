package com.util;

import com.dao.PersonDao;
import com.domain.Account;
import com.domain.Person;
import com.domain.RailwayTicket;
import com.service.PersonService;
import org.junit.Test;
import org.mockito.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author xsy
 * @create 2018-12-20 18:23
 * @desc Mockito测试，原文;https://blog.csdn.net/xiang__liu/article/details/81147933
 **/
public class MockitoTest {

    @Test
    public void simpleTest() {
        //创建mock对象，参数可以是类，也可以是接口
        List<String> list = mock(List.class);
        //设置方法的预期返回值 （如果list.get(0) 被调用 ，调用之后返回 helloworld）
        //当然前提是要创建了Mock对象，如这里就是创建了跟 List相关的 Mock对象
        //这里还看不出什么作用，因为Mock 还看不出来，List很容易就能创建
        //假如是一个很复杂的对象，构造这样一个对象很有难度，使用Mock就很方便了，我们不用去一步一步填充它的属性去构造，
        //只需要Mock 一下，就可以拿到这个对象，去测试它的方法，（当然，如果方法有参数我们是需要传递的，像get(0)）
        when(list.get(0)).thenReturn("helloworld");
        //list.get(0)的调用就会返回 helloworld
        String result = list.get(0);
        System.out.println(result);
        //验证方法调用(是否调用了get(0))
        Mockito.verify(list).get(0);
        assertEquals("helloworld", result);
    }

    @Test
    public void when_thenReturn() {
        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        //验证结果
        assertEquals("hello world world", result);
    }

    @Test(expected = IOException.class)
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //预设当流关闭时抛出异常
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    /**
     * RETURNS_SMART_NULLS和RETURNS_DEEP_STUBS
     */
//    在创建mock对象时，有的方法我们没有进行stubbing，所以调用时会放回Null这样在进行操作是很可能
//    抛出NullPointerException。如果通过RETURNS_SMART_NULLS参数创建的mock对象在没有调用stubbed方法
//    时会返回SmartNull。例如：返回类型是String，会返回"";是int，会返回0；是List，会返回空的List。
//    另外，在控制台窗口中可以看到SmartNull的友好提示。
    @Test
    public void returnsSmartNullsTest() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));

        //使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常。另外控制台窗口会提示信息“SmartNull returned by unstubbed get() method on mock”
        System.out.println(mock.toArray().length);
        System.out.println(mock);
    }


    //    RETURNS_DEEP_STUBS也是创建mock对象时的备选参数
//    RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象，方法deepstubsTest和deepstubsTest2是等价的
    @Test
    public void deepstubsTest() {
        Account account = mock(Account.class, RETURNS_DEEP_STUBS);
        when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }

    @Test
    public void deepstubsTest2() {
        Account account = mock(Account.class);
        RailwayTicket railwayTicket = mock(RailwayTicket.class);
        when(account.getRailwayTicket()).thenReturn(railwayTicket);
        when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing", account.getRailwayTicket().getDestination());
    }


    // 模拟方法体抛出异常
    @Test(expected = RuntimeException.class)
    public void doThrow_when() {
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    //使用注解来快速模拟
    @Mock
    private List mockList;

    //需要初始化mock的代码
    public MockitoTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }


    // 参数匹配
    @Test
    public void with_arguments() {
        Comparable comparable = mock(Comparable.class);
        //预设根据不同的参数返回不同的结果
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));
        //对于没有预设的情况会返回默认值
        assertEquals(0, comparable.compareTo("Not stub"));
    }

    //除了匹配制定参数外，还可以匹配自己想要的任意参数
    @Test
    public void with_unspecified_arguments() {
        List list = mock(List.class);
        //匹配任意参数
        when(list.get(anyInt())).thenReturn(1);
        when(list.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(999));
        assertTrue(list.contains(1));
        assertTrue(!list.contains(3));
        assertTrue(list.contains(3));
    }

    private class IsValid implements ArgumentMatcher<Object> {
        @Override
        public boolean matches(Object o) {
            return Integer.valueOf(o.toString()) == 1 || Integer.valueOf(o.toString()) == 2;
        }
    }

    //注意：如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配，如下代码：
    @Test
    public void all_arguments_provided_by_matchers() {
        Comparator comparator = mock(Comparator.class);
        comparator.compare("nihao", "hello");
        //如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配
        verify(comparator).compare(anyString(), eq("hello"));
        //下面的为无效的参数匹配使用
//        verify(comparator).compare(anyString(),"hello");
    }

    @Test
    public void capturing_args() {
        PersonDao personDao = mock(PersonDao.class);
        PersonService personService = new PersonService(personDao);

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        personService.update(1, "jack");
        //验证后再捕捉参数
        verify(personDao).update(argument.capture());
        //验证参数
        assertEquals(1, argument.getValue().getId());
        assertEquals("jack", argument.getValue().getName());
    }
}
