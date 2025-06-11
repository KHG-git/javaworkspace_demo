package com.sk.eadmin.admin_eadmin_batch.jobs.job0001.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PrbmCustAgntDTO {
  private String agntId;
  private String agntNm;
  private String agntRegnCd;
  private String agntIcn;
  private String crteId;
  private Date crteDttm;
  private String updtId;
  private Date updtDttm;
}