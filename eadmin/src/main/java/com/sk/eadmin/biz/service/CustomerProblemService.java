package com.sk.eadmin.biz.service;

import com.sk.eadmin.biz.dto.*;

import java.util.List;

public interface CustomerProblemService {
    public List<CustomerProblemRegistOutputDTO> getCustomerProblemRegistList(CustomerProblemRegistInputDTO param);
    public void addCustomerProblemRegist(AddCustomerProblemRegistInputDTO param);
    public void modifyCustomerProblemRegist(Integer registID, ModifyCustomerProblemRegistInputDTO param);
    public boolean deleteCustomerProblemRegist(Integer registID);
    public CustomerProblemRegistMapperOutputDTO getCustomerProblemRegistDetail(Integer registID);
}
