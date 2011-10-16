/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AmbulancePanel.java
 *
 * Created on Nov 24, 2010, 4:17:24 PM
 */

package com.wordpress.salaboy.emergencyservice.taskforms;


import com.wordpress.salaboy.api.HumanTaskService;
import com.wordpress.salaboy.context.tracking.ContextTrackingProvider;
import com.wordpress.salaboy.context.tracking.ContextTrackingService;
import com.wordpress.salaboy.emergencyservice.tasklists.SelectVehicleTaskListPanel;
import com.wordpress.salaboy.model.Vehicle;
import com.wordpress.salaboy.model.serviceclient.PersistenceService;
import com.wordpress.salaboy.model.serviceclient.PersistenceServiceProvider;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.ws_ht.api.TStatus;
import org.example.ws_ht.api.TTask;
import org.example.ws_ht.api.wsdl.IllegalAccessFault;
import org.example.ws_ht.api.wsdl.IllegalArgumentFault;
import org.example.ws_ht.api.wsdl.IllegalStateFault;

/**
 * @author salaboy
 * @author esteban
 */
public class SelectVehicleTaskFormPanel extends javax.swing.JPanel {
    private HumanTaskService taskClient; 
    private String taskId;
    private SelectVehicleTaskListPanel parent;
    private final PersistenceService persistenceService;
    private final ContextTrackingService trackingService;
    
    /** Creates new form AmbulancePanel */
    public SelectVehicleTaskFormPanel(SelectVehicleTaskListPanel parent, HumanTaskService taskClient,  String taskId) throws IOException {
        
        this.parent = parent;
        this.taskClient = taskClient;
        this.taskId = taskId;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ContextTrackingImplementation", ContextTrackingProvider.ContextTrackingServiceType.IN_MEMORY);
        persistenceService = PersistenceServiceProvider.getPersistenceService();

        trackingService = ContextTrackingProvider.getTrackingService();
        initComponents();
        
//        lblMedBone.setVisible(false);
//        lblMedFire.setVisible(false);
//        lblMedHeart.setVisible(false);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        taskActionjButton = new javax.swing.JButton();
        sendSelectedVehiclesjButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedVehiclesjTable = new javax.swing.JTable();

        setName("Ambulances"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 480));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Task Actions"));

