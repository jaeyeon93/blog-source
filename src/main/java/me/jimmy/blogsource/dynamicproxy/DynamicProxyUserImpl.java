package me.jimmy.blogsource.dynamicproxy;

public class DynamicProxyUserImpl implements DynamicProxyUser {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}
