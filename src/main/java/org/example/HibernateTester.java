package org.example;

import hibernate.pojo.Category;
import hibernate.pojo.Manufacturer;
import hibernate.pojo.Product;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//        Category c = session.get(Category.class, 2);
//        c.getProducts().forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

//        Product p = new Product();
//        p.setName("Iphone 15 Pro Max");
//        p.setPrice(new BigDecimal(35000000));
//
//        Category c = session.get(Category.class, 2);
//        p.setCategory(c);
//
//        Set<Manufacturer> mans = new HashSet<>();
//        mans.add(session.get(Manufacturer.class, 1));
//        mans.add(session.get(Manufacturer.class, 2));
//        p.setManufacturers(mans);
//        session.getTransaction().begin();
//        session.save(p);
//        session.getTransaction().commit();

//        Manufacturer man = session.get(Manufacturer.class, 2);
//        man.getProducts().forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

        // Criteria API

//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Product> query = builder.createQuery(Product.class);
//        Root root = query.from(Product.class);
//        query = query.select(root);
//
//        Predicate p1 = builder.like(root.get("name").as(String.class), "%Iphone%");
//        Predicate p2 = builder.like(root.get("name").as(String.class), "%Ipod%");
//        query = query.where(builder.or(p1, p2));
//
//        Query q = session.createQuery(query);
//
//        List<Product>  products = q.getResultList();
//        products.forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root root = query.from(Product.class);
//        query = query.multiselect(
//                builder.count(root.get("id").as(Integer.class)),
//                builder.max(root.get("price").as(BigDecimal.class))
//        );
//
//        Query q = session.createQuery(query);
//
//        Object[] kq = (Object[]) q.getSingleResult();
//        System.out.println("Count = " + kq[0]);
//        System.out.println("Max = " + kq[1]);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Product> productRoot = query.from(Product.class);
        Root<Category> categoryRoot = query.from(Category.class);
        query.where(builder.equal(productRoot.get("category"), categoryRoot.get("id")));

        query = query.multiselect(
                categoryRoot.get("name").as(String.class),
                builder.count(productRoot.get("id").as(Integer.class)),
                builder.max(productRoot.get("price").as(BigDecimal.class))
        );
        query =  query.groupBy(categoryRoot.get("name").as(String.class));
        query =  query.orderBy(builder.asc(categoryRoot.get("name").as(String.class)));

        Query q = session.createQuery(query);
        List<Object[]> kq = q.getResultList();
        kq.forEach(k -> {
            System.out.printf("%s - count: %d - Max: %.2f\n", k[0], k[1], k[2]);
        });

        session.close();

//        System.out.print("aaaaa");
    }
}
