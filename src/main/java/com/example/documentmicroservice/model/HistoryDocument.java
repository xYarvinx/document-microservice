package com.example.documentmicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "history_index")
public class HistoryDocument {
    @Id
    private Long id;
    private LocalDateTime date;
    private Long pacientId;
    private Long hospitalId;
    private Long doctorId;
    private String room;
    private String data;
}
