package com.atguigu.guli.service.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lzc
 * @create 2019-12-27 1:54
 */
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
@Data
public class TeacherQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师级别")
    private Integer level;

    @ApiModelProperty(value = "开始时间",example = "2019-01-01 08:00:00")
    private String joinDateBegin;

    @ApiModelProperty(value = "结束时间",example = "2019-01-01 08:00:00")
    private String joinDateEnd;
}
