package org.ld.services;

import org.ld.Application;
import org.ld.mapper.AdapterMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ld.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTests {

	@Autowired
	private AdapterMapper userDao;

	@Test
	public void contextLoads() {
		System.out.println(JSONUtil.obj2String(userDao.selectByPrimaryKey(1)));
	}

}
