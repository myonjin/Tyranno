package com.tyranno.ssg.vendor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto {
    private Long VendorId;
    private String vendorName;
    private String vendorImageUrl;
}
