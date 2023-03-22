/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Views;

import Models.ChatLieu;
import Models.LoaiDep;
import Models.MauSac;
import Models.Nsx;
import Models.Size;
import Services.IChatLieuService;
import Services.ILoaiDepService;
import Services.IMauSacService;
import Services.INsxService;
import Services.ISizeService;
import Services.implement.ChatLieuService;
import Services.implement.LoaiDepService;
import Services.implement.MauSacService;
import Services.implement.NsxService;
import Services.implement.SizeService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.engine.loading.internal.LoadContexts;

/**
 *
 * @author Admin
 */
public class QLCTDLog extends javax.swing.JDialog {

    int indexMs = -1;
    int indexCl = -1;
    int indexNsx = -1;
    int indexSize = -1;
    int indexLoai = -1;

    IMauSacService msService;
    IChatLieuService clService;
    INsxService nsxService;
    ISizeService sizeService;
    ILoaiDepService loaiService;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    DefaultTableModel tblModelMauSac;
    DefaultTableModel tblModelChatLieu;
    DefaultTableModel tblModelNsx;
    DefaultTableModel tblModelSize;
    DefaultTableModel tblModelLoai;

    public QLCTDLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        msService = new MauSacService();
        clService = new ChatLieuService();
        nsxService = new NsxService();
        sizeService = new SizeService();
        loaiService = new LoaiDepService();

        getModel();

        loadTableMauSac(msService.getLstByMs(txtTimKiemMs.getText()));
        loadTableChatLieu(clService.getLstByCl(txtTimKiemChatLieu.getText()));
        loadTableNsx(nsxService.getLstByTen(txtTimKiemNsx.getText()));
        loadTableSize(sizeService.getLst());
        loadTableLoai(loaiService.getLstByTen(txtTimKiemLoai.getText()));

        txtMaChatLieu.setEnabled(false);
        txtMaLoai.setEnabled(false);
        txtMaSize.setEnabled(false);
        txtMaNsx.setEnabled(false);
        txtMaMs.setEnabled(false);

