package demo.reports;

import demo.entity.Employees;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class employeeExporterExcel {

    private final XSSFWorkbook book;
    private final XSSFSheet sheet;
    private final List<Employees> employeesList;

    public employeeExporterExcel(List<Employees> employeesList) {
        this.employeesList = employeesList;
        book = new XSSFWorkbook();
        sheet = book.createSheet("Employees");
    }

    private void writeTableHeader(){
        Row row = sheet.createRow(0);
        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("Name");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("Last name");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("Email");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("Phone Number");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("Gender");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("Salary");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("Date");
        cell.setCellStyle(style);
    }

    private void writeDataTable(){
        int numbeRows = 1;
        CellStyle style = book.createCellStyle();
        XSSFFont font = book.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(Employees employees : employeesList){
            Row row = sheet.createRow(numbeRows++);
            Cell cell = row.createCell(0);
            cell.setCellValue(employees.getId());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(employees.getName());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(employees.getLastname());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(employees.getEmail());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(employees.getPhone());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(employees.getGender());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(employees.getSalary());
            sheet.autoSizeColumn(6);
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(employees.getDate().toString());
            sheet.autoSizeColumn(7);
            cell.setCellStyle(style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeTableHeader();
        writeDataTable();
        ServletOutputStream outputStream = response.getOutputStream();
        book.write(outputStream);
        book.close();
        outputStream.close();
    }
}
