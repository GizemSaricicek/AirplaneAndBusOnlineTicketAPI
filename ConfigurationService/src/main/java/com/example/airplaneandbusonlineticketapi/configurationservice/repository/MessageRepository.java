package com.example.airplaneandbusonlineticketapi.configurationservice.repository;

import com.example.airplaneandbusonlineticketapi.configurationservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
