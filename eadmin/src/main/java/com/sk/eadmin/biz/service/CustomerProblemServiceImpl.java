
package com.sk.eadmin.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sk.eadmin.biz.dto.AddCustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperOutputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistOutputDTO;
import com.sk.eadmin.biz.dto.ModifyCustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.mapper.CustomerProblemMapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerProblemServiceImpl implements CustomerProblemService {
  private final CustomerProblemMapper customerProblemMapper;

  @Override
  public List<CustomerProblemRegistOutputDTO> getCustomerProblemRegistList(@NonNull CustomerProblemRegistInputDTO input) {
	log.debug(">>>>> {}.getCustomerProblemRegistList Start <<<<<", this.getClass().getName());
	log.debug("    Parameter 1 - input[{}]", input);
    final CustomerProblemRegistMapperInputDTO mapperInput = CustomerProblemRegistMapperInputDTO.builder()
    	.problemCode(input.getProblemCode())
    	.agentRegionCode(input.getAgentRegionCode())
    	.progressStatusCode(input.getProgressStatusCode())
    	.requestDesc(input.getRequestDesc())
        .build();
    log.debug("mapperInput - {}", mapperInput);
	final List<CustomerProblemRegistMapperOutputDTO> mapperResults = customerProblemMapper.getCustomerProblemRegistList(mapperInput);
    log.debug("mapperResults - {}", mapperResults);
	List<CustomerProblemRegistOutputDTO> retList = new ArrayList<CustomerProblemRegistOutputDTO>();
	for (CustomerProblemRegistMapperOutputDTO mapperResult: mapperResults) {
		final CustomerProblemRegistOutputDTO ret = CustomerProblemRegistOutputDTO.builder()
			.regId(mapperResult.getRegId()) 
		    .custNm(mapperResult.getCustNm())
			.crteDttm(mapperResult.getCrteDttm())
			.agntIcn(mapperResult.getAgntIcn())
			.prbmDgr(mapperResult.getPrbmDgr())
			.prgsSts(mapperResult.getPrgsSts()).build();
		retList.add(ret);
	}
    log.debug("retList - {}", retList);
	log.debug(">>>>> {}.getCustomerProblemRegistList Finish <<<<<", this.getClass().getName());
    log.debug("    return - [{}]", retList);
    return retList;
  }


  @Override
   public void addCustomerProblemRegist(@NonNull AddCustomerProblemRegistInputDTO input) {

	log.debug(">>>>> {}.addCustomerProblemRegist Start <<<<<", this.getClass().getName());
	log.debug("    Parameter 1 - input[{}]", input);

	customerProblemMapper.addCustomerProblemRegist(input);

	log.debug(">>>>> {}.addCustomerProblemRegist Finish <<<<<", this.getClass().getName());

   }

   @Override
   public void modifyCustomerProblemRegist(@NonNull Integer registID, @NonNull ModifyCustomerProblemRegistInputDTO inputDTO) {

	log.debug(">>>>> {}.modifyCustomerProblemRegist Start <<<<<", this.getClass().getName());
	log.debug("    Parameter 1 - input[{}]", inputDTO);

	System.out.println("customerProblemMapper.modifyCustomerProblemRegist(registID, inputDTO)");
	System.out.println(inputDTO);

	customerProblemMapper.modifyCustomerProblemRegist(registID, inputDTO);

	log.debug(">>>>> {}.modifyCustomerProblemRegist Finish <<<<<", this.getClass().getName());

   }

   @Override
   public boolean deleteCustomerProblemRegist(Integer registID) {
	log.debug(">>>>> {}.deleteCustomerProblemRegist Start <<<<<", this.getClass().getName());
	log.debug("    Parameter 1 - regId[{}]", registID);

	customerProblemMapper.deleteCustomerProblemRegist(registID);

	log.debug(">>>>> {}.deleteCustomerProblemRegist Finish <<<<<", this.getClass().getName());

	return true;
   }

   @Override
   public CustomerProblemRegistMapperOutputDTO getCustomerProblemRegistDetail(Integer registID) {

	return customerProblemMapper.getCustomerProblemRegistDetail(registID);

   }


}