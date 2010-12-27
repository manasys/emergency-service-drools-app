/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EventGeneratorsConfigPanel.java
 *
 * Created on Dec 23, 2010, 6:27:03 PM
 */

package com.wordpress.salaboy.ui;

import com.wordpress.salaboy.CityEntitiesUtils;
import com.wordpress.salaboy.events.keyboard.KeyboardPulseEventGenerator;
import com.wordpress.salaboy.model.Ambulance;
import java.awt.MouseInfo;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author esteban
 */
public class EventGeneratorsConfigPanel extends javax.swing.JPanel {

    /** Creates new form EventGeneratorsConfigPanel */
    public EventGeneratorsConfigPanel() {
        initComponents();
        
        JComboBox combo = new JComboBox();
        Collection<List<Ambulance>> ambulances = CityEntitiesUtils.ambulances.values();
        for (List<Ambulance> listOfAmbulances : ambulances) {
            for (Ambulance ambulance : listOfAmbulances) {
                String item = "Ambulance - " + ambulance.getId();
                combo.addItem(item);
            }
        }
        
        this.tblGenerators.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(combo));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblGenerators = new javax.swing.JTable();
        btnWiiMoteLookup = new javax.swing.JButton();
        btnApplyConfiguration = new javax.swing.JButton();

        tblGenerators.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Keyboard['q','w']", "Ambulance - 0"},
		{"Keyboard['a','s']", "Ambulance - 1"}
            },
            new String [] {
                "Generator", "Ambulance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGenerators.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblGenerators);

        btnWiiMoteLookup.setText("Start WiiMote Lookup");

        btnApplyConfiguration.setText("Apply Configuration");
        btnApplyConfiguration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyConfigurationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnWiiMoteLookup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApplyConfiguration)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWiiMoteLookup)
                    .addComponent(btnApplyConfiguration))
                .addGap(146, 146, 146))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyConfigurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyConfigurationActionPerformed
        int rowCount = this.tblGenerators.getModel().getRowCount();
        KeyboardPulseEventGenerator keyboardPulseEventGenerator = KeyboardPulseEventGenerator.getInstance();
        keyboardPulseEventGenerator.resetConfigurations();
        for (int i = 0; i < rowCount; i++) {
            String generator = (String) this.tblGenerators.getValueAt(i, 0);
            Long ambulanceId = Long.parseLong(this.tblGenerators.getValueAt(i, 1).toString().split("-")[1].trim());
            Ambulance ambulance = CityEntitiesUtils.getAmbulanceById(ambulanceId);
            
            if (generator.startsWith("Keyboard")){
               //magic parser
               String[] keys = generator.substring("Keyboard['".length(),generator.length()-1).replace("'", "").split(","); 
               keyboardPulseEventGenerator.addConfiguration(keys[0].charAt(0), keys[1].charAt(0), ambulance);
            }else{
                //TODO: salaboy must add wiimote support ;)
            }
        }
    }//GEN-LAST:event_btnApplyConfigurationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApplyConfiguration;
    private javax.swing.JButton btnWiiMoteLookup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGenerators;
    // End of variables declaration//GEN-END:variables

    public static void main (String args[]){
        java.awt.EventQueue.invokeLater(new Runnable()    {

            @Override
            public void run() {
                JFrame jFrame = new JFrame();
                jFrame.add( new EventGeneratorsConfigPanel());
                jFrame.setSize(300, 600);
                jFrame.setVisible(true);
            }
        });
    }
}
