package cc.cat.entity;

import lombok.Data;

@Data
public class Picture {
    //图片路径
    private String imgUrl;
    //图片编号
    private String imgId;

    //衣服id
    private String clothId;

    //是否主图
    private int first;

    //类型
    private int status;
}
