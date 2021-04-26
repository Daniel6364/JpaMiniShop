package com.example.jpaminishop;

import com.example.jpaminishop.model.entity.Member;
import com.example.jpaminishop.model.entity.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class JpaMiniShopApplication {


    public static void testSave(EntityManager em) {

        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

    }

    public static void testLoad(EntityManager em) {

        /*
        System.out.println("==// check1");
        Member member = em.find(Member.class, "member1");
        System.out.println("==// check2");
        Team team = member.getTeam();
        System.out.println("=====// 팀 이름 => " + team.getName());
        */

        String jpql = "select m from Member m join m.team t where " +
                "t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();
        for (Member member : resultList) {
            System.out.println("==// member.username : " + member.getUsername());
        }


    }



    public static void main(String[] args) {
//        SpringApplication.run(JpaMiniShopApplication.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaminishop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

//            testSave(em);
            testLoad(em);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
