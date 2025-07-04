package com.sk.eadmin.admin_eadmin_batch.jobs.job0001.step;

import java.io.File;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJob0001Step01 {
  public static final String STEP_NAME = "JOB_0001_STEP_01";
  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;

  @Bean(STEP_NAME)
  @JobScope
  public Step step(
    @Value("#{jobParameters[fileName]}") @NonNull final String fileName
  ) {
    return new StepBuilder("writeHeaderStep", jobRepository)
      .tasklet(
        new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
            log.debug("Method Start - {}.execute", this.getClass().getName());
            log.debug("    parameters fileName [{}]", fileName);
            final File file = new File(fileName);
            if (!file.exists()) {
              throw new RuntimeException("입력받은 " + fileName + " 파일이 존재하지 않습니다.");
            }
            log.info("입력받은 파일 존재 여부 확인 완료 - {}", fileName);
            return RepeatStatus.FINISHED;
          }
        },
        transactionManager
	  ).build();
  }
}