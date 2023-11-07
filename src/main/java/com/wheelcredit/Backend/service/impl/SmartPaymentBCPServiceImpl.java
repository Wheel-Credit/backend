package com.wheelcredit.Backend.service.impl;

import com.wheelcredit.Backend.model.SmartPaymentBCP;
import com.wheelcredit.Backend.repository.SmartPaymentBCPRepository;
import com.wheelcredit.Backend.repository.SmartPaymentRepository;
import com.wheelcredit.Backend.service.ClientService;
import com.wheelcredit.Backend.service.SmartPaymentBCPService;


public class SmartPaymentBCPServiceImpl implements SmartPaymentBCPService {

    SmartPaymentBCPRepository smartPaymentBCPRepository;
    ClientService clientService;

    public SmartPaymentBCPServiceImpl(
            SmartPaymentBCPRepository smartPaymentBCPRepository,
            ClientService clientService
    ){
        this.clientService = clientService;
        this.smartPaymentBCPRepository = smartPaymentBCPRepository;
    }

    @Override
    public SmartPaymentBCP createSmartPaymentBCP(Long clientId, SmartPaymentBCP smartPaymentBCP) {

        smartPaymentBCP.setClient(clientService.findById(clientId));
        return smartPaymentBCPRepository.save(smartPaymentBCP);
    }

    @Override
    public SmartPaymentBCP getSmartPaymentBCPById(Long smartPaymentBCP_id) {
        return smartPaymentBCPRepository.findById(smartPaymentBCP_id).orElse(null);
    }
}
