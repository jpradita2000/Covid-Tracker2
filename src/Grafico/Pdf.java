/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import covid.tracker.Vertice;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.pdf.PdfPTable;
import covid.tracker.Arista;

public class Pdf {

    Vertice v;

    public Pdf(Vertice v) {
        this.v = v;
    }

    public void generarMatriz() {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("Matriz del grafo.pdf"));
            documento.open();
            documento.addTitle("Matriz");
            //  documento.addSubject("Using iText (usando iText)");
            //documento.addKeywords("Java, PDF, iText");
            documento.addAuthor("Juan Pablo Prada , Luis Felpe Evilla , Brancys");
            // documento.addCreator("Código Xules");

            PdfPTable tabla = new PdfPTable(v.cantidadDeVertices(v) + 1);
            Vertice v2 = v;
            PdfPCell c = new PdfPCell(new Paragraph(""));
            tabla.addCell(c);
            while (v2 != null) {

                c = new PdfPCell(new Paragraph("" + v2.getId()));
                tabla.addCell(c);

                v2 = (Vertice) v2.getLink();
            }

            v2 = v;
            int contadorT = v.cantidadDeVertices(v);
            int contadorN = 0;
            while (v2 != null) {

                c = new PdfPCell(new Paragraph("" + v2.getId()));
                tabla.addCell(c);

                Arista a = v2.getAristas();
                while (contadorN < contadorT) {
                    if (a != null) {
                        if (a.getId() == contadorN) {
                            c = new PdfPCell(new Paragraph("" + 1));
                            tabla.addCell(c);
                        } else {
                            c = new PdfPCell(new Paragraph("" + 0));
                            tabla.addCell(c);
                        }
                         a = a.getLink();
                    }

                    contadorN++;
                   
                }
                v2 = (Vertice) v2.getLink();
            }
//             

            documento.add(tabla);
            documento.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void falta(Vertice v) {

    }

}
