/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.drawingblanks.workouttracker;

import main.java.com.drawingblanks.workouttracker.DatabaseManager;

/**
 *
 * @author matt
 */
public class Main extends javax.swing.JFrame {
    private DatabaseManager conn;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        AddWorkoutButton = new javax.swing.JButton();
        EditWorkoutButton = new javax.swing.JButton();
        DeleteWorkoutButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        WorkoutList = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        ConnectDBMenuItem = new javax.swing.JMenuItem();
        BackupDBMenuItem = new javax.swing.JMenuItem();
        ExitMenuItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        UserInfoMenuItem = new javax.swing.JMenuItem();
        AddWeightExerciseMenuItem = new javax.swing.JMenuItem();
        AddCardioExerciseMenuItem = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WorkoutTracker");

        AddWorkoutButton.setText("Add Workout");
        AddWorkoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddWorkoutButtonActionPerformed(evt);
            }
        });

        EditWorkoutButton.setText("Edit Workout");

        DeleteWorkoutButton.setText("Delete Workout");

        jScrollPane1.setViewportView(WorkoutList);

        FileMenu.setText("File");

        ConnectDBMenuItem.setText("Connect Database");
        ConnectDBMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectDBMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(ConnectDBMenuItem);

        BackupDBMenuItem.setText("Backup Database");
        BackupDBMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackupDBMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(BackupDBMenuItem);

        ExitMenuItem.setText("Exit");
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(ExitMenuItem);

        jMenuBar1.add(FileMenu);

        EditMenu.setText("Edit");

        UserInfoMenuItem.setText("User Info");
        UserInfoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserInfoMenuItemActionPerformed(evt);
            }
        });
        EditMenu.add(UserInfoMenuItem);

        AddWeightExerciseMenuItem.setText("Add Weight Exercise");
        AddWeightExerciseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddWeightExerciseMenuItemActionPerformed(evt);
            }
        });
        EditMenu.add(AddWeightExerciseMenuItem);

        AddCardioExerciseMenuItem.setText("Add Cardio Exercise");
        AddCardioExerciseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCardioExerciseMenuItemActionPerformed(evt);
            }
        });
        EditMenu.add(AddCardioExerciseMenuItem);

        jMenuBar1.add(EditMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddWorkoutButton)
                        .addGap(18, 18, 18)
                        .addComponent(EditWorkoutButton)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteWorkoutButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddWorkoutButton)
                    .addComponent(EditWorkoutButton)
                    .addComponent(DeleteWorkoutButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuItemActionPerformed

        System.exit(0);
    }//GEN-LAST:event_ExitMenuItemActionPerformed

    private void ConnectDBMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectDBMenuItemActionPerformed
        conn = DatabaseManager.getInstance();
        WorkoutList.setListData(conn.toStringWorkout().splitWithDelimiters("\n", WIDTH));
    }//GEN-LAST:event_ConnectDBMenuItemActionPerformed

    private void BackupDBMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackupDBMenuItemActionPerformed
        if(conn != null)
            conn.exportDatabase();
    }//GEN-LAST:event_BackupDBMenuItemActionPerformed

    private void UserInfoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserInfoMenuItemActionPerformed
        UserInfo editUser = new UserInfo(conn);
        editUser.setVisible(true);
    }//GEN-LAST:event_UserInfoMenuItemActionPerformed

    private void AddWorkoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddWorkoutButtonActionPerformed
        ModifyWorkout mw = new ModifyWorkout();
        mw.setVisible(true);
    }//GEN-LAST:event_AddWorkoutButtonActionPerformed

    private void AddWeightExerciseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddWeightExerciseMenuItemActionPerformed
        NewWeightExercise x = new NewWeightExercise(conn);
        x.setVisible(true);
    }//GEN-LAST:event_AddWeightExerciseMenuItemActionPerformed

    private void AddCardioExerciseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCardioExerciseMenuItemActionPerformed
        NewCardioExercise x = new NewCardioExercise(conn);
        x.setVisible(true);
    }//GEN-LAST:event_AddCardioExerciseMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AddCardioExerciseMenuItem;
    private javax.swing.JMenuItem AddWeightExerciseMenuItem;
    private javax.swing.JButton AddWorkoutButton;
    private javax.swing.JMenuItem BackupDBMenuItem;
    private javax.swing.JMenuItem ConnectDBMenuItem;
    private javax.swing.JButton DeleteWorkoutButton;
    private javax.swing.JMenu EditMenu;
    private javax.swing.JButton EditWorkoutButton;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem UserInfoMenuItem;
    private javax.swing.JList<String> WorkoutList;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
