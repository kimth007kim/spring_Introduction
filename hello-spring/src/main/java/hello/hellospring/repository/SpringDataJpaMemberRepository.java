package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
//인터페이스만 있는데 구현체를 자동으로 만들어서 JPArepository를 받고있으면 구현체를 만들어서 등록해준다. 우리는 그것을 바로 쓰면된다.
    @Override
    Optional<Member> findByName(String name);
}
