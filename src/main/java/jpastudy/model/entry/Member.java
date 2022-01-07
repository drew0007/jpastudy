package jpastudy.model.entry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member /* extends BaseEntity */ {

	// GeneratedValue 자동생성
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String name;
	
	/* 
	 * 일대다 관계
	 * fetch = FetchType.EAGER : 즉시 로딩
	 * fetch = FetchType.LAZY : 지연 로딩
	 * @OneToMany, @ManyToMany는 기본이 지연로딩
	 */
	@OneToMany(mappedBy = "member")
	//@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
	//@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Order> orders = new ArrayList<Order>();
	
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
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
