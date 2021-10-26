package org.empresa.hibernateEmpresa;

import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import datamodel.dao.DepartamentoDAO;
import datamodel.dao.EmpleadoDAO;
import datamodel.entities.annotations.Departamento;
import datamodel.entities.annotations.Empleado;
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
					String nombreD;
					Scanner idDep = new Scanner (System.in); 
	    			Scanner nomDep = new Scanner (System.in); 
	    			Scanner respDep = new Scanner (System.in); 
					
					System.out.println("Introduzca id del departamento: ");
					id = idDep.nextInt();
					System.out.println("Introduzca nombre del departamento:");
	    			nombreD = nomDep.nextLine();
	    			System.out.println("Introduzca el codigo de responsable:");
	    			cod_responsable = respDep.nextInt();
	    			
	    			Departamento depar1 = new Departamento(id, nombreD, cod_responsable);
	    			DepartamentoDAO.insertarDepartamento(session, depar1);
	                
	                tx.commit();
	                logger.info("Se ha introducido un departamento en la BD.");
					break;
				case 2:
					
					int codigo, cod_departamento;
					String nombreE, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto;
					Scanner codigoE = new Scanner (System.in); 
	    			Scanner nombreEmp = new Scanner (System.in); 
	    			Scanner ape1 = new Scanner (System.in); 
	    			Scanner ape2 = new Scanner (System.in); 
	    			Scanner lugNac = new Scanner (System.in); 
	    			Scanner fechaNac = new Scanner (System.in);
	    			Scanner dire = new Scanner (System.in); 
	    			Scanner tel = new Scanner (System.in); 
	    			Scanner pue = new Scanner (System.in);
	    			Scanner codDep = new Scanner (System.in);
					
					System.out.println("Introduzca el código:");
	    			codigo = codigoE.nextInt();			
	    			System.out.println("Introduzca el nombre:");
	    			nombreE = nombreEmp.nextLine();
	    			System.out.println("Introduzca el primer apellido:");
	    			apellido1 = ape1.nextLine();
	    			System.out.println("Introduzca el segundo apellido:");
	    			apellido2 = ape2.nextLine();
	    			System.out.println("Introduzca el lugar de nacimiento:");
	    			lugar_nacimiento = lugNac.nextLine();
	    			System.out.println("Introduzca la fecha de nacimiento:");
	    			fecha_nacimiento = fechaNac.nextLine();
	    			System.out.println("Introduzca la dirección:");
	    			direccion = dire.nextLine();
	    			System.out.println("Introduzca el teléfono:");
	    			telefono = tel.nextLine();
	    			System.out.println("Introduzca el puesto:");
	    			puesto = pue.nextLine();
	    			System.out.println("Introduzca el código del departamento:");
	    			cod_departamento = codDep.nextInt();
	    			
	    			Empleado emple1 = new Empleado(codigo, nombreE, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto, cod_departamento);
	            	EmpleadoDAO.insertarEmpleado(session, emple1);
	                
	            	tx.commit();
	            	
	                logger.info("Se ha introducido un empleado en la BD.");
					logger.info("OPCIÓN 2.");
					break;
				case 0:
					logger.info("SALIDA DEL PROGRAMA.");
					break;
				}

			} while (opcion > 1 && opcion <= 2);

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
