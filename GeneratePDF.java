import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GeneratePDF {

    public static void main(String[] args) throws IOException {
        String pdf = createPdf();
        String destination = "edf_form_edited.pdf"; // Desination for edited form

        manipulatePdf(pdf, destination);
    }
    private static String createPdf() throws FileNotFoundException {
        String path = "edf_form.pdf";

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document doc = new Document(pdfDocument);

        Paragraph placeholder = new Paragraph("");
        doc.add(placeholder);

        doc.close();

        return path;
    }

    private static void manipulatePdf(String src, String destination) throws IOException {
        PdfReader reader = new PdfReader(src);
        PdfWriter writer = new PdfWriter(destination);
        PdfDocument pdf = new PdfDocument(reader, writer);
        Document doc = new Document(pdf);

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);

        Paragraph oneSpace = new Paragraph("");

        doc.add(new Paragraph("REQUEST FOR EMPLOYEE DEVELOPMENT FUNDS"));

        // Section 1
        doc.add(new Paragraph("1. Employee Information"));
        doc.add(new Paragraph("Name:"));
        PdfFormField nameTF = PdfTextFormField.createText(
                        pdf, new Rectangle(75, 730, 200, 20), "text", "")
                .setFontSize(12);
        form.addField(nameTF);

        doc.add(new Paragraph("Employee VID:"));
        PdfFormField empVidTF = PdfTextFormField.createText(
                        pdf, new Rectangle(120, 705, 200, 20), "text", "")
                .setFontSize(12); // Change the x offset to empVid + 125 and y offsett to same as nameTF when on one line
        form.addField(empVidTF);

        doc.add(new Paragraph("Department:"));
        PdfFormField deptTF = PdfTextFormField.createText(
                        pdf, new Rectangle(120, 680, 200, 20), "text", "")
                .setFontSize(12);
        form.addField(deptTF);

        doc.add(new Paragraph("Position:"));
        PdfFormField posTF = PdfTextFormField.createText(
                        pdf, new Rectangle(120, 655, 200, 20), "text", "")
                .setFontSize(12);
        form.addField(posTF);

        doc.add(new Paragraph("Start date of Full-Time Employment:"));
        PdfFormField startTF = PdfTextFormField.createText(
                        pdf, new Rectangle(230, 630, 200, 20), "text", "")
                .setFontSize(12);
        form.addField(startTF);


        doc.add(new Paragraph("Budget information:"));

        doc.add(new Paragraph("Invoice:"));
        PdfFormField invoiceTF = PdfTextFormField.createText(
                        pdf, new Rectangle(120, 580, 200, 20), "text", "")
                .setFontSize(12);
        form.addField(invoiceTF);

        doc.add(new Paragraph("Account #:"));
        PdfFormField accountTF = PdfTextFormField.createText(
                        pdf, new Rectangle(120, 555, 200, 20), "text", "")
                .setFontSize(12);
        form.addField(accountTF);

        doc.add(new Paragraph("Complete Section 2 or 3, then complete sections 4 and5.").setFontSize(10));

        doc.close();
        reader.close();
        writer.close();

    }

}
