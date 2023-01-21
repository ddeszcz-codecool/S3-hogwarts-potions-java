package com.codecool.hogwarts_potions.repository;

import com.codecool.hogwarts_potions.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query
            ("select r from Room  r JOIN r.residents s on s.petType <> com.codecool.hogwarts_potions.model.PetType.CAT and s.petType <> com.codecool.hogwarts_potions.model.PetType.OWL")
    List<Room> findRatFriendlyRoom();


}
