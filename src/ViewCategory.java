
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

public class ViewCategory extends javax.swing.JFrame
{

    ArrayList<Data> al;
    CategoryTableModel tableModel;
    int currentSelection = -1;

    public ViewCategory()
    {
        initComponents();
        setTitle("View/Modify Category");
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
                
        int x = (int)((screenWidth/2)-(frameWidth/2));
        int y = (int)((screenHeight/2)-(frameHeight/2));
        setLocation(x,y);      
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        al = new ArrayList<>();
        tableModel = new CategoryTableModel();
        tblCategory.setModel(tableModel);
        getCategoryData();
    }

    void getCategoryData()
    {
        try
        {
            al.clear();
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from category");

            while (rs.next())
            {
                al.add(new Data(rs.getString("category_name"), rs.getString("category_desc")));
            }
            tableModel.fireTableDataChanged();
            rs.close();
            stmt.close();
            conn.close();
            currentSelection = -1;
        } catch (Exception e)
        {
            e.getStackTrace();
        }
    }

    class CategoryTableModel extends AbstractTableModel
    {

        String title[] =
        {
            "Category Name", "Category Desc."
        };

        public String getColumnName(int index)
        {
            return title[index];
        }

        public int getColumnCount()
        {
            return title.length;
        }

        public int getRowCount()
        {
            return al.size();
        }

        public Object getValueAt(int row, int col)
        {
            Data s = al.get(row);
            if (col == 0)
            {
                return s.name;
            } else
            {
                return s.description;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        backBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCategory.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblCategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategory);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDeleteActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEditActionPerformed
    {//GEN-HEADEREND:event_btnEditActionPerformed
        if (tblCategory.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "No Row Selected");
            return;
        }
        Object[] buttons =
        {
            "Save", "Don't Save"
        };
        JTextArea taDescription = new JTextArea();
        String categoryName = al.get(tblCategory.getSelectedRow()).name;
        taDescription.setColumns(10);
        taDescription.setRows(5);
        JScrollPane sp = new JScrollPane(taDescription);
        int n = JOptionPane.showOptionDialog(this, sp, "Edit Category", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
        String desc = taDescription.getText().trim();
        if (n == JOptionPane.YES_OPTION)
        {
            if (desc.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Insufficient Information");
                return;
            }
            editCategory(categoryName, desc);
        }      
    }//GEN-LAST:event_btnEditActionPerformed

    void editCategory(String categoryName, String desc)
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from category where category_name = '" + categoryName + "'");

            if (rs.next())
            {
                rs.updateString("category_desc", desc);
                rs.updateRow();
            }
            rs.close();
            stmt.close();
            conn.close();
            getCategoryData();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDeleteActionPerformed
    {//GEN-HEADEREND:event_btnDeleteActionPerformed
        if (tblCategory.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "Select a Row to Delete");
            return;
        }
        String categoryName = al.get(tblCategory.getSelectedRow()).name;
        int n = JOptionPane.showConfirmDialog(this, "Sure To Delete " + categoryName + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION)
        {
//            deleteCategory(categoryName);
            new DatabaseFunctions().deleteRow("category", "category_name", categoryName);
            getCategoryData();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void backBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backBtActionPerformed
    {//GEN-HEADEREND:event_backBtActionPerformed
        new ProductMenu();
        dispose();
    }//GEN-LAST:event_backBtActionPerformed

    private void tblCategoryMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblCategoryMouseClicked
    {//GEN-HEADEREND:event_tblCategoryMouseClicked

        if (tblCategory.getSelectedRow() == currentSelection)
        {
            tblCategory.clearSelection();
        }
        currentSelection = tblCategory.getSelectedRow();
    }//GEN-LAST:event_tblCategoryMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBt;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCategory;
    // End of variables declaration//GEN-END:variables
    class Data
    {

        String name, description;

        Data(String name, String description)
        {
            this.name = name;
            this.description = description;
        }
    }
}
