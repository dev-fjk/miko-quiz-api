package com.elite.miko.quiz.infrastructure.dao

import com.elite.miko.quiz.UnitTestBase
import com.elite.miko.quiz.domain.model.consts.QuizStatus
import com.elite.miko.quiz.infrastructure.model.entity.Quiz
import com.elite.miko.quiz.infrastructure.model.entity.listener.QuizEntityListener
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.seasar.doma.boot.autoconfigure.DomaAutoConfiguration
import org.seasar.doma.jdbc.SelectOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

import javax.sql.DataSource
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement
import java.time.Clock
import java.time.LocalDateTime

import static org.mockito.ArgumentMatchers.eq

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DomaAutoConfiguration.class)
@ComponentScan
class QuizDaoSpec extends UnitTestBase {

    @Autowired
    QuizDao target

    @Autowired
    DataSource dataSource

    @MockBean
    QuizEntityListener listener

    @Captor
    ArgumentCaptor<String> captor

    PreparedStatement preparedStatement = Mock(PreparedStatement)
    Map<Integer, String> capturedValues = [:] as Map<Integer, String>

    Clock clock = mockClock()

    def setup() {
        capturedValues.clear()
        def connection = dataSource.getConnection()
        preparedStatement.setString(*_) >> { index, val ->
            capturedValues.put(index, val)
        }
        preparedStatement.setInt(*_) >> { index, val ->
            capturedValues.put(index, val as String)
        }
        preparedStatement.setLong(*_) >> { index, val ->
            capturedValues.put(index, val as String)
        }
        preparedStatement.setObject(*_) >> { index, val ->
            capturedValues.put(index, val as String)
        }
        preparedStatement.setDate(*_) >> { index, val ->
            capturedValues.put(index, val as String)
        }
        preparedStatement.setNull(*_) >> { index, val ->
            capturedValues.put(index, null)
        }

        def resultSet = Mock(ResultSet)
        preparedStatement.executeQuery() >> resultSet

        def generatedResultSet = Mock(ResultSet)
        generatedResultSet.next() >> true
        preparedStatement.getGeneratedKeys() >> generatedResultSet

        Mockito.doReturn(preparedStatement).when(connection).prepareStatement(captor.capture())
        Mockito.doReturn(preparedStatement).when(connection).prepareStatement(
                captor.capture(), eq(Statement.RETURN_GENERATED_KEYS)
        )
    }

    def cleanup() {
        Mockito.reset(dataSource.getConnection())
    }

    def "?????????_fetchQuizRandom"() {
        when:
        target.fetchQuizRandom(10, QuizStatus.ENABLED.getValue())
        then:
        verifyPreparedStatement()
        capturedSql() == "select tbl.quiz_id, question, commentary, status" +
                " from quiz as tbl," +
                " (select quiz_id from quiz where status = ? order by random() limit ? ) as random" +
                " where tbl.quiz_id = random.quiz_id limit ?"
        capturedParams() == ["1", "10", "10"]
    }

    def "?????????_fetchByIfPresentStatus_???????????????????????????"() {
        given:
        SelectOptions options = SelectOptions.get()

        when:
        target.fetchByIfPresentStatus(options, QuizStatus.REQUEST.getValue())

        then:
        verifyPreparedStatement()
        capturedSql() == "select quiz_id, question, commentary, status from quiz where status = ? order by quiz_id"
        capturedParams() == ["2"]
    }

    def "?????????_fetchByIfPresentStatus_???????????????????????????"() {
        given:
        SelectOptions options = SelectOptions.get()

        when:
        target.fetchByIfPresentStatus(options, null)

        then:
        verifyPreparedStatement()
        capturedSql() == "select quiz_id, question, commentary, status from quiz order by quiz_id"
        capturedParams() == []
    }

    def "?????????_fetchByQuizIdSetForUpdate"() {
        given:
        Set<Long> quizIdSet = [1L, 2L]

        when:
        target.fetchByQuizIdSetForUpdate(quizIdSet)

        then:
        verifyPreparedStatement()
        capturedSql() == "select quiz_id, question, commentary, status from quiz where quiz_id in (?, ?) for update"
        capturedParams() == ["1", "2"]
    }

    def "?????????_insert"() {
        given:
        def quiz = new Quiz()
        quiz.setQuestion("?????????")
        quiz.setCommentary("?????????")
        quiz.setQuizStatus(QuizStatus.ENABLED)

        when:
        target.insert(quiz)

        then:
        verifyPreparedStatementAutoGeneratedKey()
        capturedSql() == "insert into quiz (question, commentary, status) values (?, ?, ?)"
        capturedParams() == ["?????????", "?????????", "1"]
    }

    def "?????????_update"() {
        given:
        def quiz = new Quiz()
        quiz.setQuizId(10L)
        quiz.setQuestion("?????????")
        quiz.setCommentary("?????????")
        quiz.setQuizStatus(QuizStatus.ENABLED)
        quiz.setUpdatedAt(LocalDateTime.now(mockClock()))

        when:
        target.update(quiz)

        then:
        verifyPreparedStatement()
        capturedSql() == "update quiz set question = ?, commentary = ?, status = ?, updatedAt = ? where quizId = ?"
        capturedParams() == ["?????????", "?????????", "1", "10"]
    }

    def "?????????_deleteByQuizIdList"() {
        given:
        def quizIdList = [1L, 2L]
        when:
        target.deleteByQuizIdList(quizIdList)
        then:
        capturedSql() == "delete from quiz where quiz_id in (?, ?)"
        capturedParams() == ["1", "2"]
    }

    private def verifyPreparedStatement(int times = 1) {
        def connection = dataSource.getConnection()
        Mockito.verify(connection, Mockito.times(times)).prepareStatement(captor.capture()) == null
    }

    private def verifyPreparedStatementAutoGeneratedKey(int times = 1) {
        def connection = dataSource.getConnection()
        Mockito.verify(connection, Mockito.times(times)).prepareStatement(
                captor.capture(), eq(Statement.RETURN_GENERATED_KEYS)
        ) == null
    }

    private def capturedSql() {
        captor.getValue().replaceAll("[ \t\n]+", " ")
    }

    private def capturedParams() {
        capturedValues.values() as List<String>
    }
}
