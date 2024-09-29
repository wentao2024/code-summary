package com.umass.cs520.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.umass.cs520.domain.CodeSummarization;
import com.umass.cs520.domain.CodeSummarizations;
import com.umass.cs520.domain.Evaluations;
import com.umass.cs520.domain.dto.EvaluationsResponse;
import com.umass.cs520.domain.dto.ResultEvaluationRequest;
import com.umass.cs520.mapper.CodeSummarizationsMapper;
import com.umass.cs520.mapper.EvaluationsMapper;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CodeSummarizationsServiceTest {

  @Mock
  private CodeSummarizationsMapper mockCodeSummarizationsMapper;
  @Mock
  private EvaluationsMapper mockEvaluationsMapper;

  @InjectMocks
  private CodeSummarizationsService codeSummarizationsServiceUnderTest;

  @Test
  void testDeleteByPrimaryKey() {
    // Setup
    when(mockCodeSummarizationsMapper.deleteByPrimaryKey(0)).thenReturn(0);

    // Run the test
    final int result = codeSummarizationsServiceUnderTest.deleteByPrimaryKey(0);

    // Verify the results
    assertThat(result).isEqualTo(0);
  }

  @Test
  void testInsert() {
    // Setup
    final CodeSummarizations record = new CodeSummarizations();
    record.setSummarizationid(0);
    record.setUserid(0);
    record.setCodetext("codetext");
    record.setSummarization("summarization");
    record.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

    // Configure CodeSummarizationsMapper.insert(...).
    final CodeSummarizations record1 = new CodeSummarizations();
    record1.setSummarizationid(0);
    record1.setUserid(0);
    record1.setCodetext("codetext");
    record1.setSummarization("summarization");
    record1.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    when(mockCodeSummarizationsMapper.insert(record1)).thenReturn(0);

    // Run the test
    final int result = codeSummarizationsServiceUnderTest.insert(record);

    // Verify the results
    assertThat(result).isEqualTo(0);
  }

  @Test
  void testInsertSelective() {
    // Setup
    final CodeSummarizations record = new CodeSummarizations();
    record.setSummarizationid(0);
    record.setUserid(0);
    record.setCodetext("codetext");
    record.setSummarization("summarization");
    record.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

    // Configure CodeSummarizationsMapper.insertSelective(...).
    final CodeSummarizations record1 = new CodeSummarizations();
    record1.setSummarizationid(0);
    record1.setUserid(0);
    record1.setCodetext("codetext");
    record1.setSummarization("summarization");
    record1.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    when(mockCodeSummarizationsMapper.insertSelective(record1)).thenReturn(0);

    // Run the test
    final int result = codeSummarizationsServiceUnderTest.insertSelective(record);

    // Verify the results
    assertThat(result).isEqualTo(0);
  }

  @Test
  void testSelectByPrimaryKey() {
    // Setup
    final CodeSummarizations expectedResult = new CodeSummarizations();
    expectedResult.setSummarizationid(0);
    expectedResult.setUserid(0);
    expectedResult.setCodetext("codetext");
    expectedResult.setSummarization("summarization");
    expectedResult.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

    // Configure CodeSummarizationsMapper.selectByPrimaryKey(...).
    final CodeSummarizations codeSummarizations = new CodeSummarizations();
    codeSummarizations.setSummarizationid(0);
    codeSummarizations.setUserid(0);
    codeSummarizations.setCodetext("codetext");
    codeSummarizations.setSummarization("summarization");
    codeSummarizations.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    when(mockCodeSummarizationsMapper.selectByPrimaryKey(0)).thenReturn(codeSummarizations);

    // Run the test
    final CodeSummarizations result = codeSummarizationsServiceUnderTest.selectByPrimaryKey(0);

    // Verify the results
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testUpdateByPrimaryKeySelective() {
    // Setup
    final CodeSummarizations record = new CodeSummarizations();
    record.setSummarizationid(0);
    record.setUserid(0);
    record.setCodetext("codetext");
    record.setSummarization("summarization");
    record.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

    // Configure CodeSummarizationsMapper.updateByPrimaryKeySelective(...).
    final CodeSummarizations record1 = new CodeSummarizations();
    record1.setSummarizationid(0);
    record1.setUserid(0);
    record1.setCodetext("codetext");
    record1.setSummarization("summarization");
    record1.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    when(mockCodeSummarizationsMapper.updateByPrimaryKeySelective(record1)).thenReturn(0);

    // Run the test
    final int result = codeSummarizationsServiceUnderTest.updateByPrimaryKeySelective(record);

    // Verify the results
    assertThat(result).isEqualTo(0);
  }

  @Test
  void testUpdateByPrimaryKey() {
    // Setup
    final CodeSummarizations record = new CodeSummarizations();
    record.setSummarizationid(0);
    record.setUserid(0);
    record.setCodetext("codetext");
    record.setSummarization("summarization");
    record.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

    // Configure CodeSummarizationsMapper.updateByPrimaryKey(...).
    final CodeSummarizations record1 = new CodeSummarizations();
    record1.setSummarizationid(0);
    record1.setUserid(0);
    record1.setCodetext("codetext");
    record1.setSummarization("summarization");
    record1.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    when(mockCodeSummarizationsMapper.updateByPrimaryKey(record1)).thenReturn(0);

    // Run the test
    final int result = codeSummarizationsServiceUnderTest.updateByPrimaryKey(record);

    // Verify the results
    assertThat(result).isEqualTo(0);
  }

  @Test
  void testResultEvaluation() {
    // Setup
    final ResultEvaluationRequest request = new ResultEvaluationRequest();
    request.setCodeText("codetext");
    request.setSummarization("summarization");
    request.setFeedback("feedback");
    request.setNaturalness(0);
    request.setUsefulness(0);
    request.setConsistency(0);

    // Configure CodeSummarizationsMapper.insertSelective(...).
    final CodeSummarizations record = new CodeSummarizations();
    record.setSummarizationid(0);
    record.setUserid(0);
    record.setCodetext("codetext");
    record.setSummarization("summarization");
    record.setCreationdate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    when(mockCodeSummarizationsMapper.insertSelective(record)).thenReturn(0);

    // Run the test
    codeSummarizationsServiceUnderTest.resultEvaluation(request);

    // Verify the results
    // Confirm EvaluationsMapper.insertSelective(...).
    final Evaluations record1 = new Evaluations();
    record1.setSummarizationid(0);
    record1.setUserid(0);
    record1.setNaturalnessscore(0);
    record1.setUsefulnessscore(0);
    record1.setConsistencyscore(0);
    record1.setFeedback("feedback");
    verify(mockEvaluationsMapper).insertSelective(record1);
  }

  @Test
  void testGetEvaluations() {
    // Setup
    final EvaluationsResponse expectedResult = new EvaluationsResponse();
    expectedResult.setSuccess(false);
    final CodeSummarization codeSummarization = new CodeSummarization();
    codeSummarization.setSummarizationID(0);
    codeSummarization.setCodeText("codeText");
    codeSummarization.setSummarization("summarization");
    expectedResult.setEvaluations(List.of(codeSummarization));

    // Configure CodeSummarizationsMapper.getCodeSummarizationsWithEvaluationByUserIDs(...).
    final CodeSummarization codeSummarization1 = new CodeSummarization();
    codeSummarization1.setSummarizationID(0);
    codeSummarization1.setCodeText("codeText");
    codeSummarization1.setSummarization("summarization");
    codeSummarization1.setCreationDate("creationDate");
    codeSummarization1.setUserId(0);
    final List<CodeSummarization> codeSummarizations = List.of(codeSummarization1);
    when(mockCodeSummarizationsMapper.getCodeSummarizationsWithEvaluationByUserIDs(List.of(0))).thenReturn(codeSummarizations);

    // Run the test
    final EvaluationsResponse result = codeSummarizationsServiceUnderTest.getEvaluations(List.of(0));

    // Verify the results
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testGetEvaluations_CodeSummarizationsMapperReturnsNoItems() {
    // Setup
    final EvaluationsResponse expectedResult = new EvaluationsResponse();
    expectedResult.setSuccess(false);
    final CodeSummarization codeSummarization = new CodeSummarization();
    codeSummarization.setSummarizationID(0);
    codeSummarization.setCodeText("codeText");
    codeSummarization.setSummarization("summarization");
    expectedResult.setEvaluations(List.of(codeSummarization));

    when(mockCodeSummarizationsMapper.getCodeSummarizationsWithEvaluationByUserIDs(List.of(0)))
        .thenReturn(Collections.emptyList());

    // Run the test
    final EvaluationsResponse result = codeSummarizationsServiceUnderTest.getEvaluations(List.of(0));

    // Verify the results
    assertThat(result).isEqualTo(expectedResult);
  }
}
