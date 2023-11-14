package com.wheelcredit.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "smart_payment_bcp")
public class SmartPaymentBCP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "discount_rate", nullable = false)
    private double discountRate; //tasa de descuento

    @Column(name = "tir", nullable = false)
    private double tir;

    @Column(name = "tcea", nullable = false)
    private double tcea;

    @Column(name = "van", nullable = false)
    private double van;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finalFeeSchedule_id", referencedColumnName = "id")
    private FinalFeeSchedule finalFeeSchedule;
}
