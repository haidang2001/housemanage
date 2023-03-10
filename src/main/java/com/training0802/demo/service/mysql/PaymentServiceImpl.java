package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.PaymentMethodGatewayResponse;
import com.training0802.demo.dto.PaymentMethodManualResponse;
import com.training0802.demo.model.mysql.PaymentMethodGateway;
import com.training0802.demo.model.mysql.PaymentMethodManual;
import com.training0802.demo.repository.PaymentMethodGatewayRepository;
import com.training0802.demo.repository.PaymentMethodManualRepository;
import com.training0802.demo.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("mysql")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    public PaymentMethodManualRepository paymentMethodManualRepository;
    @Autowired
    public PaymentMethodGatewayRepository paymentMethodGatewayRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<PaymentMethodManualResponse> getListPaymentMethodManual() {
        List<PaymentMethodManual> modelPaymentManuals = paymentMethodManualRepository.findAll();
        List<PaymentMethodManualResponse> dtoPaymentManuals = new ArrayList<PaymentMethodManualResponse>();
        for (PaymentMethodManual paymentMethodManual : modelPaymentManuals) {
            PaymentMethodManualResponse dtoPaymentManual = modelMapper.map(paymentMethodManual, PaymentMethodManualResponse.class);
            dtoPaymentManuals.add(dtoPaymentManual);
        }
        return dtoPaymentManuals;
    }

    @Override
    public PaymentMethodManualResponse getPaymentMethodManualDetail(Long id) {
        PaymentMethodManual modelPaymentMethodManual = paymentMethodManualRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found payment with id: " + id));
        PaymentMethodManualResponse dtoPaymentMethodManual = modelMapper.map(modelPaymentMethodManual, PaymentMethodManualResponse.class);
        return dtoPaymentMethodManual;
    }

    @Override
    public PaymentMethodManualResponse addPaymentMethodManual(PaymentMethodManualResponse paymentMethodManualResponse) {
        PaymentMethodManual modelPaymentManual = modelMapper.map(paymentMethodManualResponse, PaymentMethodManual.class);
        PaymentMethodManual save = paymentMethodManualRepository.save(modelPaymentManual);
        paymentMethodManualResponse.setId(save.getId());
        return paymentMethodManualResponse;
    }

    @Override
    public void deletePaymentMethodManual(Long id) {
        paymentMethodManualRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found payment with id: " + id));
        paymentMethodManualRepository.deleteById(id);
    }

    @Override
    public PaymentMethodManualResponse updatePaymentMethodManual(PaymentMethodManualResponse paymentMethodManualResponse, Long id) {
        PaymentMethodManual paymentMethodManual = paymentMethodManualRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found payment with id: " + id));

        paymentMethodManual.setName(paymentMethodManualResponse.getName() == null ? paymentMethodManual.getName() : paymentMethodManualResponse.getName());
        paymentMethodManual.setType(paymentMethodManualResponse.getType()== null ? paymentMethodManual.getType() : paymentMethodManualResponse.getType());
        paymentMethodManual.setStatus(paymentMethodManualResponse.getStatus()== null ? paymentMethodManual.getStatus() : paymentMethodManualResponse.getStatus());

        paymentMethodManualRepository.save(paymentMethodManual);
        paymentMethodManualResponse = modelMapper.map(paymentMethodManual,PaymentMethodManualResponse.class);
        return paymentMethodManualResponse;
    }

    @Override
    public List<PaymentMethodGatewayResponse> getListPaymentMethodGateway() {
        List<PaymentMethodGateway> modelPaymentGateways = paymentMethodGatewayRepository.findAll();
        List<PaymentMethodGatewayResponse> dtoPaymentGateways = new ArrayList<PaymentMethodGatewayResponse>();
        for (PaymentMethodGateway paymentMethodGateway : modelPaymentGateways) {
            PaymentMethodGatewayResponse dtoPaymentGateway = modelMapper.map(paymentMethodGateway, PaymentMethodGatewayResponse.class);
            dtoPaymentGateways.add(dtoPaymentGateway);
        }
        return dtoPaymentGateways;
    }

    @Override
    public PaymentMethodGatewayResponse getPaymentMethodGatewayDetail(Long id) {
        PaymentMethodGateway modelPaymentMethodGateway = paymentMethodGatewayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found payment with id: " + id));
        PaymentMethodGatewayResponse dtoPaymentMethodGateway = modelMapper.map(modelPaymentMethodGateway, PaymentMethodGatewayResponse.class);
        return dtoPaymentMethodGateway;
    }

    @Override
    public PaymentMethodGatewayResponse addPaymentMethodManual(PaymentMethodGatewayResponse paymentMethodGatewayResponse) {
        PaymentMethodGateway modelPaymentGateway = modelMapper.map(paymentMethodGatewayResponse, PaymentMethodGateway.class);
        PaymentMethodGateway save = paymentMethodGatewayRepository.save(modelPaymentGateway);
        paymentMethodGatewayResponse.setId(save.getId());
        return paymentMethodGatewayResponse;
    }

    @Override
    public void deletePaymentMethodGateway(Long id) {
        paymentMethodGatewayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found payment with id: " + id));
        paymentMethodGatewayRepository.deleteById(id);
    }

    @Override
    public PaymentMethodGatewayResponse updatePaymentMethodGateway(PaymentMethodGatewayResponse paymentMethodGatewayResponse, Long id) {
        PaymentMethodGateway paymentMethodGateway = paymentMethodGatewayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found payment with id: " + id));

        paymentMethodGateway.setName(paymentMethodGatewayResponse.getName()== null ? paymentMethodGateway.getName() : paymentMethodGatewayResponse.getName());
        paymentMethodGateway.setImage(paymentMethodGatewayResponse.getImage()== null ? paymentMethodGateway.getImage() : paymentMethodGatewayResponse.getImage());
        paymentMethodGateway.setQrcode(paymentMethodGatewayResponse.getQrcode()== null ? paymentMethodGateway.getQrcode() : paymentMethodGatewayResponse.getQrcode());
        paymentMethodGateway.setSecretKey(paymentMethodGatewayResponse.getSecretKey()== null ? paymentMethodGateway.getSecretKey() : paymentMethodGatewayResponse.getSecretKey());
        paymentMethodGateway.setTerminalID(paymentMethodGatewayResponse.getTerminalID()== null ? paymentMethodGateway.getTerminalID() : paymentMethodGatewayResponse.getTerminalID());

        paymentMethodGatewayRepository.save(paymentMethodGateway);
        paymentMethodGatewayResponse = modelMapper.map(paymentMethodGateway,PaymentMethodGatewayResponse.class);
        return paymentMethodGatewayResponse;
    }
}
