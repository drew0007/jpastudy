package jpastudy.model.entry;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery {

	@Id @GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;
	
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	//private String city;
	//private String street;
	
	/*
	 * 임베디드 타입(복합 값 타입)
	 * 사용자가 직접 정의한 값 타입
	 */
	@Embedded
	private Address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getStreet() {
//		return street;
//	}
//
//	public void setStreet(String street) {
//		this.street = street;
//	}
}
