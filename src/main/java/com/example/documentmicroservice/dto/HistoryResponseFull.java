package com.example.documentmicroservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryResponseFull {
    private LocalDateTime date;
    private Long pacientId;
    private Long hospitalId;
    private Long doctorId;
    private String room;
    private String data;
}
