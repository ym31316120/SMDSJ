package com.ym.smdsj.plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 用于处理mybatis generator maven plugin在重复生成的时候xml文件只会merge，不会覆盖的问题
 * 使用该插件必须在pom文件中的mybatis generator插件中增加<overwrite>true</overwrite>属性
 * 并且在generatorConfig.xml中引用此插件
 * @author ym
 * @date 2019/3/8
 */
public class OverIsMergeablePlugin extends PluginAdapter {


    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}