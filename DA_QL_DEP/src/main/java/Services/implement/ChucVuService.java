/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.ChucVu;
import Reposetories.IChucVuReposetory;
import Reposetories.implement.ChuVuReposetory;
import Services.IChucVuService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChucVuService implements IChucVuService {

    IChucVuReposetory cvRepo;

    public ChucVuService() {
        cvRepo = new ChuVuReposetory();
    }

    @Override
    public List<ChucVu> getLst() {
        return cvRepo.getLstDb();
    }

    @Override
    public boolean save(ChucVu cv) {
        return cvRepo.save(cv);
    }

    @Override
    public boolean delete(ChucVu cv) {
        return cvRepo.delete(cv);
    }

    @Override
    public ChucVu getObj(String ma) {
        return cvRepo.getObj(ma);
    }

    @Override
    public ChucVu getObjById(int id) {
        return cvRepo.getObjById(id);
    }

}
