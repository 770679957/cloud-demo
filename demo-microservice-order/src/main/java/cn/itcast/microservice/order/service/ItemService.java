package cn.itcast.microservice.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.itcast.microservice.order.pojo.Item;

@Service
public class ItemService {

	// Spring框架对RESTful方式的http请求做了封装，来简化操作
	@Autowired
	private RestTemplate restTemplate;

	@Value("${demo.item.url}")
	private String demoItemUrl;

	public Item queryItemById(Long id) {
		return this.restTemplate.getForObject(demoItemUrl + id, Item.class);
	}

}
