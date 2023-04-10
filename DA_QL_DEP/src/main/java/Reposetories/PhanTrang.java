/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Reposetories;

import HibernateConfig.HibernateConfig;
import Models.ChiTietDep;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class PhanTrang extends javax.swing.JFrame {

    /**
     * Creates new form PhanTrang
     */
    private static Session session = HibernateConfig.getFACTORY().openSession();
    private Transaction transaction = session.getTransaction();
    DefaultTableModel tblModelSp;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    int pageNumber = 1;
    int pageSize = 2;

    public PhanTrang() {
        initComponents();
        tblModelSp = (DefaultTableModel) tblSanPham.getModel();

        loadTableSanPham(getLstDb());
    }

    public List<ChiTietDep> getLstDb() {
        System.out.println(" pageNumber " + pageNumber + " pageSize " + pageSize);
        Query query = session.createQuery("SELECT ctd FROM ChiTietDep ctd");
//        query.setFirstResult(2 * 3);
//        query.setMaxResults(3);
        List<ChiTietDep> lst = query.getResultList();
        return lst;

    }

    public List<ChiTietDep> lstPhanTrang() {
        List<ChiTietDep> lst = new ArrayList<>();
        List<ChiTietDep> lstData = getLstDb();

        int check = 0;
        for (int i = (pageNumber - 1) * pageSize; i < lstData.size(); i++) {
            if (check == pageSize) {
                break;
            }
            lst.add(lstData.get(i));
            check++;
        }
        return lst;
    }

    public void loadTableSanPham(List<ChiTietDep> lst) {
        int i = 0;
        int check = 0;
        tblModelSp.setRowCount(0);
        for (int j = (pageNumber - 1) * pageSize; j < lst.size(); j++) {
            if (check == pageSize) {
                System.out.println("s?dasd");
                break;
            }
            ChiTietDep ctd = lst.get(j);
            tblModelSp.addRow(new Object[]{
                ++i,
                ctd.getDep().getMa(),
                ctd.getDep().getTen(),
                ctd.getSoLuong(),
                ctd.getGiaNhap(),
                ctd.getGiaBan(),
                ctd.getLoaiDep().getTen(),
                ctd.getSize().getKichCo(),
                ctd.getChatLieu().getTen(),
                ctd.getMauSac().getMauSac(),
                ctd.getNsx().getTen(),
                ctd.getMoTa(),
                ctd.getNgayTao() == null ? "" : sdf.format(ctd.getNgayTao()),
                ctd.getNgaySua() == null ? "" : sdf.format(ctd.getNgaySua()),
                ctd.getTinhTrang() == 0 ? "Ðang Kinh Doanh" : "Ngừng Kinh Doanh"
            });

            check++;

        }
    }
//    public void loadTableSanPham(List<ChiTietDep> lst) {
//        int i = 0;
//        tblModelSp.setRowCount(0);
//
//        for (ChiTietDep ctd : lst) {
//            tblModelSp.addRow(new Object[]{
//                ++i,
//                ctd.getDep().getMa(),
//                ctd.getDep().getTen(),
//                ctd.getSoLuong(),
//                ctd.getGiaNhap(),
//                ctd.getGiaBan(),
//                ctd.getLoaiDep().getTen(),
//                ctd.getSize().getKichCo(),
//                ctd.getChatLieu().getTen(),
//                ctd.getMauSac().getMauSac(),
//                ctd.getNsx().getTen(),
//                ctd.getMoTa(),
//                ctd.getNgayTao() == null ? "" : sdf.format(ctd.getNgayTao()),
//                ctd.getNgaySua() == null ? "" : sdf.format(ctd.getNgaySua()),
//                ctd.getTinhTrang() == 0 ? "Ðang Kinh Doanh" : "Ngừng Kinh Doanh"
//            });
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên Sản Phẩm", "Số Lượng", "Giá Nhập", "Giá Bán", "Loại", "Size", "Chất Liệu", "Màu Sắc", "Nhà Sản Xuất", "Mô Tả", "Ngày Thêm", "Ngày Sửa", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1)
                        .addGap(50, 50, 50)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        pageNumber--;
        loadTableSanPham(getLstDb());

//        loadTableSanPham(lstPhanTrang());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        pageNumber++;
        loadTableSanPham(getLstDb());

//        loadTableSanPham(lstPhanTrang());
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(PhanTrang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhanTrang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhanTrang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhanTrang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhanTrang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}
