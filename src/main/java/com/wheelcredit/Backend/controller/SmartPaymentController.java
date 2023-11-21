package com.wheelcredit.Backend.controller;

import com.wheelcredit.Backend.dto.SmartPaymentDto;
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

    public SmartPaymentController(
            SmartPaymentService smartPaymentService,
            ModelMapper modelMapper) {
        this.smartPaymentService = smartPaymentService;
        this.modelMapper = modelMapper;
        // modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<SmartPayment>> getAllBicycles() {
        // print somethign
        List<SmartPayment> bicycles = smartPaymentService.getAllSmartPayments();
        System.out.println("getAllBicycles");
        return new ResponseEntity<List<SmartPayment>>(new ArrayList<>(bicycles), HttpStatus.OK);
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment/{clientId}/list
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{clientId}/list")
    public ResponseEntity<List<SmartPaymentDto>> getSmartPaymentByClientId(
            @PathVariable(name = "clientId") Long clientId) {
        List<SmartPayment> smartPayments = smartPaymentService.getSmartPaymentByClientId(clientId);
        List<SmartPaymentDto> smartPaymentDtos = new ArrayList<>();
        for (SmartPayment smartPayment : smartPayments) {
            smartPaymentDtos.add(modelMapper.map(smartPayment, SmartPaymentDto.class));
        }
        return new ResponseEntity<List<SmartPaymentDto>>(new ArrayList<>(smartPaymentDtos), HttpStatus.OK);
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment/{clientId}/create
    // Method: POST
    @Transactional()
    @PostMapping("/{clientId}/create")
    public ResponseEntity<SmartPaymentDto> createSmartPayment(@PathVariable(name = "clientId") Long clientId,
            @RequestBody SmartPayment smartPayment) {
        SmartPayment smartPaymentCreated = smartPaymentService.createSmartPayment(clientId, smartPayment);
        SmartPaymentDto smartPaymentDto = modelMapper.map(smartPaymentCreated, SmartPaymentDto.class);
        return new ResponseEntity<SmartPaymentDto>(smartPaymentDto, HttpStatus.CREATED);
    }

}
