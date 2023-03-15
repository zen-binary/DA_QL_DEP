/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.Size;
import Reposetories.ISizeReposetory;
import Reposetories.implement.SizeReposetory;
import Services.ISizeService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SizeService implements ISizeService {

    ISizeReposetory sizeRepo;

    public SizeService() {
        sizeRepo = new SizeReposetory();
    }

    @Override
    public List<Size> getLst() {
        return this.sizeRepo.getLstDb();
    }

    @Override
    public boolean save(Size s) {
        return this.sizeRepo.save(s);
    }

    @Override
    public boolean delete(Size s) {
        return this.sizeRepo.delete(s);
    }

    @Override
    public Size getObj(String ma) {
        return this.sizeRepo.getObj(ma);
    }

    @Override
    public Size getObjById(int id) {
        return this.sizeRepo.getObjById(id);
    }

}
