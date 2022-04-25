package com.company.trace.config;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

@Configuration(proxyBeanMethods = false)
public class TraceConfig {

    private static final String SERVER_ID = "server-message-id";

    @Bean
    Filter traceIdInResponseFilter(Tracer tracer) {
        return (request, response, chain) -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.addHeader(SERVER_ID, currentSpan.context().traceId());
            }
            chain.doFilter(request, response);
        };
    }
}
