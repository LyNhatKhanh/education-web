package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.dao.MemberDao;
import com.lynhatkhanh.educationweb.educationweb.model.Member;
import com.lynhatkhanh.educationweb.educationweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Iterable<Member> findAll() {
        return memberDao.findAll();
    }
}
