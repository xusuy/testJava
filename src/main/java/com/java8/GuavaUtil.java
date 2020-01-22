package com.java8;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Ordering;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author xsy
 * @desc guava工具
 **/
public class GuavaUtil {
    @Test
    public void check() {
        int i = -1;
        int j = -2;
//        checkArgument(i > 0, "the argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
    }

    @Data
    @Accessors(chain = true)
    class Foo {
        @Nullable
        String sortedBy;
        int notSortedBy;

        public String toString() {
            return Objects.toStringHelper(this).add("notSortedBy", notSortedBy).add("sortedBy", sortedBy).toString();
        }
    }

    /**
     * 当阅读链式调用产生的排序器时，应该从后往前读。
     * 排序器首先调用apply方法获取sortedBy值，并把sortedBy为null的元素都放到最前面，
     * 然后把剩下的元素按sortedBy进行自然排序。
     * 之所以要从后往前读，是因为每次链式调用都是用后面的方法包装了前面的排序器。
     */
    @Test
    public void sorted() {
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });
        Foo foo1 = new Foo().setNotSortedBy(1).setSortedBy("roce");
        Foo foo2 = new Foo().setNotSortedBy(2).setSortedBy("jack");
        Foo foo3 = new Foo().setNotSortedBy(3).setSortedBy(null);
        List<Foo> fooList = new ArrayList<Foo>() {{
            add(foo1);
            add(foo2);
            add(foo3);
        }};
        System.out.println("排序前===" + fooList);
        Collections.sort(fooList, ordering);
        System.out.println("排序后===" + fooList);
        //获取可迭代对象中最大的k个元素。
        List<Foo> maxFooList = ordering.greatestOf(fooList, 2);
        System.out.println("ordering中最大的2个对象===" + maxFooList);
        //获取语义相反的排序器
        Ordering<Foo> reverseOrdering = ordering.reverse();
        Collections.sort(fooList, reverseOrdering);
        System.out.println("语义相反的排序器排序后===" + fooList);
        //返回比较大的
        Foo maxFoo = ordering.max(foo1, foo3);
        System.out.println("返回比较大的===" + maxFoo);
    }
}
