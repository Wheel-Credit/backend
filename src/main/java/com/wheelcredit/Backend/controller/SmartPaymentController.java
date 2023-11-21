package com.wheelcredit.Backend.controller;

import com.wheelcredit.Backend.dto.SmartPaymentRequestDto;
import com.wheelcredit.Backend.dto.SmartPaymentResponseDto;
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
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment/{clientId}/list
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{clientId}/list")
    public ResponseEntity<List<SmartPaymentResponseDto>> getSmartPaymentByClientId(
            @PathVariable(name = "clientId") Long clientId) {
        List<SmartPayment> smartPayments = smartPaymentService.getSmartPaymentByClientId(clientId);
        List<SmartPaymentResponseDto> smartPaymentDtos = new ArrayList<>();
        for (SmartPayment smartPayment : smartPayments) {
            smartPaymentDtos.add(modelMapper.map(smartPayment, SmartPaymentResponseDto.class));
        }
        return new ResponseEntity<List<SmartPaymentResponseDto>>(new ArrayList<>(smartPaymentDtos), HttpStatus.OK);
    }

    // URL:
    // http://localhost:8090/api/wheel-credit/v1/smartPayment/{smartPaymentId}/update
    // Method: PUT
    @Transactional()
    @PutMapping("/{smartPaymentId}/update")
    public ResponseEntity<SmartPaymentResponseDto> updateSmartPayment(
            @PathVariable(name = "smartPaymentId") Long smartPaymentId,
            @RequestBody SmartPaymentRequestDto smartPayment) {
        SmartPayment smartPaymentUpdated = smartPaymentService.updateSmartPayment(smartPaymentId,
                modelMapper.map(smartPayment, SmartPayment.class));
        SmartPaymentResponseDto smartPaymentDto = modelMapper.map(smartPaymentUpdated, SmartPaymentResponseDto.class);
        return new ResponseEntity<SmartPaymentResponseDto>(smartPaymentDto, HttpStatus.OK);
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment/{clientId}/create
    // Method: POST
    @Transactional()
    @PostMapping("/{clientId}/create")
    public ResponseEntity<SmartPaymentResponseDto> createSmartPayment(@PathVariable(name = "clientId") Long clientId,
            @RequestBody SmartPaymentRequestDto smartPayment) {
        SmartPayment smartPaymentCreated = smartPaymentService.createSmartPayment(clientId,
                modelMapper.map(smartPayment, SmartPayment.class));
        SmartPaymentResponseDto smartPaymentDto = modelMapper.map(smartPaymentCreated, SmartPaymentResponseDto.class);
        return new ResponseEntity<SmartPaymentResponseDto>(smartPaymentDto, HttpStatus.CREATED);
    }
}
