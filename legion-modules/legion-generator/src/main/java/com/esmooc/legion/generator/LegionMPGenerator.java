package com.esmooc.legion.generator;

import com.esmooc.legion.generator.bean.Entity;
import com.esmooc.legion.generator.bean.Item;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器 Mybatis-Plus
 * @author Daimao
 */
@Slf4j
public class LegionMPGenerator {

    /**
     * 实体类名
     * 建议仅需修改
     */
    private static final String CLASS_NAME = "Student";

    /**
     * 类说明描述
     * 建议仅需修改
     */
    private static final String DESCRIPTION = "测试";

    /**
     * 作者名
     * 建议仅需修改
     */
    private static final String AUTHOR = "Daimao";

    /**
     * 是否生成树形结构相关接口
     * 建议仅需修改
     */
    private static final Boolean IS_TREE = false;

    /**
     * 数据库表名前缀
     * 下方请根据需要修改
     */
    private static final String TABLE_PRE = "t_";

    /**
     * 主键类型
     */
    private static final String PRIMARY_KEY_TYPE = "String";

    /**
     * 生成模块路径
     * (文件自动生成至该模块下)
     */
    private static final String MODULE = "/legion-modules/legion-your";

    /**
     * 模块包路径
     * （下方包路径拼接使用）
     */
    private static final String MODULE_PACKAGE = "your";

    /**
     * 实体类对应包
     * (文件自动生成至该包下)
     */
    private static final String ENTITY_PACKAGE = "com.esmooc.legion." + MODULE_PACKAGE + ".entity";

    /**
     * dao对应包 【注意修改后需到com.esmooc.legion.core.config.mybatisplus.MybatisPlusConfig配置你的mapper路径扫描】
     * (文件自动生成至该包下)
     */
    private static final String DAO_PACKAGE = "com.esmooc.legion." + MODULE_PACKAGE + ".mapper";

    /**
     * service对应包
     * (文件自动生成至该包下)
     */
    private static final String SERVICE_PACKAGE = "com.esmooc.legion." + MODULE_PACKAGE + ".service";

    /**
     * serviceImpl对应包
     * (文件自动生成至该包下)
     */
    private static final String SERVICE_IMPL_PACKAGE = "com.esmooc.legion." + MODULE_PACKAGE + ".serviceimpl";

    /**
     * controller对应包
     * (文件自动生成至该包下)
     */
    private static final String CONTROLLER_PACKAGE = "com.esmooc.legion." + MODULE_PACKAGE + ".controller";

    /**
     * 路径前缀
     */
    private static final String SYS_PATH = System.getProperty("user.dir") + MODULE + "/src/main/java/";

