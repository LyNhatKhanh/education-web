package com.lynhatkhanh.educationweb.educationweb.dao;

import com.lynhatkhanh.educationweb.educationweb.model.Member;

import java.util.List;

/*
* NOTE: Because of breaking bidirectional with People case
* */
public interface MemberDao {

    void save(Member member);

    Member findByUserId(String userId);

    List<Member> findAll();

    void updateMember(Member updatedMember);

    void delete(String userId);
}
