package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter     //값 타입은 변경 불가능하게 설계 하기위해서 Setter제거했다.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){    //public 보다는 private가 안전하다.

    }

    public Address(String city, String street, String zipcode){
        this.city=city;
        this.street=street;
        this.zipcode=zipcode;
    }
}
