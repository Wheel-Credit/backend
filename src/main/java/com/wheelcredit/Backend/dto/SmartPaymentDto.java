package com.wheelcredit.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmartPaymentDto {

    private float sellingPriceAsset; //precio de venta activo
    private int paymentPlanType;//tipo de plan de pago
    private float initialInstallment;// cuota inicial
    private int numberOfYears;// numero de años
    private float interestRate; //tasa de interes
    private String interestType; //tipo de tasa
    private String capitalization; //capitalizacion
    private int paymentFrequency;  // frecuencia de pago
    private float notaryCosts; //costos notariales
    private float registrationCosts; // costos de registro
    private float appraisal; // tasacion
    private float studyCommission; //comision de estudio
    private float activationCommission; // comision de activacion
    private float gps;
    private float shippingCosts; //portes
    private float administrativeExpenses; //gastos administrativos
    private float lifeInsurance; //seguro desagraven
    private float riskInsurance; // seguro de riesgo
    private float discountRate; //tasade descuento

}
