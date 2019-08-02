package com.lifesense.exxcel.dto;

import lombok.*;

/**
 * @author imperater
 * @date 8/1/19
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExcelDTO {

    private Integer no;

    private Integer machineId;

    private String suaKaDate;

    private String suaKaTime;

    private String name;

    private String workTime;

    private String averageTime;
}
