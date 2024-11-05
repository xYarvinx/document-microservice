package com.example.documentmicroservice.controller;

import com.example.documentmicroservice.dto.HistoryResponsePart;
import com.example.documentmicroservice.exception.ControllerExceptionHandler;
import com.example.documentmicroservice.service.HistoryService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/History/")
@RequiredArgsConstructor
@ControllerExceptionHandler
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("Account/{pacientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HistoryResponsePart> getHistoryByPacientId(
            @PathVariable Long pacientId,

            @Parameter(hidden = true)
            @RequestHeader("Authorization") String token
    ) {
        return  historyService.getHistoryByPacientId(pacientId, token);
    }

}
