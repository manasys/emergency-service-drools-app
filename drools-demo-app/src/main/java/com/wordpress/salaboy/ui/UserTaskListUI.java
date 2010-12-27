/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UserUI.java
 *
 * Created on Nov 2, 2010, 4:45:08 PM
 */
package com.wordpress.salaboy.ui;

import com.wordpress.salaboy.events.wiimote.WiiMoteOptions;
import com.wordpress.salaboy.CityEntitiesUtils;
import com.wordpress.salaboy.EmergencyService;
import com.wordpress.salaboy.MyDroolsUtilities;
import com.wordpress.salaboy.events.MapAmbulancePositionUpdatedEventNotifier;
import com.wordpress.salaboy.events.TaskListUIAmbulancePositionUpdatedEventNotifier;
import com.wordpress.salaboy.events.TaskListUIEmergencyReachedEventNotifier;
import com.wordpress.salaboy.graphicable.GraphicableFactory;
import com.wordpress.salaboy.ui.MapEventsNotifier.EventType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import org.jbpm.process.workitem.wsht.BlockingGetTaskResponseHandler;
import org.jbpm.task.AccessType;
import org.jbpm.task.Content;
import org.jbpm.task.Task;
import org.jbpm.task.TaskData;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.jbpm.task.service.TaskClient;
import org.jbpm.task.service.responsehandlers.BlockingGetContentResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskSummaryResponseHandler;
import com.wordpress.salaboy.model.Emergency.EmergencyType;
import com.wordpress.salaboy.model.Hospital;
import com.wordpress.salaboy.model.Patient;
import javax.swing.JFrame;

/**
 *
 * @author salaboy
 */
public class UserTaskListUI extends javax.swing.JFrame {

    private TaskClient taskClient;
    private static final long DEFAULT_WAIT_TIME = 5000;
    //private Task currentTask;
    private CityMapUI game;
    //Panels
    private PhoneCallsPanel phoneCallsPanel;
    private AmbulanceControlPanel ambulanceControlPanel;
    private CurrentEmergenciesPanel currentEmergenciesPanel;

    private static UserTaskListUI ui = new UserTaskListUI();
    
