package com.training0802.demo.service;

import com.training0802.demo.dto.PaymentMethodGatewayResponse;
import com.training0802.demo.dto.PaymentMethodManualResponse;

import java.util.List;

public interface PaymentService {

    List<PaymentMethodManualResponse> getListPaymentMethodManual();

    PaymentMethodManualResponse getPaymentMethodManualDetail(Long id);

    PaymentMethodManualResponse addPaymentMethodManual(PaymentMethodManualResponse paymentMethodManualResponse);

    void deletePaymentMethodManual(Long id);

    PaymentMethodManualResponse updatePaymentMethodManual(PaymentMethodManualResponse paymentMethodManualResponse, Long id);


    List<PaymentMethodGatewayResponse> getListPaymentMethodGateway();

    PaymentMethodGatewayResponse getPaymentMethodGatewayDetail(Long id);

    PaymentMethodGatewayResponse addPaymentMethodManual(PaymentMethodGatewayResponse paymentMethodGatewayResponse);

    void deletePaymentMethodGateway(Long id);

    PaymentMethodGatewayResponse updatePaymentMethodGateway(PaymentMethodGatewayResponse paymentMethodGatewayResponse, Long id);

}
