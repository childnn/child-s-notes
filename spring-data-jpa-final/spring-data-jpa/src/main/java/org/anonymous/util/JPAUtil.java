package org.anonymous.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author child
 * 2019/6/22 16:50
 */
public abstract class JPAUtil {
    private static EntityManagerFactory emf;

    static {
        /**
         * 1. 配置文件默认为 META-INF/persistence.xml
         * 2. 指定 配置文件中的 persistence-unit 名称即可
         */
//        emf = Persistence.createEntityManagerFactory("jpa");
//        System.out.println("emf.getClass() = " + emf.getClass()); //class org.hibernate.jpa.internal.EntityManagerFactoryImpl
//        System.out.println("加载配置, 创建实体管理器工厂...");
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("jpa");
            System.out.println("创建 实体管理器...");
        }
        return emf.createEntityManager();
    }
}
