package com.lifesense.exxcel.repository;

import com.lifesense.exxcel.dto.ExcelDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author imperater
 * @date 8/1/19
 */
@Mapper
public interface ExcelMapper {
    /**
     * Insert List data into MySQL.
     *
     * @return true or false.
     * @param information excelDTO lists type.
     */
    List<ExcelDTO> insertResult(List<ExcelDTO> information);

    /**
     * find and transfer data.
     *
     * @return list data
     */
    List<ExcelDTO> findResult();
}
