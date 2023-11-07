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
    private float discountRate; //tasa de descuento

    @Column(name = "tir", nullable = false)
    private float tir;

    @Column(name = "tcea", nullable = false)
    private float tcea;

    @Column(name = "van", nullable = false)
    private float van;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", referencedColumnName = "id")
    public Client client;

    @OneToOne(mappedBy = "smartPaymentBCP") // Use the property name in FinalFeeSchedule
    private FinalFeeSchedule finalFeeSchedule;
}
