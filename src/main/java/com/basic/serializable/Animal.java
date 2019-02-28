package com.basic.serializable;

import java.io.Serializable;

/**
 * @author xsy
 * @create 2019-02-28 11:12
 * @desc
 **/
public abstract class Animal implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *  code
     */
    public int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
