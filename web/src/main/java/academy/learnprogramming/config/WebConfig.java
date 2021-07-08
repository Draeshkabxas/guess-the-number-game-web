package academy.learnprogramming.config;

import academy.learnprogramming.interceptor.RequestInterceptor;
import academy.learnprogramming.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
public class WebConfig implements WebMvcConfigurer {

    // == Bean Methods ==
    @Bean
    public LocaleResolver localeResolver(){
        return new SessionLocaleResolver();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName(ViewNames.HOME);
        registry.addViewController("home")
                .setViewName(ViewNames.HOME);
        WebMvcConfigurer.super.addViewControllers(registry);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());

//        LocaleChangeInterceptor localeChangeInterceptor=
//                new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");

        registry.addInterceptor(new LocaleChangeInterceptor());



        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
