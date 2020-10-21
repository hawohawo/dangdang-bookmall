package com.dangdang.bookmall.tripartite;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class MallTripartiteApplicationTests {
	@Autowired
	private OSSClient ossClient;

	@Test
	void testUpload() throws FileNotFoundException {

// 上传文件流。
		InputStream inputStream = new FileInputStream("/home/fengjianche/Pictures/Wallpapers/wallhaven-392qey_1920x1080.png");
		ossClient.putObject("dangdangmall-hello", "wallhaven-392qey_1920x1080.png", inputStream);

// 关闭OSSClient。
		ossClient.shutdown();
		System.out.println("ok");
	}
	@Test
	void contextLoads() {
	}

}
