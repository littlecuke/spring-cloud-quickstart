package org.example.order;

// import com.netflix.loadbalancer.IRule;

import com.netflix.loadbalancer.RandomRule;
import org.example.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 开启远程调用
// 如果client所在包与主程序类不一致时需要手动指定包路径
@EnableFeignClients(
        defaultConfiguration = DefaultFeignConfiguration.class,
        basePackages = { "org.example.feign.client" }
)
// @EnableEurekaClient
@MapperScan("org.example.order.mapper")
@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced // 负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 修改负载均衡策略
     * <p>
     * 注意: 这种是作用于全局的，如果需要针对于某个微服务进行修改需要使用配置的方式进行修改
     *
     * @return {@link RandomRule}
     */
    // @Bean
    // public IRule randomRule() {
    //     return new RandomRule();
    // }

}
