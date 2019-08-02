package com.lifesense.exxcel.service;

import java.io.InputStream;
import java.util.List;

/**
 * @author imperater
 * @date 2019/08/01
 */
public interface ExcelService {
    /**
     *
     * @param inputStream
     * @param originalFilename
     * @return
     */
    List<List<Object>> getBankListByExcel(InputStream inputStream, String originalFilename);
}
