package org.example.RestAPI.exporter;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.RestAPI.model.User;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class UserExcelExporter {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "User name", "Password", "Created date", "Modified date", "List wallet" };
    static String SHEET = "User";

    public static ByteArrayInputStream UserEntityToExcel(List<User> listUser){
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (User user : listUser) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUser_name());
                row.createCell(2).setCellValue(user.getPassword());
                row.createCell(3).setCellValue(user.getCreated_date().toString());
                row.createCell(4).setCellValue(user.getModified_date().toString());
                row.createCell(5).setCellValue(user.getListWallet().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
