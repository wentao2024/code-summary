package com.umass.cs520.controller.evaluation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umass.cs520.domain.dto.CodeSummarizationDTO;
import com.umass.cs520.domain.dto.CodeSummarizationRequest;
import com.umass.cs520.domain.dto.CodeSummarizationResponse;
import com.umass.cs520.domain.dto.EvaluationsResponse;
import com.umass.cs520.domain.dto.ResultEvaluationRequest;
import com.umass.cs520.domain.dto.ResultEvaluationResponse;
import com.umass.cs520.service.CodeSummarizationsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EvaluationController {

  private final ObjectMapper objectMapper = new ObjectMapper();
  String prompt = """

      organize the notes, then return them in JSON array format.
      {
        "CodeText": "xxx",
        "Summarization": "xxx"
      }""";
  String promptWithNum = """

      analysis the code above, then return %d summarizations in JSON array format.
      {
         "success": true,
         "summarizations": [
           "Prints 'Hello, World!' to the console.",
           "..."
         ]
       }
       """;

  private final OpenAiChatClient chatClient;

  private final CodeSummarizationsService codeSummarizationsService;

  public EvaluationController(OpenAiChatClient chatClient, CodeSummarizationsService codeSummarizationsService) {
    this.chatClient = chatClient;
    this.codeSummarizationsService = codeSummarizationsService;
  }


  @PostMapping("/api/codeSummarization")
  public ResponseEntity<CodeSummarizationResponse> codeSummarization(@RequestBody CodeSummarizationRequest request)
      throws JsonProcessingException {
    // Get request parameters
    String codeInput = request.getCodeInput();
    int numberOfResults = request.getNumberOfResults();

    String result = chatClient.call(codeInput + String.format(promptWithNum, numberOfResults));

    CodeSummarizationResponse response = objectMapper.readValue(result, CodeSummarizationResponse.class);
    return ResponseEntity.ok(response);
  }


  @PostMapping("/api/fileUpload")
  public Map uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

    String content = new String(file.getBytes());
    String result = chatClient.call(content + prompt);

    List<CodeSummarizationDTO> list = objectMapper.readValue(result, List.class);
    return Map.of("generation", list);
  }

  @PostMapping("/api/resultEvaluation")
  public ResponseEntity<Object> resultEvaluation(@RequestBody ResultEvaluationRequest request) {

    // Process evaluation (save to database or perform any other operation)
    codeSummarizationsService.resultEvaluation(request);

    // Prepare response
    ResultEvaluationResponse response = new ResultEvaluationResponse(true, "Evaluation saved successfully");

    // Return response
    return ResponseEntity.ok(response);
  }

  @GetMapping("/api/evaluations")
  public ResponseEntity<Object> getEvaluations(@RequestParam(required = false) List<Integer> users) {
    // Simulated data for demonstration
    EvaluationsResponse response = codeSummarizationsService.getEvaluations(users);

    // Return response
    return ResponseEntity.ok(response);
  }

}