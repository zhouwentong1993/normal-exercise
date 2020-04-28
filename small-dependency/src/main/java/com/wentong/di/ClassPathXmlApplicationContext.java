package com.wentong.di;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    private BeanFactory beanFactory;
    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configPath) {
        beanFactory = new BeanFactory();
        beanConfigParser = new XmlBeanConfigParser();
        beanFactory.register(beanConfigParser.parseBeanDefinitions(configPath));
    }

    @Override
    public Object getBean(String id) {
        return beanFactory.getBean(id);
    }
}
