package com.fshop.item.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
/**
 * 尺寸类
 */
@Slf4j
@Data
public class Specs {

    private Integer specsId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long clothId;

    private String size;

    private String color;

    private Integer num;


}

