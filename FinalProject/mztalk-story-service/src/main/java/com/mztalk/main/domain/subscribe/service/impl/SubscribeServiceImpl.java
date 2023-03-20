package com.mztalk.main.domain.subscribe.service.impl;


import com.mztalk.main.common.CMRespDto;
import com.mztalk.main.domain.profile.dto.ProfileResponseDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeRequestDto;
import com.mztalk.main.domain.subscribe.dto.SubscribeResponseDto;
import com.mztalk.main.domain.subscribe.entity.Subscribe;
import com.mztalk.main.domain.subscribe.repository.SubscribeRepository;
import com.mztalk.main.domain.subscribe.service.SubscribeService;
import com.mztalk.main.handler.exception.CustomApiException;
import com.mztalk.main.status.RoleStatus;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeRepository subscribeRepository;


    @Override
    public Long save(SubscribeRequestDto subscribeRequestDto) {

        Long userNo = subscribeRequestDto.getUserNo();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8000/login/user-info/" + userNo,
                HttpMethod.GET,
                entity,
                String.class
        );
        JSONObject json = new JSONObject(response.getBody());
        String role = json.getString("role");

        if (role.equals("ROLE_VIP")) {
            throw new IllegalArgumentException("이미 VIP회원입니다.");
        }

        return subscribeRepository.save(subscribeRequestDto.toEntity()).getUserNo();
    }

    @Override
    public int updateStatusByUserNo(Long userNo) {

        int updatedCount = subscribeRepository.updateStatusByUserNo(userNo);

        if (updatedCount > 0) {
            return updatedCount;
        }
        return 0;
    }

    @Override
    public SubscribeResponseDto findByUserNoAndRoleStatus(Long userNo) {
        try {
            return subscribeRepository.findByUserNoAndRoleStatus(userNo, RoleStatus.valueOf("VIP"));
        }catch (Exception e){
            return null;
        }
    }



}
