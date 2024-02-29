package hellojpa;

import hellojpa.jpashop.Book;
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
            Member member = new Member();
            member.setUsername("gumi");
            member.setWorkPeriod(new Period());
            member.setHomeAddress(new Address("서울","강남구","33333"));
            em.persist(member);

            tx.commit(); // 이때 디비에 쿼리 날라감, 이때 플러쉬
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close(); // WAS 내려갈 때 닫아줘야, 커넥션 풀링이나 리소스 릴리즈 됨
    }

}
