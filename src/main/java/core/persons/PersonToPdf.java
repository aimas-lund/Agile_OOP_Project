package management;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Stream;


public class PersonToPdf {
    public void PersonToPdf() {

    }
    public void PatientToPdf(Patient patient) throws IOException, DocumentException, URISyntaxException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(7);
        addTableHeader(table);
        addRows(table,patient);

        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Unique ID", "Name", "Surname","Gender","Adress","PhoneNumber","Birthdate")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Patient patient) {
        table.addCell(patient.getUniqueId());
        table.addCell(patient.getName());
        table.addCell(patient.getSurname());
        table.addCell(String.format("%d", patient.getGender()));
        table.addCell(patient.getHomeAddress());
        table.addCell(String.format("%d",patient.getPhoneNumber()));
        table.addCell(dateToString(patient.getBirthdate()));


    }
    public String dateToString(Date birthdate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");

        if (birthdate == null) {
            return "N/A";
        } else {
            return format.format(birthdate);
        }
    }




}


