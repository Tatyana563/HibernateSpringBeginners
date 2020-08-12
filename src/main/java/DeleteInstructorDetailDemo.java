import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


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
            int detailId = 2;
            InstructorDetail detail = session.get(InstructorDetail.class, detailId);

            session.delete(detail);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