    /**
     * 运行该主函数即可生成代码
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // 模板路径
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("/btl/");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        // 生成代码
        generateCode(gt);

        // 读取你的实体类中的字段，补充生成条件构造分页查询代码【需自行复制控制台打印输出的代码自行覆盖】
        generatePlus(gt);

        // 根据类名删除生成的代码
        // deleteCode(className);
    }

    /**
     * 生成代码
     * @param gt
     * @throws IOException
     */
    private static void generateCode(GroupTemplate gt) throws IOException {

        Template entityTemplate = gt.getTemplate("entity.btl");
        Template daoTemplate = gt.getTemplate("mapper.btl");
        Template serviceTemplate = gt.getTemplate("mpService.btl");
        Template serviceImplTemplate = gt.getTemplate("mpServiceImpl.btl");
        Template controllerTemplate = gt.getTemplate("mpController.btl");
        Template mapperXmlTemplate = gt.getTemplate("mapperXml.btl");
        if (Boolean.TRUE.equals(IS_TREE)) {
            controllerTemplate = gt.getTemplate("treeMpController.btl");
        }

        Entity entity = new Entity();
        entity.setEntityPackage(ENTITY_PACKAGE);
        entity.setDaoPackage(DAO_PACKAGE);
        entity.setServicePackage(SERVICE_PACKAGE);
        entity.setServiceImplPackage(SERVICE_IMPL_PACKAGE);
        entity.setControllerPackage(CONTROLLER_PACKAGE);
        entity.setAuthor(AUTHOR);
        entity.setClassName(LegionGenerator.name(CLASS_NAME, true));
        entity.setTableName(TABLE_PRE + LegionGenerator.camel2Underline(CLASS_NAME));
        entity.setClassNameLowerCase(LegionGenerator.name(CLASS_NAME, false));
        entity.setDescription(DESCRIPTION);
        entity.setPrimaryKeyType(PRIMARY_KEY_TYPE);
        entity.setIsTree(IS_TREE);

        OutputStream out = null;

        // 生成实体类代码
        final String varEntity = "entity";
        entityTemplate.binding(varEntity, entity);
        String entityResult = entityTemplate.render();
        log.info(entityResult);
        // 创建文件
        String entityFileUrl = SYS_PATH + LegionGenerator.dotToLine(ENTITY_PACKAGE) + "/" + LegionGenerator.name(CLASS_NAME, true) + ".java";
        File entityFile = new File(entityFileUrl);
        File entityDir = entityFile.getParentFile();
        if (!entityDir.exists()) {
            entityDir.mkdirs();
        }
        if (!entityFile.exists()&&entityFile.createNewFile()) {
            // 若文件存在则不重新生成
            out = new FileOutputStream(entityFile);
            entityTemplate.renderTo(out);
        }

        // 生成dao代码
        daoTemplate.binding(varEntity, entity);
        String daoResult = daoTemplate.render();
        log.info(daoResult);
        // 创建文件
        String daoFileUrl = SYS_PATH + LegionGenerator.dotToLine(DAO_PACKAGE) + "/" + LegionGenerator.name(CLASS_NAME, true) + "Mapper.java";
        File daoFile = new File(daoFileUrl);
        File daoDir = daoFile.getParentFile();
        if (!daoDir.exists()) {
            daoDir.mkdirs();
        }
        if (!daoFile.exists()&&daoFile.createNewFile()) {
            // 若文件存在则不重新生成
            out = new FileOutputStream(daoFile);
            daoTemplate.renderTo(out);
        }

        // 生成service代码
        serviceTemplate.binding(varEntity, entity);
        String serviceResult = serviceTemplate.render();
        log.info(serviceResult);
        // 创建文件
        String serviceFileUrl = SYS_PATH + LegionGenerator.dotToLine(SERVICE_PACKAGE) + "/I" + LegionGenerator.name(CLASS_NAME, true) + "Service.java";
        File serviceFile = new File(serviceFileUrl);
        File serviceDir = serviceFile.getParentFile();
        if (!serviceDir.exists()) {
            serviceDir.mkdirs();
        }
        if (!serviceFile.exists()&&serviceFile.createNewFile()) {
            // 若文件存在则不重新生成
            out = new FileOutputStream(serviceFile);
            serviceTemplate.renderTo(out);
        }

        // 生成serviceImpl代码
        serviceImplTemplate.binding(varEntity, entity);
        String serviceImplResult = serviceImplTemplate.render();
        log.info(serviceImplResult);
        // 创建文件
        String serviceImplFileUrl = SYS_PATH + LegionGenerator.dotToLine(SERVICE_IMPL_PACKAGE) + "/I" + LegionGenerator.name(CLASS_NAME, true) + "ServiceImpl.java";
        File serviceImplFile = new File(serviceImplFileUrl);
        File serviceImplDir = serviceImplFile.getParentFile();
        if (!serviceImplDir.exists()) {
            serviceImplDir.mkdirs();
        }
        if (!serviceImplFile.exists()&&serviceImplFile.createNewFile()) {
            // 若文件存在则不重新生成
            out = new FileOutputStream(serviceImplFile);
            serviceImplTemplate.renderTo(out);
        }

        // 生成controller代码
        controllerTemplate.binding(varEntity, entity);
        String controllerResult = controllerTemplate.render();
        log.info(controllerResult);
        // 创建文件
        String controllerFileUrl = SYS_PATH + LegionGenerator.dotToLine(CONTROLLER_PACKAGE) + "/" + LegionGenerator.name(CLASS_NAME, true) + "Controller.java";
        File controllerFile = new File(controllerFileUrl);
        File controllerDir = controllerFile.getParentFile();
        if (!controllerDir.exists()) {
            controllerDir.mkdirs();
        }
        if (!controllerFile.exists()&&controllerFile.createNewFile()) {
            // 若文件存在则不重新生成
            out = new FileOutputStream(controllerFile);
            controllerTemplate.renderTo(out);
        }

        // 生成mapperXml代码
        mapperXmlTemplate.binding(varEntity, entity);
        String mapperXmlResult = mapperXmlTemplate.render();
        log.info(mapperXmlResult);
        // 创建文件
        String mapperXmlFileUrl = System.getProperty("user.dir") + MODULE + "/src/main/resources/mapper/" + LegionGenerator.name(CLASS_NAME, true) + "Mapper.xml";
        File mapperXmlFile = new File(mapperXmlFileUrl);
        File mapperXmlDir = mapperXmlFile.getParentFile();
        if (!mapperXmlDir.exists()) {
            mapperXmlDir.mkdirs();
        }
        if (!mapperXmlFile.exists()&&mapperXmlFile.createNewFile()) {
            // 若文件存在则不重新生成
            out = new FileOutputStream(mapperXmlFile);
            mapperXmlTemplate.renderTo(out);
        }

        if (out != null) {
            out.close();
        }
        log.info("生成代码成功！");
    }

