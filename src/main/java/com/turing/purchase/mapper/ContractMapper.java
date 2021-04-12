package com.turing.purchase.mapper;

import com.turing.purchase.entity.Contract;
import com.turing.purchase.entity.ContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractMapper {
    int countByExample(ContractExample example);

    int deleteByExample(ContractExample example);

    int insert(Contract record);

    int insertSelective(Contract record);

    List<Contract> selectByExampleWithBLOBs(ContractExample example);

    List<Contract> selectByExample(ContractExample example);

    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByExampleWithBLOBs(@Param("record") Contract record, @Param("example") ContractExample example);

    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);
}