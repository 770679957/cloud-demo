package cn.yyw.microservice.order.service;

import cn.yyw.microservice.order.properties.OrderProerties;
import cn.yyw.microservice.order.pojo.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;


@Service
public class ItemService {

	// Spring框架对RESTful方式的http请求做了封装，来简化操作
	@Autowired
	private RestTemplate restTemplate;

//	@Value("${demo.item.url}")
//	private String demoItemUrl;

	@Autowired
	private OrderProerties orderProerties;

	@Autowired
	private DiscoveryClient discoveryClient;


	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 进行容错处理
	public Item queryItemById(Long id) {
		//return this.restTemplate.getForObject(this.orderProerties.getItem().getUrl() + id, Item.class);
		String serviceId = "demo-microservice-item";
		return this.restTemplate.getForObject("http://" + serviceId + "/item/" + id, Item.class);
	}

	public Item queryItemByIdFallbackMethod(Long id){ // 请求失败执行的方法
		return new Item(id, "查询商品信息出错!", null, null, null);
	}


}

