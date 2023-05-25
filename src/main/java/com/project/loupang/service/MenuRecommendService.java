package com.project.loupang.service;

import com.project.loupang.domain.Menu;
import com.project.loupang.repository.MenuRepository;
import com.project.loupang.request.MenuRecommendRequest;
import com.project.loupang.response.MenuRecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuRecommendService {

    private final MenuRepository menuRepository;

    public MenuRecommendResponse recommend() {
        Long id = randMenu();
        Menu menu = menuRepository.findById(id).orElse(null);
        return menu.toResponse();
    }

    @Transactional
    public void insert(MenuRecommendRequest request) {
        Menu menu = request.toEntity();
        menuRepository.save(menu);
    }

    public Long randMenu(){
        List<Menu> all = menuRepository.findAll();
        int i = (int) (Math.random() * (all.size()-1)) + 1;
        return new Long(i);
    }
}
