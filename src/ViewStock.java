
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ViewStock extends javax.swing.JFrame
{

    String productName;
    String productID;

    ArrayList<Stock> alStock = new ArrayList<>();
    ArrayList<Stock> alBadStock = new ArrayList<>();

    StockTableModel tableModelStock;
    StockTableModel tableModelBadStock;
    int stockCurrentSelection = -1;
    int badStockCurrentSelection = -1;

    public ViewStock(String productName, String productID)
    {

        this.productName = productName;
        this.productID = productID;
        initComponents();
        setVisible(true);
        setTitle("View Stock");
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
                
        int x = (int)((screenWidth/2)-(frameWidth/2));
        int y = (int)((screenHeight/2)-(frameHeight/2));
        setLocation(x,y);      
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModelBadStock = new StockTableModel(alBadStock);
        tableModelStock = new StockTableModel(alStock);
        stockTb.setModel(tableModelStock);
        badStockTb.setModel(tableModelBadStock);
        getStock("stock");
        getStock("bad_stock");
    }

    void getStock(String table)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from " + table + " where product_id = '" + productID + "'");
            if (table.equals("stock"))
            {
                alStock.clear();
                while (rs.next())
                {
                    alStock.add(new Stock(productName, rs.getInt("id"), rs.getInt("product_quantity"), rs.getString("date_time")));
                }
                tableModelStock.fireTableDataChanged();
                stockCurrentSelection = -1;
            } else
            {
                alBadStock.clear();
                while (rs.next())
                {
                    alBadStock.add(new Stock(productName, rs.getInt("id"), rs.getInt("product_quantity"), rs.getString("date_time")));
                }
                tableModelBadStock.fireTableDataChanged();
                badStockCurrentSelection = -1;
            }
            rs.close();
            stmt.close();
            con.close();
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
        badStockTb = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stockTb = new javax.swing.JTable();
        addBadStockBt = new javax.swing.JButton();
        addStockBt = new javax.swing.JButton();
        backBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        badStockTb.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                badStockTbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(badStockTb);

        jLabel1.setText("Stock");

        jLabel2.setText("Bad Stock");

        stockTb.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                stockTbMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(stockTb);

        addBadStockBt.setText("Add Bad Stock");
        addBadStockBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addBadStockBtActionPerformed(evt);
            }
        });

        addStockBt.setText("Add Stock");
        addStockBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addStockBtActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStockBt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(addBadStockBt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addStockBt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backBt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(addBadStockBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBadStockBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addBadStockBtActionPerformed
    {//GEN-HEADEREND:event_addBadStockBtActionPerformed
        String quantity = JOptionPane.showInputDialog(this, "Enter Quantity").trim();
        if (quantity.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Insufficient Information");
            return;
        }
        int badStockQuantity = Integer.parseInt(quantity);
        addStock(badStockQuantity, "bad_stock");
        getStock("bad_stock");
    }//GEN-LAST:event_addBadStockBtActionPerformed

    private void addStockBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addStockBtActionPerformed
    {//GEN-HEADEREND:event_addStockBtActionPerformed
        String quantity = JOptionPane.showInputDialog(this, "Enter Quantity").trim();
        if (quantity.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Insufficient Information");
            return;
        }
        int stockQuantity = Integer.parseInt(quantity);
        addStock(stockQuantity, "stock");
        getStock("stock");
    }//GEN-LAST:event_addStockBtActionPerformed

    private void stockTbMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_stockTbMouseClicked
    {//GEN-HEADEREND:event_stockTbMouseClicked
        if (stockTb.getSelectedRow() == stockCurrentSelection)
        {
            stockTb.clearSelection();
        }
        stockCurrentSelection = stockTb.getSelectedRow();
    }//GEN-LAST:event_stockTbMouseClicked

    private void badStockTbMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_badStockTbMouseClicked
    {//GEN-HEADEREND:event_badStockTbMouseClicked
        if (badStockTb.getSelectedRow() == badStockCurrentSelection)
        {
            badStockTb.clearSelection();
        }
        badStockCurrentSelection = badStockTb.getSelectedRow();
    }//GEN-LAST:event_badStockTbMouseClicked

    private void backBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backBtActionPerformed
    {//GEN-HEADEREND:event_backBtActionPerformed

        new ViewProduct();
        dispose();
    }//GEN-LAST:event_backBtActionPerformed

    void addStock(int quantity, String table)
    {
        try
        {
            if (quantity <= 0)
            {
                JOptionPane.showMessageDialog(this, "Invalid Quantity");
                return;
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from " + table);
            rs.moveToInsertRow();
            rs.updateString("product_id", productID + "");
            rs.updateInt("product_quantity", quantity);
            rs.insertRow();
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e)
        {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBadStockBt;
    private javax.swing.JButton addStockBt;
    private javax.swing.JButton backBt;
    private javax.swing.JTable badStockTb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable stockTb;
    // End of variables declaration//GEN-END:variables

    private class StockTableModel extends AbstractTableModel
    {

        ArrayList<Stock> list;

        public StockTableModel(ArrayList<Stock> list)
        {
            this.list = list;
        }

        public int getRowCount()
        {
            return list.size();
        }

        @Override
        public int getColumnCount()
        {
            return 4;
        }

        @Override
        public Object getValueAt(int i, int i1)
        {
            if (i1 == 0)
            {
                return list.get(i).Id;
            } else if (i1 == 1)
            {
                return productName;
            } else if (i1 == 2)
            {
                return list.get(i).productQuantity;
            } else
            {
                return list.get(i).dateTime;
            }
        }

        public String getColumnName(int columnIndex)
        {
            if (columnIndex == 0)
            {
                return "ID";
            } else if (columnIndex == 1)
            {
                return "Product Name";
            } else if (columnIndex == 2)
            {
                return "Product Quantity";
            } else
            {
                return "Date Time";
            }
        }
    }

    private class Stock
    {

        int Id, productQuantity;
        String dateTime, productName;

        public Stock(String productName, int Id, int productQuantity, String dateTime)
        {
            this.Id = Id;
            this.productName = productName;
            this.productQuantity = productQuantity;
            this.dateTime = dateTime;
        }

    }
}