    /**
     * 删除指定类代码
     * @param className
     * @throws IOException
     */
    private static void deleteCode(String className) throws IOException {

        String entityFileUrl = SYS_PATH + LegionGenerator.dotToLine(ENTITY_PACKAGE) + "/" + LegionGenerator.name(className, true) + ".java";
        Files.delete(Paths.get(entityFileUrl));

        String daoFileUrl = SYS_PATH + LegionGenerator.dotToLine(DAO_PACKAGE) + "/" + LegionGenerator.name(className, true) + "Mapper.java";
        Files.delete(Paths.get(daoFileUrl));

        String serviceFileUrl = SYS_PATH + LegionGenerator.dotToLine(SERVICE_PACKAGE) + "/I" + LegionGenerator.name(className, true) + "Service.java";
        Files.delete(Paths.get(serviceFileUrl));

        String serviceImplFileUrl = SYS_PATH + LegionGenerator.dotToLine(SERVICE_IMPL_PACKAGE) + "/I" + LegionGenerator.name(className, true) + "ServiceImpl.java";
        Files.delete(Paths.get(serviceImplFileUrl));

        String controllerFileUrl = SYS_PATH + LegionGenerator.dotToLine(CONTROLLER_PACKAGE) + "/" + LegionGenerator.name(className, true) + "Controller.java";
        Files.delete(Paths.get(controllerFileUrl));

        String mapperXmlFileUrl = System.getProperty("user.dir") + MODULE + "/src/main/resources/mapper/" + LegionGenerator.name(className, true) + "Mapper.xml";
        Files.delete(Paths.get(mapperXmlFileUrl));

        log.info("删除代码完毕！");
    }

    private static void generatePlus(GroupTemplate gt) {

        try {
            generateMPlus(gt);
        } catch (Exception e) {
            System.out.println("请确保实体类存在并且【已编译生成的模块代码】，记得完善填入字段后再生成条件构造代码哦！");
        }
    }

    private static void generateMPlus(GroupTemplate gt) throws Exception {

        Template plusTemplate = gt.getTemplate("mplus.btl");

        Entity entity = new Entity();

        entity.setClassName(LegionGenerator.name(CLASS_NAME, true));
        entity.setClassNameLowerCase(LegionGenerator.name(CLASS_NAME, false));
        List<Item> items = new ArrayList<>();

        String path = ENTITY_PACKAGE + "." + LegionGenerator.name(CLASS_NAME, true);
        Class<Object> c = (Class<Object>) Class.forName(path);
        Object obj = c.getDeclaredConstructor().newInstance();
        java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {

            java.lang.reflect.Field field = fields[i];
            field.setAccessible(true);
            // 字段名
            String fieldName = field.getName();
            String fieldType = field.getType().getName();
            // 白名单
            if ("serialVersionUID".equals(fieldName)) {
                continue;
            }
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null && !tableField.exist()) {
                continue;
            }

            // 获得字段注解
            ApiModelProperty myFieldAnnotation = field.getAnnotation(ApiModelProperty.class);
            String fieldNameCN = fieldName;
            if (myFieldAnnotation != null) {
                fieldNameCN = myFieldAnnotation.value();
            }
            fieldNameCN = StrUtil.isBlank(fieldNameCN) ? fieldName : fieldNameCN;

            if (fieldType.startsWith("java.lang.")) {
                fieldType = StrUtil.subAfter(fieldType, "java.lang.", false);
            }

            Item item = new Item();
            item.setType(fieldType);
            item.setUpperName(LegionGenerator.name(fieldName, true));
            item.setLowerName(LegionGenerator.name(fieldName, false));
            item.setLineName(LegionGenerator.camel2Underline(fieldName));
            item.setTitle(fieldNameCN);

            items.add(item);
        }

        // 绑定参数
        plusTemplate.binding("entity", entity);
        plusTemplate.binding("items", items);
        String result = plusTemplate.render();

        System.out.println("=================================================================================");
        System.out.println("=====生成条件构造代码Plus成功！请根据需要自行复制添加以下代码至控制层方法Controller中======");
        System.out.println("=================================条件构造代码起始线=================================");

        System.out.println(result);

        System.out.println("=================================条件构造代码终止线=================================");
        System.out.println("【代码方法添加位置：" + CONTROLLER_PACKAGE + "." + CLASS_NAME + "Controller.java】");
        System.out.println("【若未读取到字段请主动编译下生成的模块代码】");
    }
}
