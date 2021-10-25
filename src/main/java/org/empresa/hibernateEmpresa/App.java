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
		int opcion, id, cod_responsable;
		String nombre;
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
