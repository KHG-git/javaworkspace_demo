package com.sk.eadmin.admin_eadmin_batch.jobs.job0001.step;

import java.util.Date;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.sk.eadmin.admin_eadmin_batch.jobs.job0001.dto.PrbmCustAgntDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJob0001Step02 {
  public static final String STEP_NAME = "JOB_0001_STEP_02";
  public static final String READER_NAME = "JOB_0001_STEP_02_READER";
  public static final String PROCESSOR_NAME = "JOB_0001_STEP_02_PROCESSOR";
  public static final String WRITER_NAME = "JOB_0001_STEP_02_WRITER";
  private static int CHUNK_SIZE = 1000;
  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;

  @Bean(STEP_NAME)
  @JobScope
  public Step step() {
    return new StepBuilder("writeDataStep", jobRepository)
        .<PrbmCustAgntDTO, PrbmCustAgntDTO>chunk(CHUNK_SIZE, transactionManager)
        .reader(reader(null))
        .processor(processor())
        .writer(writer(null)).build();
  }

  @Bean(READER_NAME)
  @StepScope
  FlatFileItemReader<PrbmCustAgntDTO> reader(
      @Value("#{jobParameters['fileName']}") @NonNull final String fileName) {
    FlatFileItemReader<PrbmCustAgntDTO> reader = new FlatFileItemReader<>();
    reader.setResource(new FileSystemResource(fileName));
    reader.setLinesToSkip(1); // 헤더 스킵
    reader.setLineMapper(new DefaultLineMapper<>() {
      {
        setLineTokenizer(new DelimitedLineTokenizer() {
          {
            setNames("agnt_id", "agnt_nm", "agnt_regn_cd", "agnt_icn");
          }
        });
        setFieldSetMapper(fieldSet -> PrbmCustAgntDTO.builder()
            .agntId(fieldSet.readString("agnt_id"))
            .agntNm(fieldSet.readString("agnt_nm"))
            .agntRegnCd(fieldSet.readString("agnt_regn_cd"))
            .agntIcn(fieldSet.readString("agnt_icn"))
            .crteId("JOB0001")
            .crteDttm(new Date())
            .updtId("JOB0001")
            .updtDttm(new Date()).build());
      }
    });
    return reader;
  }

  @Bean(WRITER_NAME)
  @StepScope
  public MyBatisBatchItemWriter<PrbmCustAgntDTO> writer(SqlSessionFactory sqlSessionFactory) {
    return new MyBatisBatchItemWriterBuilder<PrbmCustAgntDTO>()
        .sqlSessionFactory(sqlSessionFactory)
        .statementId("com.sk.eadmin_batch.jobs.job0001.mapper.Job0001")
        .build();
  }

  @Bean(PROCESSOR_NAME)
  @StepScope
  public ItemProcessor<PrbmCustAgntDTO, PrbmCustAgntDTO> processor() {
    return item -> {
      return item;
    };
  }
}