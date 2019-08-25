package cn.yyw.microservice.order.service;

import cn.yyw.microservice.order.feign.ItemFeignClient;
import cn.yyw.microservice.order.pojo.Item;
import cn.yyw.microservice.order.properties.OrderProerties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


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

	@Autowired
	private ItemFeignClient itemFeignClient;

	/**
	 * 调用商品的微服务提供的接口进行查询数据
	 * @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") 进行容错处理
	 *
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
	public Item queryItemById(Long id) {
		return this.itemFeignClient.queryItemById(id);
	}

/*	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 进行容错处理
	public Item queryItemById(Long id) {
		//return this.restTemplate.getForObject(this.orderProerties.getItem().getUrl() + id, Item.class);
		String serviceId = "demo-microservice-item";
		return this.restTemplate.getForObject("http://" + serviceId + "/item/" + id, Item.class);
	}*/

	public Item queryItemByIdFallbackMethod(Long id){ // 请求失败执行的方法
		return new Item(id, "查询商品信息出错!", null, null, null);
	}


}

