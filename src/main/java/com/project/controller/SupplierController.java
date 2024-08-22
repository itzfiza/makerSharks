package com.project.controller;

import com.project.model.Supplier;
import com.project.model.SupplierRequest;
import com.project.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/supplier")
public class SupplierController {

    @Autowired
    public SupplierService supplierService;

    @PostMapping("/query")
    public ResponseEntity<List<Supplier>> fetchSuppliers(@RequestBody SupplierRequest supplierRequest,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        String sanitizedLocation = sanitizeInput(supplierRequest.getLocation());
        List<Supplier> result = supplierService.searchSupplier(
                sanitizedLocation,
                supplierRequest.getNatureOfBusiness(),
                supplierRequest.getManufacturingProcesses(),
                page, size
        );

        return ResponseEntity.ok(result);
    }

    private String sanitizeInput(String input) {
        // Basic sanitization example, adjust as needed for your requirements
        return input.trim().replaceAll("[^a-zA-Z0-9 ]", "");
    }
}