package me.jimmy.blogsource.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyTestMain {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        generateDynamicProxy(100_000_000);
        long end = System.currentTimeMillis();
        System.out.println("소요시간 " + (end - start) + "ms");
        Thread.sleep(3000);
    }

    private static void generateDynamicProxy(int tryCount) {
        List<DynamicProxyUser> users = new ArrayList<>();
        System.out.println("DynamicProxyTestMain#generateDynamicProxy start");
        for (int i = 0; i < tryCount; i++) {
            DynamicProxyUser proxyUser = (DynamicProxyUser) Proxy.newProxyInstance(
                    DynamicProxyTestMain.class.getClassLoader(),
                    new Class[]{DynamicProxyUser.class},
                    new DynamicProxyUserNameUpperCaseHandler(new DynamicProxyUserImpl())
            );
            users.add(proxyUser);
            proxyUser.hello("will " + i);
        }
        System.out.println("DynamicProxyTestMain#generateDynamicProxy end, userSize : " + users.size());
    }
}
