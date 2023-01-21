package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.PetType;
import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final StudentService studentService;

    public RoomService(RoomRepository roomRepository, StudentService studentService) {
        this.roomRepository = roomRepository;
        this.studentService = studentService;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void addRoom(Room room) {
        roomRepository.saveAndFlush(room);
    }

    public Room getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        System.out.println(room.orElse(null));
        return room.isPresent() ? room.get() : null;
    }

    public void updateRoomById(Long id, List<Long> studentIds) {
        Room room = getRoomById(id);
        Set<Student> students = new HashSet<>();
        studentIds.stream().forEach((studId) -> {
            students.add(studentService.getStudentById(studId));
        });
        room.setResidents(students);
        roomRepository.saveAndFlush(room);

    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }


    public List<Room> getRoomsForRatOwners() {
       /* List<Room> rooms = roomRepository.findAll();


        List<Room> ratOwnersRoom = rooms.stream().filter(room -> roomSafeForRats(room)).collect(Collectors.toList());*/


        return roomRepository.findRatFriendlyRoom();
    }

    private boolean roomSafeForRats(Room room) {
        // boolean safe = true;
        for (Student student : room.getResidents()) {
            if (student.getPetType() == PetType.CAT || student.getPetType() == PetType.OWL) {
                return false;

            }
        }
        return true;
    }
}
