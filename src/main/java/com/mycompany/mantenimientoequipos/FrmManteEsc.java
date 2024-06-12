/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mantenimientoequipos;

import javax.swing.*;

import accesodatos.EquipoDAL;
import accesodatos.MantenimientoDAL;
import entidades.Equipo;
import entidades.Mantenimiento;
import utilerias.OpcionesCRUD;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author juand
 */
public class FrmManteEsc extends javax.swing.JFrame {

    private OpcionesCRUD opcionCRUD;
    private Mantenimiento mantenimientoActual = new Mantenimiento();
    private HashMap<Integer, Equipo> mapEquipos = new HashMap<Integer, Equipo>();


    /**
     * Creates new form FrmManteEsc
     */
    public FrmManteEsc(OpcionesCRUD opcion,Mantenimiento mantenimiento) {
        this.opcionCRUD = opcion;
        initComponents();
        ArrayList<Equipo> equipos = EquipoDAL.obtenerTodos();
        DefaultComboBoxModel<String> modelCombox = new DefaultComboBoxModel(equipos.toArray());
        for (Equipo eq : equipos) {
            mapEquipos.put(eq.getEquipoId(), eq);
        }
        jComboEquipos.setModel(modelCombox);
        if (opcion != OpcionesCRUD.CREAR) {
            asingarDatos(mantenimiento);
            mantenimientoActual = mantenimiento;
        }

    }

    private Mantenimiento obtenerDatos() {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setFecha(jTxtFecha.getText());
        mantenimiento.setDescripcion(jTxtADesc.getText());
        mantenimiento.setCosto(Double.parseDouble(jTxtCosto.getText()));
        Equipo equipo = (Equipo) jComboEquipos.getSelectedItem();
        mantenimiento.setEquipoId(equipo.getEquipoId());
        mantenimiento.setMantenimientoId(mantenimientoActual.getMantenimientoId());
        return mantenimiento;
    }

    private boolean validDatos() {
        boolean valid = true;
        String errorMessage = null;
        String errorField = null;

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (jTxtFecha.getText().isEmpty()) {
                        valid = false;
                        errorMessage = "La fecha es obligatoria";
                        errorField = "Validar campo";
                    }
                    break;
                case 1:
                    if (jTxtADesc.getText().isEmpty()) {
                        valid = false;
                        errorMessage = "La descripcion es obligatoria";
                        errorField = "Validar campo";
                    }
                    break;
                case 2:
                    if (jTxtCosto.getText().isEmpty()) {
                        valid = false;
                        errorMessage = "El costo es obligatorio";
                        errorField = "Validar campo";
                    }
                    break;
                case 3:
                    if (jTxtTipoMante.getText().isEmpty()) {
                        valid = false;
                        errorMessage = "El tipo de mantenimiento es obligatorio";
                        errorField = "Validar campo";
                    }
                    break;
            }
            if (!valid) {
                JOptionPane.showMessageDialog(this, errorMessage, errorField, JOptionPane.WARNING_MESSAGE);
                break;
            }
        }

        return valid;
    }

    private void asingarDatos(Mantenimiento mantenimiento) {
        jTxtFecha.setText(mantenimiento.getFecha());
        jTxtADesc.setText(mantenimiento.getDescripcion());
        jTxtTipoMante.setText(mantenimiento.getTipoMantenimiento());
        jTxtCosto.setText(Double.toString(mantenimiento.getCosto()));
        Equipo equipo = mapEquipos.get(mantenimiento.getEquipoId());
        jComboEquipos.setSelectedItem(equipo);
    }

    private void crearReg() {
        try {
            Mantenimiento mantenimiento = obtenerDatos();
            int result = MantenimientoDAL.crear(mantenimiento);
            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "El mantenimiento fue registrado existosamente", "CREAR MANTENIMIENTO",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Sucedio un error al crear el mantenimiento", "ERROR MANTENIMIENTO",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "ERROR MANTENIMIENTO",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void modificarReg() {
        try {
            Mantenimiento mantenimiento = obtenerDatos();
            int result = MantenimientoDAL.modificar(mantenimiento);
            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "El mantenimiento fue modificado existosamente", "MODIFICAR MANTENIMIENTO",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Sucedio un error al modificar el mantenimiento", "ERROR MANTENIMIENTO",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "ERROR MANTENIMIENTO",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void eliminarReg() {
        try {
            Mantenimiento mantenimiento = obtenerDatos();
            int result = MantenimientoDAL.eliminar(mantenimiento);
            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "El mantenimiento fue eliminado existosamente", "ELIMINAR MANTENIMIENTO",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Sucedio un error al eliminar el mantenimiento", "ERROR MANTENIMIENTO",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "ERROR MANTENIMIENTO",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTxtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtCosto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtTipoMante = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboEquipos = new JComboBox<String>();
        jBtnGuardar = new javax.swing.JButton();
        JBtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtADesc = new javax.swing.JTextArea();

        setTitle("Crear Mantenimiento");


        jLabel1.setText("Fecha");

        jLabel2.setText("Descripción");

        jLabel3.setText("Costo");

        jLabel4.setText("Tipo de mantenimiento");

        jLabel5.setText("Equipo");

        jComboEquipos.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        JBtnCancelar.setText("Cancelar");
        JBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtnCancelarActionPerformed(evt);
            }
        });

        jTxtADesc.setColumns(20);
        jTxtADesc.setRows(5);
        jScrollPane1.setViewportView(jTxtADesc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTxtTipoMante, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtFecha)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JBtnCancelar)
                            .addComponent(jComboEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtTipoMante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnGuardar)
                    .addComponent(JBtnCancelar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnCancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_JBtnCancelarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
         if (null != opcionCRUD) // TODO add your handling code here:
            if (validDatos()) {
                switch (opcionCRUD) {
                    case CREAR:
                        crearReg();
                        this.setVisible(false);
                        break;
                    case MODIFICAR:
                        modificarReg();
                        this.setVisible(false);
                        break;
                    case ELIMINAR:
                        eliminarReg();
                        this.setVisible(false);
                        break;
                    default:
                        break;
                }
            }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private JComboBox<String> jComboEquipos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTxtADesc;
    private javax.swing.JTextField jTxtCosto;
    private javax.swing.JTextField jTxtFecha;
    private javax.swing.JTextField jTxtTipoMante;
    // End of variables declaration//GEN-END:variables
}
