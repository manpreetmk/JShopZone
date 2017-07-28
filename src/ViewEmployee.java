
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ViewEmployee extends javax.swing.JFrame
{

    ArrayList<Data> al;
    TableModel employee;
    int currentSelection = -1;

    public ViewEmployee()
    {
        initComponents();
        setVisible(true);
        setTitle("View Employee");
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();

        int x = (int) ((screenWidth / 2) - (frameWidth / 2));
        int y = (int) ((screenHeight / 2) - (frameHeight / 2));
        setLocation(x, y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        al = new ArrayList<>();
        employee = new TableModel();
        employeeTb.setModel(employee);
        getEmployeeData();
    }

    void getEmployeeData()
    {
        try
        {
            al.clear();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from employee");
            while (rs.next())
            {
                al.add(new Data(rs.getInt("employee_id"), rs.getString("employee_name"), rs.getString("employee_phone"), rs.getString("employee_email")));
            }
            employee.fireTableDataChanged();
            rs.close();
            stmt.close();
            con.close();
            currentSelection = -1;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTb = new javax.swing.JTable();
        editBt = new javax.swing.JButton();
        backBt = new javax.swing.JButton();
        deleteBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        employeeTb.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                employeeTbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employeeTb);

        editBt.setText("Edit");
        editBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBtActionPerformed(evt);
            }
        });

        backBt.setText("Back");
        backBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                backBtActionPerformed(evt);
            }
        });

        deleteBt.setText("Delete");
        deleteBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(editBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(deleteBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBt)
                    .addComponent(backBt)
                    .addComponent(editBt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBtActionPerformed
    {//GEN-HEADEREND:event_deleteBtActionPerformed

        if (employeeTb.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "Select a Row to Delete");
            return;
        }
        String employeeName = al.get(employeeTb.getSelectedRow()).employeeName;
        int n = JOptionPane.showConfirmDialog(this, "Sure To Delete " + employeeName + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION)
        {
            new DatabaseFunctions().deleteRow("employee", "employee_name", employeeName);
            getEmployeeData();

        }
    }//GEN-LAST:event_deleteBtActionPerformed

    private void editBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editBtActionPerformed
    {//GEN-HEADEREND:event_editBtActionPerformed
        if (employeeTb.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "No Row Selected");
            return;
        }
        Object[] buttons =
        {
            "Apply Changes", "Discard Changes"
        };
        try
        {
            int id = al.get(employeeTb.getSelectedRow()).employeeId;
            String employeeEmail = al.get(employeeTb.getSelectedRow()).employeeEmail.trim();
            String employeeNumber = al.get(employeeTb.getSelectedRow()).employeeNumber.trim();

            OptionPanel1 pa = new OptionPanel1(employeeEmail, employeeNumber);
            int n = JOptionPane.showOptionDialog(this, pa, "Edit Employee", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

            if (n == JOptionPane.YES_OPTION)
            {
                String phone = pa.numberTf.getText().trim();
                String email = pa.emailTf.getText().trim();
                if (phone.isEmpty() || email.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Insufficient Information");
                    return;
                }
                for (int i = 0; i < phone.length(); i++)
                {
                    if (phone.charAt(i) < '0' || phone.charAt(i) > '9')
                    {
                        JOptionPane.showMessageDialog(this, "Invalid Phone Number");
                        return;
                    }
                }
                if (!(email.contains("@") && email.contains(".") && email.indexOf('@') < email.lastIndexOf('.')))
                {
                    JOptionPane.showMessageDialog(this, "Invalid Email-ID");
                    return;
                }
                editEmployee(id, phone, email);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_editBtActionPerformed

    private void employeeTbMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_employeeTbMouseClicked
    {//GEN-HEADEREND:event_employeeTbMouseClicked
        if (employeeTb.getSelectedRow() == currentSelection)
        {
            employeeTb.clearSelection();
        }
        currentSelection = employeeTb.getSelectedRow();
    }//GEN-LAST:event_employeeTbMouseClicked

    private void backBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backBtActionPerformed
    {//GEN-HEADEREND:event_backBtActionPerformed

        new EmployeeMenu();
        dispose();
    }//GEN-LAST:event_backBtActionPerformed

    void editEmployee(int id, String phone, String email)
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from employee where employee_id = '" + id + "'");
            if (rs.next())
            {
                rs.updateString("employee_phone", phone);
                rs.updateString("employee_email", email);
                rs.updateRow();
            }
            rs.close();
            stmt.close();
            conn.close();
            getEmployeeData();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ViewEmployee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBt;
    private javax.swing.JButton deleteBt;
    private javax.swing.JButton editBt;
    private javax.swing.JTable employeeTb;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private static class Data
    {

        String employeeName, employeeEmail, employeeNumber;
        int employeeId;

        public Data(int employeeId, String employeeName, String employeeNumber, String employeeEmail)
        {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.employeeEmail = employeeEmail;
            this.employeeNumber = employeeNumber;
        }
    }

    private class TableModel extends AbstractTableModel
    {

        String employee[] =
        {
            "Employee ID", "Employee Name", "Contact Number", "Email Account"
        };

        public String getColumnName(int column)
        {
            return employee[column];
        }

        @Override
        public int getRowCount()
        {
            return al.size();
        }

        @Override
        public int getColumnCount()
        {
            return employee.length;
        }

        @Override
        public Object getValueAt(int row, int column)
        {
            if (column == 0)
            {
                return al.get(row).employeeId;
            } else if (column == 1)
            {
                return al.get(row).employeeName;
            } else if (column == 2)
            {
                return al.get(row).employeeNumber;
            } else
            {
                return al.get(row).employeeEmail;
            }
        }
    }
}
