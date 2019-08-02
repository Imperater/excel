package com.lifesense.exxcel.service.impl;

import com.lifesense.exxcel.dto.ExcelDTO;
import com.lifesense.exxcel.dto.ExcelResultDTO;
import com.lifesense.exxcel.repository.ExcelMapper;
import com.lifesense.exxcel.service.ExcelMySql;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author imperater
 * @date 8/1/19
 */
@Service
public class ExcelMySqlImpl implements ExcelMySql {

    private final ExcelMapper excelMapper;

    private static final String EXP = "^.+\\.(?i)(xls)$";

    public ExcelMySqlImpl(ExcelMapper excelMapper) {
        this.excelMapper = excelMapper;
    }

    @Override
    public List<ExcelResultDTO> batchImport(MultipartFile file) throws Exception {

        List<ExcelDTO> excelData = new ArrayList<>();
        boolean isExcel2003 = excelType(Objects.requireNonNull(file.getOriginalFilename()));
        InputStream is = file.getInputStream();
        Workbook wb = isExcel2003(isExcel2003, is);
        Sheet sheet = wb.getSheetAt(0);
        ExcelDTO excelDTO;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            if (row.getCell(0).getCellType() != 1) {
                throw new Exception("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
            }
            Integer no = Integer.valueOf(row.getCell(1).getStringCellValue());
            Integer machineId = Integer.valueOf(row.getCell(2).getStringCellValue());
            String suaKaDate = row.getCell(3).getStringCellValue();
            String suaKaTime = row.getCell(4).getStringCellValue();

            String name = row.getCell(5).getStringCellValue();
            String workTime = row.getCell(6).getStringCellValue();
            String averageTime = row.getCell(7).getStringCellValue();
            excelData.add(ExcelDTO.builder().no(no).machineId(machineId)
                    .suaKaDate(suaKaDate).suaKaTime(String.valueOf(convertTimeValue(suaKaTime))).name(name)
                    .workTime(workTime).averageTime(averageTime)
                    .build());
        }
        excelMapper.insertResult(excelData);
        List<ExcelDTO> dataFromDatabase = excelMapper.findResult();
        return ExcelResultDTO.convertData(dataFromDatabase);
    }

    @SneakyThrows(IOException.class)
    private Workbook isExcel2003(boolean isExcel2003, InputStream in) {
        return isExcel2003 ? new HSSFWorkbook(in) : new XSSFWorkbook(in);
    }

    private boolean excelType(String fileName) throws Exception {
        if (!fileName.matches(EXP) && !fileName.matches(EXP)) {
            throw new Exception("上传文件格式不正确");
        }
        return !fileName.matches(EXP);
    }

    private Integer convertTimeValue(String target) {
        return target.endsWith("AM") ? 12 - Integer.parseInt(target) : Integer.parseInt(target);
    }
}
