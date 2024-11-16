package me.jimmy.blogsource.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserNameUpperCaseMethodInterceptor implements MethodInterceptor {
    private final Object target;

    public UserNameUpperCaseMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (method.getName().equals("hello")) {
            String name = (String) args[0];
            for (int i = 0; i < 10; i++) {
                name += ("Test" + i).toUpperCase();
            }
            args[0] = name;
            return proxy.invoke(target, args);
        }
        return target;
    }
}
