package com.testprojinc.dpcalc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculatedResultResponse {

    private String sport;
    private String result;
    private Integer points;

}
