package jpabook.jpashop.domain.order;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "order", cascade = ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	// 연관관계 주인이 Order인 이유:
	// Order -> Delivery 를 찾는 일이 더 많고, Delivery -> Order 찾는 일은 드물다.
	@OneToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	private LocalDate orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Order() {
	}

	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}

	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}