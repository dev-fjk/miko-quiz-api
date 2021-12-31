package com.elite.miko.quiz.infrastructure.dao

import com.elite.miko.quiz.UnitTestBase
import com.elite.miko.quiz.infrastructure.model.entity.Answer
import com.elite.miko.quiz.infrastructure.model.entity.listener.AnswerEntityListener
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.seasar.doma.boot.autoconfigure.DomaAutoConfiguration
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
class AnswerDaoSpec extends UnitTestBase {

    @Autowired
    AnswerDao target

    @Autowired
    DataSource dataSource

    @MockBean
    AnswerEntityListener listener

    @Captor
    ArgumentCaptor<String> captor

    def preparedStatement = Mock(PreparedStatement)
    def capturedValues = [:] as Map<Integer, String>

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

    def "正常系_fetchByQuizIdList"() {
        given:
        def quizIdList = [1L, 2L]
        when:
        target.fetchByQuizIdList(quizIdList)
        then:
        verifyPreparedStatement()
        capturedSql() == "select quiz_id, answer_id, answer1, answer2, answer3, answer4, correct_number" +
                " from answer where quiz_id in (?, ?)"
        capturedParams() == ["1", "2"]
    }

    def "正常系_insert"() {
        given:
        def answer = new Answer()
        answer.setQuizId(10L)
        answer.setAnswer1("回答1")
        answer.setAnswer2("回答2")
        answer.setAnswer3("回答3")
        answer.setAnswer4("回答4")
        answer.setCorrectNumber(1)

        when:
        target.insert(answer)

        then:
        verifyPreparedStatementAutoGeneratedKey()
        capturedSql() == "insert into answer (quizId, answer1, answer2, answer3, answer4, correctNumber)" +
                " values (?, ?, ?, ?, ?, ?)"
        capturedParams() == ["10", "回答1", "回答2", "回答3", "回答4", "1"]
    }

    def "正常系_update"() {
        given:
        def answer = new Answer()
        answer.setQuizId(10L)
        answer.setAnswerId(100L)
        answer.setAnswer1("回答1")
        answer.setAnswer2("回答2")
        answer.setAnswer3("回答3")
        answer.setAnswer4("回答4")
        answer.setCorrectNumber(1)
        answer.setUpdatedAt(LocalDateTime.now(mockClock()))

        when:
        target.update(answer)

        then:
        verifyPreparedStatement()
        capturedSql() == "update answer set quizId = ?, answer1 = ?, answer2 = ?, answer3 = ?, answer4 = ?," +
                " correctNumber = ?, updatedAt = ? where answerId = ?"
        capturedParams() == ["10", "回答1", "回答2", "回答3", "回答4", "1", "100"]
    }

    def "正常系_deleteByQuizIdList"() {
        given:
        def quizIdList = [1L, 2L]
        when:
        target.deleteByQuizIdList(quizIdList)
        then:
        verifyPreparedStatement()
        capturedSql() == "delete from answer where quiz_id in (?, ?)"
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
