package demo.reports;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import demo.entity.Employees;
import org.springframework.security.core.parameters.P;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class employeeExporterPDF {

    private final List<Employees> employeesList;

    public employeeExporterPDF(List<Employees> employeesList) {
        super();
        this.employeesList = employeesList;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Phone Number", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Salary", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
    }

    private void writeDataTable(PdfPTable table){
        for(Employees employee : employeesList){
            table.addCell(String.valueOf(employee.getId()));
            table.addCell(employee.getName());
            table.addCell(employee.getLastname());
            table.addCell(employee.getEmail());
            table.addCell(employee.getPhone());
            table.addCell(employee.getGender());
            table.addCell(String.valueOf(employee.getSalary()));
            table.addCell(String.valueOf(employee.getDate()));
        }
    }

    public void exportFile(HttpServletResponse response) throws IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);
        Paragraph title = new Paragraph("Employee List", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(title);
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingAfter(15);
        table.setWidths(new float[] {1f, 2.3f, 2.3f, 6f, 2.9f, 3.5f, 2f, 2.2f});
        table.setWidthPercentage(110);
        writeTableHeader(table);
        writeDataTable(table);
        doc.add(table);
        doc.close();
    }
}
