package com.wheelcredit.Backend.service.impl;

import com.wheelcredit.Backend.model.FinalFeeSchedule;
import com.wheelcredit.Backend.repository.FinalFeeScheduleRepository;
import com.wheelcredit.Backend.service.FinalFeeScheduleService;
import com.wheelcredit.Backend.service.SmartPaymentBCPService;

public class FinalFeeScheduleServiceImpl implements FinalFeeScheduleService {

    SmartPaymentBCPService smartPaymentBCPService;
    FinalFeeScheduleRepository finalFeeScheduleRepository;

    public FinalFeeScheduleServiceImpl(
            SmartPaymentBCPService smartPaymentBCPService,
        FinalFeeScheduleRepository finalFeeScheduleRepository
    ){
        this.finalFeeScheduleRepository = finalFeeScheduleRepository;
        this.smartPaymentBCPService = smartPaymentBCPService;
    }

    @Override
    public FinalFeeSchedule createFinalFeeSchedule(Long SmartPaymentId, FinalFeeSchedule finalFeeSchedule) {

        finalFeeSchedule.setSmartPaymentBCP(smartPaymentBCPService.getSmartPaymentBCPById(SmartPaymentId));

        return finalFeeScheduleRepository.save(finalFeeSchedule);
    }

    @Override
    public FinalFeeSchedule getFinalFeeScheduleById(Long finalFeeScheduleId) {
        return finalFeeScheduleRepository.findById(finalFeeScheduleId).orElse(null);
    }
}
