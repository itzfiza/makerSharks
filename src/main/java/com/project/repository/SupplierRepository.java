package com.project.repository;

import com.project.enums.ManufacturingProcess;
import com.project.enums.NatureOfBusiness;
import com.project.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {

    Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcessesIn(String location, NatureOfBusiness natureOfBusiness, List<ManufacturingProcess> manufacturingProcesses, Pageable pageable);
}
