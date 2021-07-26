package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @JsonIgnore         //orders 정보를 노출하지 않기 위해서 추가한 어노테이션이다. 엔티티에 프레젠티에션(화면에 출력)되는 로직이 추가된것이다. 이렇게되면 엔티티가 의존관계가 나가버린것이다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}


