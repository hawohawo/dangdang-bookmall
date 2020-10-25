package com.dangdang.bookmall.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.dangdang.bookmall.promotion.feign")
public class MallPromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallPromotionApplication.class, args);
	}

}
