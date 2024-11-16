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
            for (int i = 0; i < 10; i++) {
                name += ("Test" + i).toUpperCase();
            }
            args[0] = name;
            return method.invoke(target, args);
        }
        return target;
    }
}
