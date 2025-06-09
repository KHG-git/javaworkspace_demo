package com.sk.eadmin.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCustomerProblemRegistInputDTO {

  private String reqestID;
  private Integer registID;
  private String customerName;
  private String customerMobile;
  private String requestDesc;
  private String problemCode;
  private Integer problemDegree;
  private String progressStatusCode;
  private String agentRegionCode;
}
