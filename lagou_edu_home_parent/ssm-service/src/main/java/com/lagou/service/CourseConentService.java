package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface CourseConentService {


    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
   回显章节对应的课程信息
    */
    public Course findCourseByCourseId(int courseId);

    /*
    增加章节
     */
    public void saveSection(CourseSection section);

    /*
    更新章节信息
     */
    public void updateSection(CourseSection section);

    /*
    修改章节状态
     */
    public void updateSectionStatus(int id, int status);


}
