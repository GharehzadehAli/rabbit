package com.example.rabbit.entity;

import com.example.rabbit.dto.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MessageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "message")
    private String message;
    @Column(name = "listened_at")
    private Timestamp listenedAt;
    @Column(name = "sent_at")
    private Timestamp sentAt;

    public MessageEntity(Message message) {
        this.message = message.getMessage();
        this.listenedAt = new Timestamp(System.currentTimeMillis());
        this.sentAt = message.getSentAt();
    }
}
