package com.project.service;

import com.project.enums.ManufacturingProcess;
import com.project.enums.NatureOfBusiness;
import com.project.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);

    public List<Supplier> searchSupplier(String location, NatureOfBusiness natureOfBusiness,
                                         List<ManufacturingProcess> manufacturingProcesses, int page, int size) {
        try {
            logger.info("Searching for suppliers with location: {}, natureOfBusiness: {}, manufacturingProcesses: {}, page: {}, size: {}",
                    location, natureOfBusiness, manufacturingProcesses, page, size);

            Pageable pageable = PageRequest.of(page, size);
            Page<Supplier> suppliersPage = supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesIn(
                    location, natureOfBusiness, manufacturingProcesses, pageable);

            if (suppliersPage.isEmpty()) {
                logger.warn("No suppliers found matching the criteria: location={}, natureOfBusiness={}, manufacturingProcesses={}",
                        location, natureOfBusiness, manufacturingProcesses);
                throw new NoSuchElementException("No suppliers found matching the criteria.");
            }

            logger.info("Found {} suppliers matching the criteria on page {} with size {}", suppliersPage.getNumberOfElements(), page, size);
            return suppliersPage.getContent(); // Convert Page to List
        } catch (NoSuchElementException ex) {
            logger.error("Error occurred: No suppliers found", ex);
            throw ex;
        } catch (Exception ex) {
            logger.error("Error occurred while searching for suppliers", ex);
            throw new RuntimeException("An error occurred while searching for suppliers.", ex);
        }
    }
}
