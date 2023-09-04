package user.ivanardo.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.ivanardo.inventoryservice.model.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
