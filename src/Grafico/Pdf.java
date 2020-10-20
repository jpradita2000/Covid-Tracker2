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
    PdfPTable tabla;
    PdfPCell c;
    public Pdf(Vertice v) {
        this.v = v;
         tabla = new PdfPTable(v.cantidadDeVertices(v) + 1);
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

            
            Vertice v2 = v;
             c = new PdfPCell(new Paragraph(""));
            tabla.addCell(c);
             while(v2!=null){
                   c = new PdfPCell(new Paragraph(""+v2.getId()));
                   tabla.addCell(c);
                   v2=(Vertice) v2.getLink();
             }
             v2=v;
             boolean[][] matrizF = matrizACopiar();
             while(v2!=null){
                 c = new PdfPCell(new Paragraph(""+v2.getId()));
                 tabla.addCell(c);
                 Arista a = v2.getAristas();
                 asegurar(v2.getId(),v2.cantidadDeVertices(a), matrizF);
                 v2=(Vertice) v2.getLink();
             }
            documento.add(tabla);
            documento.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean[] falta(Vertice v) {
        boolean [] arreglo = new boolean[v.cantidadDeVertices(v)]; 
        System.out.println(v.cantidadDeVertices(v));
            Arista a = v.getAristas();
            int c = 0;
           for (int i = 0; i < arreglo.length; i++) {
               if(i==a.getId()&&a!=null){
                   arreglo[i]=true;
                   a=a.getLink();
               }
            
        }
        return arreglo;
    }
    public boolean[][] matrizACopiar(){
        boolean [][] M = new boolean[20][20];
         Vertice v2 = v;
        for (int i = 0; i < v.cantidadDeVertices(v); i++) {
                v2.getNodo(i);
                Arista a = v2.getAristas();
            for (int j = 0; j < v.cantidadDeVertices(v); j++) {
                  if(a.getId()==j){
                      M[i][j]=true;
                      a=a.getLink();
                  }else{
                      M[i][j]=false;
                  }
            }
            
        }
        return M;
    }
    public void asegurar(int f , int c,boolean M[][]){
        int s = 0;
        
        while(s<c){
           if(M[f][s]){
                 this.c = new PdfPCell(new Paragraph(""+1));
                 tabla.addCell(this.c);
           }else{
               this.c = new PdfPCell(new Paragraph(""+0));
               tabla.addCell(this.c);
           }      
            s++;
        }
           
    }

}
