package com.xin.manager.aop;

import com.xin.manager.utils.Constant;
import com.xin.manager.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 控制solr启动和关闭
 */
@Aspect
@Component
public class SolrLogAspect {
    private String SOLR_START = Constant.SOLR_START;



    @Pointcut("execution(public * com.xin.manager.service.SolrService.*(..))")
    public void solrLog(){}

    @Around("solrLog()")
    public void doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String solr = request.getParameter("solr");
        if(StringUtils.isNotBlank(solr)){
            SOLR_START = solr;
        }
        if(SOLR_START.equals(Constant.SOLR_START)){
            joinPoint.proceed();
        }
    }

    @AfterReturning(returning = "ret", pointcut = "solrLog()")
    public void doAfterReturning(Object ret) throws Throwable {
    }
}
