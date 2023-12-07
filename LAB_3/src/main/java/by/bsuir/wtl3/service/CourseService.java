package by.bsuir.wtl3.service;

import by.bsuir.wtl3.dao.CourseDao;
import by.bsuir.wtl3.dao.OrderDao;
import by.bsuir.wtl3.dao.UserDao;
import by.bsuir.wtl3.entities.Course;
import by.bsuir.wtl3.entities.Order;
import by.bsuir.wtl3.entities.User;
import by.bsuir.wtl3.exceptions.DaoException;
import by.bsuir.wtl3.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;


    public Course createCourse(Course course) throws ServiceException {
        try {
            int courseId = courseDao.add(course);
            course.setId(courseId);
            return course;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public Course findCourseById(int id) throws ServiceException {
        try {
            return courseDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateCourse(Course course) throws ServiceException {
        try {

            courseDao.update(course);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteCourse(Course course) throws ServiceException {
        try {
            courseDao.delete(course.getId());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Course> getCoursePageElements(int firstResult, int maxResult) throws ServiceException {
        try {
            List<Course> courses = courseDao.getPageElements(firstResult, maxResult);
            return courses;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long getCoursesCount() throws ServiceException {
        try {
            long coursesCount = courseDao.getTableRowsCount();
            return coursesCount;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    @Transactional
    public List<Course> getCourseByOrder(Order order) throws ServiceException {
        try{
            List<Course> courses = courseDao.getCoursesByOrder(order);
            return courses;
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
