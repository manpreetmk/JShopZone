
import java.awt.Dimension;
import java.awt.Toolkit;

public class EmployeeMenu extends javax.swing.JFrame
{

    public EmployeeMenu()
    {
        initComponents();
        setVisible(true);
        setTitle("Employee Menu");
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();

        int x = (int) ((screenWidth / 2) - (frameWidth / 2));
        int y = (int) ((screenHeight / 2) - (frameHeight / 2));
        setLocation(x, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        addEmployeeBt = new javax.swing.JButton();
        viewEmployeeBt = new javax.swing.JButton();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addEmployeeBt.setText("Add Employee");
        addEmployeeBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addEmployeeBtActionPerformed(evt);
            }
        });

        viewEmployeeBt.setText("View Employee");
        viewEmployeeBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                viewEmployeeBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEmployeeBt, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(viewEmployeeBt, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addEmployeeBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(viewEmployeeBt)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addEmployeeBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addEmployeeBtActionPerformed
    {//GEN-HEADEREND:event_addEmployeeBtActionPerformed

        new AddEmployee();
        dispose();
    }//GEN-LAST:event_addEmployeeBtActionPerformed

    private void viewEmployeeBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_viewEmployeeBtActionPerformed
    {//GEN-HEADEREND:event_viewEmployeeBtActionPerformed

        new ViewEmployee();
        dispose();
    }//GEN-LAST:event_viewEmployeeBtActionPerformed

    public static void main(String args[])
    {

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new EmployeeMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEmployeeBt;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JButton viewEmployeeBt;
    // End of variables declaration//GEN-END:variables
}
