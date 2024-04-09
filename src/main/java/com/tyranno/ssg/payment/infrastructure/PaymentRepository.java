package com.tyranno.ssg.payment.infrastructure;

import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPartnerUserId(String partnerUserId);
}
