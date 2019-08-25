package cn.yyw.microservice.order.service;

import cn.yyw.microservice.order.properties.OrderProerties;
import cn.yyw.microservice.order.pojo.Item;
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



	public Item queryItemById(Long id) {
		//return this.restTemplate.getForObject(this.orderProerties.getItem().getUrl() + id, Item.class);

		String serviceId = "demo-microservice-item";
		List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
		if(instances.isEmpty()){
			return null;
		}
		// 为了演示，在这里只获取一个实例
		ServiceInstance serviceInstance = instances.get(0);
		String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
		System.out.print(url);
		return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
	}

}

