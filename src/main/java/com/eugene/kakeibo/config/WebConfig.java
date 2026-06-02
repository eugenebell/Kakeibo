package com.eugene.kakeibo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/dashboard");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/budgets").setViewName("budgets");
        registry.addViewController("/transactions").setViewName("transactions");
    }
}