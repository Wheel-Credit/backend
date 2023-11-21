package com.wheelcredit.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmartPaymentResponseDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private double sellingPriceAsset; // precio de venta activo
    private int paymentPlanType;// tipo de plan de pago
    private double initialInstallment;// cuota inicial
    private double finalInstallment;// cuota final
    private double interestRate; // tasa de interes
    private String interestType; // tipo de tasa
    private String capitalization; // capitalizacion
    private int paymentFrequency; // frecuencia de pago
    private double notaryCosts; // costos notariales
    private String notaryCostsType; // costos notariales
    private double registrationCosts; // costos de registro
    private String registrationCostsType; // costos de registro
    private float appraisal; // tasacion
    private String appraisalType; // tasacion
    private double studyCommission; // comision de estudio
    private String studyCommissionType; // comision de estudio
    private double activationCommission; // comision de activacion
    private String activationCommissionType; // comision de activacion
    private double gps;
    private double shippingCosts; // portes
    private double administrativeExpenses; // gastos administrativos
    private double lifeInsurance; // seguro desagraven
    private double riskInsurance; // seguro de riesgo
    private double discountRate; // tasade descuento
}
