package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.InvoiceResponse;
import com.training0802.demo.service.InvoiceService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Profile("mysql")
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public List<InvoiceResponse> getListInvoice() {
        return null;
    }

    @Override
    public InvoiceResponse getDetailInvoice(Long id) {
        return null;
    }

    @Override
    public InvoiceResponse addInvoice(InvoiceResponse invoiceResponse) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }

    @Override
    public InvoiceResponse updateInvoice(InvoiceResponse invoiceResponse, Long id) {
        return null;
    }
}
