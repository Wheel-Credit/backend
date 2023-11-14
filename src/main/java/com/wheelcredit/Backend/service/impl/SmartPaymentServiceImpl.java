package com.wheelcredit.Backend.service.impl;

import com.wheelcredit.Backend.exception.ResourceNotFoundException;
import com.wheelcredit.Backend.exception.ValidationException;
import com.wheelcredit.Backend.model.Client;
import com.wheelcredit.Backend.model.FinalFeeSchedule;
import com.wheelcredit.Backend.model.SmartPayment;
import com.wheelcredit.Backend.model.SmartPaymentBCP;
import com.wheelcredit.Backend.repository.SmartPaymentRepository;
import com.wheelcredit.Backend.service.ClientService;
import com.wheelcredit.Backend.service.SmartPaymentService;

import java.util.List;

public class SmartPaymentServiceImpl implements SmartPaymentService {

    SmartPaymentRepository smartPaymentRepository;
    ClientService clientService;

    public SmartPaymentServiceImpl(
            SmartPaymentRepository smartPaymentRepository,
            ClientService clientService
    ){
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
    public FinalFeeSchedule signPaymentBCP(SmartPayment smartPayment){

        FinalFeeSchedule finalFeeSchedule = null;


        return finalFeeSchedule;
    }

    private FinalFeeSchedule getAllValues(SmartPayment smartPayment){

        FinalFeeSchedule finalFeeSchedule = null;

        if(smartPayment.getPaymentPlanType() == 24)
        {
            smartPayment.setFinalFee(0.5); //% de cuota final
            smartPayment.setNumberOfYears(2);// numero de años
        }else{
            smartPayment.setFinalFee(0.4);
            smartPayment.setNumberOfYears(3);
        }

        ////////
        double TEA;
        if(smartPayment.getInterestType().equals("TNA")){

            if(smartPayment.getCapitalization().equals("Diaria")){
                TEA = Math.pow(1+smartPayment.getInterestRate()/(360/1),360/1)-1;
            } else if (smartPayment.getCapitalization().equals("Mensual")) {
                TEA = Math.pow(1+smartPayment.getInterestRate()/(360/1),360/1)-1;
            }else {
                throw new ValidationException("El tipo de capitalización debe ser Diaria o Mensual");
            }
        }else{
            TEA = smartPayment.getInterestRate();
        }

        double TEM = Math.pow(1+TEA,smartPayment.getPaymentFrequency()/360)-1;
        double totalFinalFee = smartPayment.getInitialInstallment()*smartPayment.getFinalFee();

        for(int i = 1;i<=smartPayment.getPaymentPlanType()+1;i++ )//de 1 a 36 en Plan 36
        {
            double initialBalance = 0;

            if(i==1)
            {
                initialBalance = (totalFinalFee/
                        Math.pow(1+TEM+smartPayment.getLifeInsurance(),smartPayment.getPaymentPlanType()+1));

                finalFeeSchedule.addInitialBalance(initialBalance);//adList
                
            }

            double finalInstallmentInterest = initialBalance*TEM;

            finalFeeSchedule.addFinalInstallmentAmortization(initialBalance);//adList

            double  finalInstallmentInsurance = initialBalance* smartPayment.getLifeInsurance();

            finalFeeSchedule.addFinalInstallmentInsurance(finalInstallmentInsurance);

        }







        return finalFeeSchedule;
    }

    private void existsClientByClientId(Long clientId) {
        Client client = clientService.findById(clientId);
        if (client == null) {
            throw new ResourceNotFoundException("No existe cliente con el id: " + clientId);
        }
    }

    private void existsSmartPaymentBySmartPaymentId(Long smartPaymentId)
    {
        if(!smartPaymentRepository.existsById(smartPaymentId)){
            throw new ResourceNotFoundException("No existe el pago inteligente con el id"+ smartPaymentId);
        }
    }

    private void validateSmartPayment(SmartPayment smartPayment){
        if(smartPayment.getSellingPriceAsset() == 0){
            throw new ValidationException("El precio de venta activo no puede ser nulo");
        }
    }

}
