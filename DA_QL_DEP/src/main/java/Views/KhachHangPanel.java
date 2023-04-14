/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Models.KhachHang;
import Services.IKhachHangService;
import Services.implement.KhachHangService;
import Utilities.ExportFile.ExportKH;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhachHangPanel extends javax.swing.JPanel {

    /**
     * Creates new form KhachHangPanel
     */
    int index = -1;

    IKhachHangService khachHangService;
    DefaultTableModel tblModelKhachHang;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    KhachHang kh = null;

    public KhachHangPanel() {
        initComponents();

        khachHangService = new KhachHangService();

        tblModelKhachHang = (DefaultTableModel) tblKhachHang.getModel();
        txtMaKH.setEnabled(false);
        loadTableKhachHang(khachHangService.getAllByObj(txtTimKiem.getText()));
        cleanForm();
    }

    public void loadTableKhachHang(List<KhachHang> lst) {
        int i = 0;
        tblModelKhachHang.setRowCount(0);
        for (KhachHang kh : lst) {
            tblModelKhachHang.addRow(new Object[]{
                ++i,
                kh.getMa(),
                kh.getTen(),
                sdf.format(kh.getNgaySinh()),
                kh.getGioiTinh() == 0 ? "Nam" : "Nữ",
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getDiemTichLy(),
                kh.getNgayTao() == null ? "Null" : sdf.format(kh.getNgayTao()),
                kh.getNgaySua() == null ? "Null" : sdf.format(kh.getNgaySua())
            });
        }

    }

    public void clickTable() {
        index = tblKhachHang.getSelectedRow();
        String ma = tblModelKhachHang.getValueAt(index, 1).toString();
        String ten = tblModelKhachHang.getValueAt(index, 2).toString();
        String ngaySinh = tblModelKhachHang.getValueAt(index, 3).toString();
        String gioiTinh = tblModelKhachHang.getValueAt(index, 4).toString();
        String sdt = tblModelKhachHang.getValueAt(index, 5).toString();
        String email = tblModelKhachHang.getValueAt(index, 6).toString();
        String diaChi = tblModelKhachHang.getValueAt(index, 7).toString();

        txtMaKH.setText(ma);
        txtTenKH.setText(ten);
        txtNgaySinh.setText(ngaySinh);
        txtSDT.setText(sdt);
        txtEmail.setText(email);
        txtDiaChi.setText(diaChi);

        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);

        }

    }

    public boolean isValidateEmail(String email) {
        String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Boolean b = email.matches(EMAIL);
        return b;
    }
    public boolean isValidatePhone(String sdt) {
        String Phone = "0[0-9]{9,10}";
        Boolean b = sdt.matches(Phone);
        return b;
    }

    private KhachHang getForm() {
        String ten = txtTenKH.getText();
        String ngaySinh = txtNgaySinh.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        if (ten.trim().length() == 0 || ngaySinh.trim().length() == 0 || email.trim().length() == 0 || diaChi.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Không Được Để Trống");
            return null;
        }

        if (!isValidateEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email Không Đúng Định Dạng");
            return null;
        }
        if (!isValidatePhone(sdt)) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Không Đúng Định Dạng");
            return null;
        }
        int gioiTinh = 0;

        if (rdNam.isSelected() == true) {
            gioiTinh = 0;
        } else {
            gioiTinh = 1;
        }

        KhachHang kh = new KhachHang();
        for (int i = 0; i < khachHangService.getLst().size() + 1; i++) {
            String maKh = "KH00" + i;
            if (khachHangService.getObj(maKh) == null) {
                kh.setMa(maKh);
            }
        }
        kh.setTen(ten);
        try {
            kh.setNgaySinh(sdf.parse(ngaySinh));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dd-mm-yyyy");
            return null;
        }
        kh.setSdt(sdt);
        kh.setEmail(email);
        kh.setDiaChi(diaChi);
        kh.setGioiTinh(gioiTinh);
        kh.setNgayTao(new Date());
        kh.setNgaySua(new Date());
        kh.setTinhTrang(0);
        kh.setDiemTichLy(0);
        return kh;
    }

    public void addKhachHang() {
        KhachHang kh = getForm();
        if (kh == null) {
            return;
        }
        if (khachHangService.save(kh)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
        loadTableKhachHang(khachHangService.getAllByObj(txtTimKiem.getText()));

    }

    public void updateKhachHang() {
        index = tblKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn khách hàng");
            return;
        }
        KhachHang kh = getForm();
        if (kh == null) {
            return;
        }

        String ma = tblModelKhachHang.getValueAt(index, 1).toString();
        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Sửa Khách Hàng " + ma);
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        KhachHang khs = khachHangService.getObj(ma);
        khs.setTen(kh.getTen());
        khs.setDiaChi(kh.getDiaChi());
        khs.setEmail(kh.getEmail());
        khs.setSdt(kh.getSdt());
        khs.setNgaySinh(kh.getNgaySinh());
        khs.setGioiTinh(kh.getGioiTinh());
        khs.setNgaySua(kh.getNgaySua());

        if (khachHangService.save(khs)) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
        loadTableKhachHang(khachHangService.getAllByObj(txtTimKiem.getText()));

    }

    public void deleteKhachHang() {
        index = tblKhachHang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn khách hàng");
            return;
        }
        KhachHang kh = getForm();
        if (kh == null) {
            return;
        }
        String ma = tblModelKhachHang.getValueAt(index, 1).toString();
        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Xoá Khách Hàng " + ma);
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        KhachHang khs = khachHangService.getObj(ma);
        if (khachHangService.delete(khs)) {
            JOptionPane.showMessageDialog(this, "Xoá thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xoá thất bại");
        }
        loadTableKhachHang(khachHangService.getAllByObj(txtTimKiem.getText()));
    }

    public void cleanForm() {
        for (int i = 0; i < khachHangService.getLst().size() + 1; i++) {
            String ma = "KH00" + i;
            if (khachHangService.getObj(ma) == null) {
                txtMaKH.setText(ma);
            }
        }
        txtTenKH.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        rdNam.setSelected(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtMaKH = new textfield.TextField();
        txtTenKH = new textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        rdNam = new radio_button.RadioButtonCustom();
        rdNu = new radio_button.RadioButtonCustom();
        txtSDT = new textfield.TextField();
        txtEmail = new textfield.TextField();
        txtNgaySinh = new textfield.TextField();
        txtDiaChi = new textfield.TextField();
        btnThem = new Utilities.raven.textfield.Button();
        btnSua = new Utilities.raven.textfield.Button();
        btnXoa = new Utilities.raven.textfield.Button();
        btnXoa1 = new Utilities.raven.textfield.Button();
        jPanel1 = new javax.swing.JPanel();
        btnXuatDS = new Utilities.raven.textfield.Button();
        txtTimKiem = new textfield.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();

        dateChooser1.setForeground(new java.awt.Color(0, 204, 255));
        dateChooser1.setTextRefernce(txtNgaySinh);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Thông Tin Khách Hàng");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtMaKH.setLabelText("Mã Khách Hàng");

        txtTenKH.setLabelText("Tên Khách Hàng");
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Giới Tính :");

        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        txtSDT.setLabelText("Số Điện Thoại");

        txtEmail.setLabelText("Email");

        txtNgaySinh.setLabelText("Ngày Sinh");

        txtDiaChi.setLabelText("Địa Chỉ");

        btnThem.setBackground(new java.awt.Color(0, 255, 0));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 102));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setText("Xoá");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXoa1.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa1.setText("Clean");
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(320, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdNam, rdNu});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtMaKH, txtNgaySinh, txtTenKH});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnThem, btnXoa});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdNam, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSua, btnThem, btnXoa});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rdNam, rdNu});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtMaKH, txtNgaySinh, txtTenKH});

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnXuatDS.setBackground(new java.awt.Color(51, 153, 255));
        btnXuatDS.setText(" Export File Khách Hàng");
        btnXuatDS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDSActionPerformed(evt);
            }
        });

        txtTimKiem.setLabelText("Tìm Kiếm");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Tên KH", "Ngày Sinh", "Giới Tính", "SĐT", "Email", "Địa Chỉ", "Điểm Tích Luỹ", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXuatDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnXuatDS, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addKhachHang();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateKhachHang();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteKhachHang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXuatDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDSActionPerformed
        JFileChooser fileChooser = new JFileChooser("./file/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Export Excel");
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                int chk = JOptionPane.showConfirmDialog(this, "Xác nhận xuất file ?");
                if (chk == JOptionPane.YES_OPTION) {
                    ExportKH.writeExcel(khachHangService.getAllByObj(""), fileToSave.getAbsolutePath() + filter.getDescription());
                    JOptionPane.showMessageDialog(this, "Xuất File Excel thành công");

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Xuất File Excel thất bại");
            }
            System.out.println("Save as file: " + fileToSave.getAbsolutePath() + filter.getDescription());
        }
    }//GEN-LAST:event_btnXuatDSActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        loadTableKhachHang(khachHangService.getAllByObj(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        clickTable();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        cleanForm();
    }//GEN-LAST:event_btnXoa1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Utilities.raven.textfield.Button btnSua;
    private Utilities.raven.textfield.Button btnThem;
    private Utilities.raven.textfield.Button btnXoa;
    private Utilities.raven.textfield.Button btnXoa1;
    private Utilities.raven.textfield.Button btnXuatDS;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private radio_button.RadioButtonCustom rdNam;
    private radio_button.RadioButtonCustom rdNu;
    private javax.swing.JTable tblKhachHang;
    private textfield.TextField txtDiaChi;
    private textfield.TextField txtEmail;
    private textfield.TextField txtMaKH;
    private textfield.TextField txtNgaySinh;
    private textfield.TextField txtSDT;
    private textfield.TextField txtTenKH;
    private textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
