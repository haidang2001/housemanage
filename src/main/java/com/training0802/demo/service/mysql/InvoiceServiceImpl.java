package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.InvoiceResponse;
import com.training0802.demo.dto.TenantResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Invoice;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.model.mysql.Tenant;
import com.training0802.demo.repository.InvoiceRepository;
import com.training0802.demo.service.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile("mysql")
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    public InvoiceRepository invoiceRepository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<InvoiceResponse> getListInvoice() {
        List<Invoice> ListInvoiceEntity = invoiceRepository.findAll();
        List<InvoiceResponse> ListInvoiceDTO = new ArrayList<InvoiceResponse>();
        ListInvoiceDTO = modelMapper.map(ListInvoiceEntity, new TypeToken<List<InvoiceResponse>>() {}.getType());

        return ListInvoiceDTO;
    }

    @Override
    public InvoiceResponse getDetailInvoice(Long id) {
        Invoice modelInvoice = invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found invoice with id: " + id));
        InvoiceResponse dtoInvoice = modelMapper.map(modelInvoice, InvoiceResponse.class);

        return dtoInvoice;
    }

    @Override
    public InvoiceResponse addInvoice(InvoiceResponse invoiceResponse) {
        Invoice modelInvoice = modelMapper.map(invoiceResponse, Invoice.class);
        invoiceRepository.save(modelInvoice);
        return invoiceResponse;
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found invoice with id: " + id));
        invoiceRepository.deleteById(id);
    }

    @Override
    public InvoiceResponse updateInvoice(InvoiceResponse invoiceResponse, Long id) {
        Invoice invoiceById = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found invoice with id: " + id));

        invoiceById.setPaymentMethod(invoiceResponse.getPaymentMethod());
        invoiceById.setStatus(invoiceResponse.getStatus());
        invoiceById.setCurrentIndexElectricity(invoiceResponse.getCurrentIndexElectricity());
        invoiceById.setCurrentIndexWaterBill(invoiceResponse.getCurrentIndexWaterBill());

        Invoice save = invoiceRepository.save(invoiceById);
        invoiceResponse = modelMapper.map(save,InvoiceResponse.class);
        return invoiceResponse;
    }
}
