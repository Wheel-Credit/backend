package com.wheelcredit.Backend.service.impl;

import com.wheelcredit.Backend.exception.ResourceNotFoundException;
import com.wheelcredit.Backend.exception.ValidationException;
import com.wheelcredit.Backend.model.Client;
import com.wheelcredit.Backend.model.SmartPayment;
import com.wheelcredit.Backend.repository.SmartPaymentRepository;
import com.wheelcredit.Backend.service.ClientService;
import com.wheelcredit.Backend.service.SmartPaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartPaymentServiceImpl implements SmartPaymentService {

    SmartPaymentRepository smartPaymentRepository;
    ClientService clientService;

    public SmartPaymentServiceImpl(
            SmartPaymentRepository smartPaymentRepository,
            ClientService clientService) {
        this.clientService = clientService;
        this.smartPaymentRepository = smartPaymentRepository;
    }

    @Override
    public SmartPayment createSmartPayment(Long clientId, SmartPayment smartPayment) {
        existsClientByClientId(clientId);
        smartPayment.setClient(clientService.findById(clientId));
        validateSmartPayment(smartPayment);
        return smartPaymentRepository.save(smartPayment);
    }

    @Override
    public SmartPayment getSmartPaymentById(Long smartPayment_id) {
        existsSmartPaymentBySmartPaymentId(smartPayment_id);
        return smartPaymentRepository.findById(smartPayment_id).orElse(null);
    }

    @Override
    public SmartPayment updateSmartPayment(Long smartPayment_id, SmartPayment smartPayment) {
        existsSmartPaymentBySmartPaymentId(smartPayment_id);
        smartPayment.setId(smartPayment_id);
        smartPayment.setClient(getSmartPaymentById(smartPayment_id).getClient());
        validateSmartPayment(smartPayment);
        return smartPaymentRepository.save(smartPayment);
    }

    @Override
    public void deleteSmartPayment(Long smartPayment_id) {
        existsSmartPaymentBySmartPaymentId(smartPayment_id);
        smartPaymentRepository.deleteById(smartPayment_id);
    }

    @Override
    public List<SmartPayment> getAllSmartPayments() {
        return smartPaymentRepository.findAll();
    }

    @Override
    public List<SmartPayment> getSmartPaymentByClientId(Long clientId) {
        existsClientByClientId(clientId);
        return smartPaymentRepository.findByClientId(clientId);
    }

    private void existsClientByClientId(Long clientId) {
        Client client = clientService.findById(clientId);
        if (client == null) {
            throw new ResourceNotFoundException("No existe cliente con el id: " + clientId);
        }
    }

    private void existsSmartPaymentBySmartPaymentId(Long smartPaymentId) {
        if (!smartPaymentRepository.existsById(smartPaymentId)) {
            throw new ResourceNotFoundException("No existe el pago inteligente con el id" + smartPaymentId);
        }
    }

    private void validateSmartPayment(SmartPayment smartPayment) {
        if (smartPayment.getSellingPriceAsset() == 0) {
            throw new ValidationException("El precio de venta activo no puede ser nulo");
        }
    }

}
