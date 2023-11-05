package com.wheelcredit.Backend.service;

import com.wheelcredit.Backend.model.SmartPayment;

import java.util.List;

public interface SmartPaymentService {

    public abstract SmartPayment createSmartPayment(Long clientId, SmartPayment smartPayment);
    public abstract SmartPayment getSmartPaymentById(Long smartPayment_id);
    public abstract SmartPayment updateSmartPayment(Long smartPayment_id, SmartPayment smartPayment);
    public abstract void deleteSmartPayment(Long smartPayment_id);
    public abstract List<SmartPayment> getAllSmartPayments();
}
