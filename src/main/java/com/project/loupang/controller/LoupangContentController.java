package com.project.loupang.controller;

import com.project.loupang.request.MenuRecommendRequest;
import com.project.loupang.request.TravelRecommendRequest;
import com.project.loupang.response.LoupangResponse;
import com.project.loupang.response.MenuRecommendResponse;
import com.project.loupang.response.TravelRecommendResponse;
import com.project.loupang.security.UserDetailsImpl;
import com.project.loupang.service.LoupangCountService;
import com.project.loupang.service.MenuRecommendService;
import com.project.loupang.service.TravelRecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoupangContentController {

    private final MenuRecommendService menuService;
    private final TravelRecommendService travelService;
    private final LoupangCountService countService;

    @Operation(summary = "메뉴 추천 API", description = "제목,내용,imageUrl", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/v1/api/menu")
    public MenuRecommendResponse recommendMenu(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return menuService.recommend();
    }

    @Operation(summary = "여행지 추천 API", description = "제목,내용,imageUrl", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/v1/api/travel")
    public TravelRecommendResponse recommendTravel(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return travelService.recommend();
    }
    @Operation(summary = "루팡 시간 조회 API", description = "회원정보와 루팡 시간을 측정해서 얄러줍니다", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/v1/api/loupang")
    public LoupangResponse loupangTime(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return countService.findLoupang(userDetails);
    }

    @Operation(summary = "종료 이벤트 API", description = "종료시 호출하는 API", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/exit")
    public void exit(@AuthenticationPrincipal UserDetailsImpl userDetails){
    }

    @Operation(summary = "메뉴 등록용 API", description = "DB 저장용 API (기능과 무관)", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/menu")
    public String insertMenu(@RequestBody MenuRecommendRequest request,@AuthenticationPrincipal UserDetailsImpl userDetails){
        menuService.insert(request);
        return "메뉴등록 완료";
    }

    @Operation(summary = "여행지 등록용 API", description = "DB 저장용 API (기능과 무관)", tags = { "LoupangContentController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/v1/api/travel")
    public String insertTravel(@RequestBody TravelRecommendRequest request,@AuthenticationPrincipal UserDetailsImpl userDetails){
        travelService.insert(request);
        return "여행지등록 완료";
    }
}
