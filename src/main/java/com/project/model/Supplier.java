package com.project.model;

import com.project.enums.ManufacturingProcess;
import com.project.enums.NatureOfBusiness;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Supplier {

    @Id
    @NotNull(message = "Supplier ID cannot be null")
    private String id;

    @NotNull(message = "Company name cannot be null")
    @Size(min = 1, max = 100, message = "Company name must be between 1 and 100 characters")
    private String companyName;

    private String website;

    @NotNull(message = "Location cannot be null")
    @Size(min = 1, max = 100, message = "Location must be between 1 and 100 characters")
    private String location;

    @NotNull(message = "Nature of Business cannot be null")
    private NatureOfBusiness natureOfBusiness;

    @NotNull(message = "Manufacturing Processes cannot be null")
    private Set<ManufacturingProcess> manufacturingProcesses;
}
