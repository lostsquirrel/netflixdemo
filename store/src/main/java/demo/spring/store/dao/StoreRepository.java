package demo.spring.store.dao;

import demo.spring.store.po.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface StoreRepository {
    Page<Store> findByAddressLocationNear(
            Point location, Distance distance, Pageable pageable);

    long count();

    void save(List<Store> stores);

    Page<Store> findList(Pageable pageable);

    List<Store> stores();

    Store get(String storeId);
}
