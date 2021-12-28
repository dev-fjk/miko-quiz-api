package com.elite.miko.quiz.application.service;

import com.elite.miko.quiz.application.common.utility.HashUtil;
import com.elite.miko.quiz.application.exception.LoginFailureException;
import com.elite.miko.quiz.domain.repository.AdminAccountRepository;
import com.elite.miko.quiz.domain.service.AdminAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAccountServiceImpl implements AdminAccountService {

    private final AdminAccountRepository adminAccountRepository;
    private final HashUtil hashUtil;

    /**
     * IDとパスワードの組み合わせに対する認証処理を行う
     *
     * @param accountId : 管理者のアカウントID
     * @param password  : パスワード
     * @return 認証成功時は認証用トークンを生成して返却
     */
    @Override
    public String login(String accountId, String password) {

        // パスワードをsha256でハッシュ化する
        final String hashedPassword = hashUtil.createSha256Password(password);
        final boolean isLogin = adminAccountRepository.login(accountId, hashedPassword);
        if (!isLogin) {
            throw new LoginFailureException("ログインに失敗しました");
        }

        // TODO 認証処理盛り込み時にJWTでトークンを生成して返却するように処理を盛り込む
        return "token";
    }
}