        cleanFromMauSac();
        cleanFromChatLieu();
        cleanFormLoai();
        cleanFormNsx();
        cleanFormSize();
    }

    public void getModel() {
        tblModelMauSac = (DefaultTableModel) tblMauSac.getModel();
        tblModelChatLieu = (DefaultTableModel) tblChatLieu.getModel();
        tblModelNsx = (DefaultTableModel) tblNsx.getModel();
        tblModelSize = (DefaultTableModel) tblSize.getModel();
        tblModelLoai = (DefaultTableModel) tblLoai.getModel();
    }

    public void loadTableMauSac(List<MauSac> lst) {
        int i = 0;
        tblModelMauSac.setRowCount(0);
        for (MauSac ms : lst) {
            tblModelMauSac.addRow(new Object[]{
                ++i,
                ms.getMa(),
                ms.getMauSac(),
                ms.getNgayTao() == null ? "" : sdf.format(ms.getNgayTao()),
                ms.getNgaySua() == null ? "" : sdf.format(ms.getNgaySua())
            });
        }

    }

    public void loadTableChatLieu(List<ChatLieu> lst) {
        int i = 0;
        tblModelChatLieu.setRowCount(0);
        for (ChatLieu cl : lst) {
            tblModelChatLieu.addRow(new Object[]{
                ++i,
                cl.getMa(),
                cl.getTen(),
                cl.getNgayTao() == null ? "" : sdf.format(cl.getNgayTao()),
                cl.getNgaySua() == null ? "" : sdf.format(cl.getNgaySua())
            });
        }
    }

    public void cleanFromMauSac() {
        for (int i = 0; i < msService.getLst().size() + 1; i++) {
            String ma = "M00" + i;
            if (msService.getObj(ma) == null) {
                txtMaMs.setText(ma);
            }
        }
        txtMauSac.setText("");
    }

    public void cleanFromChatLieu() {
        for (int i = 0; i < clService.getLst().size() + 1; i++) {
            String ma = "CL00" + i;
            if (clService.getObj(ma) == null) {
                txtMaChatLieu.setText(ma);
            }
        }
        txtChatLieu.setText("");
    }

    public MauSac getFormMauSac() {

        String mauSac = txtMauSac.getText();
        MauSac ms = new MauSac();

        for (int i = 0; i < msService.getLst().size() + 1; i++) {
            String maMs = "M00" + i;
            if (msService.getObj(maMs) == null) {
                ms.setMa(maMs);
            }
        }

        ms.setMauSac(mauSac);
        ms.setNgayTao(new Date());
        ms.setNgaySua(new Date());
        ms.setTinhTrang(0);

        return ms;
    }

    public ChatLieu getFormChatLieu() {

        String chatLieu = txtChatLieu.getText();
        ChatLieu cl = new ChatLieu();

        for (int i = 0; i < clService.getLst().size() + 1; i++) {
            String maCl = "CL00" + i;
            if (clService.getObj(maCl) == null) {
                cl.setMa(maCl);;
            }
        }

        cl.setTen(chatLieu);
        cl.setNgayTao(new Date());
        cl.setNgaySua(new Date());
        cl.setTinhTrang(0);

        return cl;
    }

    public void addMauSac() {
        MauSac ms = getFormMauSac();
        if (ms == null) {
            return;
        }

        if (msService.save(ms)) {
            JOptionPane.showMessageDialog(this, "Thêm Màu Sắc Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Màu Sắc Thất Bại");
        }
        loadTableMauSac(msService.getLstByMs(txtTimKiemMs.getText()));
        cleanFromMauSac();
    }

    public void addChatLieu() {
        ChatLieu cl = getFormChatLieu();
        if (cl == null) {
            return;
        }
        if (clService.save(cl)) {
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }
        loadTableChatLieu(clService.getLstByCl(txtTimKiemChatLieu.getText()));
        cleanFromChatLieu();
    }

    public void updateMauSac() {
        indexMs = tblMauSac.getSelectedRow();

        if (indexMs == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        MauSac mauSac = getFormMauSac();
        if (mauSac == null) {
            return;
        }
        String ma = tblModelMauSac.getValueAt(indexMs, 1).toString();
        MauSac ms = msService.getObj(ma);
        ms.setMauSac(mauSac.getMauSac());
        ms.setNgaySua(mauSac.getNgaySua());

        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa");

        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        if (msService.save(ms)) {
            JOptionPane.showMessageDialog(this, "Sửa Màu Sắc Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Màu Sắc Thất Bại");
        }
        loadTableMauSac(msService.getLstByMs(txtTimKiemMs.getText()));
        cleanFromMauSac();
    }

    public void updateChatLieu() {
        indexCl = tblChatLieu.getSelectedRow();
        if (indexCl == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }
        ChatLieu chatLieu = getFormChatLieu();
        if (chatLieu == null) {
            return;
        }
        String ma = tblModelChatLieu.getValueAt(indexCl, 1).toString();
        ChatLieu cl = clService.getObj(ma);
        cl.setTen(chatLieu.getTen());
        cl.setNgaySua(chatLieu.getNgaySua());

        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa");

        if (chk != JOptionPane.YES_OPTION) {
            return;
        }

        if (clService.save(cl)) {
            JOptionPane.showMessageDialog(this, "Sửa Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Thất Bại");
        }
        loadTableChatLieu(clService.getLstByCl(txtTimKiemChatLieu.getText()));
        cleanFromChatLieu();
    }

    public void deleteMauSac() {
        indexMs = tblMauSac.getSelectedRow();

        if (indexMs == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }

        String ma = tblModelMauSac.getValueAt(indexMs, 1).toString();
        MauSac ms = msService.getObj(ma);

        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa");

        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (msService.delete(ms)) {
            JOptionPane.showMessageDialog(this, "Xóa Màu Sắc Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Màu Sắc Thất Bại");
        }
        loadTableMauSac(msService.getLstByMs(txtTimKiemMs.getText()));
        cleanFromMauSac();
    }

    public void deleteChatLieu() {
        indexCl = tblChatLieu.getSelectedRow();

        if (indexCl == -1) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn");
            return;
        }

        String ma = tblModelChatLieu.getValueAt(indexCl, 1).toString();
        ChatLieu cl = clService.getObj(ma);

        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa");

        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (clService.delete(cl)) {
            JOptionPane.showMessageDialog(this, "Xóa Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Thất Bại");
        }
        loadTableChatLieu(clService.getLstByCl(txtTimKiemChatLieu.getText()));
        cleanFromChatLieu();
    }

    public void clickTableMauSac() {
        indexMs = tblMauSac.getSelectedRow();

        txtMaMs.setText(tblModelMauSac.getValueAt(indexMs, 1).toString());
        txtMauSac.setText(tblModelMauSac.getValueAt(indexMs, 2).toString());
    }

    public void clickTableChatLieu() {
        indexCl = tblChatLieu.getSelectedRow();

        txtMaChatLieu.setText(tblModelChatLieu.getValueAt(indexCl, 1).toString());
        txtChatLieu.setText(tblModelChatLieu.getValueAt(indexCl, 2).toString());
    }

    //NSX
    public void cleanFormNsx() {
        for (int i = 0; i < nsxService.getLst().size() + 1; i++) {
            String ma = "Nsx00" + i;
            if (nsxService.getObj(ma) == null) {
                txtMaNsx.setText(ma);
            }
        }
        txtNsx.setText("");
    }

    public void loadTableNsx(List<Nsx> lst) {
        int i = 0;
        tblModelNsx.setRowCount(0);

        for (Nsx nsx : lst) {
            tblModelNsx.addRow(new Object[]{
                ++i,
                nsx.getMa(),
                nsx.getTen(),
                nsx.getNgayTao() == null ? "" : sdf.format(nsx.getNgayTao()),
                nsx.getNgaySua() == null ? "" : sdf.format(nsx.getNgaySua())
            });
        }

    }

    public Nsx getFormNsx() {
        Nsx nsx = new Nsx();
        for (int i = 0; i < nsxService.getLst().size() + 1; i++) {
            String ma = "Nsx00" + i;
            if (clService.getObj(ma) == null) {
                nsx.setMa(ma);
            }
        }

        String ten = txtNsx.getText();

        nsx.setTen(ten);
        nsx.setNgayTao(new Date());
        nsx.setNgaySua(new Date());
        nsx.setTinhTrang(0);
        return nsx;

    }

    public void addNsx() {
        Nsx nsx = getFormNsx();
        if (nsxService.save(nsx)) {
            JOptionPane.showMessageDialog(this, "Thêm Nhà Sản Xuất Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Nhà Sản Xuất Thất Bại");
        }

        loadTableNsx(nsxService.getLstByTen(txtTimKiemNsx.getText()));
        cleanFormNsx();
    }

    public void updateNsx() {
        indexNsx = tblNsx.getSelectedRow();

        if (indexNsx == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn");
            return;
        }
        String ma = tblModelNsx.getValueAt(indexNsx, 1).toString();
        Nsx nsx = nsxService.getObj(ma);
        String ten = getFormNsx().getTen();
        nsx.setTen(ten);
        nsx.setNgaySua(getFormNsx().getNgaySua());
        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (nsxService.save(nsx)) {
            JOptionPane.showMessageDialog(this, "Sửa Nsx Thành Công ");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Nsx Thất Bại");
        }
        loadTableNsx(nsxService.getLstByTen(txtTimKiemNsx.getText()));
        cleanFormNsx();
    }

    public void deleteNsx() {
        indexNsx = tblNsx.getSelectedRow();

        if (indexNsx == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn");
            return;
        }
        String ma = tblModelNsx.getValueAt(indexNsx, 1).toString();
        Nsx nsx = nsxService.getObj(ma);
        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (nsxService.delete(nsx)) {
            JOptionPane.showMessageDialog(this, "Xóa Nsx Thành Công ");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Nsx Thất Bại");
        }
        loadTableNsx(nsxService.getLstByTen(txtTimKiemNsx.getText()));
        cleanFormNsx();
    }

    public void clickTableNsx() {
        indexNsx = tblNsx.getSelectedRow();

        String ma = tblModelNsx.getValueAt(indexNsx, 1).toString();
        String ten = tblModelNsx.getValueAt(indexNsx, 2).toString();

        txtMaNsx.setText(ma);
        txtNsx.setText(ten);
    }

