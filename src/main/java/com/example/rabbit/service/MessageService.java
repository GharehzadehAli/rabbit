package com.example.rabbit.service;

import com.example.rabbit.config.RabbitConfig;
import com.example.rabbit.dao.IMessageDao;
import com.example.rabbit.dto.Message;
import com.example.rabbit.dto.MessageIn;
import com.example.rabbit.entity.MessageEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageService {
    private final IMessageDao messageDao;
    private final RabbitTemplate template;
    private static final Logger logger = LogManager.getLogger(MessageService.class);

    public MessageService(IMessageDao messageDao, RabbitTemplate template) {
        this.messageDao = messageDao;
        this.template = template;
    }

    public void sendMessage(MessageIn messageIn) {
        logger.info("user sent a message: " + messageIn);
        Message message = new Message(messageIn);
        message.setSentAt(new Timestamp(System.currentTimeMillis()));
        template.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, message);
        logger.info("user message sent successfully: " + message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_MESSAGES)
    public void listen(Message message) {
        try {
            logger.info("rabbit attempted to listen to: " + message);
            MessageEntity messageEntity = new MessageEntity(message);
            messageDao.save(messageEntity);
            logger.info("rabbit listened to message: " + messageEntity);
        } catch (Exception e) {
            logger.info("sent " + message + " to dead letter queue");
            throw e;
        }
    }

}
