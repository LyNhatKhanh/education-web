//package com.lynhatkhanh.educationweb.educationweb.dao.implement;
//
//import com.lynhatkhanh.educationweb.educationweb.dao.MemberDao;
//import com.lynhatkhanh.educationweb.educationweb.model.Member;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//public class MemberDaoImpl implements MemberDao {
//
//    private EntityManager entityManager;
//
//    @Autowired
//    public MemberDaoImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    @Transactional
//    public void save(Member member) {
//        entityManager.persist(member);
//    }
//
//    @Override
//    public Member findByUserId(String userId) {
//        Member member = entityManager.find(Member.class, userId);
//        return member;
//    }
//
//    @Override
//    public List<Member> findAll() {
//        TypedQuery<Member> query = entityManager.createQuery(
//                "SELECT m FROM Member m", Member.class);
//        return query.getResultList();
//    }
//
//    @Override
//    @Transactional
//    public void updateMember(Member updatedMember) {
//        entityManager.merge(updatedMember);
//
//    }
//
//    @Override
//    @Transactional
//    public void delete(String userId) {
//        Member deletedMember = entityManager.find(Member.class, userId);
//
//        // remove associated object reference
//        // break bidirectional link
//        deletedMember.getPeopleId().setMemberId(null);
//
//        entityManager.remove(deletedMember);
//    }
//}
