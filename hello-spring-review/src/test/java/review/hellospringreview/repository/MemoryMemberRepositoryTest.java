package review.hellospringreview.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import review.hellospringreview.domain.Member;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository  = new MemoryMemberRepository();


    // 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을수 있다. 이전테스트때문에 실패할수있으므로 종료될떄마다 기능을 실행한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

//        when
        repository.save(member);
//        then
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("Spring1").get();

        //then
        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);

    }


}
