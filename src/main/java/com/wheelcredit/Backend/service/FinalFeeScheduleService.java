package com.wheelcredit.Backend.service;

import com.wheelcredit.Backend.model.FinalFeeSchedule;

public interface FinalFeeScheduleService {

    public abstract FinalFeeSchedule createFinalFeeSchedule(Long SmartPaymentId,FinalFeeSchedule finalFeeSchedule);

    public abstract  FinalFeeSchedule getFinalFeeScheduleById(Long finalFeeScheduleId);

}
