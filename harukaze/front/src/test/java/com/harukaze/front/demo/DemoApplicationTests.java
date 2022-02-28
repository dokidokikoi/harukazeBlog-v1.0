package com.harukaze.front.demo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.harukaze.front.entity.Article;
import com.harukaze.front.mapper.ArticleMapper;
import com.harukaze.front.service.ArticleService;
import com.harukaze.front.service.TagService;
import com.harukaze.front.util.CryptoAESUtil;
import com.harukaze.front.util.IpUtils;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.ArticlePageParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.NoSuchPaddingException;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	ArticleService articleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	TagService tagService;

	@Autowired
	ArticleMapper articleMapper;

	@Test
	void contextLoads() {
		System.out.println(tagService.report());
	}

	@Test
	public void testIpAddress() {
		String url = "http://api.map.baidu.com/location/ip?&ak=XLOp9rLuNN4kAi6790zP02MT0xlw2uWm&ip=220.202.152.101";
		JSONObject jsonObject = IpUtils.sendGet2(url);
		System.out.println(jsonObject);
		//获取content中的值
		JSONObject contentJsonObject = jsonObject.getJSONObject("content");
		System.out.println("contentJsonObject-------"+contentJsonObject);
		//获取详细地址数据
		JSONObject addressDetailJsonObject = contentJsonObject.getJSONObject("address_detail");
		String address = addressDetailJsonObject.get("province",String.class);
		String city = addressDetailJsonObject.get("city",String.class);
		System.out.println("address_detail-------"+addressDetailJsonObject);
		System.out.println("address-------"+address);
		System.out.println("city-------"+city);

		String address2 = contentJsonObject.get("address",String.class);
		System.out.println("address2-----"+address2);

		JSONObject pointJsonObject = contentJsonObject.getJSONObject("point");
		String x = pointJsonObject.get("x",String.class);
		String y = pointJsonObject.get("y",String.class);
		System.out.println("x----"+x);
		System.out.println("y----"+y);
	}

	@Test
	public void test() {
		long l = System.currentTimeMillis();
		long time = new Date().getTime();
		System.out.println(l);
		System.out.println(time);
		Date date = new Date(1638871409092L);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		System.out.println(simpleDateFormat.format(date));;
	}

	@Test
	public void archives() {
		System.out.println(JSONUtil.toJsonStr(articleService.articleArchives()));
	}

	@Test
	public void articles() {
		System.out.println(JSONUtil.toJsonStr(articleMapper.selectList(new LambdaQueryWrapper<Article>().orderByDesc(Article::getCreateDate))));
	}

	@Test
	public void testCrypt() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		System.out.println(CryptoAESUtil.decrypt("dMUSQqYC16zIfe2AcNYLVw==", "JcRYQ4yt3i^9%mdt"));
	}

}
