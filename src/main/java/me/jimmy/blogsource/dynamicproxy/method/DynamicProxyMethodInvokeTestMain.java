package me.jimmy.blogsource.dynamicproxy.method;

import me.jimmy.blogsource.dynamicproxy.DynamicProxyUser;
import me.jimmy.blogsource.dynamicproxy.DynamicProxyUserImpl;
import me.jimmy.blogsource.dynamicproxy.DynamicProxyUserNameUpperCaseHandler;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyMethodInvokeTestMain {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int tryCount = 100_000_000;

        DynamicProxyUser proxyUser = generateProxy();
        for (int i = 0; i < tryCount; i++) {
            proxyUser.hello("proxy method " + i);
        }

        long end = System.currentTimeMillis();
        System.out.println("소요시간 " + (end - start) + "ms");
        Thread.sleep(3000);
    }

    private static void generateDynamicProxyAndRun(int tryCount) {
        List<DynamicProxyUser> users = new ArrayList<>();
        System.out.println("DynamicProxyTestMain#generateDynamicProxy start");
        for (int i = 0; i < tryCount; i++) {
            DynamicProxyUser proxyUser = (DynamicProxyUser) Proxy.newProxyInstance(
                    DynamicProxyMethodInvokeTestMain.class.getClassLoader(),
                    new Class[]{DynamicProxyUser.class},
                    new DynamicProxyUserNameUpperCaseHandler(new DynamicProxyUserImpl())
            );
            users.add(proxyUser);
            proxyUser.hello("will " + i);
        }
        System.out.println("DynamicProxyTestMain#generateDynamicProxy end, userSize : " + users.size());
    }

    private static DynamicProxyUser generateProxy() {
        return  (DynamicProxyUser) Proxy.newProxyInstance(
                DynamicProxyMethodInvokeTestMain.class.getClassLoader(),
                new Class[]{DynamicProxyUser.class},
                new DynamicProxyUserNameUpperCaseHandler(new DynamicProxyUserImpl())
        );
    }
}
