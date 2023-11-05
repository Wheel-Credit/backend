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
@Table(name = "final_fee_schedule")
public class FinalFeeSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "initial_balance", nullable = false)
    private float initialBalance;

    @Column(name = "final_installment_interest", nullable = false)
    private float finalInstallmentInterest;

    @Column(name = "final_installment_amortization", nullable = false)
    private float finalInstallmentAmortization;

    @Column(name = "final_installment_insurance", nullable = false)
    private float finalInstallmentInsurance;

    @Column(name = "final_balance", nullable = false)
    private float finalBalance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smartPaymentBCP_id", referencedColumnName = "id")
    private SmartPaymentBCP smartPaymentBCP;

}
