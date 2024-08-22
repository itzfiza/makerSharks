package com.project.model;

import com.project.enums.ManufacturingProcess;
import com.project.enums.NatureOfBusiness;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SupplierRequest {

    @NotNull(message = "Location cannot be null")
    private String location;

    @NotNull(message = "Nature of Business cannot be null")
    private NatureOfBusiness natureOfBusiness;

    @NotNull(message = "Manufacturing Processes cannot be null")
    private List<ManufacturingProcess> manufacturingProcesses;

}
