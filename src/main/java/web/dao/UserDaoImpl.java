package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao {//будет общаться (пока) со списком (потом БД), извлекать людей из списка, находить человека по id, добавлять в список обновлят, удалять
    @PersistenceContext// этот компонент говорит что наш компонент зависит от EntityManager
    private EntityManager em;



    //принимает на вход id человека и возвращает человека с этим id из нашей БД
    @Override
    public User findById(Long id) {
        return em.find(User.class, id);

    }

    @Override
    public List<User> getAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        if(user.getId() == null){
            em.persist(user); // Сохраняем новый объект
        }
        em.merge(user);// Обновляем существующий объект
    }

    @Override
    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }
}
