package jpabook.jpashop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.member.Member;

@Repository
public class MemberRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 영속성 컨텍스트에 의해 관리될 때 id 생성(key, value)
	 * em.persist()만 해도 id 생성
	 */
	public Long save(Member member) { // command 와 query를 분리하라 원칙 적용
		em.persist(member);
		return member.getId();
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

}
