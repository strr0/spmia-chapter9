package com.strr.zuulsvr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class TrackingFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;  // 前置过滤器
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent(){
        return FilterUtil.getCorrelationId() != null;
    }

    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public Object run() throws ZuulException {
        if (isCorrelationIdPresent()) {
            System.out.println(String.format("tmx-correlation-id found in tracking filter: %s. ", FilterUtil.getCorrelationId()));
        }
        else{
            FilterUtil.setCorrelationId(generateCorrelationId());
            System.out.println(String.format("tmx-correlation-id generated in tracking filter: %s.", FilterUtil.getCorrelationId()));
        }
        System.out.println(String.format("Processing incoming request for %s.", FilterUtil.getRequestURI()));
        return null;
    }
}
