package com.java8;

import org.junit.Test;

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
}
