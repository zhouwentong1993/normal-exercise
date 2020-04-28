package com.wentong.di;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanConfigParser implements BeanConfigParser {

    @Override
    public List<BeanDefinition> parseBeanDefinitions(String configPath) {

        JSONObject jsonObject = XML.toJSONObject(FileUtil.readString(configPath, StandardCharsets.UTF_8));
        return getBeanDefinitions(jsonObject);
    }

    private List<BeanDefinition> getBeanDefinitions(JSONObject jsonObject) {
        List<BeanDefinition> result = new ArrayList<>();
        JSONObject beans = jsonObject.getJSONObject("beans");
        JSONArray bean = beans.getJSONArray("bean");
        bean.forEach(item -> {
            JSONObject itemObject = (JSONObject) item;
            BeanDefinition beanDefinition = new BeanDefinition();
            String id = itemObject.getStr("id");
            String className = itemObject.getStr("class");
            if (StrUtil.isBlank(id) || StrUtil.isBlank(className)) {
                throw new IllegalArgumentException("请检查 beans.xml 配置");
            }
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);
            beanDefinition.setScope(itemObject.getEnum(Scope.class, "scope", Scope.PROTOTYPE));
            beanDefinition.setLazyInit(itemObject.getBool("lazy-init", true));
            Object o = itemObject.get("constructor-arg");
            if (o != null) {
                // 代表只有一个构造参数
                if (o instanceof JSONObject) {
                    ConstructorArg constructorArg = parseConstructorArg((JSONObject) o);
                    beanDefinition.setArgs(ListUtil.toList(constructorArg));
                } else if (o instanceof JSONArray) { // 代表有多个构造参数
                    JSONArray constructor = (JSONArray) o;
                    List<ConstructorArg> list = new ArrayList<>();
                    constructor.forEach(obj -> {
                        list.add(parseConstructorArg((JSONObject) obj));
                    });
                    beanDefinition.setArgs(list);
                } else {
                    throw new IllegalArgumentException("constructor-arg 节点配置有误！");
                }
            }
            result.add(beanDefinition);
        });
        return result;
    }

    private ConstructorArg parseConstructorArg(JSONObject o) {
        ConstructorArg constructorArg = new ConstructorArg();
        constructorArg.setRefId(o.getStr("ref"));
        try {
            String type = o.getStr("type");
            if (StrUtil.isNotBlank(type)) {
                constructorArg.setArgType(Class.forName(type));
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("constructor-arg 的 type 节点配置有误！");
        }
        constructorArg.setArg(o.get("value"));
        return constructorArg;
    }

    @Override
    public List<BeanDefinition> parseBeanDefinitions(InputStream inputStream) {
        JSONObject jsonObject = XML.toJSONObject(IoUtil.read(inputStream, StandardCharsets.UTF_8));
        return getBeanDefinitions(jsonObject);
    }
}
