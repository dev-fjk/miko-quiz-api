package com.elite.miko.quiz.application.common.utility;

import com.elite.miko.quiz.application.common.config.HashConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HashUtil {

    private final HashConfig hashConfig;

    /**
     * sha256形式でハッシュ化したパスワードを生成する
     *
     * @return sha256形式でハッシュ化されたパスワード
     */
    public String createHashPassword(final String password) {
        // リクエストで受け取った文字列に環境別の固定の文字列を加えた上でsha256形式でハッシュ化する
        return DigestUtils.sha256Hex(password + hashConfig.getSalt());
    }
}
