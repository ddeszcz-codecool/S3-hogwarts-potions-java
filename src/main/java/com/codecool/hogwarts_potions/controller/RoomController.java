package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    RoomService roomService;


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @PostMapping
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable("id") Long id){
        return roomService.getRoomById(id);
    }

    //todo rename it to updateStudentInThisRoomByRoomIdAndStudentsIds
    @PutMapping("/{id}")
    public void updateRoomById(@PathVariable("id") Long id, @RequestBody List<Long> studentIds) {
        roomService.updateRoomById(id, studentIds);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable("id") Long id){
        roomService.deleteRoomById(id);
    }

    //Get rooms where no cat or owl lives
    @GetMapping("/rat-owners")
    public List<Room> getRoomsForRatOwners(){
        return roomService.getRoomsForRatOwners();
    }
}
