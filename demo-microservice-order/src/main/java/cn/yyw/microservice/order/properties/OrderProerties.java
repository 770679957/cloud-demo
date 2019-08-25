package cn.yyw.microservice.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author win10
 * @ConfigurationProperties(prefix="demo") 以demo开头的配置被匹配到
 */
@Component
@ConfigurationProperties(prefix = "demo")
public class OrderProerties {

    private ItemProperties item = new ItemProperties();

    public ItemProperties getItem() {
        return item;
    }

    public void setItem(ItemProperties item) {
        this.item = item;
    }

}
