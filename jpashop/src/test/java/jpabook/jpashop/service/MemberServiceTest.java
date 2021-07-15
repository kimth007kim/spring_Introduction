package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //jpa 가 db까지돌고하는것을 보여주기위해 강의에서 spring과 인티그레이션해서 쓸것이다.
@SpringBootTest         //SpringBoot를 띄운상태로 test할때 필요
@Transactional          //있어야 롤백이 된다. 각 트랜잭션안에서 pk값이 똑같으면 영속성컨텍스트가 같다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member,memberRepository.findOne(saveId));

    }
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1= new Member();
        member1.setName("kim");

        Member member2= new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);        //예외가 발생해야한다!!



        //then
        fail("예외가 발생해야 한다.");


    }

}