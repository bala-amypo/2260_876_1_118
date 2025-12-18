package com.example.demo.repository;
import com.example.demo.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface SupplierProfileRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findBySupplierCode(String supplierCode);

    boolean existsBySupplierCode(String supplierCode);
}
