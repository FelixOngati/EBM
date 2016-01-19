/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author the_fegati
 */
public class CustomQueries {

    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ExcelReaderPU");
    EntityManager em = emfactory.createEntityManager();

    //fetch distinct lab tests from TblLabs
    public List<Object[]> getDistinctLabs() {

        TypedQuery<Object[]> query = em.createQuery(
                "select distinct e.patientId, e.admissionId from TblLabs e", Object[].class);
        List<Object[]> list = query.getResultList();
        return list;
    }

    public List<Object[]> getLabs(String patientId, String admissionId) {
        TypedQuery<Object[]> query = em.createQuery(
                "select p.patientId, l.admissionId, l.labName, l.labValue, l.labDateTime"
                + " from TblPatientDetails p INNER JOIN TblLabs l on "
                + "p.patientId = l.patientId where p.patientId = :patientId "
                + "and l.admissionId = :admissionId", Object[].class);
        query.setParameter("patientId", patientId);
        query.setParameter("admissionId", admissionId);
        List<Object[]> list = query.getResultList();
        return list;
    }
    
    public List<Object[]> getPatientDetails(String patientId){
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT p.patientId, p.patientGender, p.patientDOB, p.patientRace, "
                        + "p.patientMaritalStatus, p.patientLanguage, "
                        + "p.patientPopulationPercentageBelowPoverty "
                        + "FROM TblPatientDetails p WHERE p.patientId =:patientId", Object[].class
        );
        query.setParameter("patientId", patientId);
        List<Object[]> list = query.getResultList();
        
        return list;
    }

}
