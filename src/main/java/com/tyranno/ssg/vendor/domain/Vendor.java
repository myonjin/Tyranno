package com.tyranno.ssg.vendor.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name= "vendor_image_url")
    private String vendorImageUrl;
}
