package com.example.rabbit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Message extends MessageIn {
    private Timestamp sentAt;

    public Message(MessageIn message) {
        setMessage(message.getMessage());
    }

    @Override
    public String toString() {
        return "Message{" +
                "message=" + getMessage() +
                ",sentAt=" + sentAt +
                '}';
    }
}
