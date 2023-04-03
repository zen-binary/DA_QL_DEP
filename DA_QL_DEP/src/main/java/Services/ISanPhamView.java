/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;
import Services.implement.ChiTietDepService;
import ViewThongKe.SanPhamView;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamView {
    IChiTietDepService ctdService = new ChiTietDepService();
    public List<SanPhamView> getLstView();

}
