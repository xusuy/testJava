package com.util;

import com.domain.MockTest;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author xsy
 * @create 2018-12-21 14:08
 * @desc 原文：https://blog.csdn.net/shensky711/article/details/52771493
 **/
public class MockitoTest2 {

    //一旦创建，mock会记录所有交互，你可以验证所有你想要验证的东西
    @Test
    public void testVerify() throws Exception {
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("two");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");//验证是否调用过一次 mockedList.add("one")方法，若不是（0次或者大于一次），测试将不通过

        //验证调用过2次 mockedList.add("two")方法，若不是，测试将不通过
        verify(mockedList, times(2)).add("two");

        //从未调用过
        verify(mockedList, never()).add("three");

        //用atLeast()/atMost()验证
        verify(mockedList, atLeastOnce()).add("one");
        //下面这句将不能通过测试
        verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");

        //验证是否调用过一次 mockedList.clear()方法，若没有（0次或者大于一次），测试将不通过
        verify(mockedList).clear();
    }

    //Stubbing,对于stubbing，有以下几点需要注意：
//        对于有返回值的方法，mock会默认返回null、空集合、默认值。比如，为int/Integer返回0，为boolean/Boolean返回false
//        stubbing可以被覆盖，但是请注意覆盖已有的stubbing有可能不是很好
//        一旦stubbing，不管调用多少次，方法都会永远返回stubbing的值
//        当你对同一个方法进行多次stubbing，最后一次stubbing是最重要的

    @Test
    public void testStubbing() throws Exception {
        //你可以mock具体的类，而不仅仅是接口
        LinkedList mockedList = mock(LinkedList.class);

        //设置桩
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //打印 "first"
        System.out.println(mockedList.get(0));

        verify(mockedList).get(0);

        //这里会打印 "null" 因为 get(999) 没有设置
        System.out.println(mockedList.get(999));

        //这里会抛runtime exception
        System.out.println(mockedList.get(1));
    }

    //参数匹配
    @Test
    public void testArgumentMatcher() throws Exception {
        LinkedList mockedList = mock(LinkedList.class);
        //用内置的参数匹配器来stub
        when(mockedList.get(anyInt())).thenReturn("element");

        //打印 "element"
        System.out.println(mockedList.get(999));

        //你也可以用参数匹配器来验证，此处测试通过
        verify(mockedList).get(anyInt());
        //此处测试将不通过，因为没调用get(33)
        verify(mockedList).get(eq(33));


        //如果你使用了参数匹配器，那么所有参数都应该使用参数匹配器
//        verify(mock).someMethod(anyInt(), anyString(), eq("third argument"));
        //上面是正确的，因为eq返回参数匹配器

//        verify(mock).someMethod(anyInt(), anyString(), "third argument");
        //上面将会抛异常，因为第三个参数不是参数匹配器，一旦使用了参数匹配器来验证，那么所有参数都应该使用参数匹配

    }


    //验证调用顺序
    @Test
    public void testVerificationInOrder() throws Exception {
        // 必须按特定顺序调用其方法的单个mock
        List singleMock = mock(List.class);

        //使用单个mock对象
        singleMock.add("was added first");
        singleMock.add("was added second");

        //创建inOrder
        InOrder inOrder = inOrder(singleMock);

        //验证调用次数，若是调换两句，将会出错，因为singleMock.add("was added first")是先调用的
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

//        inOrder.verify(singleMock).add("was added second");
//        inOrder.verify(singleMock).add("was added first");

        // 多个mock对象
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //创建多个mock对象的inOrder
        inOrder = inOrder(firstMock, secondMock);

        //验证firstMock先于secondMock调用
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");
    }


    //验证mock对象没有产生过交互
    @Test
    public void testInteractionNeverHappened() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);

        //测试通过
        verifyZeroInteractions(mockOne, mockTwo);

        mockOne.add("");
        verifyZeroInteractions(mockTwo);
        //测试不通过，因为mockOne已经发生过交互了
        verifyZeroInteractions(mockOne, mockTwo);
    }

    @Test
    public void testStubbingConsecutiveCalls() throws Exception {

        MockTest mock = mock(MockTest.class);
        when(mock.someMethod("some arg")).thenReturn("foo").thenThrow(new RuntimeException(""));

        //第一次调用，返回foo
        System.out.println(mock.someMethod("some arg"));

        //第二次调用抛RuntimeException
        System.out.println(mock.someMethod("some arg"));

        //后续继续调用，抛RuntimeException，以最后一个stub为准
        System.out.println(mock.someMethod("some arg"));

        //下面是一个更简洁的写法
        when(mock.someMethod("some arg")).thenReturn("one", "two", "three");
    }


    //spy监视真正的对象
    @Test
    public void testSpy() throws Exception {
        List list = new LinkedList();
        List spy = spy(list);

        //可选的，你可以stub某些方法
        when(spy.size()).thenReturn(100);

        //调用"真正"的方法
        spy.add("one");
        spy.add("two");

        //打印one
        System.out.println(spy.get(0));

        //size()方法被stub了，打印100
        System.out.println(spy.size());

        //可选，验证spy对象的行为
        verify(spy).add("one");
        verify(spy).add("two");

        //下面写法有问题，spy.get(10)会抛IndexOutOfBoundsException异常
        when(spy.get(10)).thenReturn("foo");
        //可用以下方式
        doReturn("foo").when(spy).get(10);
    }


    @Test
    public void testDefaultValue() throws Exception {
        List listOne = mock(List.class, Mockito.RETURNS_SMART_NULLS);
        List listTwo = mock(List.class, invocation -> {
            return null;
        });

        reset(listOne);
        //从这开始，之前的交互和stub将全部失效
    }

}
