package com.lifesense.exxcel.dto;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

/**
 * @author imperater
 * @date 8/1/19
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelResultDTO {

    private String name;

    private String averageTime;

    public static List<ExcelResultDTO> convertData(List<ExcelDTO> target) {
        List<ExcelResultDTO> result = Lists.newArrayListWithExpectedSize(target.size());
        target.forEach(o -> result.add(ExcelResultDTO.builder().name(o.getName()).averageTime(o.getAverageTime()).build()));
        return result;
    }
}
