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
@Table(name = "smart_payment")
public class SmartPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="selling_price_ asset",nullable=false)
    private double sellingPriceAsset; //precio de venta activo

    @Column(name="payment_plan_type",nullable=false)
    private int paymentPlanType;//tipo de plan de pago

    @Column(name="initial_installment",nullable=false)
    private double initialInstallment;// cuota inicial

    @Column(name = "final_fee", nullable = false)//
    private double finalFee;// cuota final

    @Column(name = "number_years", nullable = false)//
    private int numberOfYears;// numero de años

    @Column(name= "interest_rate",nullable = false)
    private double interestRate; //tasa de interes

    @Column(name= "interest_type",nullable = false)
    private String interestType; //tipo de tasa

    @Column(name= "capitalization",nullable = false)
    private String capitalization; //capitalizacion

    @Column(name= "payment_frequency",nullable = false)
    private int paymentFrequency;  // frecuencia de pago

    @Column(name= "notary_costs",nullable = false)
    private double notaryCosts; //costos notariales

    @Column(name= "registration_costs",nullable = false)
    private double registrationCosts; // costos de registro

    @Column(name= "appraisal",nullable = false)
    private float appraisal; // tasacion

    @Column(name= "study_commission",nullable = false)
    private double studyCommission; //comision de estudio

    @Column(name= "activation_commission",nullable = false)
    private double activationCommission; // comision de activacion

    @Column(name= "gps",nullable = false)
    private double gps;

    @Column(name= "shipping_costs",nullable = false)
    private double shippingCosts; //portes

    @Column(name= "administrative_expenses",nullable = false)
    private double administrativeExpenses; //gastos administrativos

    @Column(name= "life_insurance",nullable = false)
    private double lifeInsurance; //seguro desagraven

    @Column(name= "risk_insurance",nullable = false)
    private double riskInsurance; // seguro de riesgo

    @Column(name= "discount_rate",nullable = false)
    private double discountRate; //tasade descuento

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", referencedColumnName = "id")
    public Client client;
}
