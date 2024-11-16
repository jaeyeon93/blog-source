package me.jimmy.blogsource.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyUserNameUpperCaseHandler implements InvocationHandler {
    private final Object target;

    public DynamicProxyUserNameUpperCaseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("hello")) {
            String name = (String) args[0];
            args[0] = name.toUpperCase() + " hello world";
            return method.invoke(target, args);
        }
        return target;
    }
}
