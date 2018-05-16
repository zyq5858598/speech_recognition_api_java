package com.zyq;

/**
 * Created by yuzhou on 2018/3/14.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)                            // 允许使用自定义JDK代理切面
public class Server {
  public static void main(String[] args) {
    SpringApplication.run(Server.class, args);
  }
}
