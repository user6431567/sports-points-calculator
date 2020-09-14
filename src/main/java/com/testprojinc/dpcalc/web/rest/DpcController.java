package com.testprojinc.dpcalc.web.rest;

import com.testprojinc.dpcalc.dto.CalculatedResultResponse;
import com.testprojinc.dpcalc.service.DpcService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DpcController {

    private final DpcService dpcService;

    @GetMapping(value = "/sport/list", produces = "application/json")
    public List<String> getSportsList() {
        return dpcService.getSportsList();
    }

    @GetMapping(value = "/sport/{sport}/result/{result}", produces = "application/json")
    public CalculatedResultResponse calculatePoints(@PathVariable String sport, @PathVariable String result) {
        return dpcService.calculatePoints(sport, result);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetail> handleRuntimeException(RuntimeException rte) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDetail(rte.getClass().getSimpleName(), rte.getMessage()));
    }

    @Data
    private static class ErrorDetail {
        private long timestamp = System.currentTimeMillis();
        private String errorType;
        private String errorMessage;

        public ErrorDetail(String errorType, String errorMessage) {
            this.errorType = errorType;
            this.errorMessage = errorMessage;
        }
    }

}
