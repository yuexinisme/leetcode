package com.nick.imdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.nick.imdb.mapper")
public class ImdbApplication {
    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    public static void main(String[] args) {
    //    System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(ImdbApplication.class, args);
    }

}
