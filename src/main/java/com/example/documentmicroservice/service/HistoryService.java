package com.example.documentmicroservice.service;

import com.example.documentmicroservice.dto.HistoryResponsePart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    public List<HistoryResponsePart> getHistoryByPacientId(Long pacientId, String token) {
        return null;
    }
}
