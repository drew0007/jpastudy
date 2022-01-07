package jpastudy.model.entity.item;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/*
 * InheritanceType - SINGLE_TABLE : 단일 테이블 전략으로 테이블 하나만 사용
 * @DiscriminatorColumn : 부모 클래스 구분 컬럼 지정
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;
	private String price;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
