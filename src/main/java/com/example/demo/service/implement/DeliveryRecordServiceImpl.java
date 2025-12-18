import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    @Autowired
    DeliveryRecordRepository repo;

    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {
        return repo.save(delivery);
    }

    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return repo.findByPoId(poId);
    }

    public DeliveryRecord getDeliveryById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<DeliveryRecord> getAllDeliveries() {
        return repo.findAll();
    }
}
