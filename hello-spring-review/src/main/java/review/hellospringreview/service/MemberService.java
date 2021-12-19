package review.hellospringreview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import review.hellospringreview.domain.Member;
import review.hellospringreview.repository.MemberRepository;
import review.hellospringreview.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //DI 방식으로회원 서비스코드 구성성
   private final MemberRepository memberRepository;

   @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository =memberRepository;
    }


    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
