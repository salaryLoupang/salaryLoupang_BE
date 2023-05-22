package com.project.loupang.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HealthCheckController {

    @Operation(summary = "서버가 켜저있는지 확인하는 API", description = "켜져 있을 경우 1 return", tags = { "HealthCheckController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/v1/api/health")
    public String healthCheck(){
        return "1";
    }
}
