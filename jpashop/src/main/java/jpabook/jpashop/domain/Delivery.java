package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)        //꼭 스트링으로 해야 안밀린다.
    private DelivaryStatus status;  //READY, COMP
}
