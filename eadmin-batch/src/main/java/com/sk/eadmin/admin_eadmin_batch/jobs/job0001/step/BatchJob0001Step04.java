package com.sk.eadmin.admin_eadmin_batch.jobs.job0001.step;

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
public class BatchJob0001Step04 {
  public static final String STEP_NAME = "JOB_0001_STEP_04";
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
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.debug("Method Start - {}.execute", this.getClass().getName());
            log.debug("    parameters fileName [{}]", fileName);
            log.error(">>>>>> Batch Job0001 오류 발생 알림");
            throw new Exception("Job0001 Batch 실행 실패");
          }
        },
        transactionManager
	  ).build();
  }
}