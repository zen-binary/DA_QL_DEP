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
import Utilities.ImageUltil;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import ui.NotificationMess;
import Utilities.ExportFile.ExportNV;
import Utilities.ImportFile.ImportNV;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class NhanVienPanel extends javax.swing.JPanel {

    int index = -1;
    String url = null;
    IChucVuService cvService;
    INguoiDungService ndService;
    DefaultTableModel tblModelNhanVien;
    ImageUltil imageUltil = new ImageUltil();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public NhanVienPanel() {
        initComponents();
        cvService = new ChucVuService();
        ndService = new NguoiDungService();

//        initCboChucVu();
//        initCboTrangThai();
        tblModelNhanVien = (DefaultTableModel) tblNhanVien.getModel();
        loadTableNhanVien(ndService.getAllByObj(txtTimKiem.getText(), "Nhân Viên", -1));
        txtMa.setEnabled(false);
        cboFindLoc.setEditable(false);
        cleanForm();

    }

//    public void initCboChucVu() {
//        DefaultComboBoxModel<ChucVu> boxModel = new DefaultComboBoxModel<>();
//        cboChucVu.removeAllItems();
//        cboChucVu.setModel((DefaultComboBoxModel) boxModel);
//
//        for (ChucVu chucVu : cvService.getLst()) {
//            boxModel.addElement(chucVu);
//        }
//
////if (x.getTrangThai() == 1) {
////                comboSize.removeElement(x);
////            } else {
////                comboSize.addElement(x);
////            }
//    }
//    public void initCboTrangThai() {
//        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
//        cboTrangThai.removeAllItems();
//        cboTrangThai.setModel((DefaultComboBoxModel) boxModel);
//        boxModel.addElement("Ðang Làm");
//        boxModel.addElement("Nghỉ Làm");
//
//    }
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
                nd.getTrangThai() == 0 ? "Ðang Làm" : "Nghỉ Làm"

            });
        }

    }

    public void clickTableNhanVien() {
        index = tblNhanVien.getSelectedRow();
        String ma = (String) tblModelNhanVien.getValueAt(index, 1);

        NguoiDung nd = ndService.getObj(ma);

        String ten = (String) tblModelNhanVien.getValueAt(index, 2);
        String ngaySinh = tblModelNhanVien.getValueAt(index, 3).toString();
        String gioiTinh = (String) tblModelNhanVien.getValueAt(index, 4);
        String email = (String) tblModelNhanVien.getValueAt(index, 5);
        String sdt = (String) tblModelNhanVien.getValueAt(index, 6);
        String diaChi = (String) tblModelNhanVien.getValueAt(index, 7);
//        Object chuVu = tblModelNhanVien.getValueAt(index, 8);
        String trangThai = (String) tblModelNhanVien.getValueAt(index, 8);

        String matKhau = nd.getMatKhau();

        String hinhAnh = nd.getHinhAnh();
        txtMa.setText(ma);
        txtTen.setText(ten);
        txtNgaySinh.setText(ngaySinh);

        txtEmail.setText(email);
        txtSdt.setText(sdt);
        txtDiaChi.setText(diaChi);
        txtMatKhau.setText(matKhau);
        if (trangThai.equalsIgnoreCase("Ðang Làm")) {
            rdoDangLam.setSelected(true);
        } else {
            rdoNghiLam.setSelected(true);
        }

        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

//        int cvCount = cboChucVu.getItemCount();
//        for (int i = 0; i < cvCount; i++) {
//            Object ch = cboChucVu.getItemAt(i);
//            if (ch.toString().equals(chuVu.toString())) {
//                cboChucVu.setSelectedIndex(i);
//                break;
//            }
//        }
        url = nd.getHinhAnh().toString();
        lblAnhUser.setIcon(imageUltil.KichThuocIcon(new ImageIcon("./images/User/" + url), lblAnhUser.getWidth(), lblAnhUser.getHeight()));

    }

    public void cleanForm() {
        for (int i = 0; i < ndService.getLst().size() + 1; i++) {
            String ma = "ND00" + i;
            if (ndService.getObj(ma) == null) {
                txtMa.setText(ma);
            }
        }

        txtTen.setText("");
        txtNgaySinh.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        rdoDangLam.setSelected(true);
        rdoNam.setSelected(true);
        url = null;
        lblAnhUser.setIcon(null);
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

    public NguoiDung getForm() {
        NguoiDung nd = new NguoiDung();
        Date d = new Date();
        for (int i = 0; i < ndService.getLst().size() + 1; i++) {
            String maND = "ND00" + i;
            if (ndService.getObj(maND) == null) {
                nd.setMa(maND);
            }
        }

        String ten = txtTen.getText();
        String ngaySinh = txtNgaySinh.getText();
        String email = txtEmail.getText();
        String sdt = txtSdt.getText();
        String diaChi = txtDiaChi.getText();
        String matKhau = txtMatKhau.getText();
        if (ten.trim().length() == 0 || ngaySinh.trim().length() == 0 || email.trim().length() == 0 || sdt.trim().length() == 0 || diaChi.trim().length() == 0 || matKhau.trim().length() == 0) {
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

        ChucVu cv = cvService.getObjByTen("Nhân Viên");

        int tt = 0;
        int gt = 0;

        if (rdoNam.isSelected() == true) {
            gt = 0;
        }

        if (rdoNu.isSelected() == true) {
            gt = 1;
        }
        if (rdoDangLam.isSelected() == true) {
            tt = 0;
        } else {
            tt = 1;
        }

        nd.setTen(ten);
        try {
            nd.setNgaySinh(sdf.parse(ngaySinh));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dd-mm-yyyy");
            return null;
        }
        nd.setEmail(email);
        nd.setSdt(sdt);
        nd.setDiaChi(diaChi);
        nd.setMatKhau(matKhau);
        nd.setGioiTinh(gt);
        nd.setTrangThai(tt);
        nd.setNgayTao(d);
        nd.setNgaySua(d);
        nd.setChucVu(cv);

        if (url == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh");
            return null;
        }
        nd.setHinhAnh(url);

        return nd;

    }

    public void addNguoiDung() {
        NguoiDung nd = this.getForm();
        if (nd == null) {
            return;
        }

        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Thêm Nhân Viên Có Mã Là: " + nd.getMa());
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        if (ndService.save(nd)) {
            JOptionPane.showMessageDialog(this, "Thêm Nhân Viên Thành Công");
            cleanForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Nhân Viên Thất Bại");
        }
        findCboLoc();
    }

    public void updateNguoiDung() {
        index = tblNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Nhân Viên");
            return;
        }

        NguoiDung getForm = getForm();
        if (getForm == null) {
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Sửa Nhân Viên Có Mã Là: " + getForm.getMa());
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
//        nd.setChucVu(getForm.getChucVu());
        nd.setTrangThai(getForm.getTrangThai());
        nd.setMatKhau(getForm.getMatKhau());
        nd.setHinhAnh(getForm.getHinhAnh());
        nd.setNgaySua(new Date());

        if (ndService.save(nd)) {
            JOptionPane.showMessageDialog(this, "Sửa Nhân Viên Thành Công");
            cleanForm();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Nhân Viên Thất Bại");
        }
        findCboLoc();

    }

    public void deleteNguoiDung() {
        index = tblNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Nhân Viên");
            return;
        }
        String ma = tblModelNhanVien.getValueAt(index, 1).toString();

        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Xóa Nhan Viên Có Mã: " + ma);
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        NguoiDung nd = ndService.getObj(ma);

        if (ndService.delete(nd)) {
            JOptionPane.showMessageDialog(this, "Xóa Nhân Viên Thành Công");
            findCboLoc();
            cleanForm();
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Nhân Viên Thất Bại");
        }

    }

    public void findCboLoc() {
        int index = cboFindLoc.getSelectedIndex();
        if (index == 1) {
            loadTableNhanVien(ndService.getAllByObj(txtTimKiem.getText(), "Nhân Viên", 0));
        } else if (index == 2) {
            loadTableNhanVien(ndService.getAllByObj(txtTimKiem.getText(), "Nhân Viên", 1));
        } else {
            loadTableNhanVien(ndService.getAllByObj(txtTimKiem.getText(), "Nhân Viên", -1));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblAnhUser = new javax.swing.JLabel();
        txtMa = new textfield.TextField();
        txtTen = new textfield.TextField();
        txtNgaySinh = new textfield.TextField();
        txtEmail = new textfield.TextField();
        txtSdt = new textfield.TextField();
        rdoNam = new radio_button.RadioButtonCustom();
        rdoNu = new radio_button.RadioButtonCustom();
        jLabel2 = new javax.swing.JLabel();
        txtMatKhau = new textfield.TextField();
        txtDiaChi = new textfield.TextField();
        btnCapNhat = new Utilities.raven.textfield.Button();
        btnThem = new Utilities.raven.textfield.Button();
        btnXoa = new Utilities.raven.textfield.Button();
        btnXuatFile = new Utilities.raven.textfield.Button();
        btnNhapFile = new Utilities.raven.textfield.Button();
        btnXuatFile1 = new Utilities.raven.textfield.Button();
        jLabel3 = new javax.swing.JLabel();
        rdoDangLam = new radio_button.RadioButtonCustom();
        rdoNghiLam = new radio_button.RadioButtonCustom();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtTimKiem = new textfield.TextField();
        cboFindLoc = new combo_suggestion.ComboBoxSuggestion();
        jSeparator1 = new javax.swing.JSeparator();

        dateChooser1.setForeground(new java.awt.Color(0, 204, 255));
        dateChooser1.setTextRefernce(txtNgaySinh);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Quản Lý Nhân Viên");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblAnhUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhUserMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhUser, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhUser, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
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

        txtMatKhau.setLabelText("Mật Khẩu");

        txtDiaChi.setLabelText("Địa Chỉ");

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 102));
        btnCapNhat.setText("Sửa");
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

        btnXuatFile.setBackground(new java.awt.Color(0, 204, 51));
        btnXuatFile.setText(" Export File");
        btnXuatFile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnNhapFile.setBackground(new java.awt.Color(0, 204, 51));
        btnNhapFile.setText(" Import File");
        btnNhapFile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNhapFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapFileActionPerformed(evt);
            }
        });

        btnXuatFile1.setBackground(new java.awt.Color(204, 204, 204));
        btnXuatFile1.setText("Clean");
        btnXuatFile1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFile1ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Trạng Thái");

        buttonGroup2.add(rdoDangLam);
        rdoDangLam.setText("Ðang Làm");

        buttonGroup2.add(rdoNghiLam);
        rdoNghiLam.setText("Nghỉ Làm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtMa, txtMatKhau, txtNgaySinh, txtSdt, txtTen});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdoNam, rdoNu});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCapNhat, btnNhapFile, btnThem, btnXoa, btnXuatFile, btnXuatFile1});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(10, 10, 10)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(rdoDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(rdoNghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(btnXuatFile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên", "Ngày Sinh", "Giới Tính", "Email", "SDT", "Địa Chỉ", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        txtTimKiem.setLabelText("Tìm Kiếm");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        cboFindLoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboFindLoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Ðang Làm", "Nghỉ Làm" }));
        cboFindLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFindLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1206, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboFindLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFindLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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

    private void lblAnhUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhUserMouseClicked
        try {
            JFileChooser chonFile = new JFileChooser("./images/User/");
            chonFile.showOpenDialog(null);
            File anh = chonFile.getSelectedFile();
            url = anh.getAbsolutePath();

            lblAnhUser.setIcon(imageUltil.KichThuocIcon(new ImageIcon(url), lblAnhUser.getWidth(), lblAnhUser.getHeight()));

            File file = new File(url);
            file.renameTo(new File("./images/User/" + file.getName()));
            url = file.getName();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_lblAnhUserMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        findCboLoc();

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
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
                    ExportNV.writeExcel(ndService.getAllByObj("", "Nhân Viên", -1), fileToSave.getAbsolutePath() + filter.getDescription());
                    JOptionPane.showMessageDialog(this, "Xuất File Excel thành công");

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Xuất File Excel thất bại");
            }
            System.out.println("Save as file: " + fileToSave.getAbsolutePath() + filter.getDescription());
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnNhapFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapFileActionPerformed
        JFileChooser fileChooser = new JFileChooser("./file/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Import Excel");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileOpen = fileChooser.getSelectedFile();
            try {
                List<NguoiDung> list = ImportNV.readExcel(fileOpen.getAbsolutePath());
                int chk = JOptionPane.showConfirmDialog(this, "Xác nhận thêm " + list.size() + " nhân viên ?");
                if (chk == JOptionPane.YES_OPTION) {
                    for (NguoiDung x : list) {
                        NguoiDung n = ndService.getObj(x.getMa());
                        System.out.println("ma Nd: " + x.getMa());
                        if (n != null) {

                            continue;
                        } else {
                            ndService.save(x);
                        }

                    }
                    JOptionPane.showMessageDialog(this, "Thêm File Excel thành công");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Thêm File Excel thất bại");
            }
            System.out.println("Save as file: " + fileOpen.getAbsolutePath());
            loadTableNhanVien(ndService.getAllByObj(txtTimKiem.getText(), "Nhân Viên", -1));
            cleanForm();

        }
    }//GEN-LAST:event_btnNhapFileActionPerformed

    private void cboFindLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFindLocActionPerformed
        findCboLoc();
    }//GEN-LAST:event_cboFindLocActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Utilities.raven.textfield.Button btnCapNhat;
    private Utilities.raven.textfield.Button btnNhapFile;
    private Utilities.raven.textfield.Button btnThem;
    private Utilities.raven.textfield.Button btnXoa;
    private Utilities.raven.textfield.Button btnXuatFile;
    private Utilities.raven.textfield.Button btnXuatFile1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private combo_suggestion.ComboBoxSuggestion cboFindLoc;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnhUser;
    private radio_button.RadioButtonCustom rdoDangLam;
    private radio_button.RadioButtonCustom rdoNam;
    private radio_button.RadioButtonCustom rdoNghiLam;
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
