package test.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
public class GenericDao<T> {

    @PersistenceContext(unitName = "default")
    protected EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(GenericDao.class);

    public GenericDao(Class<T> clazz) {
    }

    public GenericDao() {
    }
    @Transactional(readOnly = true)
    public T findById(Class<T> typeClass, Object id) {
        return (T) em.find(typeClass, id);
    }

    @Transactional
    public void flush() {
        em.flush();
    }

    @Transactional
    public void create(T o) {
        em.persist(o);
    }

    @Transactional
    public void update(T o) {
        em.merge(o);
    }

    @Transactional
    public void delete(Class<T> typeClass, Object id) {
        Object o = em.getReference(typeClass, id);
        em.remove(o);
    }
}
