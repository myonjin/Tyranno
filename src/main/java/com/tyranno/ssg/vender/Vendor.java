package com.tyranno.ssg.vender;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;
    
    @Column(name= "vendor_image_url")
    private String vendorImageUrl;
}
