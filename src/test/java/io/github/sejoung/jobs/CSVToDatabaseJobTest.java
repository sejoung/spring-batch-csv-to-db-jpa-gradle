package io.github.sejoung.jobs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.sejoung.TestBatchConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBatchTest
@SpringBootTest(classes = {FileToDatabaseJob.class, TestBatchConfig.class})
class CSVToDatabaseJobTest {

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @DisplayName("CSVToDatabaseJobTest")
  @Test
  void job() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    Assertions.assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo(ExitStatus.COMPLETED.getExitCode());
  }
}