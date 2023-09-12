package org.example;

import hibernate.pojo.Category;
import hibernate.pojo.Product;
import org.hibernate.Session;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class HibernateTester {
    public static void main(String[] args) {
        Session session = HibernateUtils.getFACTORY().openSession();

//        Category c = new Category();  // transient
//        c.setName("Phu kien");
//        c.setDescription("Phu kien 1k");
//        session.save(c);

//        Category c = session.get(Category.class, 2); // persistent
//        c.setDescription("Phu kien 99k");
//        session.getTransaction().begin();
////        session.delete(c);
//        session.save(c);
//        session.getTransaction().commit();

//        Query q = session.createQuery("FROM Category");
//        List<Category> cats = q.getResultList();
//        cats.forEach(c -> System.out.printf("%d - %s\n", c.getId(), c.getName()));

//        Product p = new Product();
//        p.setName("Ipad Pro 2022");
//        p.setPrice(new BigDecimal(22000000));
//
//        Category c = session.get(Category.class, 2);
//        p.setCategory(c);
//        session.save(p);

        Category c = session.get(Category.class, 2);
        c.getProducts().forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

        session.close();

//        System.out.print("aaaaa");
    }
}
