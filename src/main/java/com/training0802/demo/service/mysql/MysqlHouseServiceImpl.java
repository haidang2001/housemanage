package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.service.HouseService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Profile("mysql")
public class MysqlHouseServiceImpl implements HouseService {
    @Autowired
    public MysqlHouseRepository mysqlHouseRepository;
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public ModelMapper modelMapper;
    private final String FOLDER_PATH="D:\\Download\\DangNguyen (1)\\TrainingProj\\TrainingProj\\nchdang\\images\\";
    public Page<House> getHousesPage(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<House> houses = mysqlHouseRepository.findAll(pageable);
//        List<HouseResponse> dtoHouses = new ArrayList<HouseResponse>();
//        dtoHouses = modelMapper.map(houses, new TypeToken<List<HouseResponse>>() {}.getType());
//        if(houses.hasContent()) {
//            List<House> content = houses.getContent();
//            List<HouseResponse> dtoHouses = new ArrayList<HouseResponse>();
//            dtoHouses = modelMapper.map(houses, new TypeToken<List<HouseResponse>>() {}.getType());
//            return dtoHouses;
//        } else {
//            return new ArrayList<HouseResponse>();
//        }
        return houses;
    }
    @Override
    public List<HouseResponse> getHouses() {
        List<House> mysqlModelHouses = mysqlHouseRepository.findAll();
        List<HouseResponse> dtoHouses = new ArrayList<HouseResponse>();
//        for (House house: mysqlModelHouses){
//            HouseResponse dtoHouse = new HouseResponse();
//            dtoHouse.setId(house.getId());
//            dtoHouse.setName(house.getName());
//            dtoHouse.setEstablishDate(house.getEstablishDate());
//            dtoHouse.setTotalRooms(house.getTotalRooms());
//            AccountResponse accountResponse = modelMapper.map(house.getManager(),AccountResponse.class);
//            dtoHouse.setManager(accountResponse);
//            dtoHouse.setImage(house.getImage());
//            dtoHouse.setStatus(house.getStatus());
//            dtoHouse.setDescription(house.getDescription());
////            HouseResponse dtoHouse = modelMapper.map(house,HouseResponse.class);
//            dtoHouses.add(dtoHouse);
//        }
        dtoHouses = modelMapper.map(mysqlModelHouses, new TypeToken<List<HouseResponse>>() {}.getType());
        return dtoHouses;
    }

    @Override
    public HouseResponse getHouseDetail(Long id) {
        House modelHouse = mysqlHouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found this house with id: " + id));
        HouseResponse dtoHouse = modelMapper.map(modelHouse, HouseResponse.class);
//        dtoHouse.setManagerName(modelHouse.getManager().getName());
        return dtoHouse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HouseResponse addHouse( HouseResponse houseResponse,MultipartFile image) throws IOException {
        House modelHouse = modelMapper.map(houseResponse, House.class);
        House byName = mysqlHouseRepository.findByName(modelHouse.getName());
        if(Objects.isNull(byName)){

            House save = mysqlHouseRepository.save(modelHouse);

            String filePath = FOLDER_PATH + image.getOriginalFilename();
            save.setImage(filePath);
            image.transferTo(new File(filePath));


//            Account accountByName = accountRepository.findByName(houseResponse.getManager());
//            accountByName.setHouse(save);
//            accountRepository.save(accountByName);
            houseResponse.setId(save.getId());
            houseResponse.setImage(filePath);
            // save image file to directory or cloud storage
//            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//            Path path = Paths.get("uploads");
//            Files.createDirectories(path);
//            try (InputStream inputStream = image.getInputStream()) {
//                Files.copy(inputStream, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException e) {
//                throw new IOException("Failed to save file " + fileName, e);
//            }

            return houseResponse;
        }else{
            throw new RuntimeException("This name house existed please another name");
        }
//        houseResponse.setManagerName(save.getManager().getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHouse(Long id) {
        House house = mysqlHouseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found house with id: " + id));
        Account byHouse = accountRepository.findByHouse(house);
        if(!Objects.isNull(byHouse)){
            byHouse.setHouse(null);
        }

        mysqlHouseRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HouseResponse updateHouse(HouseResponse houseResponse, Long id) {
        House houseById = mysqlHouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found house with id: " + id));

        houseById.setName(houseResponse.getName());
        houseById.setLocation(houseResponse.getLocation());
        houseById.setEstablishDate(houseResponse.getEstablishDate());
        houseById.setTotalRooms(houseResponse.getTotalRooms());

//        Account ManagerFoundByName = accountRepository.findByName(houseResponse.getManager());
//        houseById.setManager(ManagerFoundByName);
//        Account managerEntity = modelMapper.map(houseResponse.getManager(),Account.class);
//        houseById.setManager(managerEntity);
        houseById.setManager(houseResponse.getManager());
        houseById.setImage(houseResponse.getImage());
        houseById.setStatus(houseResponse.getStatus());
        houseById.setDescription(houseResponse.getDescription());
//        houseById.setRentalFeeHouseList(houseResponse.getRentalFeeHouseList());
//        houseById.setRoomList(houseResponse.getRoomList());

        House save = mysqlHouseRepository.save(houseById);
        houseResponse.setId(save.getId());
//        houseResponse.setManagerName(save.getManager().getName());
        return houseResponse;
    }

    public byte[] loadImage(Long id) throws IOException{
        Optional<House> byId = mysqlHouseRepository.findById(id);
        String image = byId.get().getImage();

//        String filePath = fileData.get().getImage();
        byte[] images = Files.readAllBytes(Paths.get(image));
        return images;
    }

}
