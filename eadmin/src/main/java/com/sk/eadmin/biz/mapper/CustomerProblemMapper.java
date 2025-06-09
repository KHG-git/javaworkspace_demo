package com.sk.eadmin.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sk.eadmin.biz.dto.AddCustomerProblemRegistInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperInputDTO;
import com.sk.eadmin.biz.dto.CustomerProblemRegistMapperOutputDTO;
import com.sk.eadmin.biz.dto.ModifyCustomerProblemRegistInputDTO;

@Mapper
public interface CustomerProblemMapper {

    List<CustomerProblemRegistMapperOutputDTO> getCustomerProblemRegistList(CustomerProblemRegistMapperInputDTO param);
    void addCustomerProblemRegist(AddCustomerProblemRegistInputDTO param);
    void modifyCustomerProblemRegist(@Param("registID") Integer registID, @Param("inputDTO") ModifyCustomerProblemRegistInputDTO param);
    boolean deleteCustomerProblemRegist(Integer registID);
    CustomerProblemRegistMapperOutputDTO getCustomerProblemRegistDetail(Integer registID);

}
