package com.turing.purchase.mapper;

import com.turing.purchase.entity.ContractApply;
import com.turing.purchase.entity.ContractApplyExample;
import com.turing.purchase.entity.ContractApplyWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractApplyMapper {
    int countByExample(ContractApplyExample example);

    int deleteByExample(ContractApplyExample example);

    int insert(ContractApplyWithBLOBs record);

    int insertSelective(ContractApplyWithBLOBs record);

    List<ContractApplyWithBLOBs> selectByExampleWithBLOBs(ContractApplyExample example);

    List<ContractApply> selectByExample(ContractApplyExample example);

    int updateByExampleSelective(@Param("record") ContractApplyWithBLOBs record, @Param("example") ContractApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") ContractApplyWithBLOBs record, @Param("example") ContractApplyExample example);

    int updateByExample(@Param("record") ContractApply record, @Param("example") ContractApplyExample example);
}