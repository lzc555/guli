package com.atguigu.guli.service.edu;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author lzc
 * @create 2019-12-26 14:11
 */
public class CodeGenerator {

    @Test
    public void genCode() {

        String prefix = "db190722_";
        String moduleName = "edu";

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();

        //代码输出位置
        String projectPath = System.getProperty("user.dir");//java JDK 自己带的东西。 获取当前项目模块的路径，D:\JAVALearnninngProgram\guli-project\guli\service\service-edu
        gc.setOutputDir(projectPath + "/src/main/java");//设置输出目录

        gc.setAuthor("lzc");//作者信息
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去掉Service接口的首字母I, mybatis-plus 默认为 IteacherService ,这里需要修改为 TeacherService
        gc.setIdType(IdType.ID_WORKER_STR); //主键策略,实体类中的主键ID
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型，默认为LocalDateTime
        gc.setSwagger2(true);//开启Swagger2模式
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.101.184.221:3306/" + prefix + "guli_" + moduleName);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("782954");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName); //模块名
        pc.setParent("com.atguigu.guli.service");//决定了 com.atguigu.guli.service.edu
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(moduleName + "_\\w*");//设置要映射的表名,这里的意思是匹配 edu_ 开头的所有表
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_");//设置表前缀不生成

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀
        strategy.setVersionFieldName("version");//乐观锁字段名称

        //自动填充
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        strategy.setRestControllerStyle(true); //restful api风格控制器, restful风格 也就是 http + json格式
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        //设置BaseEntity
        strategy.setSuperEntityClass("com.atguigu.guli.service.base.model.BaseEntity");
        //填写BaseEntity中的公共字段
        strategy.setSuperEntityColumns("id", "gmt_create", "gmt_modified");

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
