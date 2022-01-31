package com.strr.zuulsvr.filter;

import brave.Tracer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class ResponseFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private final Tracer tracer;

    @Autowired
    public ResponseFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;  // 后置过滤器
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(String.format("Adding the correlation id to the outbound headers. %s", FilterUtil.getCorrelationId()));
        FilterUtil.addCorrelationId(tracer.currentSpan().toString());
        System.out.println(String.format("Completing outgoing request for %s.", FilterUtil.getRequestURI()));
        return null;
    }
}
