package me.jimmy.blogsource.cglib;

import org.springframework.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.List;

public class CglibTestMain {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        generate(100_000_000);
        long end = System.currentTimeMillis();
        System.out.println("소요시간 : " + (end - start) + "ms");
        Thread.sleep(10000);
    }

    private static void generate(int tryCount) {
        List<CglibUser> users = new ArrayList<>();
        System.out.println("CglibGenerator#generate start");
        for (int i = 0; i < tryCount; i++) {
            CglibUser target = new CglibUser();
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(CglibUser.class);
            enhancer.setCallback(new UserNameUpperCaseMethodInterceptor(target));

            CglibUser proxy = (CglibUser) enhancer.create();
            users.add(proxy);
            proxy.hello("jimmy " + i);
        }
        System.out.println("CglibGenerator#generate end, userSize : " + users.size());
    }
}