//    Size
    public void cleanFormSize() {

        for (int i = 0; i < sizeService.getLst().size() + 1; i++) {
            String ma = "S00" + i;
            if (sizeService.getObj(ma) == null) {
                txtMaSize.setText(ma);
            }
        }
        txtSize.setText("");
    }

    public void loadTableSize(List<Size> lst) {
        int i = 0;
        tblModelSize.setRowCount(0);
        for (Size size : lst) {
            tblModelSize.addRow(new Object[]{
                ++i,
                size.getMa(),
                size.getKichCo(),
                size.getNgayTao() == null ? "" : sdf.format(size.getNgayTao()),
                size.getNgaySua() == null ? "" : sdf.format(size.getNgaySua())
            });
        }
    }

    public Size getFormSize() {
        Size s = new Size();

        for (int i = 0; i < sizeService.getLst().size() + 1; i++) {
            String ma = "S00" + i;
            if (sizeService.getObj(ma) == null) {
                s.setMa(ma);
            }
        }
        String kichCo = txtSize.getText();

        s.setKichCo(Float.valueOf(kichCo));
        s.setNgaySua(new Date());
        s.setNgayTao(new Date());
        s.setTinhTrang(0);

        return s;
    }

    public void addSize() {
        Size s = getFormSize();

        if (sizeService.save(s)) {
            JOptionPane.showMessageDialog(this, "Thêm Size Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Size Thất Bại");
        }
        txtTimKiemSize.setText("");
        loadTableSize(sizeService.getLst());
        cleanFormSize();
    }

    public void updateSize() {
        indexSize = tblSize.getSelectedRow();
        Size size = getFormSize();
        String ma = tblModelSize.getValueAt(indexSize, 1).toString();
        Size s = sizeService.getObj(ma);
        s.setKichCo(size.getKichCo());
        s.setNgaySua(size.getNgaySua());
        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (sizeService.save(s)) {
            JOptionPane.showMessageDialog(this, "Sửa Size Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Size Thất Bại");
        }
        txtTimKiemSize.setText("");
        loadTableSize(sizeService.getLst());
        cleanFormSize();

    }

    public void deleteSize() {
        indexSize = tblSize.getSelectedRow();

        String ma = tblModelSize.getValueAt(indexSize, 1).toString();
        Size s = sizeService.getObj(ma);
        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (sizeService.delete(s)) {
            JOptionPane.showMessageDialog(this, "Xóa Size Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Size Thất Bại");
        }
        txtTimKiemSize.setText("");
        loadTableSize(sizeService.getLst());
        cleanFormSize();

    }

    public void clickTableSize() {
        indexSize = tblSize.getSelectedRow();

        String ma = tblModelSize.getValueAt(indexSize, 1).toString();
        String size = tblModelSize.getValueAt(indexSize, 2).toString();
        txtSize.setText(size);
        txtMaSize.setText(ma);
    }
