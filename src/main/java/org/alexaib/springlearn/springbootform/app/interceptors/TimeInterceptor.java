package org.alexaib.springlearn.springbootform.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("TimeInterceptor: entering preHandle()");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        Thread.sleep(new Random().nextInt(500));


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        long passedTime = (long) request.getAttribute("startTime") - endTime;

        if (modelAndView != null)
            modelAndView.addObject("passedTime", passedTime);

        logger.info(String.format("TimeInterceptor: passed time %d ", passedTime));
        logger.info("TimeInterceptor: exitings postHandle()");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
