package com.eaglebank.service;

import com.eaglebank.api.util.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
