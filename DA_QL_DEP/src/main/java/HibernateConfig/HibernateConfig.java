package HibernateConfig;

import Models.ChatLieu;
import Models.ChiTietDep;
import Models.ChucVu;
import Models.Dep;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.KhachHang;
import Models.KhuyenMai;
import Models.LoaiDep;
import Models.MauSac;
import Models.NguoiDung;
import Models.Nsx;
import Models.Size;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=DA_QL_DEP");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(ChatLieu.class);
        conf.addAnnotatedClass(ChiTietDep.class);
        conf.addAnnotatedClass(LoaiDep.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(Dep.class);
        conf.addAnnotatedClass(Size.class);
        conf.addAnnotatedClass(Nsx.class);

        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(NguoiDung.class);

        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(KhuyenMai.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }

}
