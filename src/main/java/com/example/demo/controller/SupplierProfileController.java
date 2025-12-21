import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.Supplier;
import com.example.demo.service.SupplierProfileService;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier Profile")
public class SupplierProfileController {

    private final SupplierProfileService supplierService;

    public SupplierProfileController(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @GetMapping("/")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PutMapping("/{id}/status")
    public Supplier updateStatus(@PathVariable Long id,
                                 @RequestParam boolean active) {
        return supplierService.updateSupplierStatus(id, active);
    }

    @GetMapping("/lookup/{supplierCode}")
    public Supplier getBySupplierCode(@PathVariable String supplierCode) {
        return supplierService.getBySupplierCode(supplierCode);
    }
}
