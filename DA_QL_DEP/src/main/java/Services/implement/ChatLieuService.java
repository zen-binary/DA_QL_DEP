/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.implement;

import Models.ChatLieu;
import Reposetories.IChatLieuReposetory;
import Reposetories.implement.ChatLieuReposetory;
import Services.IChatLieuService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChatLieuService implements IChatLieuService {

    IChatLieuReposetory clRepo;

    public ChatLieuService() {
        clRepo = new ChatLieuReposetory();
    }

    @Override
    public List<ChatLieu> getLst() {
        return clRepo.getLstDb();
    }

    @Override
    public boolean save(ChatLieu cl) {
        return clRepo.save(cl);
    }

    @Override
    public boolean delete(ChatLieu cl) {
        return clRepo.delete(cl);
    }

    @Override
    public ChatLieu getObj(String ma) {
        return clRepo.getObj(ma);
    }

    @Override
    public ChatLieu getObjById(int id) {
           return clRepo.getObjById(id);
    }

}
