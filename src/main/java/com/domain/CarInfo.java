/**
 *
 */
package com.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 车辆信息实体（品牌，车系，车型）
 */
public class CarInfo {

    private String code;//编号
    private String name;//名称
    @JsonIgnore
    private String remark;//备注

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
