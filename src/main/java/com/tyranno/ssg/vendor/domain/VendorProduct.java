package com.tyranno.ssg.vendor.domain;

import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vendor_product")
public class VendorProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
