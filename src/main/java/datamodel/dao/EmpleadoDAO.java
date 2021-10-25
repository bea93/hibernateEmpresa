package datamodel.dao;

import org.hibernate.Session;

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

	
	
}
