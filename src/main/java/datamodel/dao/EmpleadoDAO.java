package datamodel.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import org.hibernate.Session;
import org.hibernate.query.Query;

import datamodel.entities.annotations.Empleado;


public class EmpleadoDAO {
	public static void insertarEmpleado(Session s, Empleado empleado) {
		s.save(empleado);
	}

	public static Empleado getEmpleado(Session s, int id) {
		return s.get(Empleado.class, id);
	}

	public static void updateEmpleado(Session s, Empleado empleado) {
		s.update(empleado);
	}

	public static void deleteEmpleado(Session s, Empleado empleado) {
		s.delete(empleado);
	}

	//Consulta con criteria
		public static Empleado consultarEmpleado(Session s, int cod_departamento) {
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<Empleado> query = builder.createQuery(Empleado.class);
			Root<Empleado> root = query.from(Empleado.class);
	    	query.select(root).where(builder.equal(root.get("codDepartamento"), cod_departamento));
	    	Query<Empleado> q = s.createQuery(query);
	    	List<Empleado> result = (List<Empleado>) q.list();
			for(Empleado e : result) {
				System.out.println(e.toString());
			}
	    	return null;
		}
	
}
