/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.drawingblanks.workouttracker;

/**
 *
 * @author matt
 */
public class ModifyWorkout extends javax.swing.JFrame {

    /**
     * Creates new form ModifyWorkout
     */
    public ModifyWorkout() {
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

        AddWeightButton = new javax.swing.JButton();
        EditWeightButton = new javax.swing.JButton();
        DeleteWeightButton = new javax.swing.JButton();
        AddCardioButton = new javax.swing.JButton();
        EditCardioButton = new javax.swing.JButton();
        DeleteCardioButton = new javax.swing.JButton();
        WeightScrollPane = new javax.swing.JScrollPane();
        WeightList = new javax.swing.JList<>();
        CardioScrollPane = new javax.swing.JScrollPane();
        CardioList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modify Exercise");

        AddWeightButton.setText("Add Weight");
        AddWeightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddWeightButtonActionPerformed(evt);
            }
        });

        EditWeightButton.setText("Edit Weight");

        DeleteWeightButton.setText("Delete Weight");

        AddCardioButton.setText("Add Cardio");
        AddCardioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCardioButtonActionPerformed(evt);
            }
        });

        EditCardioButton.setText("Edit Cardio");

        DeleteCardioButton.setText("Delete Cardio");

        WeightScrollPane.setViewportView(WeightList);

        CardioScrollPane.setViewportView(CardioList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CardioScrollPane)
                    .addComponent(WeightScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddWeightButton)
                                .addGap(18, 18, 18)
                                .addComponent(EditWeightButton)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteWeightButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddCardioButton)
                                .addGap(18, 18, 18)
                                .addComponent(EditCardioButton)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteCardioButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddWeightButton)
                    .addComponent(EditWeightButton)
                    .addComponent(DeleteWeightButton))
                .addGap(18, 18, 18)
                .addComponent(WeightScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddCardioButton)
                    .addComponent(EditCardioButton)
                    .addComponent(DeleteCardioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CardioScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddWeightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddWeightButtonActionPerformed
        ModifyWeight mw = new ModifyWeight();
        mw.show();
    }//GEN-LAST:event_AddWeightButtonActionPerformed

    private void AddCardioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCardioButtonActionPerformed
        ModifyCardio mc = new ModifyCardio();
        mc.show();
    }//GEN-LAST:event_AddCardioButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ModifyWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyWorkout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyWorkout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCardioButton;
    private javax.swing.JButton AddWeightButton;
    private javax.swing.JList<String> CardioList;
    private javax.swing.JScrollPane CardioScrollPane;
    private javax.swing.JButton DeleteCardioButton;
    private javax.swing.JButton DeleteWeightButton;
    private javax.swing.JButton EditCardioButton;
    private javax.swing.JButton EditWeightButton;
    private javax.swing.JList<String> WeightList;
    private javax.swing.JScrollPane WeightScrollPane;
    // End of variables declaration//GEN-END:variables
}
