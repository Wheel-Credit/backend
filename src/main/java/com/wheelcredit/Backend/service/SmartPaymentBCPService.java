package com.wheelcredit.Backend.service;

import com.wheelcredit.Backend.model.SmartPaymentBCP;

public interface SmartPaymentBCPService {

    public abstract  SmartPaymentBCP createSmartPaymentBCP(Long clientId, SmartPaymentBCP smartPaymentBCP);

    public abstract SmartPaymentBCP getSmartPaymentBCPById(Long smartPaymentBCP_id);


}
