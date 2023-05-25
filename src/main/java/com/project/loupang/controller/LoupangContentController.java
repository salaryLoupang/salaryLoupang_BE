package com.project.loupang.controller;

import com.project.loupang.request.MenuRecommendRequest;
import com.project.loupang.response.MenuRecommendResponse;
import com.project.loupang.service.MenuRecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoupangContentController {

    private final MenuRecommendService menuService;

    @Operation(summary = "메뉴 추천 API", description = "제목,내용,imageUrl", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/v1/api/menu")
    public MenuRecommendResponse recommendMenu(){
        return menuService.recommend();
    }

    @Operation(summary = "메뉴 등록용 API", description = "DB 저장용 API (기능과 무관)", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/menu")
    public String insertMenu(@RequestBody MenuRecommendRequest request){
        menuService.insert(request);
        return "메뉴등록 완료";
    }
}
