package ru.gb.j2ee.logging;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Nikita Ermakov
 *
 * EJB Interceptor for custom logging
 */
public class MethodLogger {

    @AroundInvoke
    public Object printLog(InvocationContext ctx) throws Exception {
        System.out.println("Class: " + ctx.getMethod().getDeclaringClass().getSimpleName()
                + " invoked method " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
