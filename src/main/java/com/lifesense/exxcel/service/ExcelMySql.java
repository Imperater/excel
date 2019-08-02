package com.lifesense.exxcel.service;

import com.lifesense.exxcel.dto.ExcelResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author imperater
 * @date 8/1/19
 */
public interface ExcelMySql {

    /**
     * batch import.
     *
     * @param file     file
     * @return true or false
     * @throws Exception exception
     */
    List<ExcelResultDTO> batchImport(MultipartFile file) throws Exception;
}
