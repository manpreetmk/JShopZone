
import java.awt.Dimension;
import java.awt.Toolkit;


public class ProductMenu extends javax.swing.JFrame
{

   
    public ProductMenu()
    {
        initComponents();
        setVisible(true);
        setTitle("Product Menu");
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
                
        int x = (int)((screenWidth/2)-(frameWidth/2));
        int y = (int)((screenHeight/2)-(frameHeight/2));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(x,y);      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        addCategoryBt = new javax.swing.JButton();
        viewBt = new javax.swing.JButton();
        viewSubBt = new javax.swing.JButton();
        subCatAddBt = new javax.swing.JButton();
        addProductBt1 = new javax.swing.JButton();
        ViewBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addCategoryBt.setText("Add Category");
        addCategoryBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addCategoryBtActionPerformed(evt);
            }
        });

        viewBt.setText("View/Modify Category");
        viewBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                viewBtActionPerformed(evt);
            }
        });

        viewSubBt.setText("View SubCategory");
        viewSubBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                viewSubBtActionPerformed(evt);
            }
        });

        subCatAddBt.setText("Add Sub Category");
        subCatAddBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                subCatAddBtActionPerformed(evt);
            }
        });

        addProductBt1.setText("Add Product");
        addProductBt1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addProductBt1ActionPerformed(evt);
            }
        });

        ViewBt.setText("View Products");
        ViewBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ViewBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addCategoryBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewBt, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(subCatAddBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewSubBt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addProductBt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addCategoryBt)
                .addGap(15, 15, 15)
                .addComponent(viewBt)
                .addGap(18, 18, 18)
                .addComponent(subCatAddBt)
                .addGap(18, 18, 18)
                .addComponent(viewSubBt)
                .addGap(18, 18, 18)
                .addComponent(addProductBt1)
                .addGap(18, 18, 18)
                .addComponent(ViewBt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addCategoryBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addCategoryBtActionPerformed
    {//GEN-HEADEREND:event_addCategoryBtActionPerformed
        new AddCategory();
        dispose();
    }//GEN-LAST:event_addCategoryBtActionPerformed

    private void viewBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_viewBtActionPerformed
    {//GEN-HEADEREND:event_viewBtActionPerformed

        new ViewCategory();
        dispose();
    }//GEN-LAST:event_viewBtActionPerformed

    private void viewSubBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_viewSubBtActionPerformed
    {//GEN-HEADEREND:event_viewSubBtActionPerformed

        new ViewSubCat();
        dispose();
    }//GEN-LAST:event_viewSubBtActionPerformed

    private void subCatAddBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_subCatAddBtActionPerformed
    {//GEN-HEADEREND:event_subCatAddBtActionPerformed
        new AddSubCategory();
        dispose();
    }//GEN-LAST:event_subCatAddBtActionPerformed

    private void addProductBt1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addProductBt1ActionPerformed
    {//GEN-HEADEREND:event_addProductBt1ActionPerformed
        new AddProduct();
        dispose();
    }//GEN-LAST:event_addProductBt1ActionPerformed

    private void ViewBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ViewBtActionPerformed
    {//GEN-HEADEREND:event_ViewBtActionPerformed

        new ViewProduct();
        dispose();
    }//GEN-LAST:event_ViewBtActionPerformed

   
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ProductMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ViewBt;
    private javax.swing.JButton addCategoryBt;
    private javax.swing.JButton addProductBt1;
    private javax.swing.JButton subCatAddBt;
    private javax.swing.JButton viewBt;
    private javax.swing.JButton viewSubBt;
    // End of variables declaration//GEN-END:variables
}
