package demo.spring.store.dao;

import demo.spring.store.po.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface StoreRepository extends PagingAndSortingRepository<Store, UUID> {
    Page<Store> findByAddressLocationNear(
            Point location, Distance distance, Pageable pageable);
}
