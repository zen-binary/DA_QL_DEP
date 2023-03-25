/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.ChatLieu;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChatLieuService {

    public List<ChatLieu> getLst();

    public List<ChatLieu> getLstByCl(String cl);

    public boolean save(ChatLieu cl);

    public boolean delete(ChatLieu cl);

    public ChatLieu getObj(String ma);
    public ChatLieu getObjByTen(String ten);

    public ChatLieu getObjById(int id);
}
