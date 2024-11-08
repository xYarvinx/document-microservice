package com.example.documentmicroservice.service;


import com.example.documentmicroservice.dto.RoleValidationRequest;
import com.example.documentmicroservice.dto.RoleValidationResponse;
import com.example.documentmicroservice.dto.TokenValidationRequest;
import com.example.documentmicroservice.dto.TokenValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;

    public TokenValidationResponse sendTokenValidationRequest(String token) {
        String correlationId = UUID.randomUUID().toString();
        TokenValidationRequest request = new TokenValidationRequest(token, correlationId);

        rabbitTemplate.convertAndSend("authExchange", "auth.request." + correlationId, request);

        Message responseMessage = rabbitTemplate.receive("authResponseQueue", 5000);
        if (responseMessage != null) {
            return (TokenValidationResponse) rabbitTemplate.getMessageConverter().fromMessage(responseMessage);
        } else {
            throw new RuntimeException("No response received within timeout period");
        }
    }

    public RoleValidationResponse sendRoleValidationRequest(String token, List<String> roles) {
        String correlationId = UUID.randomUUID().toString();
        RoleValidationRequest request = new RoleValidationRequest(token, correlationId, roles);

        rabbitTemplate.convertAndSend("roleExchange", "role.request." + correlationId, request);

        Message responseMessage = rabbitTemplate.receive("roleResponseQueue", 5000);
        if (responseMessage != null) {
            return (RoleValidationResponse) rabbitTemplate.getMessageConverter().fromMessage(responseMessage);
        } else {
            throw new RuntimeException("No response received within timeout period");
        }
    }

    public Boolean isDoctorExists(Long userId) {
            Map<String, Object> message = new HashMap<>();
            message.put("userId", userId);
            message.put("role", "DOCTOR");

            Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive(
                    "userExchange",
                    "user.exist.request",
                    message
            );

            return response != null && response;
    }

    public Boolean isPacientExists(Long userId) {
        Map<String, Object> message = new HashMap<>();
        message.put("userId", userId);
        message.put("role", "USER");

        Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive(
                "userExchange",
                "user.exist.request",
                message
        );

        return response != null && response;
    }


    public Boolean isHospitalExists(Long hospitalId) {
            Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive(
                    "hospitalExchange",
                    "hospital.exist.request",
                    hospitalId
            );

            return response != null && response;
    }

    public Boolean isRoomExists(String room, Long hospitalId) {
            Map<String, Object> message = new HashMap<>();
            message.put("room", room);
            message.put("hospitalId", hospitalId);

            Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive(
                    "roomExchange",
                    "room.exist.request",
                    message
            );

            return response != null && response;
    }

    public Long getUserIdFromToken(String token) {
        Long response = (Long) rabbitTemplate.convertSendAndReceive(
                "userExchange",
                "user.id.by.token.request",
                token.substring(7)
        );

        if (response != null) {
            return response;
        } else {
            throw new RuntimeException("Не удалось получить user ID по токену");
        }
    }
}
