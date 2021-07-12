package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository     //스프링 bean으로 등록해준다.Componentscan대상이 된다.
public class MemberRepository {

    @PersistenceContext //JPA를 사용하기떄문에 JPA가 제공하는 표준  // 어노테이션이있으면 주입해준다.
    private EntityManager em;

    public void save(Member member){            //persist 하면 영속성 컨텍스트에 Member을 넣고 커밋되는 시점에 DB에 반영이됨
        em.persist(member);
    }

    public Member findOne(Long id){             //단건 조회
        return em.find(Member.class,id);
    }
    public List<Member> findAll(){          //JPQL을 썼다. from 의 대상에 테이블이아니라 엔티티이다.
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();

    }

}
