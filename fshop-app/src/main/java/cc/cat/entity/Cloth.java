package cc.cat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * 商品类
 */
@Data
public class Cloth {

    //衣服编号
    @JsonSerialize(using= ToStringSerializer.class)
    private BigInteger id;
    private String sId;

    //衣服名字
    private String name;

    //衣服分类编号
    private Integer  categoryId;

    //衣服价格
    private BigDecimal price;

    //商品码
    private  String code;

    //图片地址
    private String image;

    //描述信息
    private String description;

    //状态
    private int status;

    //排序编号
    private int sort;
    //主分类编号
    private int categoryFather;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    //创建用户id
    @TableField(fill = FieldFill.INSERT)
    private String createUser;
    //更新用户id
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    //是否删除
    private int isDeleted;

    //商品剩余数量
    private int num;

}
