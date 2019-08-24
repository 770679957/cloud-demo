package cn.yyw.microservice.controller;


import cn.yyw.microservice.pojo.Item;
import cn.yyw.microservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author win10
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 127.0.0.1:8081/order/59193738268961441
    @GetMapping(value = "/item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        return this.itemService.queryItemById(id);
    }

}
