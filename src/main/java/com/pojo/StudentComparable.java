package com.pojo;

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

//    @Override
//    public int compareTo(StudentComparable o) {
//        return this.score>o.score?1:-1;
//    }

    //如果使用TreeMap集合，需要实现compareTo方法并且自定义返回0才是相同的key
    @Override
    public int compareTo(StudentComparable o) {
        int compareInt = Integer.compare(this.score, o.score);
        return compareInt != 0 ? compareInt : this.name.compareTo(o.name);
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
