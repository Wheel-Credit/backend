package com.wheelcredit.Backend.controller;

import com.wheelcredit.Backend.dto.SmartPaymentDto;
import com.wheelcredit.Backend.model.Client;
import com.wheelcredit.Backend.model.SmartPayment;
import com.wheelcredit.Backend.model.SmartPaymentBCP;
import com.wheelcredit.Backend.service.ClientService;
import com.wheelcredit.Backend.service.FinalFeeScheduleService;
import com.wheelcredit.Backend.service.SmartPaymentBCPService;
import com.wheelcredit.Backend.service.SmartPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/wheel-credit/v1/smartPayment")
public class SmartPaymentController {

    private final SmartPaymentService smartPaymentService;
    private final SmartPaymentBCPService smartPaymentBCPService;
    private final FinalFeeScheduleService finalFeeScheduleService;
    private final ModelMapper modelMapper;

    public SmartPaymentController(SmartPaymentService smartPaymentService,
                                  SmartPaymentBCPService smartPaymentBCPService,
                                  FinalFeeScheduleService finalFeeScheduleService,
                                  ModelMapper modelMapper)
    {
        this.smartPaymentService = smartPaymentService;
        this.smartPaymentBCPService = smartPaymentBCPService;
        this.finalFeeScheduleService = finalFeeScheduleService;
        this.modelMapper = modelMapper;

    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment
    // Method: POST

    @Transactional
    @PostMapping("/{clientId}/create")
    public ResponseEntity<SmartPaymentBCP> createSmartPayment(@PathVariable(name = "clientId") Long clientId, @RequestBody SmartPayment smartPaymentDto)
    {
        SmartPayment smartPaymentCreated = smartPaymentService.createSmartPayment(clientId,smartPaymentDto);

        SmartPaymentBCP smartPaymentBCP = null;
        SmartPaymentBCP smartPaymentBCPCreated = smartPaymentBCPService.createSmartPaymentBCP(clientId,smartPaymentBCP);

        return new ResponseEntity<SmartPaymentBCP>(smartPaymentBCPCreated, HttpStatus.CREATED);
    }

}
