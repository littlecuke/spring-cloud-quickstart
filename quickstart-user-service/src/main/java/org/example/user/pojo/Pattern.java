package org.example.user.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "pattern") // 配置热更新
public class Pattern {
    private String dateformat;
    private String envSharedValue;
}
