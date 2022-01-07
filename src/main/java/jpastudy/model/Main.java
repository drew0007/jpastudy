package jpastudy.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpastudy.model.entity.item.Album;
import jpastudy.model.entity.item.Book;
import jpastudy.model.entry.Member;
import jpastudy.model.entry.Order;
import jpastudy.model.entry.OrderStatus;

public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            logic3(em); //TODO 비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {
		
		Member member = new Member();
		member.setName("테스트1");
		em.persist(member);
		//변경 감지
		member.setName("테스트2");
		
		//flush : 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영
		//em.flush(), 트랜잭션 커밋, JPQL 쿼리 실행 시 동작
		em.flush();
		em.clear();
    	
    	Long id1 = 1L;
    	
    	Member findMember = em.find(Member.class, id1);
    	System.out.println("member name = " + findMember.getName());
    }
    
    public static void logic2(EntityManager em) {
		
		Member member = new Member();
		member.setName("테스트1");
		em.persist(member);
		  
		Order order1 = new Order();
		order1.setStatus(OrderStatus.ORDER);
		order1.setMember(member);
		em.persist(order1);
		  
		Order order2 = new Order();
		order2.setStatus(OrderStatus.CANCEL);
		order2.setMember(member);
		em.persist(order2);
		
		em.flush();
		em.clear();
    	
    	Long id1 = 1L;
    	Long id2 = 2L;
    	
    	Member findMember = em.find(Member.class, id1);
    	System.out.println("member name = " + findMember.getName());
    	//지연로딩, 즉시로딩 확인
    	System.out.println("member orders size = " + findMember.getOrders().size());

    	Order findOrder = em.find(Order.class, id2);
    	System.out.println("order status = " + findOrder.getStatus());
    }
    
    /*
     * 영속성 전이
     * cascade
     */
    public static void logic3(EntityManager em) {
		
		Member member = new Member();
		member.setName("테스트1");
		  
		Order order1 = new Order();
		order1.setStatus(OrderStatus.ORDER);
		order1.setMember(member);
		  
		Order order2 = new Order();
		order2.setStatus(OrderStatus.CANCEL);
		order2.setMember(member);
		
		// 부모 저장, 연관된 자식들 저장
		em.persist(member);
		
		em.flush();
		em.clear();
    	
    	Long id1 = 1L;
    	Long id2 = 2L;
    	
    	Member findMember = em.find(Member.class, id1);
    	System.out.println("member name = " + findMember.getName());
    	//지연로딩, 즉시로딩 확인
    	System.out.println("member orders size = " + findMember.getOrders().size());

    	Order findOrder = em.find(Order.class, id2);
    	System.out.println("order status = " + findOrder.getStatus());
    	
    }

    /*
     * 단일테이블 전략 사용
     */
    public static void logic4(EntityManager em) {

    	Album album = new Album();
    	album.setName("앨범이름");
    	album.setPrice("500");
    	album.setArtist("아티스트");
    	em.persist(album);
    	
    	Book book = new Book();
    	book.setName("책이름");
    	book.setPrice("1000");
    	book.setAuthor("작가");
    	em.persist(book);
    	
    	em.flush();
    	em.clear();
    	
    	Long id1 = 1L;
    	Album findAlbum = em.find(Album.class, id1);
    	System.out.println("name = " + findAlbum.getName() + ", price = " + findAlbum.getPrice() + ", artist = " + findAlbum.getArtist());

    	Long id2 = 2L;
    	Book findBook = em.find(Book.class, id2);
    	System.out.println("name = " + findBook.getName() + ", price = " + findBook.getPrice() + ", author = " + findBook.getAuthor());
    }
}
