package core.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import core.buildings.Department;
import core.persons.Patient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;


public class PersonToPdf {

    public void PatientToPdf(Department department) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("patients.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(7);
        addTableHeader(table);

        for (Patient p : department.getPatients()) {

            addRows(table,p);

        }
        document.add(table);

        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Unique ID", "Name", "Surname","Gender","Birthdate","Address","PhoneNumber")
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
        table.addCell((patient.getGender()).toString());
        table.addCell(dateToString(patient.getBirthdate()));
        table.addCell(patient.getHomeAddress());
        table.addCell(String.format("%d",patient.getPhoneNumber()));



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


