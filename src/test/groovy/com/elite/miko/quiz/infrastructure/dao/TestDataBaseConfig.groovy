package com.elite.miko.quiz.infrastructure.dao

import org.mockito.Mockito
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

import javax.sql.DataSource
import java.sql.Connection

@TestConfiguration
class TestDataBaseConfig {

    @Bean
    @Primary
    DataSource mockDataSource() {
        def connection = Mockito.mock(Connection.class)
        def dataSource = Mockito.mock(DataSource.class)
        Mockito.doReturn(connection).when(dataSource).getConnection()
        return dataSource
    }
}
