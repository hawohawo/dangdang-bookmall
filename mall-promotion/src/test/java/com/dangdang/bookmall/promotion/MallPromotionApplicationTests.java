package com.dangdang.bookmall.promotion;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class MallPromotionApplicationTests {

	@Autowired
	private OSSClient ossClient;

	@Test
	void testUpload() throws FileNotFoundException {
//		// Endpoint以杭州为例，其它Region请按实际情况填写。
//		String endpoint = "oss-cn-hangzhou.aliyuncs.com";
//// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
//		String accessKeyId = "LTAI4GGAsG4FgoKpE3Ct1Nyd";
//		String accessKeySecret = "NhCANzAJz0gZHFprQGEF8x559s7fEK";
//
//// 创建OSSClient实例。
//		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

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
