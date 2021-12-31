package com.elite.miko.quiz

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.objectweb.asm.TypeReference
import spock.lang.Specification

import java.time.Clock
import java.time.ZoneId
import java.time.ZonedDateTime

class UnitTestBase extends Specification {

    static def jsonBasePath = "src/test/resources/json/"

    static Clock mockClock() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                2021, 12, 12,
                10, 15, 0, 999999999, ZoneId.of("Asia/Tokyo")
        )
        Clock clock = Clock.fixed(zonedDateTime.toInstant(), ZoneId.of("Asia/Tokyo"))
        return clock
    }

    static def readJsonToObject(String jsonDir, String fileName, TypeReference typeReference) {
        def jsonPath = jsonBasePath + jsonDir + fileName + ".json"
        def objectMapper = new ObjectMapper()
        objectMapper.registerModule(new JavaTimeModule())
        return objectMapper.readValue(new File(jsonPath), typeReference)
    }
}
