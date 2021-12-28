package com.elite.miko.quiz.infrastructure.repository;

import com.elite.miko.quiz.domain.repository.AdminAccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AdminAccountRepositoryImpl implements AdminAccountRepository {

    @Override
    public boolean login(String adminId, String hashPassword) {
        return false;
    }
}
