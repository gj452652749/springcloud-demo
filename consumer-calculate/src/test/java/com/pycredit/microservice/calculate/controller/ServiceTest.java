package com.pycredit.microservice.calculate.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ServiceTest {
	RestTemplate httpRest;
	@Before
	public void before() {
		httpRest=new RestTemplate();
		PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(500);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(500);
		List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
        headers.add(new BasicHeader("Connection", "keep-alive"));
		CloseableHttpClient httpclient=HttpClientBuilder.create()
				.setConnectionManager(pollingConnectionManager)
				.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
				.setDefaultHeaders(headers)	
				.build();
		httpRest.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpclient));
		// TODO Auto-generated constructor stub
	}
	@Test
	public void performance() {
		long start=System.currentTimeMillis();
		ResponseEntity<String> response=httpRest.getForEntity("http://localhost:3333/add?a=1&b=3", String.class);
		System.out.println(System.currentTimeMillis()-start);
		//System.out.println(response.getBody());
		start=System.currentTimeMillis();
		response=httpRest.getForEntity("http://localhost:3333/add?a=1&b=3", String.class);
		System.out.println(System.currentTimeMillis()-start);
		//System.out.println(response.getBody());
		start=System.currentTimeMillis();
		response=httpRest.getForEntity("http://localhost:3333/add?a=1&b=3", String.class);
		System.out.println(System.currentTimeMillis()-start);
		//System.out.println(response.getBody());
	}

}
