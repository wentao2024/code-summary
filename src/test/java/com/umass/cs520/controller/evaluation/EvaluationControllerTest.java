package com.umass.cs520.controller.evaluation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.umass.cs520.domain.CodeSummarization;
import com.umass.cs520.domain.dto.EvaluationsResponse;
import com.umass.cs520.domain.dto.ResultEvaluationRequest;
import com.umass.cs520.service.CodeSummarizationsService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EvaluationController.class)
class EvaluationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OpenAiChatClient mockChatClient;
  @MockBean
  private CodeSummarizationsService mockCodeSummarizationsService;

  @Test
  void testCodeSummarization() throws Exception {
    // Setup
    when(mockChatClient.call("message")).thenReturn("content");

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(post("/api/codeSummarization")
            .content("content").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }

  @Test
  void testUploadFile() throws Exception {
    // Setup
    when(mockChatClient.call("message")).thenReturn("content");

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(multipart("/api/fileUpload")
            .file(new MockMultipartFile("file", "originalFilename", MediaType.APPLICATION_JSON_VALUE, "content".getBytes()))
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }

  @Test
  void testResultEvaluation() throws Exception {
    // Setup
    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(post("/api/resultEvaluation")
            .content("content").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");

    // Confirm CodeSummarizationsService.resultEvaluation(...).
    final ResultEvaluationRequest request = new ResultEvaluationRequest();
    request.setCodeText("codeText");
    request.setSummarization("summarization");
    request.setFeedback("feedback");
    request.setNaturalness(0);
    request.setUsefulness(0);
    verify(mockCodeSummarizationsService).resultEvaluation(request);
  }

  @Test
  void testGetEvaluations() throws Exception {
    // Setup
    // Configure CodeSummarizationsService.getEvaluations(...).
    final EvaluationsResponse evaluationsResponse = new EvaluationsResponse();
    evaluationsResponse.setSuccess(false);
    final CodeSummarization codeSummarization = new CodeSummarization();
    codeSummarization.setSummarizationID(0);
    codeSummarization.setCodeText("codeText");
    codeSummarization.setSummarization("summarization");
    evaluationsResponse.setEvaluations(List.of(codeSummarization));
    when(mockCodeSummarizationsService.getEvaluations(List.of(0))).thenReturn(evaluationsResponse);

    // Run the test
    final MockHttpServletResponse response = mockMvc.perform(get("/api/evaluations")
            .param("users", "0")
            .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    // Verify the results
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
  }
}
