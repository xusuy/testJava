/**
 *
 */
package com.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 车辆信息实体（品牌，车系，车型）
 */
@Data
@Accessors(chain = true)
public class CarInfo {

    private String code;//编号
    private String name;//名称
    @JsonIgnore         //忽略
    private String remark;//备注
    @JsonProperty("car_owner")//指定json映射的属性
    private String carOwer;//车主

//    @Override
//    public String toString() {
//        return "CarInfo{" +
//                "code='" + code + '\'' +
//                ", name='" + name + '\'' +
//                ", remark='" + remark + '\'' +
//                ", carOwer='" + carOwer + '\'' +
//                '}';
//    }
}
