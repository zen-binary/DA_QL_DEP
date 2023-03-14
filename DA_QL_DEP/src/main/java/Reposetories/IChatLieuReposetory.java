/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reposetories;


import Models.ChatLieu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChatLieuReposetory {
    public List<ChatLieu> getLstDb();
    
    public boolean save(ChatLieu cl);
    
    public boolean delete(ChatLieu cl);
    
    public ChatLieu getObj(String ma);
    
    public ChatLieu getObjById(int id);
}
