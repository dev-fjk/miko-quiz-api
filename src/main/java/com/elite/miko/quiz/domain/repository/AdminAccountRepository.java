package com.elite.miko.quiz.domain.repository;

public interface AdminAccountRepository {

    boolean login(String adminId, String hashPassword);
}
