package com.pojo;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author xsy
 * @create 2019-12-06 14:59
 * @desc 实现了比较器的实体类
 **/
@Setter
@Getter
@AllArgsConstructor
public class StudentComparable implements Comparable<StudentComparable> {
    /**
     * 名称
     */
    private String name;

    /**
     * 分数
     */
    private int score;

    //升序
//    @Override
//    public int compareTo(StudentComparable o) {
//        return this.score > o.score ? 1 : -1;
//    }

    //如果使用TreeMap集合，需要实现compareTo方法并且自定义返回0才是相同的key
//    @Override
//    public int compareTo(StudentComparable o) {
//        int compareInt = Integer.compare(this.score, o.score);
//        return compareInt != 0 ? compareInt : this.name.compareTo(o.name);
//    }

    //采用google的guava ComparisonChain：执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略。
    @Override
    public int compareTo(StudentComparable that) {

        return ComparisonChain.start()

                .compare(this.score, that.score)

                .compare(this.name, that.name)

//                .compare(this.anEnum, that.anEnum, Ordering.natural().nullsLast())

                .result();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentComparable that = (StudentComparable) o;
        return score == that.score &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}
