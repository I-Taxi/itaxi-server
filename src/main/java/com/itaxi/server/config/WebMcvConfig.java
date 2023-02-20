package com.itaxi.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itaxi.server.filters.HTMLCharacterEscapes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
@EnableWebMvc
@Configuration
public class WebMcvConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }
    @Bean
    public WebMvcConfigurerAdapter controlTowerWebConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                super.configureMessageConverters(converters);

                // 5. WebMvcConfigurerAdapter에 MessageConverter 추가
                converters.add(htmlEscapingConveter());
            }


            private HttpMessageConverter<?> htmlEscapingConveter() {
                ObjectMapper objectMapper = new ObjectMapper();
                // 3. ObjectMapper에 특수 문자 처리 기능 적용
                objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

                // 4. MessageConverter에 ObjectMapper 설정
                MappingJackson2HttpMessageConverter htmlEscapingConverter =
                        new MappingJackson2HttpMessageConverter();
                htmlEscapingConverter.setObjectMapper(objectMapper);

                return htmlEscapingConverter;
            }
        };


    }
    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

}
