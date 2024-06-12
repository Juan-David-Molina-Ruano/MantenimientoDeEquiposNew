    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
     */
    package equipos;

    import javax.swing.JOptionPane;

    import accesodatos.EquipoDAL;
    import entidades.Equipo;
    import utilerias.OpcionesCRUD;

    import java.sql.Date;
    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;

    /**
     *
     * @author juand
     */
    public class FrmEquiposEsc extends javax.swing.JFrame {

        private OpcionesCRUD opcionCRUD;
        private Equipo equipoActual = new Equipo();

        /**
         * Creates new form FrmEquiposEsc
         */
        public FrmEquiposEsc(OpcionesCRUD opcion, Equipo equipo) {
            this.opcionCRUD = opcion;
            initComponents();
            if (opcion != OpcionesCRUD.CREAR) {
                asingarDatos(equipo);
                equipoActual = equipo;
            }
        }

        private Equipo obtenerDatos() {
            Equipo equipo = new Equipo();
            equipo.setNumeroSerie(jTxtNumSerie.getText());
            equipo.setModelo(jTxtModelo.getText());
            equipo.setMarca(jTxtMarca.getText());
            equipo.setUbicacion(jTxtUbi.getText());
            try {
                equipo.setFechaAdqui(Date.valueOf(jTxtFechaAd.getText()));
            } catch (Exception e) {
                // Manejar el error si la fecha no está en el formato esperado
                JOptionPane.showMessageDialog(this, "Verifique el formato de la fecha que sea dd|MM|yyyy" + e, "Error de formato", JOptionPane.ERROR_MESSAGE);
                // Establecer la bandera en true si hay un error en el formato de fecha
            }
            equipo.setEquipoId(equipoActual.getEquipoId());
            // Retornar null si hay un error en el formato de fecha
            return equipo;
        }

        private boolean validarDatos() {
            boolean valid = true;

            if (jTxtModelo.getText().isEmpty()) {
                valid = false;
                JOptionPane.showMessageDialog(this,
                        "El modelo es obligatorio",
                        "Validar campo",
                        JOptionPane.WARNING_MESSAGE);
            }

            if (jTxtMarca.getText().isEmpty()) {
                valid = false;
                JOptionPane.showMessageDialog(this,
                        "La marca es obligatoria",
                        "Validar campo",
                        JOptionPane.WARNING_MESSAGE);
            }

            String fechatxt = jTxtFechaAd.getText();
            if (fechatxt.isEmpty()) {
                valid = false;
                JOptionPane.showMessageDialog(this,
                        "La fecha de adquisición es obligatoria",
                        "Validar campo",
                        JOptionPane.WARNING_MESSAGE);
            }

            return valid;
        }

        private void asingarDatos(Equipo equipo) {
            jTxtModelo.setText(equipo.getModelo());
            jTxtMarca.setText(equipo.getMarca());
            jTxtNumSerie.setText(equipo.getNumeroSerie());
            jTxtFechaAd.setText(equipo.getFechaAdqui().toString());
            jTxtUbi.setText(equipo.getUbicacion());

        }

        private void crearReg() {
            try {
                Equipo equipo = obtenerDatos();
                int result = EquipoDAL.crear(equipo);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this,
                            "El equipo fue registrado existosamente", "CREAR EQUIPO",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Sucedio un error al crear el equipo", "ERROR EQUIPO",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex, "ERROR EQUIPO",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        private void modificarReg() {
            try {
                Equipo equipo = obtenerDatos();
                int result = EquipoDAL.modificar(equipo);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this,
                            "El equipo fue modificado existosamente", "MODIFICAR EQUIPO",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Sucedio un error al modificar el equipo", "ERROR EQUIPO",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex, "ERROR EQUIPO",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        private void eliminarReg() {
            try {
                Equipo equipo = obtenerDatos();
                int result = EquipoDAL.eliminar(equipo);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this,
                            "El equipo fue eliminado existosamente", "ELIMINAR EQUIPO",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Sucedio un error al eliminar el equipo", "ERROR EQUIPO",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(), "ERROR EQUIPO",
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

            jTxtUbi = new javax.swing.JTextField();
            jBtnGuardar = new javax.swing.JButton();
            JBtnCancelar = new javax.swing.JButton();
            jLabel1 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jTxtFechaAd = new javax.swing.JTextField();
            jLabel4 = new javax.swing.JLabel();
            jTxtNumSerie = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            jTxtModelo = new javax.swing.JTextField();
            jTxtMarca = new javax.swing.JTextField();
            jLabel6 = new javax.swing.JLabel();

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

            jLabel1.setText("Numero de serie");

            jLabel3.setText("Fecha de adquisición");

            jLabel4.setText("Ubicacion");

            jLabel5.setText("Marca");

            jLabel6.setText("Modelo");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jBtnGuardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBtnCancelar)
                            .addContainerGap(278, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTxtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTxtNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTxtUbi, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTxtFechaAd, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(59, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTxtNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtFechaAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(57, 57, 57)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnGuardar)
                        .addComponent(JBtnCancelar))
                    .addContainerGap(33, Short.MAX_VALUE))
            );

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
            if (null != opcionCRUD) {
                if(validarDatos()){
                    // TODO add your handling code here:
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
            }

        }//GEN-LAST:event_jBtnGuardarActionPerformed

        private void JBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnCancelarActionPerformed
            // TODO add your handling code here:
            this.setVisible(false);
        }//GEN-LAST:event_JBtnCancelarActionPerformed

        /**
         * @param args the command line arguments
         */

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton JBtnCancelar;
        private javax.swing.JButton jBtnGuardar;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JTextField jTxtFechaAd;
        private javax.swing.JTextField jTxtMarca;
        private javax.swing.JTextField jTxtModelo;
        private javax.swing.JTextField jTxtNumSerie;
        private javax.swing.JTextField jTxtUbi;
        // End of variables declaration//GEN-END:variables
    }
