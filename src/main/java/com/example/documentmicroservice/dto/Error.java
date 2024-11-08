package com.example.documentmicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "Объект, представляющий информацию об ошибке")
public class Error {
    @Schema(description = "Сообщение об ошибке", example = "Error message", required = true)
    private String message;
}
