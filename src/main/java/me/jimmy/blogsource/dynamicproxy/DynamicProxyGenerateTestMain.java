package me.jimmy.blogsource.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyGenerateTestMain {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        generateDynamicProxyAndRun(100_000_000);
        long end = System.currentTimeMillis();
        System.out.println("소요시간 " + (end - start) + "ms");
        Thread.sleep(3000);
    }

    private static void generateDynamicProxyAndRun(int tryCount) {
        List<DynamicProxyUser> users = new ArrayList<>();
        System.out.println("DynamicProxyTestMain#generateDynamicProxy start");
        for (int i = 0; i < tryCount; i++) {
            DynamicProxyUser proxyUser = (DynamicProxyUser) Proxy.newProxyInstance(
                    DynamicProxyGenerateTestMain.class.getClassLoader(),
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
                DynamicProxyGenerateTestMain.class.getClassLoader(),
                new Class[]{DynamicProxyUser.class},
                new DynamicProxyUserNameUpperCaseHandler(new DynamicProxyUserImpl())
        );
    }
}
