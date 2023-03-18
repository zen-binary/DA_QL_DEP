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
    public List<ChiTietDep> getAllByObj(int tinhTrang, String ten) {
       return ctdRepo.getAllByObj(tinhTrang, ten);
    }

}
