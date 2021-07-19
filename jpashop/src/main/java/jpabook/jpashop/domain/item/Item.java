package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;      //변경해야할일이있으면 메서드로 변경하도록해야한다. 객체지향적인 방법이다.

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    //== 비즈니스 로직==//
    //재고를 늘리고 줄이고 할것이다. 보통 도메인 주소 설계할때 엔티티 자체가 처리할수있의면 엔티티안에 넣는게 좋다.
    /**
     * stock 증가
     */

    public void addStock(int quantity){ //재고 수량을 늘리는 로직
        this.stockQuantity+=quantity;
    }
    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock= this.stockQuantity-quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity=restStock;

        //보통 개발할떄 StockQuantity가져와서 더하고빼서 setStock을 해서 값을 설정했을텐데, 재고를 넣고 빼는 로직은 itementity를 가지고있기때문에 여기엤는것이 가장좋다.

    }

}
