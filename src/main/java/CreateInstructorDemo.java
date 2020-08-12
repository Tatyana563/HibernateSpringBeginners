import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {
    public static final String INSTRUCTOR_MAIL = "tom@gmail.com";

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        Instructor newInstructor = new Instructor("Tom", "Green", INSTRUCTOR_MAIL);
        InstructorDetail newDetails = new InstructorDetail("javacourse","sport");
        newInstructor.setInstructorDetail(newDetails);

        try{
            session.beginTransaction();
            session.persist(newInstructor);
            session.getTransaction().commit();
        }
       finally{
            session.close();
            factory.close();
        }
    }
}
