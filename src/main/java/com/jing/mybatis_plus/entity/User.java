package com.jing.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID) //代表数据库中的主键自增   数据库中没有添加主键
    private Long id;

    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT) //插入操作时自动执行
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) //修改和插入时自动执行
    private Date updateTime;

    //版本号
    @TableField(fill = FieldFill.INSERT) //插入时默认为1
    @Version //乐观锁
    private Integer version;

    @TableLogic//配置逻辑删除
    private Integer deleted;

}
