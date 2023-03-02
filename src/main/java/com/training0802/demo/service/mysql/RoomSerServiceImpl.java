package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.RoomSerResponse;
import com.training0802.demo.model.mysql.RoomSer;
import com.training0802.demo.repository.RoomSerRepository;
import com.training0802.demo.service.RoomSerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile("mysql")
public class RoomSerServiceImpl implements RoomSerService {
    @Autowired
    public RoomSerRepository roomSerRespository;
    @Autowired
    public ModelMapper modelMapper;
    @Override
    public List<RoomSerResponse> getListRoomService() {
        List<RoomSer> modelRoomSerList = roomSerRespository.findAll();
        List<RoomSerResponse> dtoRoomServiceList = new ArrayList<RoomSerResponse>();
        for (RoomSer roomSer : modelRoomSerList){
            RoomSerResponse roomSerResponse = modelMapper.map(roomSer, RoomSerResponse.class);
            dtoRoomServiceList.add(roomSerResponse);
        }
        return dtoRoomServiceList;
    }

    @Override
    public RoomSerResponse getDetailRoomService(Long id) {
        RoomSer roomSer = roomSerRespository.findById(id).orElseThrow(() -> new RuntimeException("Not found room ser with id: " + id));
        RoomSerResponse dtoRoomService = modelMapper.map(roomSer, RoomSerResponse.class);
        return dtoRoomService;
    }

    @Override
    public void addRoomService(RoomSerResponse roomSerResponse) {
        RoomSer modelRoomSer = modelMapper.map(roomSerResponse,RoomSer.class);
        roomSerRespository.save(modelRoomSer);
    }

    @Override
    public void deleteRoomService(Long id) {
        roomSerRespository.deleteById(id);
    }

    @Override
    public RoomSerResponse updateRoomService(RoomSerResponse RoomSerResponse, Long id) {
        RoomSer roomSerById = roomSerRespository.findById(id).orElseThrow(() -> new RuntimeException("Not found room ser with this id"));

        roomSerById.setId(id);
        roomSerById.setName(RoomSerResponse.getName());
        roomSerById.setRoom(roomSerById.getRoom());

        roomSerRespository.save(roomSerById);
        return RoomSerResponse;
    }
}
