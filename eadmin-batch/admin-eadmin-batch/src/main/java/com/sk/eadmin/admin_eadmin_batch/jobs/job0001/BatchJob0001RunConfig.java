package com.sk.eadmin.admin_eadmin_batch.jobs.job0001;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sk.eadmin.admin_eadmin_batch.jobs.job0001.step.BatchJob0001Step01;
import com.sk.eadmin.admin_eadmin_batch.jobs.job0001.step.BatchJob0001Step02;
import com.sk.eadmin.admin_eadmin_batch.jobs.job0001.step.BatchJob0001Step03;
import com.sk.eadmin.admin_eadmin_batch.jobs.job0001.step.BatchJob0001Step04;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJob0001RunConfig {
  public static final String JOB_NAME = "JOB_0001";
  private final JobRepository jobRepository;

  @Bean(JOB_NAME)
  public Job createExcelFileFromDbBatchJob(
    @Qualifier(BatchJob0001Step01.STEP_NAME) Step step01,
    @Qualifier(BatchJob0001Step02.STEP_NAME) Step step02,
    @Qualifier(BatchJob0001Step03.STEP_NAME) Step step03,
    @Qualifier(BatchJob0001Step04.STEP_NAME) Step step04
  ) {
    return new JobBuilder(JOB_NAME, jobRepository)
	   .start(step01)
	     .on("FAILED")
	     .to(step04)
	   .from(step01)
	   .on("*")
	   .to(step02)
	     .on("FAILED")
	     .to(step04)
	   .from(step02)
	   .on("*")
	   .to(step03)
	   .end()
       .build();
  }
}