package demo.spring.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StoreController {

    @RequestMapping("/")
    public Object home() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("msg", "this is stores service");
        return data;
    }
}
