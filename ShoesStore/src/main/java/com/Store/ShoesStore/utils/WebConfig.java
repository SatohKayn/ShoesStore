package com.Store.ShoesStore.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String userDirectory = Paths.get("")
                .toAbsolutePath()
                .toString();
        String uploadsDir = "/images/**";
        String uploadsPath = "file:"+userDirectory + File.separator + "ShoesStore" + File.separator + "images" + File.separator;

        registry.addResourceHandler(uploadsDir)
                .addResourceLocations(uploadsPath);
    }
}
