package com.training0802.demo.service;

import com.training0802.demo.dto.InvoiceResponse;
import com.training0802.demo.dto.RoomResponse;

import java.util.List;

public interface InvoiceService {
    List<InvoiceResponse> getListInvoice();

    InvoiceResponse getDetailInvoice(Long id);

    InvoiceResponse addInvoice(InvoiceResponse invoiceResponse);

    void deleteInvoice(Long id);

    InvoiceResponse updateInvoice(InvoiceResponse invoiceResponse, Long id);

}
