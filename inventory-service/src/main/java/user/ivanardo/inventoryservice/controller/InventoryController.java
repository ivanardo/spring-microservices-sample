package user.ivanardo.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import user.ivanardo.inventoryservice.dto.InventoryResponse;
import user.ivanardo.inventoryservice.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInsStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
