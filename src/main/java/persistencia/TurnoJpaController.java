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
import logica.Odontologo;
import logica.Paciente;
import logica.Turno;
import persistencia.exceptions.NonexistentEntityException;

public class TurnoJpaController implements Serializable {

    public TurnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TurnoJpaController() {
        emf = Persistence.createEntityManagerFactory("consultorio_odontologicoPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turno turno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo odontologo = turno.getOdontologo();
            if (odontologo != null) {
                odontologo = em.getReference(odontologo.getClass(), odontologo.getId());
                turno.setOdontologo(odontologo);
            }
            Paciente paciente = turno.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getId());
                turno.setPaciente(paciente);
            }
            em.persist(turno);
            if (odontologo != null) {
                odontologo.getTurnos().add(turno);
                odontologo = em.merge(odontologo);
            }
            if (paciente != null) {
                paciente.getTurnos().add(turno);
                paciente = em.merge(paciente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turno turno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno persistentTurno = em.find(Turno.class, turno.getId_turno());
            Odontologo odontologoOld = persistentTurno.getOdontologo();
            Odontologo odontologoNew = turno.getOdontologo();
            Paciente pacienteOld = persistentTurno.getPaciente();
            Paciente pacienteNew = turno.getPaciente();
            if (odontologoNew != null) {
                odontologoNew = em.getReference(odontologoNew.getClass(), odontologoNew.getId());
                turno.setOdontologo(odontologoNew);
            }
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getId());
                turno.setPaciente(pacienteNew);
            }
            turno = em.merge(turno);
            if (odontologoOld != null && !odontologoOld.equals(odontologoNew)) {
                odontologoOld.getTurnos().remove(turno);
                odontologoOld = em.merge(odontologoOld);
            }
            if (odontologoNew != null && !odontologoNew.equals(odontologoOld)) {
                odontologoNew.getTurnos().add(turno);
                odontologoNew = em.merge(odontologoNew);
            }
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getTurnos().remove(turno);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getTurnos().add(turno);
                pacienteNew = em.merge(pacienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = turno.getId_turno();
                if (findTurno(id) == null) {
                    throw new NonexistentEntityException("The turno with id " + id + " no longer exists.");
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
            Turno turno;
            try {
                turno = em.getReference(Turno.class, id);
                turno.getId_turno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turno with id " + id + " no longer exists.", enfe);
            }
            Odontologo odontologo = turno.getOdontologo();
            if (odontologo != null) {
                odontologo.getTurnos().remove(turno);
                odontologo = em.merge(odontologo);
            }
            Paciente paciente = turno.getPaciente();
            if (paciente != null) {
                paciente.getTurnos().remove(turno);
                paciente = em.merge(paciente);
            }
            em.remove(turno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities() {
        return findTurnoEntities(true, -1, -1);
    }

    public List<Turno> findTurnoEntities(int maxResults, int firstResult) {
        return findTurnoEntities(false, maxResults, firstResult);
    }

    private List<Turno> findTurnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
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

    public Turno findTurno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from(Turno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
