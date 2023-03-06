package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
