import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            InstructorDetail detail = (InstructorDetail) session
                    .createQuery("from InstructorDetail where instructor.email = :email")
                    .setParameter("email", CreateInstructorDemo.INSTRUCTOR_MAIL)
                    .uniqueResult();
            session.delete(detail);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
