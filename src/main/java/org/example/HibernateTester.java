package org.example;

import org.hibernate.Session;

public class HibernateTester {
    public static void main(String[] args) {
        Session session = HibernateUtils.getFACTORY().openSession();

        Category c = new Category();
        c.setName("Phu kien");
        c.setDescription("Phu kien 1k");
        session.save(c);

        session.close();

//        System.out.print("aaaaa");
    }
}
