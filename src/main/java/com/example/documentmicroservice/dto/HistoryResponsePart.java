package com.example.documentmicroservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoryResponsePart {
    LocalDateTime date;
    Long pacientId;
    Long hospitalId;
}