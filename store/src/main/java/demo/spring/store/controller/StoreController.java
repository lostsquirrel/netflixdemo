package demo.spring.store.controller;

import demo.spring.store.dao.StoreRepository;
import demo.spring.store.po.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StoreController {

    private StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    @RequestMapping("/")
    public Object home() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("msg", "this is stores service");
        return data;
    }
//
//    public Object chain() {
//
//    }

    @RequestMapping("/near")
    public Page<Store> search(@RequestParam("latitude")Double latitude,
                              @RequestParam("longitude")Double longitude,
                              @RequestParam("distance")Double range,
                              @RequestParam(value = "page", defaultValue = "1")Integer page,
                              @RequestParam(value = "size", defaultValue = "10")Integer size
                              ) {
        Point location = new Point(longitude, latitude);
        Distance distance = new Distance(range);
        Pageable pageable = new PageRequest(page - 1, size);
        return storeRepository.findByAddressLocationNear(location, distance, pageable);
    }

    @GetMapping("/stores")
    public List<Store> stores() {
        return storeRepository.stores();
    }

    @GetMapping("/stores/{storeId}")
    public Store stores(@PathVariable("storeId")String storeId) {
        return storeRepository.get(storeId);
    }

}
