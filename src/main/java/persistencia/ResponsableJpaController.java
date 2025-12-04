package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Paciente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Responsable;
import persistencia.exceptions.NonexistentEntityException;

public class ResponsableJpaController implements Serializable {

    public ResponsableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ResponsableJpaController() {
        emf = Persistence.createEntityManagerFactory("consultorio_odontologicoPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsable responsable) {
        if (responsable.getPacientes() == null) {
            responsable.setPacientes(new ArrayList<Paciente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Paciente> attachedPacientes = new ArrayList<Paciente>();
            for (Paciente pacientesPacienteToAttach : responsable.getPacientes()) {
                pacientesPacienteToAttach = em.getReference(pacientesPacienteToAttach.getClass(), pacientesPacienteToAttach.getId());
                attachedPacientes.add(pacientesPacienteToAttach);
            }
            responsable.setPacientes(attachedPacientes); 
            em.persist(responsable);
            for (Paciente pacientesPaciente : responsable.getPacientes()) {
                Responsable oldResponsableOfPacientesPaciente = pacientesPaciente.getResponsable();
                pacientesPaciente.setResponsable(responsable);
                pacientesPaciente = em.merge(pacientesPaciente);
                if (oldResponsableOfPacientesPaciente != null) {
                    oldResponsableOfPacientesPaciente.getPacientes().remove(pacientesPaciente);
                    oldResponsableOfPacientesPaciente = em.merge(oldResponsableOfPacientesPaciente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Responsable responsable) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsable persistentResponsable = em.find(Responsable.class, responsable.getId());
            List<Paciente> pacientesOld = persistentResponsable.getPacientes();
            List<Paciente> pacientesNew = responsable.getPacientes();
            List<Paciente> attachedPacientesNew = new ArrayList<Paciente>();
            for (Paciente pacientesNewPacienteToAttach : pacientesNew) {
                pacientesNewPacienteToAttach = em.getReference(pacientesNewPacienteToAttach.getClass(), pacientesNewPacienteToAttach.getId());
                attachedPacientesNew.add(pacientesNewPacienteToAttach);
            }
            pacientesNew = attachedPacientesNew;
            responsable.setPacientes(pacientesNew);
            responsable = em.merge(responsable);
            for (Paciente pacientesOldPaciente : pacientesOld) {
                if (!pacientesNew.contains(pacientesOldPaciente)) {
                    pacientesOldPaciente.setResponsable(null);
                    pacientesOldPaciente = em.merge(pacientesOldPaciente);
                }
            }
            for (Paciente pacientesNewPaciente : pacientesNew) {
                if (!pacientesOld.contains(pacientesNewPaciente)) {
                    Responsable oldResponsableOfPacientesNewPaciente = pacientesNewPaciente.getResponsable();
                    pacientesNewPaciente.setResponsable(responsable);
                    pacientesNewPaciente = em.merge(pacientesNewPaciente);
                    if (oldResponsableOfPacientesNewPaciente != null && !oldResponsableOfPacientesNewPaciente.equals(responsable)) {
                        oldResponsableOfPacientesNewPaciente.getPacientes().remove(pacientesNewPaciente);
                        oldResponsableOfPacientesNewPaciente = em.merge(oldResponsableOfPacientesNewPaciente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = responsable.getId();
                if (findResponsable(id) == null) {
                    throw new NonexistentEntityException("The responsable with id " + id + " no longer exists.");
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
            Responsable responsable;
            try {
                responsable = em.getReference(Responsable.class, id);
                responsable.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsable with id " + id + " no longer exists.", enfe);
            }
            List<Paciente> pacientes = responsable.getPacientes();
            for (Paciente pacientesPaciente : pacientes) {
                pacientesPaciente.setResponsable(null);
                pacientesPaciente = em.merge(pacientesPaciente);
            }
            em.remove(responsable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Responsable> findResponsableEntities() {
        return findResponsableEntities(true, -1, -1);
    }

    public List<Responsable> findResponsableEntities(int maxResults, int firstResult) {
        return findResponsableEntities(false, maxResults, firstResult);
    }

    private List<Responsable> findResponsableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Responsable.class));
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

    public Responsable findResponsable(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Responsable.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Responsable> rt = cq.from(Responsable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
