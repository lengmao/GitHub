//package com.frame.config;
//
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.context.annotation.Bean;
//
//import javax.servlet.MultipartConfigElement;
//
///**
// * @author scaf_xs
// * @ClassName: FileConfig
// * @Description: TODO(这里用一句话描述这个类的作用)
// * @date 2018/1/16 11:04
// */
//
//public class FileConfig {
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//
//        MultipartConfigFactory factory=new MultipartConfigFactory();
//
//        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
//        // 这样在文件上传的地方就需要进行异常信息的处理了;
//        factory.setMaxFileSize("256KB");// KB\MB
//        factory.setMaxRequestSize("512KB");
//        // Sets the directory location where files will be stored.
//        // factory.setLocation("路径地址");
//        return factory.createMultipartConfig();
//    }
//}
