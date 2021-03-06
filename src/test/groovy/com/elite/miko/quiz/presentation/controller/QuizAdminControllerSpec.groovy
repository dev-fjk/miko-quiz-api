package com.elite.miko.quiz.presentation.controller

import com.elite.miko.quiz.UnitTestBase
import com.elite.miko.quiz.application.common.config.TokenConfig
import com.elite.miko.quiz.application.common.utility.WebTokenUtil
import com.elite.miko.quiz.application.exception.ValidationException
import com.elite.miko.quiz.domain.model.dto.QuizAddDto
import com.elite.miko.quiz.domain.model.dto.QuizUpdateListDto
import com.elite.miko.quiz.domain.model.result.QuizManageResult
import com.elite.miko.quiz.domain.service.QuizAdminService
import com.elite.miko.quiz.presentation.converter.ProblemConverter
import com.elite.miko.quiz.presentation.converter.ResponseConverter
import com.elite.miko.quiz.presentation.model.form.QuizAddForm
import com.elite.miko.quiz.presentation.model.form.QuizUpdateForm
import com.elite.miko.quiz.presentation.model.response.QuizManageListResponse
import com.elite.miko.quiz.presentation.validator.QuizValidator
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = QuizAdminController.class, includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, classes = [QuizExceptionHandler.class, ProblemConverter.class]
))
@ActiveProfiles(["test", "noauth"])
@ImportAutoConfiguration
class QuizAdminControllerSpec extends UnitTestBase {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    QuizAdminService adminService = Mock()
    @SpringBean
    ResponseConverter responseConverter = Mock()
    @SpringBean
    QuizValidator quizValidator = Mock()
    @SpringBean
    ModelMapper modelMapper = Mock()

    @MockBean
    TokenConfig tokenConfig
    @MockBean
    WebTokenUtil tokenUtil

    @Shared
    def basePath = "/miko/v1/admin/"

    @Unroll
    def "?????????_fetchQuiz_#testName"() {
        given:
        def start = inputStart
        def count = inputCount

        def quizManageResult = QuizManageResult.builder().build()

        1 * adminService.fetchQuiz(start - 1, count) >> quizManageResult
        1 * responseConverter.convert(quizManageResult) >> QuizManageListResponse.builder().build()

        expect:
        mockMvc.perform(get(basePath + "quizzes" + "?start=" + start + "&count=" + count))
                .andExpect(status().isOk())

        where:
        testName          | inputStart | inputCount
        "start/count ?????????" | 1          | 1
        "count ?????????"       | 51         | 50
    }

    @Unroll
    def "?????????_fetchQuiz_400?????????_#testName"() {
        given:
        def start = inputStart
        def count = inputCount

        def quizManageResult = QuizManageResult.builder().build()

        0 * adminService.fetchQuiz(start - 1, count)
        0 * responseConverter.convert(quizManageResult)

        expect:
        mockMvc.perform(get(basePath + "quizzes" + "?start=" + start + "&count=" + count))
                .andExpect(status().isBadRequest())

        where:
        testName      | inputStart | inputCount
        "start??????????????????" | 0          | 50
        "count??????????????????" | 1          | 0
        "count??????????????????" | 1          | 51
    }

    @Unroll
    def "?????????_fetchRequestQuiz_#testName"() {
        given:
        def start = inputStart
        def count = inputCount

        def quizManageResult = QuizManageResult.builder().build()

        1 * adminService.fetchRequestQuiz(start - 1, count) >> quizManageResult
        1 * responseConverter.convert(quizManageResult) >> QuizManageListResponse.builder().build()

        expect:
        mockMvc.perform(get(basePath + "quizzes/requests" + "?start=" + start + "&count=" + count))
                .andExpect(status().isOk())

        where:
        testName          | inputStart | inputCount
        "start/count ?????????" | 1          | 1
        "count ?????????"       | 51         | 50
    }

