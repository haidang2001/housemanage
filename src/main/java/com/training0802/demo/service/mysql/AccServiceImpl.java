package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.AccResponse;
import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.model.mysql.Acc;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.repository.AccRepository;
import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.service.AccService;
import com.training0802.demo.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("mysql")
public class AccServiceImpl implements AccService {
    @Autowired
    public AccRepository accRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<AccResponse> getAcc() {
        accRepository.findAll();
        List<Acc> rawAccountList = accRepository.findAll();
        List<AccResponse> accountList = new ArrayList<AccResponse>();
        for (Acc acc : rawAccountList) {
            AccResponse dto = modelMapper.map(acc, AccResponse.class);
            accountList.add(dto);
        }
        return accountList;
    }

    @Override
    public AccResponse getOneAcc(Long id) {
        Acc modelAcc = accRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found acc with this id: " + id));
        AccResponse dtoAcc = modelMapper.map(modelAcc, AccResponse.class);

        return dtoAcc;
    }

    @Override
    public AccResponse addAcc(AccResponse accResponse) {
        if(accRepository.existsByUsername(accResponse.getUsername())){
            throw new RuntimeException("Username already exists!");
        }
        if(!accResponse.getPassword().equals(accResponse.getRepassword())){
            throw new RuntimeException("Password and Repassword not same");
        }
        accResponse.setPassword(passwordEncoder.encode(accResponse.getPassword()));
        accResponse.setRepassword(passwordEncoder.encode(accResponse.getRepassword()));

        Acc modelAccount = new Acc();
        modelAccount.setRole(accResponse.getRole());
        modelAccount.setUsername(accResponse.getUsername());
        modelAccount.setPassword(accResponse.getPassword());
        modelAccount.setRepassword(accResponse.getRepassword());

        Acc save = accRepository.save(modelAccount);
        accResponse.setId(save.getId());
        return accResponse;
    }




}
