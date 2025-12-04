package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Responsable;
import logica.Turno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.ObraSocial;
import logica.Paciente;
import persistencia.exceptions.NonexistentEntityException;

public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PacienteJpaController() {
        emf = Persistence.createEntityManagerFactory("consultorio_odontologicoPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) {
        if (paciente.getTurnos() == null) {
            paciente.setTurnos(new ArrayList<Turno>());
        }


        ObraSocial obraSocial = paciente.getObraSocial();

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Responsable responsable = paciente.getResponsable();
            if (responsable != null) {
                Responsable managedResponsable = paciente.getResponsable();
                if (managedResponsable != null) {
                    managedResponsable.getPacientes().add(paciente);
                    em.merge(managedResponsable);
                }
            }

            List<Turno> attachedTurnos = new ArrayList<Turno>();
            for (Turno turnosTurnoToAttach : paciente.getTurnos()) {
                turnosTurnoToAttach = em.getReference(turnosTurnoToAttach.getClass(), turnosTurnoToAttach.getId_turno());
                attachedTurnos.add(turnosTurnoToAttach);
            }
            paciente.setTurnos(attachedTurnos);

            em.persist(paciente);

            if (responsable != null) {
                responsable.getPacientes().add(paciente);
                responsable = em.merge(responsable);
            }

            for (Turno turnosTurno : paciente.getTurnos()) {
                Paciente oldPacienteOfTurnosTurno = turnosTurno.getPaciente();
                turnosTurno.setPaciente(paciente);
                turnosTurno = em.merge(turnosTurno);
                if (oldPacienteOfTurnosTurno != null) {
                    oldPacienteOfTurnosTurno.getTurnos().remove(turnosTurno);
                    oldPacienteOfTurnosTurno = em.merge(oldPacienteOfTurnosTurno);
                }
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            Responsable responsableOld = persistentPaciente.getResponsable();
            Responsable responsableNew = paciente.getResponsable();
            List<Turno> turnosOld = persistentPaciente.getTurnos();
            List<Turno> turnosNew = paciente.getTurnos();
            if (responsableNew != null) {
                responsableNew = em.merge(responsableNew);
                paciente.setResponsable(responsableNew);
            }

            List<Turno> attachedTurnosNew = new ArrayList<Turno>();
            for (Turno turnosNewTurnoToAttach : turnosNew) {
                turnosNewTurnoToAttach = em.getReference(turnosNewTurnoToAttach.getClass(), turnosNewTurnoToAttach.getId_turno());
                attachedTurnosNew.add(turnosNewTurnoToAttach);
            }
            turnosNew = attachedTurnosNew;
            paciente.setTurnos(turnosNew);

            paciente = em.merge(paciente);

            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getPacientes().remove(paciente);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getPacientes().add(paciente);
                responsableNew = em.merge(responsableNew);
            }


            for (Turno turnosOldTurno : turnosOld) {
                if (!turnosNew.contains(turnosOldTurno)) {
                    turnosOldTurno.setPaciente(null);
                    turnosOldTurno = em.merge(turnosOldTurno);
                }
            }
            for (Turno turnosNewTurno : turnosNew) {
                if (!turnosOld.contains(turnosNewTurno)) {
                    Paciente oldPacienteOfTurnosNewTurno = turnosNewTurno.getPaciente();
                    turnosNewTurno.setPaciente(paciente);
                    turnosNewTurno = em.merge(turnosNewTurno);
                    if (oldPacienteOfTurnosNewTurno != null && !oldPacienteOfTurnosNewTurno.equals(paciente)) {
                        oldPacienteOfTurnosNewTurno.getTurnos().remove(turnosNewTurno);
                        oldPacienteOfTurnosNewTurno = em.merge(oldPacienteOfTurnosNewTurno);
                    }
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            Responsable responsable = paciente.getResponsable();
            if (responsable != null) {
                responsable.getPacientes().remove(paciente);
                responsable = em.merge(responsable);
            }
            List<Turno> turnos = paciente.getTurnos();
            for (Turno turnosTurno : turnos) {
                turnosTurno.setPaciente(null);
                turnosTurno = em.merge(turnosTurno);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
