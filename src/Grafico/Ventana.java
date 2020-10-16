/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import covid.tracker.Arista;
import covid.tracker.Grafo;
import covid.tracker.CovidTracker;
import covid.tracker.Nodo;
import covid.tracker.Vertice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author JPPM
 */
public class Ventana extends javax.swing.JFrame {

    // CovidTracker cvt;
    int p;
    int x;
    int y;
    int reguladorIteracion = 0;
    Punto[] puntos;
    int contadorParaAgregarColumna;

    private Grafo cvt;

    public Ventana() {

        initComponents();
        puntos = new Punto[70];
        p=0;
        x = 40;
        y = this.jPanel1.getHeight() / 6;
        cvt = new Grafo();
        contadorParaAgregarColumna = 0;
        this.jPanel1.setBackground(new Color(255, 205, 178));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        grupoBotones.add(opcion1);
        grupoBotones.add(opcion2);
        grupoBotones.add(opcion3);
    }

    public void seleccionado() {
        if (this.opcion1.isSelected()) {
            return;
        } else if (this.opcion2.isSelected()) {
            cvt.aplicarMascarilla();
        } else if (this.opcion3.isSelected()) {
            cvt.aplicarMascarillaAleatorio();
        }

    }

    public int configuracion() {
        if (this.opcion1.isSelected()) {
            this.opcion2.setEnabled(false);
            this.opcion3.setEnabled(false);
            return 1;
        } else if (this.opcion2.isSelected()) {
            this.opcion1.setEnabled(false);
            this.opcion3.setEnabled(false);
            return 2;
        } else if (this.opcion3.isSelected()) {
            this.opcion1.setEnabled(false);
            this.opcion2.setEnabled(false);
            return 3;
        }
        return -1;
    }

    public int numeroValido() {
        int n = Integer.parseInt(this.verticeField.getText());
        while (n <= 0) {
            this.verticeField.setText("");
            n = Integer.parseInt(JOptionPane.showInputDialog("DIGITE NUMERO DE VERTICES MAYOR A 0"));
        }
        return n;
    }
    
