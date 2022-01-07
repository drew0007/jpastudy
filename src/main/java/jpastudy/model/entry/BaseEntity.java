package jpastudy.model.entry;

import java.util.Date;

import javax.persistence.MappedSuperclass;

/*
 * 테이블과 매핑하지 않고 상속 받는 자식 클래스에게 매핑 정보만 제공
 */
@MappedSuperclass
public class BaseEntity {

	private Date createdDate;
	private Date lastModifiedDate;
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
