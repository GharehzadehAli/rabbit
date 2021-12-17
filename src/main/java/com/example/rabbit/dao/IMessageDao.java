package com.example.rabbit.dao;

import com.example.rabbit.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageDao extends CrudRepository<MessageEntity, Integer> {
}
