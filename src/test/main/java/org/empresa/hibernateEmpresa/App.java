package org.empresa.hibernateEmpresa;

import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datamodel.dao.DepartamentoDAO;
import datamodel.entities.annotations.Departamento;
import datamodel.utils.HibernateUtil;

public class App {

	private static Logger logger = LogManager.getLogger(App.class);

	static SessionFactory sessionFactory;

	public static void main(String[] args) {
		int opcion;
		Scanner teclado = new Scanner(System.in);
		String methodName = App.class.getSimpleName() + ".main()";
		logger.info(String.format("%1$s: >>>>>> Main execution started.", methodName));
		// La SessionFactory se instancia 1 vez por ejecución del programa
		// Todas las sesiones de hibernate se obtienen de esa SessionFactory;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			// Las opereaciones save/update/delete
			tx = session.beginTransaction();

			do {
				System.out.println("Introduzca número de opción: ");
				System.out.println("1. Insertar departamento");
				System.out.println("2. Insertar empleado");
				System.out.println("0. Salir");
				opcion = teclado.nextInt();

				switch (opcion) {
				case 1:
					int id, cod_responsable;
					String nombre;
					
					System.out.println("Introduzca id del departamento: ");
					id = teclado.nextInt();
					System.out.println("Introduzca nombre del departamento:");
	    			nombre = teclado.nextLine();
	    			System.out.println("Introduzca el codigo de responsable:");
	    			cod_responsable = teclado.nextInt();
	    			
	    			Departamento depar1 = new Departamento(id, nombre, cod_responsable);
	            	DepartamentoDAO departamento = new DepartamentoDAO();
	                departamento.insertarDepartamento(session, depar1);
	                
	                logger.info("Se ha introducido un departamento en la BD.");
					break;
				case 2:
					int codigo, cod_departamento;
					String nombre, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto;
					
					System.out.println("Introduzca código de empleado:");
	    			codigo = teclado.nextInt();			
	    			System.out.println("Introduzca el nombre:");
	    			nombre = teclado.nextLine();
	    			System.out.println("Introduzca el primer apellido:");
	    			apellido1 = teclado.nextLine();
	    			System.out.println("Introduzca el segundo apellido:");
	    			apellido2 = teclado.nextLine();
	    			System.out.println("Introduzca el lugar de nacimiento:");
	    			lugar_nacimiento = teclado.nextLine();
	    			System.out.println("Introduzca la fecha de nacimiento:");
	    			fecha_nacimiento = teclado.nextLine();
	    			System.out.println("Introduzca la dirección:");
	    			direccion = teclado.nextLine();
	    			System.out.println("Introduzca el teléfono:");
	    			telefono = teclado.nextLine();
	    			System.out.println("Introduzca el puesto:");
	    			puesto = teclado.nextLine();
	    			System.out.println("Introduzca el código del departamento:");
	    			cod_departamento = teclado.nextInt();
	    			
	    			Empleado emple1 = new Empleado(codigo, nombre, apellido1, apellido2, 
	    										   lugar_nacimiento, fecha_nacimiento, direccion, 
	    										   telefono, puesto, cod_departamento);
	    			
	            	EmpleadoDAO empleado = new EmpleadoDAO();
	            	
	                empleado.insertarEmpleado(session, emple1);
					
					logger.info("OPCIÓN 2.");
					break;
				case 0:
					logger.info("SALIDA DEL PROGRAMA.");
					break;
				}

			} while (opcion <= 2);

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(String.format("%1$s: error when inserting registries.", methodName), e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		logger.info(String.format("%1$s: >>>>>> Main execution finished.", methodName));
	}
}
