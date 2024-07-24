package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.constant.SystemConstant;
import com.lynhatkhanh.educationweb.educationweb.dao.LectureRepository;
import com.lynhatkhanh.educationweb.educationweb.model.Lecture;
import com.lynhatkhanh.educationweb.educationweb.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    private LectureRepository lectureRepository;

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture findById(int theId) {
        return lectureRepository.findById(theId).orElseThrow(() -> new RuntimeException("Course id not found - " + theId));
    }

    @Override
    public void deleteById(int theId) {
        lectureRepository.deleteById(theId);
    }

    @Override
    public Page<Lecture> findAll(Integer pageNo) {

        Pageable pageable = PageRequest.of(pageNo-1, SystemConstant.PAGE_SIZE);

        return lectureRepository.findAll(pageable);
    }

    @Override
    public List<Lecture> searchByKeyword(String keyword) {
        return lectureRepository.searchLecturesByKeyword(keyword);
    }

    @Override
    public Page<Lecture> searchByKeyword(String keyword, Integer pageNo) {

        List<Lecture> lectures = lectureRepository.searchLecturesByKeyword(keyword);
        Pageable pageable = PageRequest.of(pageNo-1, SystemConstant.PAGE_SIZE);

        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > lectures.size() ? lectures.size() : (pageable.getOffset() + pageable.getPageSize()));

        List<Lecture> showList = lectures.subList(start, end);

        return new PageImpl<>(showList, pageable, lectures.size());
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

}
