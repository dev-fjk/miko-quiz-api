package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.domain.service.AdminAccountService;
import org.springframework.stereotype.Service;

@Service
public class AdminAccountServiceImpl implements AdminAccountService {

    @Override
    public boolean login(String adminId, String password) {
        return false;
    }
}
