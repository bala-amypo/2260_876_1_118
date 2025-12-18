package com.example.demo.service;
import java.util.List;
import com.example.demo.model.DeliveryRecord;

public interface DeliveryRecordService {

    DeliveryRecord recordDelivery(DeliveryRecord delivery);

    List<DeliveryRecord> getDeliveriesByPO(Long poId);

    DeliveryRecord getDeliveryById(Long id);

    List<DeliveryRecord> getAllDeliveries();
}
