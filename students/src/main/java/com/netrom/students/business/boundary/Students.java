/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netrom.students.business.boundary;

import com.netrom.students.business.entity.Student;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author EGLOBAL
 */
@Stateless
public class Students implements Serializable {

    @PersistenceContext(unitName = "studentPU")
    private EntityManager em;

    public List<Student> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> from = cq.from(Student.class);
        cq.select(from);
        TypedQuery<Student> query = em.createQuery(cq);
        return query.getResultList();
    }

    public void save(Student student) {
        if (student.getId() == null) {
            try {
                em.persist(student);
            } catch (Exception ex) {
                System.err.println("Error with em.persist(student): " + ex.getMessage());
            }
        } else {
            em.merge(student);
        }
    }

    public void delete(Student student) {
        Student removeStudent = em.find(Student.class, student.getId());
        em.remove(removeStudent);
    }

}
