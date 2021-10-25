package datamodel.dao;

import org.hibernate.Session;

import datamodel.entities.annotations.Departamento;

public class DepartamentoDAO {
	public static void insertarDepartamento(Session s, Departamento departamento) {
		s.save(departamento);
	}

	public static Departamento getDepartamento(Session s, Departamento departamento) {
		return s.get(Departamento.class, departamento);
	}

	public static void updateDepartamento(Session s, Departamento departamento) {
		s.update(departamento);
	}

	public static void deleteDepartamento(Session s, Departamento departamento) {
		s.delete(departamento);
	}
}
