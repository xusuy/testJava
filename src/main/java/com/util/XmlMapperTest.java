package com.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author xsy
 * @create 2019-12-26 18:20
 * @desc xml映射
 **/
public class XmlMapperTest {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person("yitian", "易天", 25, "10000", new Date());
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String text = mapper.writeValueAsString(p1);
        System.out.println("实体类转xml====" + text);

        Person p2 = mapper.readValue(text, Person.class);
        System.out.println("xml转实体类====" + p2);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("xml")
class Person {
    @JsonProperty("name")
    private String name;
    @JsonProperty("nickname")
    //@JacksonXmlText
    private String nickname;
    @JsonProperty("age")
    private int age;
    @JsonProperty("identityCode")
    @JacksonXmlCData
    private String identityCode;
    @JsonProperty("birthday")
    //@JacksonXmlProperty(isAttribute = true)
    @JsonFormat(pattern = "yyyy/MM/DD")
    private Date birthday;
}
