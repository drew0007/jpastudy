package jpastudy.model.entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	/*
	 * @JoinColumn : 외래키 설정
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	/* 
	 * EnumType - STRING : enum 이름, ORDINAL : enum 순서
	 */
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToOne
	@JoinColumn(name = "DELEVERY_ID")
	private Delivery delivery;

	//연관관계 메소드//
	public void setMember(Member member) {
		//기존 관계 제거
		if (this.member != null) {
			this.member.getOrders().remove(this);
		}
		this.member = member;
		member.getOrders().add(this);
	}
	
	public Member getMember() {
		return member;
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
}
