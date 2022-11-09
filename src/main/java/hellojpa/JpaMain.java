package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // web application service  시 web server 올라올 때 실행 때 딱 1번만(DB당 하나)

        EntityManager em = emf.createEntityManager(); // 트랜잭션 단위마다, 고객의 요청이 올 때마다, 쓰레드간에 공유X, 사용하고 버려야

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            저장
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

//            조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id ="+findMember.getId());
//            System.out.println("findMember.name ="+findMember.getName());

//            삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

//            수정
//            자바 객체의 값만 바꿔도 돼, JPA 통해서 entity 가져오면 JPA 가 관리 -> 트랜잭션 커밋 시점에 변경 체크해-> 커밋직전 update 쿼리 올림
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("helloJPA");

//            JPQL
//            엔티티 객체 대상 쿼리 (SQL = 데이터베이스 테이블 대상 쿼리)
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name = "+member.getName());
//            }

//            //비영속
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJPA");
//
//            //영속 -> 이때 db 에 저장 안 됨
//            System.out.println("===BRFORE===");
//            em.persist(member);
//            em.detach(member); // 준영속 상태
//            System.out.println("===AFTER===");

                Member member = new Member();
                member.setUsername("member1");
                em.persist(member);

                Team team = new Team();
                team.setName("teamA");
                team.getMembers().add(member);
                em.persist(team);


            tx.commit(); // 꼭, 이때 디비에 쿼리 날라감, 이때 플러쉬
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close(); // WAS 내려갈 때 닫아줘야, 커넥션 풀링이나 리소스 릴리즈 됨
    }
}
