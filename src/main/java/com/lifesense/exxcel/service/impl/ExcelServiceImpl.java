package com.lifesense.exxcel.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author imperater
 * @date 2019/08/01
 */
@Service
public class ExcelServiceImpl {

    private static final String XLS = ".xls";

    private static final String XLSX = ".xlsx";

//    @SneakyThrows(Exception.class)
    public static List getBankListByExcel(InputStream in, String fileName)  {
        List list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = null;
        try {
            work = getWorkbook(in, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == work) {
            try {
                throw new Exception("创建Excel工作薄为空！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        try {
            work.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (XLS.equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (XLSX.equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }

    public static void main(String[] args)throws IOException {

        InputStream inputStream = new FileInputStream("/home/imperater/Documents/target.xls");
        getBankListByExcel(inputStream, "target.xls");
    }
}
