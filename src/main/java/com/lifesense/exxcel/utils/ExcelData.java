package com.lifesense.exxcel.utils;

import com.lifesense.exxcel.dto.ExcelResultDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author imperater
 * @date 2019/08/01
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4444017239100620999L;

    /**
     * Excel Tittle
     */
    private List<String> titles;

    /**
     * Data
     */
    private List<ExcelResultDTO> rows;

    /**
     * 页签名称
     */
    private String name;

}

