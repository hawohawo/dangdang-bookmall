package com.dangdang.bookmall.product;

import com.dangdang.bookmall.product.entity.TypeEntity;
import com.dangdang.bookmall.product.service.BaseinfoService;
import com.dangdang.bookmall.product.service.TypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MallProductApplicationTests {

	@Autowired
	private TypeService typeService;

	@Test
	void contextLoads() {
		TypeEntity typeEntity = new TypeEntity();
		typeEntity.setName("教材");
		typeService.save(typeEntity);
		System.out.println("保存成功");
	}

}
