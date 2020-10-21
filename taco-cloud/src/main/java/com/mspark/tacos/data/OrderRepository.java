package com.mspark.tacos.data;

import com.mspark.tacos.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByDeliveryZip(String deliveryZip);
    List<Order> readOrderByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<Order> findByDeliveryToAndDeliveryCityAllIgnoreCase(String deleveryTo, String deliveryCity);
    List<Order> findByDeliveryCityOrderByDeliveryTo(String city);

}
