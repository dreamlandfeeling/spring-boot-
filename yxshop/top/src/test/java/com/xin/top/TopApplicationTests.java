package com.xin.top;

import com.xin.top.mapper.TbContentMapper;
import com.xin.top.model.TbContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopApplicationTests {

	private TbContentMapper tbContentMapper;
	@Test
	public void contextLoads() {
		List<TbContent> list = tbContentMapper.findAll();
		for (TbContent tbContent : list) {
			System.out.println(tbContent);
		}
	}

}
