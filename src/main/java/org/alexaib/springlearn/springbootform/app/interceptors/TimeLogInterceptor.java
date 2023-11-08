package org.alexaib.springlearn.springbootform.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component("timeLogInterceptor")
public class TimeLogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TimeLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Entering preHandle()");
        logger.info(String.format("Intercepting %s", handler));
        if (handler instanceof HandlerMethod)
            logger.info(String.format("Is method of controller %s", handler));
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        Thread.sleep(new Random().nextInt(500));


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        long passedTime = endTime - (long) request.getAttribute("startTime");

        if (handler instanceof HandlerMethod && modelAndView != null)
            modelAndView.addObject("passedTime", passedTime);

        logger.info(String.format("Passed time %d ms", passedTime));
        logger.info("Exiting postHandle()");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
