package com.tyranno.ssg.vendor.domain;

import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class VendorProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendorId")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
