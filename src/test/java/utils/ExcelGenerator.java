package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

import static cleanTest.TestBaseCucumber.*;

public class ExcelGenerator {
public static void main(String[] args) throws IOException {

        String command = "gradle :test --tests \"cleanTest.TestRunner\"";
        try
        {
            Runtime.getRuntime().exec(command);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private static XSSFWorkbook workbook;
    public static void initExcel(){
        workbook = new XSSFWorkbook();
    }

    public static void excelSheet(List<List<String>> listaArticulos, String nombreHoja) throws IOException {
        int styleCount = 0;
//        XSSFWorkbook workbook = new XSSFWorkbook("output/JavaBooks.xlsx");
        Sheet sheet = workbook.createSheet(nombreHoja);
        sheet.setColumnWidth(1, 15000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 5000);
        CellStyle headerStyle = workbook.createCellStyle();
        CellStyle currencyStyle = workbook.createCellStyle();
        currencyStyle.setDataFormat((short) 8);
        convertUrls2ExcelFormulaUrl(listaArticulos);
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
//        listaArticulos.add(0,Arrays.asList(new String[]{"Descripcion", "Mejor precio", "Precio viejo","Promocion", "Imagen URL"}));
        String [] headers = {"Descripcion", "Mejor precio", "Precio viejo","Promocion", "Imagen URL"};
        /*XSSFFormulaEvaluator formulaEvaluator =
                workbook.getCreationHelper().createFormulaEvaluator();
        formulaEvaluator.evaluateFormulaCell(formulaCell);*/


        int rowCount = 0;
        int columnCount = 0;
        ++rowCount;
        sheet.createRow(++rowCount).createCell(1).setCellValue("Scrap de Farmacity");
        //SETTING HEADERS
        Row headerRow = sheet.createRow(++rowCount);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(++columnCount);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        //SETTING BODY
        for (List<String> rows : listaArticulos) {

            Row row = sheet.createRow(++rowCount);
            columnCount = 0;

            for (String field : rows) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String)
                    cell.setCellValue((String) field);

                if (columnCount == 5)
                    cell.setCellFormula(field);
                if (columnCount == 2) {
                    try {
                        cell.setCellValue(Float.valueOf(field.replace(".", "").replace(",", ".")));
                        cell.setCellStyle(currencyStyle);
                    } catch (Exception e){
                        cell.setCellValue("");
                    }
                }
            }
        }
        ++rowCount;


    }
    public static void saveExcel() throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream("output\\JavaBooks.xlsx")) {
            workbook.write(outputStream);
        }
    }
    public static void convertUrls2ExcelFormulaUrl(List<List<String>> listaArticulos){
        for (List<String> article:
                listaArticulos) {
            article.set(4,"HYPERLINK(\""+ article.get(4) + "\",\"Imagen articulo\")");
        }
    }
}
