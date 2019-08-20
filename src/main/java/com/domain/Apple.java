package com.domain;

import com.util.wechat.bean.BaseBean;

/**
 * @author xsy
 * @create 2019-08-16 16:50
 * @desc
 **/
public class Apple extends BaseBean {
    /** 编号 */
    private Long id;
    /** 颜色 */
    private String color;
    /** 重量 */
    private Float weight;
    /** 产地 */
    private String origin;

    public Apple() {
    }

    public Apple(Long id, String color, Float weight, String origin) {
        this.id = id;
        this.color = color;
        this.weight = weight;
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
