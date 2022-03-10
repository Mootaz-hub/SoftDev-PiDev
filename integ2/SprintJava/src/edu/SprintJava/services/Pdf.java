/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.SprintJava.services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.SprintJava.entities.Produit;
import edu.SprintJava.entities.Session;
import java.io.FileOutputStream;
import java.util.Date;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mariem
 */
public class Pdf {

    public static double TotaleCommande = 0;
      public static  ObservableList<Produit> listpanier = FXCollections.observableArrayList();

    private static String FILE = "C:\\test\\Commande.pdf";

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

//*********************************************************************************************
    //********************  MAIN **********************************************
//    public static void main(String[] args) {
//        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(FILE));
//            document.open();
//            addTitlePage(document);
//            Desktop.getDesktop().open(new File("C:\\test\\Commande.pdf"));
//            document.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //********************************* END MAIN ************************************************  
    //********************************************************************************************   
    private static void addTitlePage(Document document)
            throws DocumentException, BadElementException {
      CommandeCRUD c = new CommandeCRUD();
        String nom=c.affichernom(Session.getUser().getUsername());

        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        Paragraph paragraph = new Paragraph("Votre commande ", catFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        preface.add(paragraph);
        addEmptyLine(preface, 3);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Madame/Monsieur " +nom + " , vous avez passez une commande le " + new Date(), smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Vous trouvez ci-dessous la liste de vos produit.",
                smallBold));
        //****************************************************************************************************

        PdfPTable table = new PdfPTable(3);
        PdfPCell c1 = new PdfPCell(new Phrase("Produit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prix"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantite"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        System.out.println("lsteeee,hereeee"+listpanier);
        for (Produit p : Produit.Panier) {
            table.addCell(p.getNom_produit());
            table.addCell(Double.toString(p.getPrix()));
            table.addCell(Integer.toString(Produit.getQuantite()));

        }
  
       // table.setHeaderRows(1);

        addEmptyLine(preface, 2);
        Paragraph preface2 = new Paragraph();
        preface2.add(new Paragraph(
                "Votre prix totale est : " + TotaleCommande,
                redFont));
        preface2.setAlignment(Element.ALIGN_RIGHT);
        preface2.setIndentationLeft(300);
        Image img;
        try {
            img = Image.getInstance("C:\\Users\\mariem\\Documents\\NetBeansProjects\\gestionshopp\\src\\images\\logo.jpg");
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);
            addEmptyLine(preface, 1);
        } catch (IOException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

        document.add(preface);
        document.add(table);
        document.add(preface2);
        // Start a new page
        // document.newPage();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void createpdf() {
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addTitlePage(document);
            Desktop.getDesktop().open(new File("C:\\test\\Commande.pdf"));
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
