package com.project.loupang.service;

import com.project.loupang.domain.Travel;
import com.project.loupang.repository.TravelRepository;
import com.project.loupang.request.TravelRecommendRequest;
import com.project.loupang.response.TravelRecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelRecommendService {

    private final TravelRepository travelRepository;

    public TravelRecommendResponse recommend() {
        Long id = randTravel();
        Travel travel = travelRepository.findById(id).orElse(null);
        return travel.toResponse();
    }

    @Transactional
    public void insert(TravelRecommendRequest request) {
        Travel travel = request.toEntity();
        travelRepository.save(travel);
    }

    public Long randTravel(){
        List<Travel> all = travelRepository.findAll();
        int i = (int) (Math.random() * (all.size()-1)) + 1;
        return new Long(i);
    }
}
