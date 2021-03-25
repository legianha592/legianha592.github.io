package org.example.RestAPI.importer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.RestAPI.finalstring.FinalMessage;
import org.example.RestAPI.model.User;
import org.example.RestAPI.model.Wallet;
import org.example.RestAPI.service.IUserService;
import org.example.RestAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WalletExcelImporter {
    @Autowired
    IUserService userService;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Wallet name", "User Id"};
    static String SHEET = "Wallet";

    public boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public List<Wallet> ExcelToWalletEntity(InputStream is) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Wallet> listWallet = new ArrayList<Wallet>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Wallet wallet = new Wallet();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            wallet.setWallet_name(currentCell.getStringCellValue());
                            break;

                        case 1:
                            long user_Id = (long) currentCell.getNumericCellValue();
                            Optional<User> user = userService.findById(user_Id);

                            try{
                                wallet.setUser(user.get());
                            }
                            catch(Exception e){
                                throw new Exception(FinalMessage.NO_USER);
                            }

                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                listWallet.add(wallet);
            }

            workbook.close();

            return listWallet;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
