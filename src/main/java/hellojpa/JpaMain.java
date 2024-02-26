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
            Team team1=new Team();
            team1.setName("team1");
            em.persist(team1);

            Team team2=new Team();
            team2.setName("team2");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("ggumi");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("yangYang");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m").getResultList();

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
