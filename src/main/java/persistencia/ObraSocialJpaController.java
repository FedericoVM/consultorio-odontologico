package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.ObraSocial;
import persistencia.exceptions.NonexistentEntityException;


public class ObraSocialJpaController implements Serializable {

    public ObraSocialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
        public ObraSocialJpaController() {
        emf = Persistence.createEntityManagerFactory("consultorio_odontologicoPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObraSocial obraSocial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(obraSocial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObraSocial obraSocial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            obraSocial = em.merge(obraSocial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = obraSocial.getId_obra_social();
                if (findObraSocial(id) == null) {
                    throw new NonexistentEntityException("The obraSocial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObraSocial obraSocial;
            try {
                obraSocial = em.getReference(ObraSocial.class, id);
                obraSocial.getId_obra_social();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The obraSocial with id " + id + " no longer exists.", enfe);
            }
            em.remove(obraSocial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObraSocial> findObraSocialEntities() {
        return findObraSocialEntities(true, -1, -1);
    }

    public List<ObraSocial> findObraSocialEntities(int maxResults, int firstResult) {
        return findObraSocialEntities(false, maxResults, firstResult);
    }

    private List<ObraSocial> findObraSocialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ObraSocial.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObraSocial findObraSocial(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObraSocial.class, id);
        } finally {
            em.close();
        }
    }

    public int getObraSocialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ObraSocial> rt = cq.from(ObraSocial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
