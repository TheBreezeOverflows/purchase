package com.turing.purchase.mapper;

import com.turing.purchase.entity.ContractDetail;
import com.turing.purchase.entity.ContractDetailExample;
import com.turing.purchase.entity.ContractDetailWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractDetailMapper {
    int countByExample(ContractDetailExample example);

    int deleteByExample(ContractDetailExample example);

    int insert(ContractDetailWithBLOBs record);

    int insertSelective(ContractDetailWithBLOBs record);

    List<ContractDetailWithBLOBs> selectByExampleWithBLOBs(ContractDetailExample example);

    List<ContractDetail> selectByExample(ContractDetailExample example);

    int updateByExampleSelective(@Param("record") ContractDetailWithBLOBs record, @Param("example") ContractDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") ContractDetailWithBLOBs record, @Param("example") ContractDetailExample example);

    int updateByExample(@Param("record") ContractDetail record, @Param("example") ContractDetailExample example);
}