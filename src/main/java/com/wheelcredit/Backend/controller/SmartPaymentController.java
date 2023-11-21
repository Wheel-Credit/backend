package com.wheelcredit.Backend.controller;
import com.wheelcredit.Backend.model.SmartPayment;

import com.wheelcredit.Backend.service.SmartPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/wheel-credit/v1/smartPayment")
public class SmartPaymentController {

    private final SmartPaymentService smartPaymentService;
    private final ModelMapper modelMapper;

    public SmartPaymentController(SmartPaymentService smartPaymentService,
                                  ModelMapper modelMapper)
    {
        this.smartPaymentService = smartPaymentService;
        this.modelMapper = modelMapper;
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment/{clientId}/create
    // Method: POST

    @Transactional(readOnly = true)
    @PostMapping("/{clientId}/create")
    public ResponseEntity<SmartPayment> createSmartPayment(@PathVariable(name = "clientId") Long clientId, @RequestBody SmartPayment smartPayment)
    {
        SmartPayment smartPaymentCreated = smartPaymentService.createSmartPayment(clientId,smartPayment);

        return new ResponseEntity<SmartPayment>(smartPaymentCreated, HttpStatus.CREATED);
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<SmartPayment>> getAllBicycles() {
        //print somethign
        List<SmartPayment> bicycles = smartPaymentService.getAllSmartPayments();
        System.out.println("getAllBicycles");
        return new ResponseEntity<List<SmartPayment>>(new ArrayList<>(bicycles), HttpStatus.OK);
    }


}
