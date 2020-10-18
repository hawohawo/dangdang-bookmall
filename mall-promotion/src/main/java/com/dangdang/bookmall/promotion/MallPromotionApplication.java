package com.dangdang.bookmall.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MallPromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallPromotionApplication.class, args);
	}

}
