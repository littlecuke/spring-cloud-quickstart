package org.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求进入网关后会经过三类过滤器：当前路由配置的过滤器、DefaultFilter、GlobalFilter，
 * 请求路由后会将当前路由过滤器、DefaultFilter和GlobalFilter合并到一个过滤器链(集合)中，
 * 排序后依次执行每个过滤器
 *
 * 执行顺序：
 * 1.每个过滤器都必须要指定一个int类型的order值，order值越小，优先级越高，执行顺序越靠前
 * 2.GlobalFilter通过实现Ordered接口或者使用@Order注解自定义order值
 * 3.路由过滤器和DefaultFilter的order值由Spring决定，默认是按照申明的顺序从1递增
 * 4.当过滤器的order值一样时，执行顺序为：DefaultFilter > 当前路由配置的过滤器 > GlobalFilter
 */
// @Order(-1)
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("custom global filter");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
