/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Views;

import Models.KhuyenMai;
import Services.IKhuyenMaiService;
import Services.implement.KhuyenMaiService;
import Utilities.GenQRCode;
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
public class KhuyenMaiDlog extends javax.swing.JDialog {

    int index = -1;
    DefaultTableModel tblModelKhuyenMai;
    IKhuyenMaiService kmService;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    KhuyenMai khuyenMai = null;

    public KhuyenMaiDlog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tblModelKhuyenMai = (DefaultTableModel) tblKhuyenMai.getModel();
        kmService = new KhuyenMaiService();
        checkKhuyenMai();
        loadTable(kmService.getAllByTen(txtTimKiem.getText()));
        txtMa.setEnabled(false);
        cleanForm();
    }

    public void loadTable(List<KhuyenMai> lst) {
        int i = 0;
        tblModelKhuyenMai.setRowCount(0);
        for (KhuyenMai km : lst) {
            tblModelKhuyenMai.addRow(new Object[]{
                ++i,
                km.getMa(),
                km.getTen(),
                km.getPhanTramGiam(),
                km.getSoLuong(),
                km.getNgayBatDau() == null ? "null" : sdf.format(km.getNgayBatDau()),
                km.getNgayKetThuc() == null ? "null" : sdf.format(km.getNgayKetThuc()),
                km.getTinhTrang() == 0 ? "Hoạt Động" : "Hết Hạn",
                km.getMoTa()
            });
        }

    }

    public void cleanForm() {

        for (int i = 0; i < kmService.getLst().size() + 1; i++) {
            String ma = "KM00" + i;
            if (kmService.getObj(ma) == null) {
                txtMa.setText(ma);
            }
        }
        txtTen.setText("");
        txtPhanTramGiam.setText("");
        txtSoLuong.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtMoTa.setText("");
        rdoHoatDong.setSelected(true);
    }

    public void clickTable() {
        index = tblKhuyenMai.getSelectedRow();
        String ma = tblModelKhuyenMai.getValueAt(index, 1).toString();
        String ten = tblModelKhuyenMai.getValueAt(index, 2).toString();
        String phanTramGiam = tblModelKhuyenMai.getValueAt(index, 3).toString();
        String soLuong = tblModelKhuyenMai.getValueAt(index, 4).toString();
        String ngayBatDau = tblModelKhuyenMai.getValueAt(index, 5).toString();
        String ngayKetThuc = tblModelKhuyenMai.getValueAt(index, 6).toString();
        String tinhTrang = tblModelKhuyenMai.getValueAt(index, 7).toString();
        String moTa = tblModelKhuyenMai.getValueAt(index, 8).toString();

        txtMa.setText(ma);
        txtTen.setText(ten);
        txtPhanTramGiam.setText(phanTramGiam);
        txtSoLuong.setText(soLuong);
        txtNgayBatDau.setText(ngayBatDau);
        txtNgayKetThuc.setText(ngayKetThuc);
        txtMoTa.setText(moTa);

        if (tinhTrang.equalsIgnoreCase("Hoạt Động")) {
            rdoHoatDong.setSelected(true);
        } else {
            rdoHetHan.setSelected(true);
        }

    }

    public boolean isValidDateRange(String startDateString, String endDateString) {
        try {

            Date startDate = sdf.parse(startDateString);
            Date endDate = sdf.parse(endDateString);
            if (startDate.after(endDate)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public KhuyenMai getForm() {
        KhuyenMai km = new KhuyenMai();
        for (int i = 0; i < kmService.getLst().size() + 1; i++) {
            String maKM = "ND00" + i;
            if (kmService.getObj(maKM) == null) {
                km.setMa(maKM);
            }
        }

        String ten = txtTen.getText();
        String phanTramGiam = txtPhanTramGiam.getText();
        String soLuong = txtSoLuong.getText();
        String ngayBatDau = txtNgayBatDau.getText();
        String ngayKetThuc = txtNgayKetThuc.getText();
        String moTa = txtMoTa.getText();
        if (ten.trim().length() == 0 || phanTramGiam.trim().length() == 0 || ngayBatDau.trim().length() == 0 || ngayKetThuc.trim().length() == 0 || moTa.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Không Được Để Trống");
            return null;
        }

        int tinhTrang = 0;
        if (rdoHoatDong.isSelected() == true) {
            tinhTrang = 0;
        } else {
            tinhTrang = 1;
        }

        Double phanTram = 0.0;
        int sl = 0;
        try {
            phanTram = Double.valueOf(phanTramGiam);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm là số");
            return null;
        }
        try {
            sl = Integer.valueOf(soLuong);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là số");
            return null;
        }

        try {
            km.setNgayBatDau(sdf.parse(ngayBatDau));
            km.setNgayKetThuc(sdf.parse(ngayKetThuc));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng dd-mm-yyyy");
            return null;
        }
        if (!isValidDateRange(ngayBatDau, ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày Kết Thúc Phải Sau Ngày Bắt Đầu");
            return null;
        }
        Date d = new Date();
        if (!isValidDateRange(sdf.format(d), ngayBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày Bắt Đầu Không Được Sau Ngày Tạo");
            return null;
        }

        km.setTen(ten);
        km.setPhanTramGiam(phanTram);
        km.setSoLuong(sl);
        km.setMoTa(moTa);
        km.setNgayTao(new Date());
        km.setNgaySua(new Date());
        km.setTinhTrang(tinhTrang);

        return km;
    }

    public void addKhuyenMai() {
        KhuyenMai km = getForm();

        if (km == null) {
            return;
        }

        if (kmService.save(km)) {
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công");

        } else {
            JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");

        }
        loadTable(kmService.getAllByTen(txtTimKiem.getText()));

    }

    public void updateKhuyenMai() {
        index = tblKhuyenMai.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn");
            return;
        }
        KhuyenMai khuyenMai = getForm();
        if (khuyenMai == null) {
            return;
        }

        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Sửa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        KhuyenMai km = kmService.getAllByTen(txtTimKiem.getText()).get(index);
        km.setTen(khuyenMai.getTen());
        km.setPhanTramGiam(khuyenMai.getPhanTramGiam());
        km.setSoLuong(khuyenMai.getSoLuong());
        km.setMoTa(khuyenMai.getMoTa());
        km.setNgayKetThuc(khuyenMai.getNgayKetThuc());
        km.setNgayBatDau(khuyenMai.getNgayBatDau());
        km.setNgaySua(khuyenMai.getNgaySua());
        km.setTinhTrang(khuyenMai.getTinhTrang());
        if (kmService.save(km)) {
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");

        } else {
            JOptionPane.showMessageDialog(rootPane, "Sửa thất bại");

        }
        loadTable(kmService.getAllByTen(txtTimKiem.getText()));

    }

    public void deleteKhuyenMai() {
        index = tblKhuyenMai.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn");
            return;
        }
        int chk = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        KhuyenMai km = kmService.getAllByTen(txtTimKiem.getText()).get(index);

        if (kmService.delete(km)) {
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công");

        } else {
            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại");

        }
        loadTable(kmService.getAllByTen(txtTimKiem.getText()));

    }
    public boolean isSameDate(String date1String, String date2String) {
        try {
            Date date1 = sdf.parse(date1String);
            Date date2 = sdf.parse(date2String);
            return date1.equals(date2);
        } catch (Exception e) {
            return false;
        }
    }
    public void chonKhuyenMai() {
        index = tblKhuyenMai.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn vui lòng chọn");
            return;
        }

        khuyenMai = kmService.getAllByTen(txtTimKiem.getText()).get(index);
        if (khuyenMai.getTinhTrang() == 1) {
            JOptionPane.showMessageDialog(this, "Khuyến Mại Đã Hết Hạn");
            khuyenMai = null;
            return;
        }
        Date ngayHomNay = new Date();
        if (!isSameDate(sdf.format(ngayHomNay), sdf.format(khuyenMai.getNgayBatDau()))) {
            JOptionPane.showMessageDialog(this, "Khuyến Mại Chưa Đến Ngày Bắt Đầu");
            khuyenMai = null;
            return;
        }
        this.dispose();
    }

    public KhuyenMai getKhuyenMai() {
        return this.khuyenMai;
    }

    public boolean checkKhuyenMai() {
        Date ngayHomNay = new Date();
        for (KhuyenMai km : kmService.getLst()) {
            if (isValidDateRange(sdf.format(ngayHomNay), sdf.format(km.getNgayKetThuc())) == false) {

                km.setTinhTrang(1);
                kmService.save(km);
            }

            System.out.println("km:" + km.toString());
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtMa = new textfield.TextField();
        txtTen = new textfield.TextField();
        txtNgayBatDau = new textfield.TextField();
        txtMoTa = new textfield.TextField();
        txtPhanTramGiam = new textfield.TextField();
        txtSoLuong = new textfield.TextField();
        txtNgayKetThuc = new textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        rdoHoatDong = new radio_button.RadioButtonCustom();
        rdoHetHan = new radio_button.RadioButtonCustom();
        btnThem = new Utilities.raven.textfield.Button();
        btnXoa = new Utilities.raven.textfield.Button();
        btnSua = new Utilities.raven.textfield.Button();
        btnXoa1 = new Utilities.raven.textfield.Button();
        button1 = new Utilities.raven.textfield.Button();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        txtTimKiem = new textfield.TextField();
        btnChonKhuyenMai = new Utilities.raven.textfield.Button();
        button4 = new Utilities.raven.textfield.Button();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        dateChooser1.setForeground(new java.awt.Color(0, 204, 255));
        dateChooser1.setTextRefernce(txtNgayBatDau);

        dateChooser2.setForeground(new java.awt.Color(0, 204, 255));
        dateChooser2.setTextRefernce(txtNgayKetThuc);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Khuyến Mãi");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtMa.setLabelText("Mã");

        txtTen.setLabelText("Tên Khuyến Mãi");

        txtNgayBatDau.setLabelText("Ngày bắt đầu");

        txtMoTa.setLabelText("Mô Tả");

        txtPhanTramGiam.setLabelText("Phần trăm giảm");

        txtSoLuong.setLabelText("Số Lượng");
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtNgayKetThuc.setLabelText("Ngày kết thúc");

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Tình Trạng:");

        buttonGroup1.add(rdoHoatDong);
        rdoHoatDong.setText("Hoạt Động");

        buttonGroup1.add(rdoHetHan);
        rdoHetHan.setText("Hết Hạn");

        btnThem.setBackground(new java.awt.Color(0, 255, 51));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(51, 153, 255));
        btnXoa.setText("Clean");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 51));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa1.setBackground(new java.awt.Color(255, 0, 0));
        btnXoa1.setText("Xóa");
        btnXoa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(0, 153, 255));
        button1.setText("Tạo QR");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 101, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rdoHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addContainerGap(129, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMa, txtMoTa, txtNgayBatDau, txtNgayKetThuc, txtPhanTramGiam, txtSoLuong, txtTen});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdoHetHan, rdoHoatDong});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnThem, btnXoa, button1});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên", "Phần trăm giảm", "Số Lượng", "Ngày bắt đầu", "Ngày kết thúcNgày kết thúc", "Tình Trạng", "Mô Tả"
            }
        ));
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        txtTimKiem.setLabelText("Tìm Kiếm");

        btnChonKhuyenMai.setBackground(new java.awt.Color(0, 153, 255));
        btnChonKhuyenMai.setText("Chọn Khuyến Mại");
        btnChonKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChonKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhuyenMaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnChonKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChonKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        button4.setBackground(new java.awt.Color(255, 51, 51));
        button4.setText("Close");
        button4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_button4ActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        clickTable();
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        cleanForm();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addKhuyenMai();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateKhuyenMai();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        deleteKhuyenMai();
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnChonKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhuyenMaiActionPerformed
        chonKhuyenMai();
    }//GEN-LAST:event_btnChonKhuyenMaiActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        index = tblKhuyenMai.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Khuyến Mãi");
            return;
        }

        String maKm = tblKhuyenMai.getValueAt(index, 1).toString();

        JFileChooser fileChooser = new JFileChooser("./QR/KhuyenMai/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg", "jpg");
        fileChooser.setFileFilter(filter);

        fileChooser.setDialogTitle("Tạo QR Khuyến Mãi");
        fileChooser.setSelectedFile(new File(maKm));
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try {
                int chk = JOptionPane.showConfirmDialog(this, "Xác nhận Tạo QR?");
                if (chk == JOptionPane.YES_OPTION) {
                    GenQRCode.GenQRCode(fileToSave.getAbsolutePath() + filter.getDescription(), maKm);
                    JOptionPane.showMessageDialog(this, "Xuất QR thành công");

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Xuất QR thất bại");
                return;
            }
            System.out.println("Save as QR: " + fileToSave.getAbsolutePath() + filter.getDescription());
        }
    }//GEN-LAST:event_button1ActionPerformed

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
            java.util.logging.Logger.getLogger(KhuyenMaiDlog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiDlog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiDlog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiDlog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhuyenMaiDlog dialog = new KhuyenMaiDlog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Utilities.raven.textfield.Button btnChonKhuyenMai;
    private Utilities.raven.textfield.Button btnSua;
    private Utilities.raven.textfield.Button btnThem;
    private Utilities.raven.textfield.Button btnXoa;
    private Utilities.raven.textfield.Button btnXoa1;
    private Utilities.raven.textfield.Button button1;
    private Utilities.raven.textfield.Button button4;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private radio_button.RadioButtonCustom rdoHetHan;
    private radio_button.RadioButtonCustom rdoHoatDong;
    private javax.swing.JTable tblKhuyenMai;
    private textfield.TextField txtMa;
    private textfield.TextField txtMoTa;
    private textfield.TextField txtNgayBatDau;
    private textfield.TextField txtNgayKetThuc;
    private textfield.TextField txtPhanTramGiam;
    private textfield.TextField txtSoLuong;
    private textfield.TextField txtTen;
    private textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
