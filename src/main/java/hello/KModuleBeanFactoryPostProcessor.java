package hello;


import org.kie.api.builder.ReleaseId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class KModuleBeanFactoryPostProcessor implements BeanFactoryPostProcessor, ApplicationContextAware, ResourceLoaderAware {

    private static final Logger log            = LoggerFactory.getLogger(KModuleBeanFactoryPostProcessor.class);

    private static final String WEB_INF_FOLDER =  "WEB-INF" + File.separator + "classes" + File.separator;

    protected URL configFileURL;
    protected ReleaseId releaseId;

    private String configFilePath;
    private ApplicationContext context;

    public KModuleBeanFactoryPostProcessor() {
        initConfigFilePath();
    }

    protected void initConfigFilePath() {
        URL resource = getClass().getResource("/");
        System.out.println("*************initConfigFilePath--"+resource);
        System.out.println("*************getCodeSource--"+getClass().getProtectionDomain().getCodeSource());
        System.out.println("*************cl:--" + getClass().getClassLoader());
        if (resource != null) {
            configFilePath = resource.getPath();
        }
    }

    public void setReleaseId(ReleaseId releaseId) {
        this.releaseId = releaseId;
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info(":: BeanFactoryPostProcessor::postProcessBeanFactory called ::");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            System.out.println("*****************" + applicationContext);
            configFileURL = applicationContext.getResource("classpath:/").getURL();
            context = applicationContext;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("1**********************"+resourceLoader.getClass());
        Resource resource = resourceLoader.getResource("/");
        System.out.println("2**********************"+ resource);
        if ( resource instanceof ClassPathResource){
            System.out.println("2.1**********************" + ((ClassPathResource) resource).getPath());
        }
        System.out.println("3**********************"+resourceLoader.getResource("classpath:/"));
    }
}