    public void dibujarVertices(int opcion) {
        Graphics g = this.jPanel1.getGraphics();
        Vertice v = cvt.getPtr();
        int op = 1;
        int y2;
        if (opcion == 1) {
            while (v != null) {
                Punto punto = new Punto();
                switch (op) {
                    case 1:
                        y2 = this.y * 3;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2+30);
                            g.setColor(Color.black);
                        } else {
                            g.drawOval(x, y2, 60, 60);
                            g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        }
                        punto.setCordenada(x, y2);
                        puntos[p]=punto;
                        p++;
                        op++;
                        break;
                    case 2:
                        y2 = this.y * 2;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2+30);
                            g.setColor(Color.black);

                        } else {
                            g.drawOval(x, y2, 60, 60);
                            g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        }
                        punto.setCordenada(x, y2);
                        puntos[p]=punto;
                        p++;
                        op++;
                        break;
                    case 3:
                        y2 = this.y * 4;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2+30);
                            g.setColor(Color.black);

                        } else {
                            g.drawOval(x, y2, 60, 60);
                            g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        }
                        punto.setCordenada(x, y2);
                        puntos[p]=punto;
                        p++;
                        this.x = this.x + 150;
                        op = 1;
                        break;
                }

                v = (Vertice) v.getLink();
            }
            this.x=40;
        } else {
             while (v != null) {
                switch (op) {
                    case 1:
                        y2 = this.y * 3;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2+30);
                            g.setColor(Color.black);
                        }
                       // g.drawString("usuario " + v.getId(), x + 5, y2 + 30);
                        op++;
                        break;
                    case 2:
                        y2 = this.y * 2;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2+30);
                            g.setColor(Color.black);

                        } 
                        op++;
                        break;
                    case 3:
                        y2 = this.y * 4;
                        if (v.isInfectado()) {
                            g.fillOval(x, y2, 60, 60);
                            g.setColor(Color.white);
                            g.drawString("usuario " + v.getId(), x, y2+30);
                            g.setColor(Color.black);

                        } 
                        this.x = this.x + 150;
                        op = 1;
                        break;
                }

                v = (Vertice) v.getLink();
            }
            this.x=40;
        }
    }
    public void dibujarAristas(){
         Graphics g = this.jPanel1.getGraphics();
         Vertice v = cvt.getPtr();
         while(v!=null){
             Arista   a = v.getAristas();
             while(a!=null){
                 Punto coordenada1 = puntos[a.getId()];
                 Punto coordenada2 = puntos[v.getId()];
//                 switch(cuadrante(coordenada1,coordenada2)){
//                     case 1:
//                         coordenada1.setX(coordenada1.x+30);
//                         coordenada2.setY(coordenada2.y+30);
//                     break;
//                     case 2:
//                         coordenada1.setX(coordenada1.x-30);
//                         coordenada2.setY(coordenada2.y+30);
//                     break;
//                     case 3:
//                         coordenada1.setX(coordenada1.x-30);
//                          coordenada2.setY(coordenada2.y-30);
//                     break;
//                     case 4:
//                         coordenada1.setX(coordenada1.x+30);
//                         coordenada2.setY(coordenada2.y-30);
//                     break;
//                 }        
                 g.drawLine(coordenada1.x, coordenada1.y,coordenada2.x ,coordenada2.y);
                 a=a.getLink();
             }
             v = (Vertice) v.getLink();
         }
        
    }
    public int cuadrante(Punto coordenada1, Punto coordenada2) {

        int n = -1;

        if (coordenada2.x > coordenada1.x && coordenada2.y < coordenada1.y) {
            n = 1;
        } else if (coordenada2.x < coordenada1.x && coordenada2.y < coordenada1.y) {
            n = 2;
        } else if (coordenada2.x < coordenada1.x && coordenada2.y > coordenada1.y) {
            n=3;
        }else{
            n=4;
        }

        return n;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotones = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        verticeField = new javax.swing.JTextField();
        opcion1 = new javax.swing.JRadioButton();
        opcion2 = new javax.swing.JRadioButton();
        opcion3 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Iteracion = new javax.swing.JButton();
        iteraciones = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        verticeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticeFieldActionPerformed(evt);
            }
        });

        opcion1.setText("Mascarilla");
        opcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion1ActionPerformed(evt);
            }
        });

        opcion2.setText("Sin Mascarilla");
        opcion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion2ActionPerformed(evt);
            }
        });

        opcion3.setText("Aleatorio");
        opcion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion3ActionPerformed(evt);
            }
        });

        Iteracion.setText("Iterar");
        Iteracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IteracionActionPerformed(evt);
            }
        });

        jLabel1.setText("Vertices");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(opcion3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(opcion2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                            .addComponent(jLabel2))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(verticeField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(opcion1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Iteracion)
                        .addGap(67, 67, 67)
                        .addComponent(iteraciones)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verticeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(50, 50, 50)
                .addComponent(opcion1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcion2)
                    .addComponent(jLabel2))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcion3)
                    .addComponent(jLabel3))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Iteracion)
                    .addComponent(iteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(297, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IteracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IteracionActionPerformed

        if (verticeField.getText().isEmpty() == false) {
            if (configuracion() != -1) {

                if (reguladorIteracion == 0) {
                    cvt.setVertices(numeroValido());
                    cvt.generarGrafo();
                    seleccionado();
                    this.dibujarVertices(1);
                    this.dibujarAristas();
                    reguladorIteracion++;
                } else {
                    cvt.infectar(cvt.getPtr());
                    this.dibujarVertices(2);
                    reguladorIteracion++;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione modalidad");
            }

        } else {
            JOptionPane.showMessageDialog(null, "DIGITE UNA CANTIDAD DE VERTICES");
        }
        String label = "" + reguladorIteracion;
        this.iteraciones.setText(label);
        cvt.listarVertices();
        cvt.mostrarInfectados();
    }//GEN-LAST:event_IteracionActionPerformed

    private void verticeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verticeFieldActionPerformed

    private void opcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion1ActionPerformed
        //cvt.aplicarMascarilla();
    }//GEN-LAST:event_opcion1ActionPerformed

    private void opcion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion2ActionPerformed

    }//GEN-LAST:event_opcion2ActionPerformed

    private void opcion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion3ActionPerformed
        // cvt.aplicarMascarillaAleatorio();
    }//GEN-LAST:event_opcion3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Iteracion;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.JLabel iteraciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton opcion1;
    private javax.swing.JRadioButton opcion2;
    private javax.swing.JRadioButton opcion3;
    private javax.swing.JTextField verticeField;
    // End of variables declaration//GEN-END:variables
}
