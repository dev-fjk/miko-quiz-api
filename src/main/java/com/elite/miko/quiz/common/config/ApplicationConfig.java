package com.elite.miko.quiz.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.Clock;
import java.time.ZoneId;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 基本必ず使う依存関係のDI定義クラス
 */
@Profile("!test")
@Configuration
public class ApplicationConfig {

    // 日本向けTimeZoneの設定
    private static final String JP_TIME_ZONE = "Asia/Tokyo";

    @Bean(name = "clock")
    public Clock clock() {
        return Clock.system(ZoneId.of(JP_TIME_ZONE));
    }

    /**
     * ModelMapper
     *
     * @return : ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * 自作ObjectMapperの定義
     *
     * @return : ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        // 大文字小文字は区別しない
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        // デシリアライズ時に、JSON中には存在するフィールドがJavaクラスには無い場合、無視する
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 空のmodelパース時にエラーにしない
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // フィールドのみをデシリアライズ対象にすうｒ
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper;
    }
}
