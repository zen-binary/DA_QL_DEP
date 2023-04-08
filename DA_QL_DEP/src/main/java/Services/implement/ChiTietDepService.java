/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.ChiTietDep;
import Reposetories.IChiTietDepReposetory;
import Reposetories.implement.ChiTietDepReposetory;
import Services.IChiTietDepService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietDepService implements IChiTietDepService {

    IChiTietDepReposetory ctdRepo;

    public ChiTietDepService() {
        ctdRepo = new ChiTietDepReposetory();
    }

    @Override
    public List<ChiTietDep> getLst() {
        return ctdRepo.getLstDb();
    }

    @Override
    public boolean save(ChiTietDep ctd) {
        return ctdRepo.save(ctd);
    }

    @Override
    public boolean delete(ChiTietDep ctd) {
        return ctdRepo.delete(ctd);

    }

    @Override
    public ChiTietDep getObj(String ma) {
        return ctdRepo.getObj(ma);
    }

    @Override
    public ChiTietDep getObjById(int id) {
        return ctdRepo.getObjById(id);
    }

    @Override
    public List<ChiTietDep> getAllByObj(int tinhTrang, String ten, int soLuong) {
        return ctdRepo.getAllByObj(tinhTrang, ten, soLuong);
    }

    @Override
    public List<ChiTietDep> getLstByTen(String ten) {
        return ctdRepo.getLstByTen(ten);
    }

    @Override
    public ChiTietDep getFindAllObj(int idDep, int idLoai, int idSize, int idMs, int idCl, int idNsx) {
        return ctdRepo.getFindAllObj(idDep, idLoai, idSize, idMs, idCl, idNsx);
    }

    @Override
    public int getCount() {
        return ctdRepo.getCount();
    }

    @Override
    public int getCountTinhTrang(int tinhTrang) {
        return ctdRepo.getCountTinhTrang(tinhTrang);
    }

    @Override
    public List<ChiTietDep> getAllBySoLuong(int soLuong, String ten) {
        return ctdRepo.getAllBySoLuong(soLuong,ten);
    }

    @Override
    public List<ChiTietDep> getAllByTinhTrang(int tinhTrang, String ten) {
        return ctdRepo.getAllByTinhTrang(tinhTrang,ten);
    }

}
