package demo.spring.store.dao.impl;

import demo.spring.store.dao.StoreRepository;
import demo.spring.store.po.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StoreRepositoryImpl implements StoreRepository {

    @Autowired
    private RedisTemplate<String, Store> storeRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String STORE_ID_LIST = "stores";

    private static final String STORE_DETAIL = "store:%s";

    private static final String STORE_GEO = "store_geo";
    @Override
    public Page<Store> findByAddressLocationNear(Point location, Distance distance, Pageable pageable) {
        Circle circle = new Circle(location, distance);
        GeoResults<RedisGeoCommands.GeoLocation<String>> rs = stringRedisTemplate.opsForGeo().geoRadius(STORE_GEO, circle);
        Iterator<GeoResult<RedisGeoCommands.GeoLocation<String>>> it = rs.iterator();
        List<String> ids = new ArrayList<>();
        // 分页
        int i = 0;
        int offset = pageable.getOffset();
        for(int j = 0; it.hasNext(); i++) {
            if (i >= offset) {
                if (j++ < pageable.getPageSize()) {
                    GeoResult<RedisGeoCommands.GeoLocation<String>> item = it.next();
                    String storeId = item.getContent().getName();
                    ids.add(String.format(STORE_DETAIL, storeId));
                }
            }
        }
        List<Store> stores = storeRedisTemplate.opsForValue().multiGet(ids);
        Page<Store> page = new PageImpl<Store>(stores, pageable, i--);
        return page;
    }

    @Override
    public long count() {
        return stringRedisTemplate.opsForList().size(STORE_ID_LIST);
    }

    @Override
    public void save(List<Store> stores) {
        List<String> ids = new ArrayList<>();
        Map<String, Store> storesMap = new HashMap<>();
        Map<String, Point> memMap = new HashMap<>();
        for (Store store : stores) {
            String id = UUID.randomUUID().toString().replace("-", "");
            ids.add(id);
            store.setId(id);
            storesMap.put(String.format(STORE_DETAIL, id), store);
            memMap.put(id, store.getAddress().getLocation());
        }
        stringRedisTemplate.opsForGeo().geoAdd(STORE_GEO, memMap);
        storeRedisTemplate.opsForValue().multiSet(storesMap);
        stringRedisTemplate.opsForList().leftPushAll(STORE_ID_LIST, ids);
    }

    @Override
    public Page<Store> findList(Pageable pageable) {
        return null;
    }

    @Override
    public List<Store> stores() {
        List<String> ids = stringRedisTemplate.opsForList().range(STORE_ID_LIST, 0, 10);
        return storeRedisTemplate.opsForValue().multiGet(ids);
    }

    @Override
    public Store get(String storeId) {
        return storeRedisTemplate.opsForValue().get(String.format(STORE_DETAIL, storeId));
    }

}