    public static UserTaskListUI getInstance(){
        return ui;
    }
    /** Creates new form UserUI */
    public UserTaskListUI() {
        initComponents();
        initTaskServer();
        initTaskClient();


        phoneCallsPanel = new PhoneCallsPanel(this);
        currentEmergenciesPanel = new CurrentEmergenciesPanel(this);
        ambulanceControlPanel = new AmbulanceControlPanel(this);
        this.mainJTabbedPane.add(this.phoneCallsPanel, 0);
        this.mainJTabbedPane.add(this.ambulanceControlPanel, 1);
        this.mainJTabbedPane.add(this.currentEmergenciesPanel, 2);
        this.mainJTabbedPane.setSelectedComponent(this.phoneCallsPanel);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        mainJTabbedPane = new javax.swing.JTabbedPane();
        hospitalJPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        patientJTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        managerjPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlReport = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstReports = new javax.swing.JList();
        btnClearReport = new javax.swing.JButton();
        btnRefreshReport = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuItemConfigreEventGenerators = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jDialog2Layout = new org.jdesktop.layout.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Emergency Service Console");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainJTabbedPane.setPreferredSize(new java.awt.Dimension(300, 400));

        hospitalJPanel.setPreferredSize(new java.awt.Dimension(300, 400));

        patientJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Age", "Gender", "Hospital"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        patientJTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectPatientToAccept(evt);
            }
        });
        jScrollPane4.setViewportView(patientJTable1);

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout hospitalJPanelLayout = new org.jdesktop.layout.GroupLayout(hospitalJPanel);
        hospitalJPanel.setLayout(hospitalJPanelLayout);
        hospitalJPanelLayout.setHorizontalGroup(
            hospitalJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(hospitalJPanelLayout.createSequentialGroup()
                .add(hospitalJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(hospitalJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                    .add(hospitalJPanelLayout.createSequentialGroup()
                        .add(146, 146, 146)
                        .add(jButton1)))
                .addContainerGap())
        );
        hospitalJPanelLayout.setVerticalGroup(
            hospitalJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(hospitalJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 257, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jButton1)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        mainJTabbedPane.addTab("Hospital", hospitalJPanel);

        pnlReport.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlReportComponentShown(evt);
            }
        });

        jScrollPane1.setViewportView(lstReports);

        btnClearReport.setText("Clear");
        btnClearReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearReportActionPerformed(evt);
            }
        });

        btnRefreshReport.setText("Refresh");
        btnRefreshReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshReportActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlReportLayout = new org.jdesktop.layout.GroupLayout(pnlReport);
        pnlReport.setLayout(pnlReportLayout);
        pnlReportLayout.setHorizontalGroup(
            pnlReportLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
            .add(pnlReportLayout.createSequentialGroup()
                .addContainerGap()
                .add(btnRefreshReport)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnClearReport)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        pnlReportLayout.setVerticalGroup(
            pnlReportLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlReportLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlReportLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnRefreshReport)
                    .add(btnClearReport))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reports", pnlReport);

        org.jdesktop.layout.GroupLayout managerjPanelLayout = new org.jdesktop.layout.GroupLayout(managerjPanel);
        managerjPanel.setLayout(managerjPanelLayout);
        managerjPanelLayout.setHorizontalGroup(
            managerjPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(managerjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );
        managerjPanelLayout.setVerticalGroup(
            managerjPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(managerjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Reports");

        mainJTabbedPane.addTab("Manager", managerjPanel);

        fileMenu.setText("File");

        jMenuItem1.setText("Wii Mote Binding");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        menuItemConfigreEventGenerators.setText("Configure Event Generators");
        menuItemConfigreEventGenerators.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigreEventGeneratorsActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemConfigreEventGenerators);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenuItem.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                aboutMenuItemMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(mainJTabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(mainJTabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void selectPatientToAccept(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectPatientToAccept
        // TODO add your handling code here:
    }//GEN-LAST:event_selectPatientToAccept

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            taskClient.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(UserTaskListUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void aboutMenuItemMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_aboutMenuItemMenuKeyPressed
        new About().setVisible(true);
    }//GEN-LAST:event_aboutMenuItemMenuKeyPressed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable()    {

            @Override
            public void run() {
                new About().setVisible(true);

            }
        });
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshPatientsTable();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable()    {

            @Override
            public void run() {
                new WiiMoteOptions(game).setVisible(true);

            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void refreshReportList() {
        DefaultListModel model = new DefaultListModel();
        for (String message : EmergencyService.logger.getLogs()) {
            model.addElement(message);
        }

        this.lstReports.setModel(model);
    }

    private void btnClearReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearReportActionPerformed
        EmergencyService.logger.clearMessages();
        this.refreshReportList();
    }//GEN-LAST:event_btnClearReportActionPerformed

    private void pnlReportComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlReportComponentShown
        this.refreshReportList();
    }//GEN-LAST:event_pnlReportComponentShown

    private void btnRefreshReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshReportActionPerformed
        this.refreshReportList();
    }//GEN-LAST:event_btnRefreshReportActionPerformed

    private void menuItemConfigreEventGeneratorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigreEventGeneratorsActionPerformed
        
        java.awt.EventQueue.invokeLater(new Runnable()    {

            @Override
            public void run() {
                JFrame jFrame = new JFrame();
                jFrame.add( new EventGeneratorsConfigPanel());
                jFrame.setSize(300, 600);
                jFrame.setVisible(true);
            }
        });

    }//GEN-LAST:event_menuItemConfigreEventGeneratorsActionPerformed

    public void refreshPatientsTable() {
        DefaultTableModel tableModel = ((DefaultTableModel) this.patientJTable1.getModel());

        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tableModel.removeRow(0);
        }

        for (Hospital hospital : CityEntitiesUtils.hospitals.values()) {
            for (Patient patient : hospital.getPatients()) {
                tableModel.addRow(new Object[]{patient.getId(), patient.getName(), patient.getAge(), patient.getGender(), hospital.getName()});

            }

        }




    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable()     {

            public void run() {
                new UserTaskListUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btnClearReport;
    private javax.swing.JButton btnRefreshReport;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel hospitalJPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList lstReports;
    private javax.swing.JTabbedPane mainJTabbedPane;
    private javax.swing.JPanel managerjPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemConfigreEventGenerators;
    public javax.swing.JTable patientJTable1;
    private javax.swing.JPanel pnlReport;
    // End of variables declaration//GEN-END:variables

    private void initTaskServer() {
        MyDroolsUtilities.initTaskServer();
    }

    private void initTaskClient() {
        taskClient = MyDroolsUtilities.initTaskClient();
    }

    void setUIMap(CityMapUI game) {
        this.game = game;
     

    }

    public void callHandled() {
        this.phoneCallsPanel.refresh();

    }

    public void sendAmbulance(EmergencyType emergencyType, Long ambulanceId) {
        this.currentEmergenciesPanel.addNewEmergency(ambulanceId);
        mainJTabbedPane.setSelectedComponent(this.currentEmergenciesPanel);
        
       

        //@TODO: NOTE: I Don't like this approach because the UI is deciding something that the process should do, not at HT level
        this.game.addAmbulance(GraphicableFactory.newAmbulance(CityEntitiesUtils.getAmbulanceById(ambulanceId)));
        EmergencyService.getInstance().getMapEventsNotifier().addWorldEventNotifier(EventType.AMBULANCE_POSITION, 
                            new MapAmbulancePositionUpdatedEventNotifier());
        EmergencyService.getInstance().getMapEventsNotifier().addWorldEventNotifier(EventType.AMBULANCE_POSITION,
                new TaskListUIAmbulancePositionUpdatedEventNotifier(this.currentEmergenciesPanel));
        EmergencyService.getInstance().getMapEventsNotifier().addWorldEventNotifier(EventType.EMERGENCY_REACHED,
                new TaskListUIEmergencyReachedEventNotifier());

        EmergencyService.getInstance().getEmergencyReachedNotified().put(ambulanceId, false);

    }

    public void medicalEvaluationCompleted(int priority, String comment) {
        try {
            BlockingTaskSummaryResponseHandler handler = new BlockingTaskSummaryResponseHandler();
            taskClient.getTasksAssignedAsPotentialOwner("doctor", "en-UK", handler);
            List<TaskSummary> taskSums = handler.getResults();
            TaskSummary taskSum = taskSums.get(0);

            taskClient.start(taskSum.getId(), "doctor", null);
            BlockingGetTaskResponseHandler handlerT = new BlockingGetTaskResponseHandler();
            taskClient.getTask(taskSum.getId(), handlerT);
            Task task3 = handlerT.getTask();
            TaskData taskData = task3.getTaskData();

            System.out.println("TaskData = " + taskData);
            BlockingGetContentResponseHandler handlerC = new BlockingGetContentResponseHandler();
            taskClient.getContent(taskData.getDocumentContentId(), handlerC);
            Content content = handlerC.getContent();

            System.out.println("Content= " + content);
            ByteArrayInputStream bais = new ByteArrayInputStream(content.getContent());
            ObjectInputStream ois = new ObjectInputStream(bais);
            String taskinfo2 = (String) ois.readObject();

            System.out.println("TaskInfo 2= " + taskinfo2);
            HashMap<String, Object> info = new HashMap<String, Object>();


            info.put("emergency.priority", String.valueOf(priority));
            ContentData result = new ContentData();
            result.setAccessType(AccessType.Inline);
            result.setType("java.util.Map");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(info);
            out.close();
            result.setContent(bos.toByteArray());

            taskClient.complete(taskSum.getId(), "doctor", result, null);

        } catch (Exception e) {
            Logger.getLogger(UserTaskListUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public JTabbedPane getMainJTabbedPane() {
        return mainJTabbedPane;
    }


    public TaskClient getTaskClient() {
        return taskClient;
    }

    public CurrentEmergenciesPanel getCurrentEmergenciesPanel() {
        return currentEmergenciesPanel;
    }

    public CityMapUI getGame() {
        return game;
    }
    
}
