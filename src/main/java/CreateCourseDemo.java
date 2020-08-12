import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        Course bcourse = new Course("java for beginners");
        Course icourse = new Course("java for interm");


        try {
            session.beginTransaction();
            Instructor tempInstructor = (Instructor) session
                    .createQuery("from Instructor where email = :email")
                    .setParameter("email", CreateInstructorDemo.INSTRUCTOR_MAIL)
                    .uniqueResult();
            tempInstructor.add(bcourse);
            tempInstructor.add(icourse);
            session.persist(tempInstructor);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
