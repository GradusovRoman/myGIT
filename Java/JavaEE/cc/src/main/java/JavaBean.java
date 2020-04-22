import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JavaBean {

    @PersistenceContext(unitName = "postgres")
    EntityManager entityManager;

    public void saveUser(UserEntity user){
        System.out.println("=======================================================================================");
        entityManager.persist(user);
        System.out.println("=======================================================================================");
    }

}
