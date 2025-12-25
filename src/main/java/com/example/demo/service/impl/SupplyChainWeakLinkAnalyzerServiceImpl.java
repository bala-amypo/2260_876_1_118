@Service
public class SupplyChainWeakLinkAnalyzerServiceImpl
        implements SupplyChainWeakLinkAnalyzerService {

    private final SupplierProfileRepository supplierRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierRiskAlertRepository alertRepo;

    public SupplyChainWeakLinkAnalyzerServiceImpl(
            SupplierProfileRepository supplierRepo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierRiskAlertRepository alertRepo) {

        this.supplierRepo = supplierRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public List<SupplierProfile> activeSuppliers() {
        return supplierRepo.findAll()
                .stream()
                .filter(SupplierProfile::getActive)
                .toList();
    }

    @Override
    public List<SupplierProfile> suppliersWithEmail() {
        return supplierRepo.findAll()
                .stream()
                .filter(s -> s.getEmail() != null)
                .toList();
    }

    @Override
    public List<SupplierProfile> suppliersByCodePattern(String pattern) {
        return supplierRepo.findAll()
                .stream()
                .filter(s -> s.getSupplierCode() != null &&
                             s.getSupplierCode().contains(pattern))
                .toList();
    }

    @Override
    public List<PurchaseOrderRecord> poIssuedBetween(LocalDate from, LocalDate to) {
        return poRepo.findAll()
                .stream()
                .filter(po ->
                        po.getIssuedDate() != null &&
                        !po.getIssuedDate().isBefore(from) &&
                        !po.getIssuedDate().isAfter(to))
                .toList();
    }

    @Override
    public List<DeliveryRecord> partialDeliveries() {
        return deliveryRepo.findAll()
                .stream()
                .filter(d ->
                        d.getDeliveredQuantity() != null &&
                        d.getOrderedQuantity() != null &&
                        d.getDeliveredQuantity() < d.getOrderedQuantity())
                .toList();
    }

    @Override
    public List<SupplierRiskAlert> unresolvedAlerts() {
        return alertRepo.findAll()
                .stream()
                .filter(a -> !a.getResolved())
                .toList();
    }

    @Override
    public List<SupplierRiskAlert> mediumRiskAlerts() {
        return alertRepo.findAll()
                .stream()
                .filter(a -> "MEDIUM".equalsIgnoreCase(a.getRiskLevel()))
                .toList();
    }
}