    @Unroll
    def "?????????_fetchRequestQuiz_400?????????_#testName"() {
        given:
        def start = inputStart
        def count = inputCount

        def quizManageResult = QuizManageResult.builder().build()

        0 * adminService.fetchRequestQuiz(start - 1, count)
        0 * responseConverter.convert(quizManageResult)

        expect:
        mockMvc.perform(get(basePath + "quizzes/requests" + "?start=" + start + "&count=" + count))
                .andExpect(status().isBadRequest())

        where:
        testName      | inputStart | inputCount
        "start??????????????????" | 0          | 50
        "count??????????????????" | 1          | 0
        "count??????????????????" | 1          | 51
    }

    @Unroll
    def "?????????_addQuiz"() {
        given:
        def quizAddForm = readJsonToObject("/form/", "quizAddForm",
                new TypeReference<QuizAddForm>() {})

        def objectMapper = new ObjectMapper()
        def jsonRequest = objectMapper.writeValueAsString(quizAddForm)

        1 * modelMapper.map(quizAddForm, QuizAddDto.class)
        1 * adminService.insertQuiz(_)

        expect:
        mockMvc.perform(post("/miko/v1/admin/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isCreated())
    }

    @Unroll
    def "?????????_addQuiz_????????????????????????????????????400?????????"() {
        given:
        def quizAddForm = readJsonToObject("/form/", "quizAddForm",
                new TypeReference<QuizAddForm>() {}) as QuizAddForm
        quizAddForm.setQuestion(null)

        def objectMapper = new ObjectMapper()
        def jsonRequest = objectMapper.writeValueAsString(quizAddForm)

        0 * modelMapper.map(quizAddForm, QuizAddDto.class)
        0 * adminService.insertQuiz(_)

        expect:
        mockMvc.perform(post("/miko/v1/admin/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
    }

    def "?????????_updateQuiz"() {
        given:
        def quizUpdateForm = readJsonToObject("/form/", "quizUpdateForm",
                new TypeReference<QuizUpdateForm>() {})

        def objectMapper = new ObjectMapper()
        def jsonRequest = objectMapper.writeValueAsString(quizUpdateForm)

        1 * quizValidator.validate(quizUpdateForm)
        1 * modelMapper.map(quizUpdateForm, QuizUpdateListDto.class)
        1 * adminService.updateQuiz(_)

        expect:
        mockMvc.perform(put("/miko/v1/admin/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isNoContent())
    }

    @Unroll
    def "?????????_updateQuiz_????????????????????????????????????400?????????"() {
        given:
        def quizUpdateForm = readJsonToObject("/form/", "quizUpdateForm",
                new TypeReference<QuizUpdateForm>() {})

        def objectMapper = new ObjectMapper()
        def jsonRequest = objectMapper.writeValueAsString(quizUpdateForm)

        1 * quizValidator.validate(quizUpdateForm) >> {
            throw new ValidationException("??????????????????????????????")
        }
        0 * modelMapper.map(quizUpdateForm, QuizUpdateListDto.class)
        0 * adminService.updateQuiz(_)

        expect:
        mockMvc.perform(put("/miko/v1/admin/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
    }

    def "?????????_deleteQuiz"() {
        given:
        def quizIdList = [1L, 2L]

        1 * quizValidator.validate(quizIdList)
        1 * adminService.deleteQuiz(quizIdList)

        expect:
        mockMvc.perform(delete(basePath + "quizzes" + "?quizIdList=1,2"))
                .andExpect(status().isNoContent())
    }

    def "?????????_deleteQuiz"() {
        given:
        def quizIdList = [1L, 2L]

        1 * quizValidator.validate(quizIdList) >> {
            throw new ValidationException("??????????????????????????????")
        }
        0 * adminService.deleteQuiz(_)

        expect:
        mockMvc.perform(delete(basePath + "quizzes" + "?quizIdList=1,2"))
                .andExpect(status().isBadRequest())
    }
}
