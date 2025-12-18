package com.example.demo.repository;
import com.example.demo.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface SupplierProfileRepository extends JpaRepository<SupplierProfile, Long> {

    Optional<SupplierProfile> findBySupplierCode(String supplierCode);

    boolean existsBySupplierCode(String supplierCode);
}
