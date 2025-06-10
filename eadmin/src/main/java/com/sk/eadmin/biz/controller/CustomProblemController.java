
package com.sk.eadmin.biz.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.eadmin.biz.dto.AddCustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperOutputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistOutputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistResDTO;
import com.sk.eadmin.biz.dto.ModifyCustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.service.CustomerProblemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "customer-problem", description = "고객문의")
@RestController
@RequestMapping("/api/v1/customer/problem-mgmt")
@RequiredArgsConstructor
public class CustomProblemController {
  private final CustomerProblemService customerProblemService;

  @Operation(summary = "고객접수문의 리스트 조회", description = "입력조건에 따라 접수된 고객 문의 리스트를 조회한다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "403", description = "접속 권한이 없습니다.")
    }
  )
  @GetMapping("/customer-problem")
  public ResponseEntity<List<CustomerProblemRegistResDTO>> getCustomerProblemRegistList(
    @RequestParam(name = "problemCode", required = false)
    @Valid @Pattern(regexp = "^000[1-4]$")
    @Schema(
      description = "문의코드. 0001 - 사용요금관련, 0002 - 통화품질관련, 0003 - 대리점 서비스 관련, 0004 - 해외 로밍 관련",
      name = "problemCode",
      type = "string",
      example = "0001"
    )
    String problemCode,

    @RequestParam(name = "agentRegionCode", required = false)
    @Valid @Pattern(regexp = "^0[1-3]$")
    @Schema(
      description = "담당지역코드. 01 - 서울, 02 - 경기, 03 - 인천",
      name = "agentRegionCode",
      type = "string",
      example = "01"
    )
    String agentRegionCode,

    @RequestParam(name = "progressStatusCode", required = false)
    @Valid @Pattern(regexp = "^0[1-2]$")
    @Schema(
      description = "진행상태코드. 01 - 성공, 02 - 대기",
      name = "progressStatusCode",
      type = "string",
      example = "01"
    )
    String progressStatusCode,

    @Valid
    @Schema(
      description = "요청필터",
      name = "requestFilter",
      type = "string",
      example = "샘플"
    )
    @RequestParam(name = "requestFilter", required = false)
    String requestFilter
  ) {
    final List<CustomerProblemRegistOutputDTO> serviceResults = customerProblemService.getCustomerProblemRegistList(
         CustomerProblemRegistInputDTO.builder()
            .problemCode(problemCode)
            .agentRegionCode(agentRegionCode)
            .progressStatusCode(progressStatusCode)
            .requestDesc(requestFilter).build()
      );
    List<CustomerProblemRegistResDTO> rets = new ArrayList<CustomerProblemRegistResDTO>();      
    for (CustomerProblemRegistOutputDTO serviceResult: serviceResults) {
      final CustomerProblemRegistResDTO retObject = CustomerProblemRegistResDTO.builder()
          .regId(serviceResult.getRegId())
          .custNm(serviceResult.getCustNm())
          .custMbl(serviceResult.getCustMbl())
          .reqDesc(serviceResult.getReqDesc())
          .prbmCd(serviceResult.getPrbmCd())
          .prbmDgr(serviceResult.getPrbmDgr())
          .prgsSts(serviceResult.getPrgsSts())
          .prgsStsVal(serviceResult.getPrgsStsVal())
          .crteDttm(serviceResult.getCrteDttm())
          .agntIcn(serviceResult.getAgntIcn()).build();
      rets.add(retObject);
    }
    return new ResponseEntity<List<CustomerProblemRegistResDTO>>(rets, HttpStatus.OK);
  }







/* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Post */
@PostMapping("/customer-problem")
@Operation(summary = "고객 문의 등록", description = "고객의 문의를 등록한다.")
public ResponseEntity<Void>  addCustomerProblemRegist( @Valid @RequestBody AddCustomerProblemRegistInputDTO inputDTO  ) {

  System.out.println("Controller : addCustomerProblemRegist");
  System.out.println(inputDTO);
  customerProblemService.addCustomerProblemRegist(inputDTO);
  return ResponseEntity.noContent().build();

  }


  /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Patch */
@PatchMapping("/customer-problem/{registID}")
@Operation(summary = "고객 문의 수정", description = "고객의 문의를 수정 한다.")
public ResponseEntity<Void>  modifyCustomerProblemRegist( @Valid @PathVariable Integer registID 
                                                      ,  @Valid @RequestBody ModifyCustomerProblemRegistInputDTO inputDTO ) {

  System.out.println("Controller : modifyCustomerProblemRegist");
  System.out.println("registID : " + registID);
  System.out.println("inputDTO : " + inputDTO);  
  customerProblemService.modifyCustomerProblemRegist(registID, inputDTO);
  return ResponseEntity.noContent().build();

  }
  


    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Delete */
@DeleteMapping("/customer-problem/{registID}")
@Operation(summary = "고객접수문의삭제", description = "고객의 문의를 삭제 한다.")
public ResponseEntity<Void>  deleteCustomerProblemRegist( @Valid @PathVariable Integer registID  ) {

  System.out.println("Controller : deleteCustomerProblemRegist");
  System.out.println("registID : " + registID);
  customerProblemService.deleteCustomerProblemRegist(registID);
  return ResponseEntity.noContent().build();

  }

@GetMapping("/customer-problem/{registID}")
@Operation(summary = "고객접수문의 상세조회", description = "고객의 문의를 상세 조회 한다.")
public ResponseEntity<CustomerProblemRegistResDTO> getCustomerProblemRegistDetail(@Valid @PathVariable Integer registID) {

  System.out.println("Controller : getCustomerProblemRegistDetail");
  System.out.println("registID : " + registID);

  CustomerProblemRegistMapperOutputDTO fromDB = customerProblemService.getCustomerProblemRegistDetail(registID);

  System.out.println(fromDB);
  System.out.println();
  System.out.println();



    CustomerProblemRegistResDTO res = CustomerProblemRegistResDTO.builder()
        .custNm(fromDB.getCustNm())
        .reqDesc(fromDB.getReqDesc())
        .custMbl(fromDB.getCustMbl())
        .prgsSts(fromDB.getPrgsSts())
        .prgsStsVal(fromDB.getPrgsStsVal())
        .prbmDgr(fromDB.getPrbmDgr())
        .regId(fromDB.getRegId())
        .build();

        System.out.println(res);

   return ResponseEntity.ok(res);
}



}



