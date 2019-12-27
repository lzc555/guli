package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.mapper.TeacherMapper;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author lzc
 * @since 2019-12-26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQueryVo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);//TODO 优化
        }

        if (level != null) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge("join_date", joinDateBegin);
        }

        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le("join_date", joinDateEnd);
        }

        return baseMapper.selectPage(pageParam, queryWrapper);
    }

//    /**
//     *
//     * 可以通过改造源码，来完成自己的需求
//     * <p>
//     * 删除不存在的逻辑上属于成功
//     * </p>
//     *
//     * @param result 数据库操作返回影响条数
//     * @return boolean
//     */
//    private static boolean delBool(Integer result) {
//        return null != result && result > 0;
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public boolean removeById(Serializable id) {
//        return delBool(baseMapper.deleteById(id));
//    }


}
