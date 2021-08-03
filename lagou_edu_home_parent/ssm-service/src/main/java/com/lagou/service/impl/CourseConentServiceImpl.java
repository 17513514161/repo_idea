package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import com.lagou.domain.PromotionSpace;
import com.lagou.service.CourseConentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseConentServiceImpl implements CourseConentService {

    @Autowired
    private CourseContentMapper courseContentMapper;


    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseById(courseId);

        return list;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /*
    增加章节
     */
    @Override
    public void saveSection(CourseSection section) {

        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        courseContentMapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection courseSection) {

        //补全信息
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {

        //封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);

        // 调用Mapper
        courseContentMapper.updateSectionStatus(courseSection);
    }


}
