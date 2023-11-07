package com.wheelcredit.Backend.controller;

import com.wheelcredit.Backend.model.Client;
import com.wheelcredit.Backend.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/wheel-credit/v1/smartPayment")
public class SmartPaymentController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;

    public SmartPaymentController(ClientService clientService, ModelMapper modelMapper)
    {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    // URL: http://localhost:8090/api/wheel-credit/v1/smartPayment
    // Method: POST


}
