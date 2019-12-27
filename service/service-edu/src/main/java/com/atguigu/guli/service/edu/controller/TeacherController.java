package com.atguigu.guli.service.edu.controller;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lzc
 * @since 2019-12-26
 */
@RestController
@RequestMapping("/admin/edu/teacher")
@Api(description = "讲师管理")      //过期方法，用什么代替？
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "分页讲师列表",notes = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQueryVo", value = "查询对象", required = false)
            TeacherQueryVo teacherQueryVo){

        Page<Teacher> pageParam = new Page<>(page, limit);
//        IPage<Teacher> pageModel = teacherService.page(pageParam, null);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "所有讲师列表",notes = "显示数据库中没有被逻辑删除的所有讲师列表")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list).message("查询成功");
    }

    @ApiOperation(value = "根据id删除讲师",notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(name = "id",value = "讲师id",required = true)   // name指的是前端页面需要传输值为 id 的 name 字段
            @PathVariable("id") String id){
        teacherService.removeById(id);
        return R.ok().message("删除成功");
    }

    @ApiOperation(value = "新增讲师",notes = "新增讲师")
    @PostMapping("save")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "修改讲师")
    @PutMapping("update")
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据id获取讲师",notes = "根据id获取讲师")
    @GetMapping("get/{id}")
    public R get(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }
}

