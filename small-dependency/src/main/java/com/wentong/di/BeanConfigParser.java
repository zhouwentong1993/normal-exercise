package com.wentong.di;

import java.io.InputStream;
import java.util.List;

public interface BeanConfigParser {

    List<BeanDefinition> parseBeanDefinitions(String configPath);

    List<BeanDefinition> parseBeanDefinitions(InputStream inputStream);
}
