package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)//읽기전용일때 readOnly를 써주면 속도가 빨라짐,읽기에는 가급적이면 readOnly=true를 용하자
                                //transaction안에서 데이터변경을 해야한다. 사용하면 public메소드들이 걸려들어간다.
@RequiredArgsConstructor //final이 있는 필드만 가지고 생성자로 생성해준다.
public class MemberService {

    /**
      이런 방식으로 했을떄 Access할수있는 방법이없다 필드고 private이기 때문이다. */

//    @Autowired
//    private MemberRepository memberRepository;
    /**
     그럴때 사용하는 방법이 세터인젝션 방법을 사용한다. 스프링이 바로주입하는게아니라. 매개변수로 들어와서 주입을 해준다. */

//    private MemberRepository memberRepository;
//    @Autowired  //장점:Test케이스 할때 직접 주입해줄수 있다. 가짜 멤버리포지토리같은것 주입가능  단점:(치명적) 돌아가는 동안 누군가가 바꿀수 있다.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
    /**
     따라서 요즘 권장하는 방법은 생성자 인젝션이다.  */
    private final MemberRepository memberRepository;    //필드를 변경할일 없으니 final로 하는것을 권장 컴파일시점에서 생성자가 제대로썼는지 확인할 수있으므로

//    @Autowired    스프링 최신버전에서는 생성자하나만있으면 @Autowired없어도 자동으로 인젝션해준다.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//  @RequiredArgsConstructor를 썼기떄문에 생성자 안써도됨
    /**
     * 회원가입
     */
    @Transactional          //읽기가 많기때문에 기본값으로 읽기 전용을 써주고 쓰기가 있으면따로 써주자
    public Long join(Member member){
        validateDuplicateMember(member);        // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {   //중복회원 확인하는 메서드
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    // 회원 전체 조회

    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
