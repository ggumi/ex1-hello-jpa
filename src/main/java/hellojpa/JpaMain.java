package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행

        // web application service  시 web server 올라올 때 실행 때 딱 1번만(DB당 하나)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 트랜잭션 단위마다, 고객의 요청이 올 때마다, 쓰레드간에 공유X, 사용하고 버려야
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("ggumi");
            em.persist(member1);

            em.flush();
            em.clear();

            Member referenceMember = em.getReference(Member.class, member1.getId());
            System.out.println("referenceMember.getClass() = " + referenceMember.getClass());
//            referenceMember.getUsername();// 강제초기화
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(referenceMember));
            Hibernate.initialize(referenceMember);
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(referenceMember));



            tx.commit(); // 이때 디비에 쿼리 날라감, 이때 플러쉬
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close(); // WAS 내려갈 때 닫아줘야, 커넥션 풀링이나 리소스 릴리즈 됨
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 = " + (m1 instanceof Member));
        System.out.println("m2 = " + (m2 instanceof Member));
        System.out.println("m2 = " + (m1 == m2));
    }
}
