package com.hwc.framework;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.service.ClUserAuthService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.utils.OSSUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAuthTests {

	@Autowired
	private ClUserAuthService clUserAuthService;
	@Autowired
	private ClUserService clUserService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCharge() {
	}

}
