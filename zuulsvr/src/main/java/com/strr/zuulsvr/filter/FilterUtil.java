package com.strr.zuulsvr.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.net.URL;

public class FilterUtil {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";
    // public static final String PRE_FILTER_TYPE = "pre";
    // public static final String POST_FILTER_TYPE = "post";
    // public static final String ROUTE_FILTER_TYPE = "route";

    public static String getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(CORRELATION_ID) !=null) {
            return ctx.getRequest().getHeader(CORRELATION_ID);
        }
        else{
            return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public static void setCorrelationId(String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public static String getOrgId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(ORG_ID) !=null) {
            return ctx.getRequest().getHeader(ORG_ID);
        }
        else{
            return ctx.getZuulRequestHeaders().get(ORG_ID);
        }
    }

    public static void setOrgId(String orgId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(ORG_ID, orgId);
    }

    public static String getUserId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(USER_ID) !=null) {
            return ctx.getRequest().getHeader(USER_ID);
        }
        else{
            return ctx.getZuulRequestHeaders().get(USER_ID);
        }
    }

    public static void setUserId(String userId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(USER_ID, userId);
    }

    public static String getAuthToken() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeader(AUTH_TOKEN);
    }

    public static String getServiceId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //We might not have a service id if we are using a static, non-eureka route.
        if (ctx.get(FilterConstants.SERVICE_ID_KEY)==null) return "";
        return ctx.get(FilterConstants.SERVICE_ID_KEY).toString();
    }

    public static void setServiceId(String serviceId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.put(FilterConstants.SERVICE_ID_KEY, serviceId);
    }

    public static String getRequestURI() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getRequestURI();
    }

    public static void setRequestURI(String requestURI) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.put(FilterConstants.REQUEST_URI_KEY, requestURI);
    }

    public static void addCorrelationId(String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().addHeader(CORRELATION_ID, correlationId);
    }

    public static URL getRouteHost() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRouteHost();
    }
}
