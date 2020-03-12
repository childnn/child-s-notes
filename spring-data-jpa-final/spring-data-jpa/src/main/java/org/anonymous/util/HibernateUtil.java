package org.anonymous.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author child
 * 2019/6/22 20:52
 */
public abstract class HibernateUtil {
    private static final Configuration cfg;
    private static final SessionFactory sf;

    static {
        cfg = new Configuration().configure();
        sf = cfg.buildSessionFactory();
    }

    public static Session openSession() {
        return sf.openSession();
    }
}
