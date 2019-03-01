package conglin.serendipity.util;
/*
 * Description:
 * Author: 从林
 * Class: 2016240206
 * Student Id: 2016903629
 * Created at 2019-02-26  19:40
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        log.info("ApplicationContext配置成功,applicationContext对象：" + SpringUtil.applicationContext);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz) {
        return getApplicationContext().getBean(name,clazz);
    }

}
