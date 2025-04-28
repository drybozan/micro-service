package com.micro.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    //request alındıktan sonra mikroservisten bir yanıt alındıktan sonra calısacaktir
    //istek basligin almaya calısıp icideki koralsyon id yi alıyoruz
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtility.getCorrelationId(requestHeaders);

                if(!(exchange.getResponse().getHeaders().containsKey(filterUtility.CORRELATION_ID))){ //retry pattern modelinde her istek tekrar edilidğinde korelasyon id de tekrra ediyo header ksımında,bunu onlemek icin if eklendi
                    logger.debug("Updated the correlation id to the outbound headers: {}", correlationId);
                    exchange.getResponse().getHeaders().add(filterUtility.CORRELATION_ID, correlationId);
                }

            }));
        };
    }
}