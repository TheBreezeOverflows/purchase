package com.turing.purchase.entity;

import lombok.Data;

import java.util.List;

@Data
public class EasyUIDataGridJsonEntity {
    private Integer total;
    private List<?> rows;
}
