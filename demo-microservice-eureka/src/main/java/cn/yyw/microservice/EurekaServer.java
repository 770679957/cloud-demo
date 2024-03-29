package cn.yyw.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author win10
 * @EnableEurekaServer 申明这是一个Eureka服务
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    //http://127.0.0.1:6868/
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
    }

}
