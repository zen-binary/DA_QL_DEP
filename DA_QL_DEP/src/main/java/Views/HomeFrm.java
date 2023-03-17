/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class HomeFrm extends javax.swing.JFrame {

    List<JPanel> lstPanel;
    List<JPanel> lstBtnPanels;

    public HomeFrm() {
        initComponents();
        setLocationRelativeTo(null);
//         this.setExtendedState(this.MAXIMIZED_BOTH);
//         pnl_menu.setPreferredSize(new Dimension(500, pnl_menu.getPreferredSize().height)); // Thiết lập chiều cao của panel thành 500 pixels
        initPanel();
        initBtnClick();
        
        setTitle("Dep dep");
        Image icon = Toolkit.getDefaultToolkit().getImage("./images/logo.png");
        this.setIconImage(icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void initPanel() {
        lstPanel = new ArrayList<>();

        SanPhamPanel spPanel = new SanPhamPanel();
        NhanVienPanel nvPanel = new NhanVienPanel();
        KhachHangPanel khPanel = new KhachHangPanel();
        BanHangPanel bhPanel = new BanHangPanel();
        HoaDonPanel hdPanel = new HoaDonPanel();
        ThongKePanel tkPanel = new ThongKePanel();
        TrangChuPanel tcPanel = new TrangChuPanel();
        lstPanel.add(tcPanel);
        lstPanel.add(spPanel);
        lstPanel.add(nvPanel);
        lstPanel.add(khPanel);
        lstPanel.add(bhPanel);
        lstPanel.add(hdPanel);
        lstPanel.add(tkPanel);

        for (JPanel jPanel : lstPanel) {
            pnl_card.add(jPanel);
        }

    }

    public void showPanel(int index) {
        for (JPanel panel : lstPanel) {
            panel.setVisible(false);
        }
        lstPanel.get(index).setVisible(true);

    }

    public void initBtnClick() {
        //        Btn_Panel

        lstBtnPanels = new ArrayList<>();
        lstBtnPanels.add(Pnl_btn_TrangChu);
        lstBtnPanels.add(pnl_btn_SanPham);
        lstBtnPanels.add(pnl_btn_NhanVien);
        lstBtnPanels.add(pnl_btn_KhachHang);
        lstBtnPanels.add(pnl_btn_BanHang);
        lstBtnPanels.add(pnl_btn_HoaDon);
        lstBtnPanels.add(pnl_btn_ThongKe);
        lstBtnPanels.add(pnl_btn_DangXuat);
    }

    public void BtnClick(int index) {
        for (JPanel panel : lstBtnPanels) {
            panel.setBackground(new Color(15, 179, 228));
        }
        lstBtnPanels.get(index).setBackground(new Color(5,159,205));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonUI1 = new ui.ButtonUI();
        modelCard1 = new ui.ModelCard();
        splinePoint1 = new ui.SplinePoint();
        hoverIndex1 = new swing.HoverIndex();
        hoverIndex2 = new swing.HoverIndex();
        jPanel2 = new javax.swing.JPanel();
        pnl_menu = new javax.swing.JPanel();
        pnl_btn_ThongKe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnl_btn_HoaDon = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnl_btn_BanHang = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnl_btn_KhachHang = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnl_btn_NhanVien = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        pnl_btn_SanPham = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        Pnl_btn_TrangChu = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pnl_btn_DangXuat = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        imageAvatar1 = new swing.ImageAvatar();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnl_card = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_menu.setBackground(new java.awt.Color(117, 196, 255));
        pnl_menu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true));
        pnl_menu.setMaximumSize(getPreferredSize());
        pnl_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_btn_ThongKe.setBackground(new java.awt.Color(15, 179, 228));
        pnl_btn_ThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_btn_ThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_btn_ThongKeMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thống Kê");

        javax.swing.GroupLayout pnl_btn_ThongKeLayout = new javax.swing.GroupLayout(pnl_btn_ThongKe);
        pnl_btn_ThongKe.setLayout(pnl_btn_ThongKeLayout);
        pnl_btn_ThongKeLayout.setHorizontalGroup(
            pnl_btn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        pnl_btn_ThongKeLayout.setVerticalGroup(
            pnl_btn_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_btn_ThongKeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(pnl_btn_ThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 230, 70));

        pnl_btn_HoaDon.setBackground(new java.awt.Color(15, 179, 228));
        pnl_btn_HoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_btn_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_btn_HoaDonMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hóa Đơn");

        javax.swing.GroupLayout pnl_btn_HoaDonLayout = new javax.swing.GroupLayout(pnl_btn_HoaDon);
        pnl_btn_HoaDon.setLayout(pnl_btn_HoaDonLayout);
        pnl_btn_HoaDonLayout.setHorizontalGroup(
            pnl_btn_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        pnl_btn_HoaDonLayout.setVerticalGroup(
            pnl_btn_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_btn_HoaDonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(pnl_btn_HoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 230, 70));

        pnl_btn_BanHang.setBackground(new java.awt.Color(15, 179, 228));
        pnl_btn_BanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_btn_BanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_btn_BanHangMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Bán Hàng");

        javax.swing.GroupLayout pnl_btn_BanHangLayout = new javax.swing.GroupLayout(pnl_btn_BanHang);
        pnl_btn_BanHang.setLayout(pnl_btn_BanHangLayout);
        pnl_btn_BanHangLayout.setHorizontalGroup(
            pnl_btn_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        pnl_btn_BanHangLayout.setVerticalGroup(
            pnl_btn_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_btn_BanHangLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(pnl_btn_BanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 230, 70));

        pnl_btn_KhachHang.setBackground(new java.awt.Color(15, 179, 228));
        pnl_btn_KhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_btn_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_btn_KhachHangMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Khách Hàng");

        javax.swing.GroupLayout pnl_btn_KhachHangLayout = new javax.swing.GroupLayout(pnl_btn_KhachHang);
        pnl_btn_KhachHang.setLayout(pnl_btn_KhachHangLayout);
        pnl_btn_KhachHangLayout.setHorizontalGroup(
            pnl_btn_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        pnl_btn_KhachHangLayout.setVerticalGroup(
            pnl_btn_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_btn_KhachHangLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(pnl_btn_KhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 230, 70));

        pnl_btn_NhanVien.setBackground(new java.awt.Color(15, 179, 228));
        pnl_btn_NhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_btn_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_btn_NhanVienMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nhân Viên");

        javax.swing.GroupLayout pnl_btn_NhanVienLayout = new javax.swing.GroupLayout(pnl_btn_NhanVien);
        pnl_btn_NhanVien.setLayout(pnl_btn_NhanVienLayout);
        pnl_btn_NhanVienLayout.setHorizontalGroup(
            pnl_btn_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        pnl_btn_NhanVienLayout.setVerticalGroup(
            pnl_btn_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_btn_NhanVienLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(pnl_btn_NhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 230, 70));

        pnl_btn_SanPham.setBackground(new java.awt.Color(15, 179, 228));
        pnl_btn_SanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_btn_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_btn_SanPhamMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Sản Phẩm");

        javax.swing.GroupLayout pnl_btn_SanPhamLayout = new javax.swing.GroupLayout(pnl_btn_SanPham);
        pnl_btn_SanPham.setLayout(pnl_btn_SanPhamLayout);
        pnl_btn_SanPhamLayout.setHorizontalGroup(
            pnl_btn_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        pnl_btn_SanPhamLayout.setVerticalGroup(
            pnl_btn_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_btn_SanPhamLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(pnl_btn_SanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 230, 70));

        Pnl_btn_TrangChu.setBackground(new java.awt.Color(15, 179, 228));
        Pnl_btn_TrangChu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        Pnl_btn_TrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pnl_btn_TrangChuMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Trang Chủ");

        javax.swing.GroupLayout Pnl_btn_TrangChuLayout = new javax.swing.GroupLayout(Pnl_btn_TrangChu);
        Pnl_btn_TrangChu.setLayout(Pnl_btn_TrangChuLayout);
        Pnl_btn_TrangChuLayout.setHorizontalGroup(
            Pnl_btn_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        Pnl_btn_TrangChuLayout.setVerticalGroup(
            Pnl_btn_TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_btn_TrangChuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(Pnl_btn_TrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 230, 70));

        pnl_btn_DangXuat.setBackground(new java.awt.Color(15, 179, 228));
        pnl_btn_DangXuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_btn_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_btn_DangXuatMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Đăng Xuất");

        javax.swing.GroupLayout pnl_btn_DangXuatLayout = new javax.swing.GroupLayout(pnl_btn_DangXuat);
        pnl_btn_DangXuat.setLayout(pnl_btn_DangXuatLayout);
        pnl_btn_DangXuatLayout.setHorizontalGroup(
            pnl_btn_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        pnl_btn_DangXuatLayout.setVerticalGroup(
            pnl_btn_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_btn_DangXuatLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnl_menu.add(pnl_btn_DangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 720, 230, 70));
        pnl_menu.add(imageAvatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 160, 150));

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin");
        pnl_menu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 230, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Lại Văn Chiến");
        pnl_menu.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 230, -1));

        getContentPane().add(pnl_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 790));

        pnl_card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        pnl_card.setLayout(new java.awt.CardLayout());
        getContentPane().add(pnl_card, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 1240, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Pnl_btn_TrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pnl_btn_TrangChuMouseClicked
        showPanel(0);
        BtnClick(0);
    }//GEN-LAST:event_Pnl_btn_TrangChuMouseClicked

    private void pnl_btn_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_btn_KhachHangMouseClicked
        showPanel(3);
        BtnClick(3);

    }//GEN-LAST:event_pnl_btn_KhachHangMouseClicked

    private void pnl_btn_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_btn_SanPhamMouseClicked
        showPanel(1);
        BtnClick(1);

    }//GEN-LAST:event_pnl_btn_SanPhamMouseClicked

    private void pnl_btn_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_btn_NhanVienMouseClicked
        showPanel(2);
        BtnClick(2);

    }//GEN-LAST:event_pnl_btn_NhanVienMouseClicked

    private void pnl_btn_BanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_btn_BanHangMouseClicked
        showPanel(4);
        BtnClick(4);


    }//GEN-LAST:event_pnl_btn_BanHangMouseClicked

    private void pnl_btn_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_btn_HoaDonMouseClicked
        showPanel(5);
        BtnClick(5);


    }//GEN-LAST:event_pnl_btn_HoaDonMouseClicked

    private void pnl_btn_ThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_btn_ThongKeMouseClicked
        showPanel(6);
        BtnClick(6);


    }//GEN-LAST:event_pnl_btn_ThongKeMouseClicked

    private void pnl_btn_DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_btn_DangXuatMouseClicked
        BtnClick(7);

    }//GEN-LAST:event_pnl_btn_DangXuatMouseClicked

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
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pnl_btn_TrangChu;
    private ui.ButtonUI buttonUI1;
    private swing.HoverIndex hoverIndex1;
    private swing.HoverIndex hoverIndex2;
    private swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private ui.ModelCard modelCard1;
    private javax.swing.JPanel pnl_btn_BanHang;
    private javax.swing.JPanel pnl_btn_DangXuat;
    private javax.swing.JPanel pnl_btn_HoaDon;
    private javax.swing.JPanel pnl_btn_KhachHang;
    private javax.swing.JPanel pnl_btn_NhanVien;
    private javax.swing.JPanel pnl_btn_SanPham;
    private javax.swing.JPanel pnl_btn_ThongKe;
    private javax.swing.JPanel pnl_card;
    private javax.swing.JPanel pnl_menu;
    private ui.SplinePoint splinePoint1;
    // End of variables declaration//GEN-END:variables
}
