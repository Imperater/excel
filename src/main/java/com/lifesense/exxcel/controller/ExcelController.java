package com.lifesense.exxcel.controller;

import com.lifesense.exxcel.dto.ExcelResultDTO;
import com.lifesense.exxcel.service.ExcelMySql;
import com.lifesense.exxcel.utils.ExcelData;
import com.lifesense.exxcel.utils.ExportExcelUtils;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author imperater
 * @date 2019/08/01
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    private static final String EMPTY = "文件不能为空";

    private static final String SUCCESS = "上传成功";

    private static final String EXCEL_NAME = "result";

    private final ExcelMySql excelMySql;

    public ExcelController(ExcelMySql excelMySql) {
        this.excelMySql = excelMySql;
    }

    @PostMapping("/import")
    @SneakyThrows(Exception.class)
    public void convertExcelInformation(@RequestParam("file") MultipartFile file, HttpServletResponse response) {

        InputStream in = new FileInputStream("");
        List<ExcelResultDTO> result = excelMySql.batchImport(in);
        ExportExcelUtils.exportExcel(response, "hello.xlsx", convertExcel(result));
    }

    private ExcelData convertExcel(List<ExcelResultDTO> result) {
        return ExcelData.builder().name(EXCEL_NAME).rows(result)
                .titles(Arrays.asList("no", "averageTime", "allTime"))
                .build();
    }

   /* @PostMapping
    @ResponseBody
    public String uploadExcel(HttpServletRequest request) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");
        if (Objects.requireNonNull(file).isEmpty()) {
            return EMPTY;
        }
        @Cleanup InputStream inputStream = file.getInputStream();
        List<List<Object>> list = excelService.getBankListByExcel(inputStream, file.getOriginalFilename());

        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);
            //TODO 随意发挥
            System.out.println(lo);

        }
        return SUCCESS;
    }*/


   /* @GetMapping
    @SneakyThrows(Exception.class)
    public void excel(HttpServletResponse response, HttpServletRequest request) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");
        if (Objects.requireNonNull(file).isEmpty()) {
            return;
        }
        @Cleanup InputStream inputStream = file.getInputStream();
        List<List<Object>> list = excelService.getBankListByExcel(inputStream, file.getOriginalFilename());

        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);

            //TODO 随意发挥
            System.out.println(lo);

        }
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("a1");
        titles.add("a2");
        titles.add("a3");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        List<Object> row = new ArrayList();
        row.add("11111111111");
        row.add("22222222222");
        row.add("3333333333");
        rows.add(row);

        data.setRows(rows);
        //生成本地
        *//*File f = new File("c:/test.xlsx");
        FileOutputStream out = new FileOutputStream(f);
        ExportExcelUtils.exportExcel(data, out);
        out.close();*//*
        ExportExcelUtils.exportExcel(response, "hello.xlsx", data);
    }*/


}
