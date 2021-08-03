package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /*
    多条件课程列表查询
     */

    public List<Course> findCourseByCondition(CourseVo courseVo);


    /*
    保存课程信息
     */
    public void saveCourse(Course course);

    /*
    保存讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /*
    根据id获取课程信息
     */
    public CourseVo findCourseById(Integer id);

    /*
    修改课程信息
     */
    public void updateCourse(Course course);

    /*
    修改讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /*
    <!--课程状态管理-->
     */
    public void updateCourseStatus(Course course);

}
