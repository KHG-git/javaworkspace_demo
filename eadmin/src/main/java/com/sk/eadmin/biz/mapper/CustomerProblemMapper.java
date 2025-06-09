package com.sk.eadmin.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sk.eadmin.biz.dto.AddCustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperOutputDTO;

@Mapper
public interface CustomerProblemMapper {

    List<CustomerProblemRegistMapperOutputDTO> getCustomerProblemRegistList(CustomerProblemRegistMapperInputDTO param);
    void addCustomerProblemRegist(AddCustomerProblemRegistInputDTO param);

}
