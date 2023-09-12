package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {
    // đc cấp và dùng chung 1 lần duy nhất
    private static final SessionFactory FACTORY;

    // khối tĩnh đc gọi lần đầu tiên khi lớp này đc nạp
    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost/hibernate_demo");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "");
        props.put(Environment.SHOW_SQL, "true");

        conf.setProperties(props);
        conf.addAnnotatedClass(Category.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY =conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
