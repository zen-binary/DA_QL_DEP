/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Models.ChucVu;
import Models.NguoiDung;
import Services.IChucVuService;
import Services.INguoiDungService;
import Services.implement.ChucVuService;
import Services.implement.NguoiDungService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVienPanel extends javax.swing.JPanel {

    int index = -1;
    IChucVuService cvService;
    INguoiDungService ndService;
    DefaultTableModel tblModelNhanVien;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public NhanVienPanel() {
        initComponents();
        cvService = new ChucVuService();
        ndService = new NguoiDungService();

        initCboChucVu();
        initCboTrangThai();
        cleanForm();
        tblModelNhanVien = (DefaultTableModel) tblNhanVien.getModel();
        loadTableNhanVien(ndService.getLst());

    }

    public void initCboChucVu() {
        DefaultComboBoxModel<ChucVu> boxModel = new DefaultComboBoxModel<>();
        cboChucVu.removeAllItems();
        cboChucVu.setModel((DefaultComboBoxModel) boxModel);

        for (ChucVu chucVu : cvService.getLst()) {
            boxModel.addElement(chucVu);
        }

//if (x.getTrangThai() == 1) {
//                comboSize.removeElement(x);
//            } else {
//                comboSize.addElement(x);
//            }
    }

    public void initCboTrangThai() {
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        cboTrangThai.removeAllItems();
        cboTrangThai.setModel((DefaultComboBoxModel) boxModel);
        boxModel.addElement("Ðang Làm");
        boxModel.addElement("Nghỉ Làm");

    }

    public void loadTableNhanVien(List<NguoiDung> lst) {
        int i = 0;
        tblModelNhanVien.setRowCount(0);
        for (NguoiDung nd : lst) {
            tblModelNhanVien.addRow(new Object[]{
                ++i,
                nd.getMa(),
                nd.getTen(),
                //                nd.getNgaySinh(),
                sdf.format(nd.getNgaySinh()),
                nd.getGioiTinh() == 0 ? "Nam" : "Nữ",
                nd.getEmail(),
                nd.getSdt(),
                nd.getDiaChi(),
                nd.getChucVu(),
                nd.getTrangThai() == 0 ? "Ðang Làm" : "Nghỉ Làm"

            });
        }

    }

    public void clickTableNhanVien() {
        index = tblNhanVien.getSelectedRow();
        NguoiDung nd = ndService.getLst().get(index);
        String ma = (String) tblModelNhanVien.getValueAt(index, 1);
        String ten = (String) tblModelNhanVien.getValueAt(index, 2);
        String ngaySinh = tblModelNhanVien.getValueAt(index, 3).toString();
        String gioiTinh = (String) tblModelNhanVien.getValueAt(index, 4);
        String email = (String) tblModelNhanVien.getValueAt(index, 5);
        String sdt = (String) tblModelNhanVien.getValueAt(index, 6);
        String diaChi = (String) tblModelNhanVien.getValueAt(index, 7);
        Object chuVu = tblModelNhanVien.getValueAt(index, 8);
        String trangThai = (String) tblModelNhanVien.getValueAt(index, 9);

        String matKhau = nd.getMatKhau();
        String hinhAnh = nd.getHinhAnh();
        txtMa.setText(ma);
        txtTen.setText(ten);
        txtNgaySinh.setText(ngaySinh);

        txtEmail.setText(email);
        txtSdt.setText(sdt);
        txtDiaChi.setText(diaChi);
        txtMatKhau.setText(matKhau);
        cboTrangThai.setSelectedItem(trangThai);

        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        int cvCount = cboChucVu.getItemCount();
        for (int i = 0; i < cvCount; i++) {
            Object ch = cboChucVu.getItemAt(i);
            if (ch.toString().equals(chuVu.toString())) {
                cboChucVu.setSelectedIndex(i);
                break;
            }
        }

    }

    public void cleanForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtNgaySinh.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        cboTrangThai.setSelectedIndex(0);
        cboTrangThai.setSelectedItem(0);
        rdoNam.setSelected(true);
    }

    public NguoiDung getForm() {
        NguoiDung nd = new NguoiDung();
        Date d = new Date();

        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String ngaySinh = txtNgaySinh.getText();
        String email = txtEmail.getText();
        String sdt = txtSdt.getText();
        String diaChi = txtDiaChi.getText();
        String matKhau = txtMatKhau.getText();

        ChucVu cv = (ChucVu) cboChucVu.getSelectedItem();

        int tt = 0;
        int gt = 0;

        if (rdoNam.isSelected() == true) {
            gt = 0;
        }

        if (rdoNu.isSelected() == true) {
            gt = 1;
        }
        if (cboTrangThai.getSelectedItem().equals("Nghỉ Làm")) {
            tt = 1;
        } else {
            tt = 0;
        }

        nd.setMa(ma);
        nd.setTen(ten);
        nd.setNgaySinh(d);
        nd.setEmail(email);
        nd.setSdt(sdt);
        nd.setDiaChi(diaChi);
        nd.setMatKhau(matKhau);
        nd.setGioiTinh(gt);
        nd.setTrangThai(tt);
        nd.setNgayTao(d);
        nd.setChucVu(cv);

        return nd;

    }

    public void addNguoiDung() {
        NguoiDung nd = this.getForm();
        if (nd == null) {
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Chac chan muon them");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        if (ndService.save(nd)) {
            JOptionPane.showMessageDialog(this, "Them thanh cong");
            loadTableNhanVien(ndService.getLst());
            cleanForm();

            return;
        } else {
            JOptionPane.showMessageDialog(this, "Them That Bai");
        }
    }

    public void updateNguoiDung() {
        index = tblNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui long chon NV");
            return;
        }
        NguoiDung getForm = getForm();
        if (getForm == null) {
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Chac chan muon update");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        
        String ma = tblModelNhanVien.getValueAt(index, 1).toString();
        NguoiDung nd = ndService.getObj(ma);
        
        nd.setTen(getForm.getTen());
        nd.setNgaySinh(getForm.getNgaySinh());
        nd.setEmail(getForm.getEmail());
        nd.setSdt(getForm.getSdt());
        nd.setDiaChi(getForm.getDiaChi());
        nd.setGioiTinh(getForm.getGioiTinh());
        nd.setChucVu(getForm.getChucVu());
        nd.setTrangThai(getForm.getTrangThai());
        nd.setMatKhau(getForm.getMatKhau());

        nd.setNgaySua(new Date());

        if (ndService.save(nd)) {
            JOptionPane.showMessageDialog(this, "Update thanh cong");
            loadTableNhanVien(ndService.getLst());
            cleanForm();
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Update That Bai");
        }
    }
    
    public void deleteNguoiDung(){
        index = tblNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui long chon NV");
            return;
        }
        
        int chk = JOptionPane.showConfirmDialog(this, "Chac chan muon Xoa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        
        String ma = tblModelNhanVien.getValueAt(index, 1).toString();
        NguoiDung nd = ndService.getObj(ma);
        
        if (ndService.delete(nd)) {
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            loadTableNhanVien(ndService.getLst());
            cleanForm();
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Xoa That Bai");
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtMa = new textfield.TextField();
        txtTen = new textfield.TextField();
        txtNgaySinh = new textfield.TextField();
        txtEmail = new textfield.TextField();
        txtSdt = new textfield.TextField();
        rdoNam = new radio_button.RadioButtonCustom();
        rdoNu = new radio_button.RadioButtonCustom();
        jLabel2 = new javax.swing.JLabel();
        cboTrangThai = new combobox.Combobox();
        txtMatKhau = new textfield.TextField();
        txtDiaChi = new textfield.TextField();
        cboChucVu = new combobox.Combobox();
        btnCapNhat = new Utilities.raven.textfield.Button();
        btnThem = new Utilities.raven.textfield.Button();
        btnXoa = new Utilities.raven.textfield.Button();
        btnQr = new Utilities.raven.textfield.Button();
        btnXuatFile = new Utilities.raven.textfield.Button();
        btnNhapFile = new Utilities.raven.textfield.Button();
        btnXuatFile1 = new Utilities.raven.textfield.Button();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtTimKiem = new textfield.TextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Quản Lý Nhân Viên");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        txtMa.setLabelText("Mã");

        txtTen.setLabelText("Tên");

        txtNgaySinh.setLabelText("Ngày Sinh");

        txtEmail.setLabelText("Email");

        txtSdt.setLabelText("SÐT");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Giới Tính:");

        cboTrangThai.setLabeText("Trạng Thái");

        txtMatKhau.setLabelText("Mật Khẩu");

        txtDiaChi.setLabelText("Địa Chỉ");

        cboChucVu.setLabeText("Chức Vụ");
        cboChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 102));
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 255, 0));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnQr.setBackground(new java.awt.Color(51, 255, 0));
        btnQr.setText("QR");
        btnQr.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnXuatFile.setBackground(new java.awt.Color(0, 204, 51));
        btnXuatFile.setText("Xuất File");
        btnXuatFile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnNhapFile.setBackground(new java.awt.Color(0, 204, 51));
        btnNhapFile.setText("Nhập File");
        btnNhapFile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnXuatFile1.setBackground(new java.awt.Color(204, 204, 204));
        btnXuatFile1.setText("Reset");
        btnXuatFile1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFile1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(501, 501, 501)
                        .addComponent(btnQr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboChucVu, cboTrangThai, txtDiaChi, txtEmail, txtMa, txtMatKhau, txtNgaySinh, txtSdt, txtTen});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdoNam, rdoNu});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCapNhat, btnNhapFile, btnQr, btnThem, btnXoa, btnXuatFile, btnXuatFile1});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatFile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên", "Ngày Sinh", "Giới Tính", "Email", "SDT", "Địa Chỉ", "Chức Vụ", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        txtTimKiem.setLabelText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        clickTableNhanVien();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnXuatFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFile1ActionPerformed
        cleanForm();
    }//GEN-LAST:event_btnXuatFile1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addNguoiDung();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        updateNguoiDung();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteNguoiDung();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Utilities.raven.textfield.Button btnCapNhat;
    private Utilities.raven.textfield.Button btnNhapFile;
    private Utilities.raven.textfield.Button btnQr;
    private Utilities.raven.textfield.Button btnThem;
    private Utilities.raven.textfield.Button btnXoa;
    private Utilities.raven.textfield.Button btnXuatFile;
    private Utilities.raven.textfield.Button btnXuatFile1;
    private javax.swing.ButtonGroup buttonGroup1;
    private combobox.Combobox cboChucVu;
    private combobox.Combobox cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private radio_button.RadioButtonCustom rdoNam;
    private radio_button.RadioButtonCustom rdoNu;
    private javax.swing.JTable tblNhanVien;
    private textfield.TextField txtDiaChi;
    private textfield.TextField txtEmail;
    private textfield.TextField txtMa;
    private textfield.TextField txtMatKhau;
    private textfield.TextField txtNgaySinh;
    private textfield.TextField txtSdt;
    private textfield.TextField txtTen;
    private textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
