package jpabook.jpashop.domain.member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

	@PersistenceContext
	private EntityManager em;

	public Long save(Member member) { // command 와 query를 분리하라 원칙 적용
		em.persist(member);
		return member.getId();
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

}
