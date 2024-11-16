package me.jimmy.blogsource.cglib.method;

import me.jimmy.blogsource.cglib.CglibUser;
import me.jimmy.blogsource.cglib.UserNameUpperCaseMethodInterceptor;
import org.springframework.cglib.proxy.Enhancer;

public class CglibMethodInvokeTestMain {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        CglibUser proxy = generateProxy();
        int tryCount = 100_000_000;
        for (int i = 0; i < tryCount; i++) {
            proxy.hello("cglib method call  " + i);
        }

        long end = System.currentTimeMillis();
        System.out.println("소요시간 : " + (end - start) + "ms");
        Thread.sleep(3000);
    }

    private static CglibUser generateProxy() {
        CglibUser target = new CglibUser();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibUser.class);
        enhancer.setCallback(new UserNameUpperCaseMethodInterceptor(target));

        return  (CglibUser) enhancer.create();
    }
}