//Loai
    public void cleanFormLoai(){
        for (int i = 0; i < loaiService.getLst().size()+1; i++) {
            String ma = "LD00" + i;
            if (loaiService.getObj(ma) == null) {
                txtMaLoai.setText(ma);
            }
        }
        txtLoai.setText("");
        
    }
    public void loadTableLoai(List<LoaiDep> lst) {
        int i = 0;

        tblModelLoai.setRowCount(0);
        for (LoaiDep ld : lst) {
            tblModelLoai.addRow(new Object[]{
                ++i,
                ld.getMa(),
                ld.getTen(),
                ld.getNgayTao() == null ? "" : sdf.format(ld.getNgayTao()),
                ld.getNgaySua() == null ? "" : sdf.format(ld.getNgaySua())
            });
        }
    }

    public LoaiDep getFormLoaiDep() {
        LoaiDep ld = new LoaiDep();
        for (int i = 0; i < loaiService.getLst().size()+1; i++) {
            String ma = "LD00" + i;
            if (loaiService.getObj(ma) == null) {
                ld.setMa(ma);
            }
        }
        
        String ten = txtLoai.getText();

        
        ld.setTen(ten);
        ld.setNgayTao(new Date());
        ld.setNgaySua(new Date());
        ld.setTinhTrang(0);
        return ld;
    }

    public void addLoaiDep() {
        LoaiDep ld = getFormLoaiDep();

        if (loaiService.save(ld)) {
            JOptionPane.showMessageDialog(this, "Thêm Loại Dép Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Loại Dép Thất Bại");
        }
        loadTableLoai(loaiService.getLstByTen(txtTimKiemLoai.getText()));
        cleanFormLoai();
    }

    public void updateLoaiDep() {
        indexLoai = tblLoai.getSelectedRow();

        String ma = tblModelLoai.getValueAt(indexLoai, 1).toString();
        LoaiDep loai = getFormLoaiDep();
        LoaiDep ld = loaiService.getObj(ma);
        ld.setTen(loai.getTen());
        ld.setNgaySua(loai.getNgaySua());
        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (loaiService.save(ld)) {
            JOptionPane.showMessageDialog(this, "Sửa Loại Dép Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa Loại Dép Thất Bại");
        }
        loadTableLoai(loaiService.getLstByTen(txtTimKiemLoai.getText()));
        cleanFormLoai();
    }

    public void deleteLoaiDep() {
        indexLoai = tblLoai.getSelectedRow();

        String ma = tblModelLoai.getValueAt(indexLoai, 1).toString();
        LoaiDep ld = loaiService.getObj(ma);
        int chk = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa");
        if (chk != JOptionPane.YES_OPTION) {
            return;
        }
        if (loaiService.delete(ld)) {
            JOptionPane.showMessageDialog(this, "Xóa Loại Dép Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa Loại Dép Thất Bại");
        }
        loadTableLoai(loaiService.getLstByTen(txtTimKiemLoai.getText()));
        cleanFormLoai();
    }

    public void clickTableLoaiDep() {
        indexLoai = tblLoai.getSelectedRow();

        String ma = tblModelLoai.getValueAt(indexLoai, 1).toString();
        String ten = tblModelLoai.getValueAt(indexLoai, 2).toString();
        txtMaLoai.setText(ma);
        txtLoai.setText(ten);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txtLoai = new textfield.TextField();
        txtMaLoai = new textfield.TextField();
        button5 = new swing.Button();
        button6 = new swing.Button();
        button7 = new swing.Button();
        button24 = new swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoai = new javax.swing.JTable();
        txtTimKiemLoai = new textfield.TextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtSize = new textfield.TextField();
        txtMaSize = new textfield.TextField();
        button8 = new swing.Button();
        button9 = new swing.Button();
        button10 = new swing.Button();
        button23 = new swing.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSize = new javax.swing.JTable();
        txtTimKiemSize = new textfield.TextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtNsx = new textfield.TextField();
        txtMaNsx = new textfield.TextField();
        button11 = new swing.Button();
        button12 = new swing.Button();
        button13 = new swing.Button();
        button22 = new swing.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNsx = new javax.swing.JTable();
        txtTimKiemNsx = new textfield.TextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        txtChatLieu = new textfield.TextField();
        txtMaChatLieu = new textfield.TextField();
        button14 = new swing.Button();
        button15 = new swing.Button();
        button16 = new swing.Button();
        button21 = new swing.Button();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        txtTimKiemChatLieu = new textfield.TextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtMauSac = new textfield.TextField();
        txtMaMs = new textfield.TextField();
        btnThem = new swing.Button();
        button18 = new swing.Button();
        button19 = new swing.Button();
        button20 = new swing.Button();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        txtTimKiemMs = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        button4 = new swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        txtLoai.setLabelText("Tên Loại");

        txtMaLoai.setLabelText("Mã");

        button5.setBackground(new java.awt.Color(51, 255, 0));
        button5.setText("Thêm");
        button5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        button6.setBackground(new java.awt.Color(255, 255, 0));
        button6.setText("Sửa");
        button6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        button7.setBackground(new java.awt.Color(255, 51, 51));
        button7.setText("Xóa");
        button7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });

        button24.setBackground(new java.awt.Color(0, 153, 255));
        button24.setText("Clean");
        button24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button24, button5, button6, button7});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tblLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên Loại", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tblLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoai);

        txtTimKiemLoai.setLabelText("Tìm Kiếm");
        txtTimKiemLoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemLoaiKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTimKiemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Loại", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txtSize.setLabelText("Kích Cỡ");
        txtSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSizeActionPerformed(evt);
            }
        });

        txtMaSize.setLabelText("Mã");

        button8.setBackground(new java.awt.Color(51, 255, 0));
        button8.setText("Thêm");
        button8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });

        button9.setBackground(new java.awt.Color(255, 255, 0));
        button9.setText("Sửa");
        button9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });

        button10.setBackground(new java.awt.Color(255, 51, 51));
        button10.setText("Xóa");
        button10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });

        button23.setBackground(new java.awt.Color(0, 153, 255));
        button23.setText("Clean");
        button23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button10, button23, button8, button9});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tblSize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Kích Cỡ", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tblSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSizeMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSize);

        txtTimKiemSize.setLabelText("Tìm Kiếm");
        txtTimKiemSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSizeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTimKiemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Size", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        txtNsx.setLabelText("Nhà Sản Xuất");
        txtNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNsxActionPerformed(evt);
            }
        });

        txtMaNsx.setLabelText("Mã");

        button11.setBackground(new java.awt.Color(51, 255, 0));
        button11.setText("Thêm");
        button11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });

        button12.setBackground(new java.awt.Color(255, 255, 0));
        button12.setText("Sửa");
        button12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });

        button13.setBackground(new java.awt.Color(255, 51, 51));
        button13.setText("Xóa");
        button13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });

        button22.setBackground(new java.awt.Color(0, 153, 255));
        button22.setText("Clean");
        button22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button11, button12, button13, button22});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tblNsx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Nhà Sản Xuất", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tblNsx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNsxMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNsx);

        txtTimKiemNsx.setLabelText("Tìm Kiếm");
        txtTimKiemNsx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemNsxKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTimKiemNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Nhà Sản Xuất", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        txtChatLieu.setLabelText("Chất Liệu");
        txtChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChatLieuActionPerformed(evt);
            }
        });

        txtMaChatLieu.setLabelText("Mã");

        button14.setBackground(new java.awt.Color(51, 255, 0));
        button14.setText("Thêm");
        button14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button14ActionPerformed(evt);
            }
        });

        button15.setBackground(new java.awt.Color(255, 255, 0));
        button15.setText("Sửa");
        button15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button15ActionPerformed(evt);
            }
        });

        button16.setBackground(new java.awt.Color(255, 51, 51));
        button16.setText("Xóa");
        button16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button16ActionPerformed(evt);
            }
        });

        button21.setBackground(new java.awt.Color(0, 153, 255));
        button21.setText("Clean");
        button21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button14, button15, button16, button21});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Chất Liệu", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblChatLieu);

        txtTimKiemChatLieu.setLabelText("Tìm Kiếm");
        txtTimKiemChatLieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemChatLieuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTimKiemChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Chất Liệu", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        txtMauSac.setLabelText("Màu Sắc");
        txtMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMauSacActionPerformed(evt);
            }
        });

        txtMaMs.setLabelText("Mã");

        btnThem.setBackground(new java.awt.Color(51, 255, 0));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        button18.setBackground(new java.awt.Color(255, 255, 0));
        button18.setText("Sửa");
        button18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button18ActionPerformed(evt);
            }
        });

        button19.setBackground(new java.awt.Color(255, 51, 51));
        button19.setText("Xóa");
        button19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button19ActionPerformed(evt);
            }
        });

        button20.setBackground(new java.awt.Color(0, 153, 255));
        button20.setText("Clean");
        button20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaMs, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(button19, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(button18, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(button20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        jPanel14Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThem, button18, button19, button20});

        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaMs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jScrollPane6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane6MouseClicked(evt);
            }
        });

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Màu Sắc", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblMauSac);

        txtTimKiemMs.setLabelText("Tìm Kiếm");
        txtTimKiemMs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMsKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtTimKiemMs, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemMs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Màu Sắc", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Chi Tiết Sản Phẩm");

        button4.setBackground(new java.awt.Color(255, 51, 51));
        button4.setText("Close");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSizeActionPerformed

    private void txtNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNsxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNsxActionPerformed

    private void txtChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChatLieuActionPerformed

    private void txtMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMauSacActionPerformed

    private void button17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button17ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_button4ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addMauSac();
    }//GEN-LAST:event_btnThemActionPerformed

    private void button18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button18ActionPerformed
        updateMauSac();
    }//GEN-LAST:event_button18ActionPerformed

    private void button19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button19ActionPerformed
        deleteMauSac();
    }//GEN-LAST:event_button19ActionPerformed

    private void button14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button14ActionPerformed
        addChatLieu();
    }//GEN-LAST:event_button14ActionPerformed

    private void button15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button15ActionPerformed
        updateChatLieu();
    }//GEN-LAST:event_button15ActionPerformed

    private void jScrollPane6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane6MouseClicked

    }//GEN-LAST:event_jScrollPane6MouseClicked

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        clickTableMauSac();
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        clickTableChatLieu();
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void button16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button16ActionPerformed
        deleteChatLieu();
    }//GEN-LAST:event_button16ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        addNsx();
    }//GEN-LAST:event_button11ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        updateNsx();
    }//GEN-LAST:event_button12ActionPerformed

    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button13ActionPerformed
        deleteNsx();
    }//GEN-LAST:event_button13ActionPerformed

    private void tblNsxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNsxMouseClicked
        clickTableNsx();
    }//GEN-LAST:event_tblNsxMouseClicked

    private void tblSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSizeMouseClicked
        clickTableSize();
    }//GEN-LAST:event_tblSizeMouseClicked

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        addSize();
    }//GEN-LAST:event_button8ActionPerformed

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        updateSize();
    }//GEN-LAST:event_button9ActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        deleteSize();
    }//GEN-LAST:event_button10ActionPerformed

    private void txtTimKiemSizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSizeKeyReleased
        Float size = null;
        try {
            size = Float.valueOf(txtTimKiemSize.getText());

        } catch (Exception e) {
            return;
        }
        if (txtTimKiemSize.getText().equals("")) {
            loadTableSize(sizeService.getLst());
        } else {
            loadTableSize(sizeService.getLstBySize(Float.valueOf(txtTimKiemSize.getText())));
        }


    }//GEN-LAST:event_txtTimKiemSizeKeyReleased

    private void tblLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiMouseClicked
        clickTableLoaiDep();
    }//GEN-LAST:event_tblLoaiMouseClicked

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        addLoaiDep();
    }//GEN-LAST:event_button5ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        updateLoaiDep();
    }//GEN-LAST:event_button6ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        deleteLoaiDep();
    }//GEN-LAST:event_button7ActionPerformed

    private void txtTimKiemLoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemLoaiKeyReleased
        loadTableLoai(loaiService.getLstByTen(txtTimKiemLoai.getText()));

    }//GEN-LAST:event_txtTimKiemLoaiKeyReleased

    private void txtTimKiemNsxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemNsxKeyReleased
        loadTableNsx(nsxService.getLstByTen(txtTimKiemNsx.getText()));
    }//GEN-LAST:event_txtTimKiemNsxKeyReleased

    private void txtTimKiemChatLieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemChatLieuKeyReleased
        loadTableChatLieu(clService.getLstByCl(txtTimKiemChatLieu.getText()));
    }//GEN-LAST:event_txtTimKiemChatLieuKeyReleased

    private void txtTimKiemMsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMsKeyReleased
        loadTableMauSac(msService.getLstByMs(txtTimKiemMs.getText()));
    }//GEN-LAST:event_txtTimKiemMsKeyReleased

    private void button20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button20ActionPerformed
        cleanFromMauSac();
    }//GEN-LAST:event_button20ActionPerformed

    private void button21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button21ActionPerformed
        cleanFromChatLieu();
    }//GEN-LAST:event_button21ActionPerformed

    private void button22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button22ActionPerformed
        cleanFormNsx();
    }//GEN-LAST:event_button22ActionPerformed

    private void button23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button23ActionPerformed
      cleanFormSize();
    }//GEN-LAST:event_button23ActionPerformed

    private void button24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button24ActionPerformed
        cleanFormLoai();
    }//GEN-LAST:event_button24ActionPerformed

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
            java.util.logging.Logger.getLogger(QLCTDLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLCTDLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLCTDLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLCTDLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLCTDLog dialog = new QLCTDLog(new javax.swing.JFrame(), true);
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
    private swing.Button btnThem;
    private swing.Button button10;
    private swing.Button button11;
    private swing.Button button12;
    private swing.Button button13;
    private swing.Button button14;
    private swing.Button button15;
    private swing.Button button16;
    private swing.Button button18;
    private swing.Button button19;
    private swing.Button button20;
    private swing.Button button21;
    private swing.Button button22;
    private swing.Button button23;
    private swing.Button button24;
    private swing.Button button4;
    private swing.Button button5;
    private swing.Button button6;
    private swing.Button button7;
    private swing.Button button8;
    private swing.Button button9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblLoai;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblNsx;
    private javax.swing.JTable tblSize;
    private textfield.TextField txtChatLieu;
    private textfield.TextField txtLoai;
    private textfield.TextField txtMaChatLieu;
    private textfield.TextField txtMaLoai;
    private textfield.TextField txtMaMs;
    private textfield.TextField txtMaNsx;
    private textfield.TextField txtMaSize;
    private textfield.TextField txtMauSac;
    private textfield.TextField txtNsx;
    private textfield.TextField txtSize;
    private textfield.TextField txtTimKiemChatLieu;
    private textfield.TextField txtTimKiemLoai;
    private textfield.TextField txtTimKiemMs;
    private textfield.TextField txtTimKiemNsx;
    private textfield.TextField txtTimKiemSize;
    // End of variables declaration//GEN-END:variables
}
