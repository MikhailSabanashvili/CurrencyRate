package com.company.helper;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:variables.properties")
public class ConfigProperties {
    @Getter
    @Value("${exchange.token}")
    private String exchangeToken;
    @Getter
    @Value("${giff.token}")
    private String giffToken;
    @Getter
    @Value("${tag.rich}")
    private String tagRich;
    @Getter
    @Value("${tag.broke}")
    private String tagBroke;
}
