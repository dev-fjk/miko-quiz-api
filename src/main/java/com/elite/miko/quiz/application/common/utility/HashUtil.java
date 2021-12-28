package com.elite.miko.quiz.application.common.utility;

import com.elite.miko.quiz.application.common.config.HashConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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
    public String createSha256Password(final String password) {
        if (StringUtils.isEmpty(hashConfig.getSalt())) {
            throw new IllegalStateException("ハッシュ化用の文字列が未設定です");
        }
        // リクエストで受け取った文字列に環境別の固定の文字列を加えた上でsha256形式でハッシュ化する
        return DigestUtils.sha256Hex(password + hashConfig.getSalt());
    }
}
