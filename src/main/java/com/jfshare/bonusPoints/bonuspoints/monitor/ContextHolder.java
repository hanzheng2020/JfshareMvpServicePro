package com.jfshare.bonusPoints.bonuspoints.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextHolder {
    private static ApplicationContext context;

    @Autowired
    public ContextHolder(ApplicationContext ac) {
        context = ac;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
