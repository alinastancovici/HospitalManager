package com.siit.hospital_manager.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class CreatePatientDto {
    @NotNull(message = "Name can not be null")
    @Pattern(regexp = "[A-Z][a-z]{1,15}+ [A-Z][a-z]{1,15}+")
    private String name;

    @Range(min = 0, max = 120)
    @NotNull(message = "Age can't be null")
    private Integer age;

    @NotNull(message = "PhoneNumber can't be null")
    @Pattern(regexp = "[0][0-9]{9}")
    private String phoneNumber;

}