        taskActionjButton.setText("Start");
        taskActionjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskActionjButtonActionPerformed(evt);
            }
        });

        sendSelectedVehiclesjButton.setText("Dispatch Vehicle(s)");
        sendSelectedVehiclesjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendSelectedVehiclesjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taskActionjButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sendSelectedVehiclesjButton)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taskActionjButton)
                    .addComponent(sendSelectedVehiclesjButton))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehicle List"));

        btnRefresh.setText("Refresh Vehicle List");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        Object [][] vehicles = new Object[persistenceService.getAllVehicles().size()][2];
        int i = 0;
        for(Vehicle vehicle : persistenceService.getAllVehicles()){
            vehicles[i][0] = vehicle.getId();
            vehicles[i][1] = vehicle.getName();
            i++;
        }
        selectedVehiclesjTable.setModel(new javax.swing.table.DefaultTableModel(
            vehicles,
            new String [] {
                "ID", "Vehicle"
            }
        ));
        jScrollPane1.setViewportView(selectedVehiclesjTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnRefresh)
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(334, 334, 334))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sendSelectedVehiclesjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendSelectedVehiclesjButtonActionPerformed
        ObjectOutputStream out = null;
        try {
            int[] selected = selectedVehiclesjTable.getSelectedRows();
            
           List<Vehicle> selectedVehicles = new ArrayList<Vehicle>(selected.length);
           
            for(int i = 0; i < selected.length; i++){
                selectedVehicles.add(persistenceService.loadVehicle((String)selectedVehiclesjTable.getModel().getValueAt(i, 0)));
            }
            Map<String, Object> info = new HashMap<String, Object>();
            info.put("emergency.vehicles", selectedVehicles);
            

//            ContentData result = new ContentData();
//            result.setAccessType(AccessType.Inline);
//            result.setType("java.util.Map");
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            out = new ObjectOutputStream(bos);
//            out.writeObject(info);
//            result.setContent(bos.toByteArray());
           
            getTaskClient().setAuthorizedEntityId("garage_emergency_service");
            getTaskClient().complete(this.taskId, info);
            

            this.parent.hideDialog();

        } catch (IllegalArgumentFault ex) {
            Logger.getLogger(SuggestedProceduresTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateFault ex) {
            Logger.getLogger(SuggestedProceduresTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessFault ex) {
            Logger.getLogger(SuggestedProceduresTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(SuggestedProceduresTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendSelectedVehiclesjButtonActionPerformed

    private void taskActionjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskActionjButtonActionPerformed
        try {
            TTask task = getTaskClient().getTaskInfo(taskId);
            TStatus status = task.getStatus();
            String statusName = status.name();
            System.out.println("Status of the TASK = "+statusName);
            //@TODO: check the status and show or not the button!
        } catch (IllegalArgumentFault ex) {
            Logger.getLogger(EmergencyMinimalQuestionnaireTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        getTaskClient().setAuthorizedEntityId("garage_emergency_service");
        try {
            getTaskClient().start(taskId);
        } catch (IllegalArgumentFault ex) {
            Logger.getLogger(EmergencyMinimalQuestionnaireTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateFault ex) {
            Logger.getLogger(EmergencyMinimalQuestionnaireTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessFault ex) {
            Logger.getLogger(EmergencyMinimalQuestionnaireTaskFormPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        taskActionjButton.setText("Started...");
        taskActionjButton.setEnabled(false);
}//GEN-LAST:event_taskActionjButtonActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        Object [][] vehicles = new Object[persistenceService.getAllVehicles().size()][2];
        int i = 0;
        for(Vehicle vehicle : persistenceService.getAllVehicles()){
            vehicles[i][0] = vehicle.getId();
            vehicles[i][1] = vehicle.getName();
            i++;
        }
        selectedVehiclesjTable.setModel(new javax.swing.table.DefaultTableModel(
            vehicles,
            new String [] {
                "ID", "Vehicle"
            }
        ));
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable selectedVehiclesjTable;
    private javax.swing.JButton sendSelectedVehiclesjButton;
    private javax.swing.JButton taskActionjButton;
    // End of variables declaration//GEN-END:variables

//    void configurePanel(String taskinfo) {
//        String[] values= taskinfo.split(",");
//       // String doctorId = values[0].trim(); 
       // String ambulanceId = values[1].trim(); 
//        String patientAge = values[0].trim(); 
//        String patientGender = values[1].trim(); 
//        String emergencyLocationX = values[2].trim(); 
//        String emergencyLocationY = values[3].trim(); 
//        String emergencyType = values[4].trim(); 
        
       // this.ambulancejList.setSelectedIndex(Integer.parseInt(ambulanceId));
       // this.doctorsjList.setSelectedIndex(Integer.parseInt(doctorId));
//        
//        this.lblMedBone.setVisible(false);
//        this.lblMedFire.setVisible(false);
//        this.lblMedHeart.setVisible(false);
        
//        Ambulance ambulance = CityEntitiesUtils.getAmbulanceById(Long.parseLong(ambulanceId));
//        List<MedicalKit> kits = ambulance.getKits();
//        
//        for (MedicalKit medicalKit : kits) {
//            switch (medicalKit.getType()){
//                case BONES:
//                    this.lblMedBone.setVisible(true);
//                    break;
//                case BURNS:
//                    this.lblMedFire.setVisible(true);
//                    break;
//                case REANIMATION:
//                    this.lblMedHeart.setVisible(true);
//                    break;
//                    
//            }
//        }
        
//        this.medicalKitsJInternalFrame.validate();
        
//    }

    private HumanTaskService getTaskClient() {
        return this.taskClient;
    }
    
}
