package jpabook.jpashop.api;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController //@ResponseBody+ @Controller
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    //조회 v1: 응답값으로 엔티티를 전부 외부에 노출
    @GetMapping("/api/v1/members")  //제일 단순한 방법으로 조회하기
    public List<Member> membersV1(){
            return memberService.findMembers();  //가져와서 리스트로 엔티티를 반환하면 끝난다.
    }                                           //엔티티의 정보를 노출하면 엔티티의 모든 정보가 외부에 노출된다!!!!!
                                                //모든케이스에 대응할수 없다.

    //조회 v2: 응답 값으로 엔티티가 아닌 별도의 DTO 사용
    @GetMapping("/api/v2/members")
    public Result memberV2(){           //엔티티를 새로운 배열로 만들어서 리턴하는 방법
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id =memberService.join(member);
        return new CreateMemberResponse(id);
    }

//    요청이온것을 request body로 creatememberRequest에 바인딩이되고 엔티티와 프레젠테이션과 분리할수있다.
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);

    }
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                              @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id,request.getName());
        Member findMember = memberService.findOne(id);

        return new UpdateMemberResponse(findMember.getId(), findMember.getName());

    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
