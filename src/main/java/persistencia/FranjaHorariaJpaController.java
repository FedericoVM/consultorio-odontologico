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
import logica.FranjaHoraria;
import logica.Odontologo;
import persistencia.exceptions.NonexistentEntityException;


public class FranjaHorariaJpaController implements Serializable {

    public FranjaHorariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public FranjaHorariaJpaController(){
        emf = Persistence.createEntityManagerFactory("consultorio_odontologicoPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FranjaHoraria franjaHoraria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo odontologo = franjaHoraria.getOdontologo();
            if (odontologo != null) {
                odontologo = em.getReference(odontologo.getClass(), odontologo.getId());
                franjaHoraria.setOdontologo(odontologo);
            }
            em.persist(franjaHoraria);
            if (odontologo != null) {
                odontologo.getHorarios().add(franjaHoraria);
                odontologo = em.merge(odontologo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FranjaHoraria franjaHoraria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FranjaHoraria persistentFranjaHoraria = em.find(FranjaHoraria.class, franjaHoraria.getId_franja());
            Odontologo odontologoOld = persistentFranjaHoraria.getOdontologo();
            Odontologo odontologoNew = franjaHoraria.getOdontologo();
            if (odontologoNew != null) {
                odontologoNew = em.getReference(odontologoNew.getClass(), odontologoNew.getId());
                franjaHoraria.setOdontologo(odontologoNew);
            }
            franjaHoraria = em.merge(franjaHoraria);
            if (odontologoOld != null && !odontologoOld.equals(odontologoNew)) {
                odontologoOld.getHorarios().remove(franjaHoraria);
                odontologoOld = em.merge(odontologoOld);
            }
            if (odontologoNew != null && !odontologoNew.equals(odontologoOld)) {
                odontologoNew.getHorarios().add(franjaHoraria);
                odontologoNew = em.merge(odontologoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = franjaHoraria.getId_franja();
                if (findFranjaHoraria(id) == null) {
                    throw new NonexistentEntityException("The franjaHoraria with id " + id + " no longer exists.");
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
            FranjaHoraria franjaHoraria;
            try {
                franjaHoraria = em.getReference(FranjaHoraria.class, id);
                franjaHoraria.getId_franja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The franjaHoraria with id " + id + " no longer exists.", enfe);
            }
            Odontologo odontologo = franjaHoraria.getOdontologo();
            if (odontologo != null) {
                odontologo.getHorarios().remove(franjaHoraria);
                odontologo = em.merge(odontologo);
            }
            em.remove(franjaHoraria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FranjaHoraria> findFranjaHorariaEntities() {
        return findFranjaHorariaEntities(true, -1, -1);
    }

    public List<FranjaHoraria> findFranjaHorariaEntities(int maxResults, int firstResult) {
        return findFranjaHorariaEntities(false, maxResults, firstResult);
    }

    private List<FranjaHoraria> findFranjaHorariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FranjaHoraria.class));
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

    public FranjaHoraria findFranjaHoraria(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FranjaHoraria.class, id);
        } finally {
            em.close();
        }
    }

    public int getFranjaHorariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FranjaHoraria> rt = cq.from(FranjaHoraria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
