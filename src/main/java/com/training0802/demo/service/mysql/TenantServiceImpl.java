package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.TenantResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.model.mysql.Tenant;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.repository.RoomRepository;
import com.training0802.demo.repository.TenantRepository;
import com.training0802.demo.service.TenantService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("mysql")
public class TenantServiceImpl implements TenantService {
    @Autowired
    public TenantRepository tenantRepository;
    @Autowired
    public MysqlHouseRepository mysqlHouseRepository;
    @Autowired
    public RoomRepository roomRepository;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<TenantResponse> getListTenant() {
        List<Tenant> modelTenants = tenantRepository.findAll();
        List<TenantResponse> dtoTenants = new ArrayList<TenantResponse>();
        for (Tenant tenant : modelTenants) {
            TenantResponse dtoTenant = modelMapper.map(tenant, TenantResponse.class);
            dtoTenants.add(dtoTenant);
        }
        return dtoTenants;
    }

    @Override
    public TenantResponse getTenantDetail(Long id) {
        Tenant modelTenant = tenantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found tenant with id: " + id));
        TenantResponse dtoTenant = modelMapper.map(modelTenant, TenantResponse.class);
        return dtoTenant;
    }

    @Override
    public TenantResponse addTenant(TenantResponse tenantResponse) {
        Tenant modelTenant = modelMapper.map(tenantResponse, Tenant.class);
        Tenant save = tenantRepository.save(modelTenant);
        tenantResponse.setId(save.getId());
        return tenantResponse;
    }

    @Override
    public void deleteTenant(Long id) {


        tenantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found tenant with id: " + id));
        tenantRepository.deleteById(id);


    }

    @Override
    public TenantResponse updateTenant(TenantResponse tenantResponse, Long id) {
        Tenant tenantById = tenantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found tenant with id: " + id));

        tenantById.setName(tenantResponse.getName());
        tenantById.setBirthDate(tenantResponse.getBirthDate());
        tenantById.setGender(tenantResponse.getGender());
        tenantById.setPhone(tenantResponse.getPhone());
        tenantById.setEmail(tenantById.getEmail());
        tenantById.setIdNumber(tenantResponse.getIdNumber());
        tenantById.setPermanentAddress(tenantById.getPermanentAddress());
        House house = mysqlHouseRepository.findById(tenantResponse.getIdHouse()).orElseThrow(() -> new EntityNotFoundException("Not exist house with id: " + id));
        tenantById.setHouse(house);
        Room room = roomRepository.findById(tenantResponse.getIdRoom()).orElseThrow(() -> new EntityNotFoundException("Not exist room with id: " + id));
        tenantById.setRoom(room);
//        tenantById.setRoom(tenantResponse.getRoom());
        tenantById.setRentDate(tenantResponse.getRentDate());
        tenantById.setStatus(tenantResponse.getStatus());
        tenantById.setDescription(tenantResponse.getDescription());

        Tenant save = tenantRepository.save(tenantById);
        tenantResponse.setId(save.getId());
        return tenantResponse;
    }
}
