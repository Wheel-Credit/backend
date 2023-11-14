package com.wheelcredit.Backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private List<Double> initialBalance;

    @Column(name = "final_installment_interest", nullable = false)
    private List<Double> finalInstallmentInterest;

    @Column(name = "final_installment_amortization", nullable = false)
    private List<Double> finalInstallmentAmortization;

    @Column(name = "final_installment_insurance", nullable = false)
    private List<Double> finalInstallmentInsurance;

    @Column(name = "final_balance", nullable = false)
    private List<Double> finalBalance;

    @OneToOne(mappedBy = "finalFeeSchedule") // Use the property name in FinalFeeSchedule
    private SmartPaymentBCP smartPaymentBCP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", referencedColumnName = "id")
    public Client client;

    public void addInitialBalance(Double balance) {
        if (initialBalance == null) {
            initialBalance = new ArrayList<>();
        }
        initialBalance.add(balance);
    }

    public void addFinalInstallmentAmortization(Double balance) {
        if (finalInstallmentInterest == null) {
            finalInstallmentInterest = new ArrayList<>();
        }
        finalInstallmentInterest.add(balance);
    }

    public void addFinalInstallmentInsurance(Double balance) {
        if (finalInstallmentInsurance == null) {
            finalInstallmentInsurance = new ArrayList<>();
        }
        finalInstallmentInsurance.add(balance);
    }
}
