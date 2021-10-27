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

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//Variables del menú
		int opcion, opcion2;
		
		//Variables Departamento
		int codigo, cod_responsable;
		String nombreD;
		
		//Variables Empleado
		int codigoEmp, cod_departamento;
		String nombreE, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto;
		
		//Scanners
		Scanner teclado = new Scanner(System.in);
		//Departamento
		Scanner codDep = new Scanner (System.in); 
		Scanner nomDep = new Scanner (System.in); 
		Scanner respDep = new Scanner (System.in);
		//Empleado
		Scanner codigoE = new Scanner (System.in); 
		Scanner nombreEmp = new Scanner (System.in); 
		Scanner ape1 = new Scanner (System.in); 
		Scanner ape2 = new Scanner (System.in); 
		Scanner lugNac = new Scanner (System.in); 
		Scanner fechaNac = new Scanner (System.in);
		Scanner dire = new Scanner (System.in); 
		Scanner tel = new Scanner (System.in); 
		Scanner pue = new Scanner (System.in);
		Scanner codDep2 = new Scanner (System.in);
		
		
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
				System.out.println("1. Departamentos");
				System.out.println("2. Empleados");
				System.out.println("0. Salir");
				opcion = teclado.nextInt();

				switch (opcion) {
				case 1:
					do {
						System.out.println("Introduzca número de opción: ");
						System.out.println("1. Crear Departamento");
						System.out.println("2. Mostrar Departamento");
						System.out.println("3. Actualizar Departamento");
						System.out.println("4. Eliminar Departamento");
						System.out.println("0. Salir");
						opcion2 = teclado.nextInt();

						switch (opcion2) {
						case 1: //Crear departamento
							System.out.println("Introduzca id del departamento: ");
							codigo = codDep.nextInt();
							System.out.println("Introduzca nombre del departamento:");
			    			nombreD = nomDep.nextLine();
			    			System.out.println("Introduzca el codigo de responsable:");
			    			cod_responsable = respDep.nextInt();
			    			
			    			Departamento depar1 = new Departamento(codigo, nombreD, cod_responsable);
			    			DepartamentoDAO.insertarDepartamento(session, depar1);
			                
			                tx.commit();
			                logger.info("Se ha introducido un departamento en la BD.");
							logger.info("OPCIÓN 1.");
							break;
						case 2: //Mostrar departamento
							System.out.println("Introduzca código del Departamento: ");
							codigo = codDep.nextInt();
							
							DepartamentoDAO.getDepartamento(session, codigo);
							logger.info("OPCIÓN 2.");
							break;
						case 3: //Actualizar Departamento
							System.out.println("Introduzca id del departamento: ");
							codigo = codDep.nextInt();
							System.out.println("Introduzca nombre del departamento:");
			    			nombreD = nomDep.nextLine();
			    			System.out.println("Introduzca el codigo de responsable:");
			    			cod_responsable = respDep.nextInt();
			    			
			    			Departamento depar3 = new Departamento(codigo, nombreD, cod_responsable);
			    			DepartamentoDAO.updateDepartamento(session, depar3);
			                
			                tx.commit();
							
			                logger.info("Se ha actualizado un departamento en la BD.");
							logger.info("OPCIÓN 3.");
							break;
						case 4: //Eliminar Departamento
							System.out.println("Introduzca código del departamento: ");
							codigo = codDep.nextInt();
							
							Departamento depar4 = new Departamento(codigo);
							DepartamentoDAO.deleteDepartamento(session, depar4);
							
							tx.commit();
							
							logger.info("Se ha eliminado un departamento de la BD.");
							logger.info("OPCIÓN 4.");
							break;
						case 0:
							logger.info("SALIDA DEL PROGRAMA.");
							break;
						}

					} while (opcion2 > 1 && opcion2 <= 4);

					logger.info("OPCIÓN 1.");
					break;
				case 2:
					do {
						System.out.println("Introduzca número de opción: ");
						System.out.println("1. Crear Empleado");
						System.out.println("2. Mostrar Empleado");
						System.out.println("3. Actualizar Empleado");
						System.out.println("4. Eliminar Empleado");
						System.out.println("0. Salir");
						opcion2 = teclado.nextInt();

						switch (opcion2) {
						case 1:
							System.out.println("Introduzca el código de empleado:");
			    			codigoEmp = codigoE.nextInt();			
			    			System.out.println("Introduzca el nombre del empleado:");
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
			    			cod_departamento = codDep2.nextInt();
			    			
			    			Empleado emple1 = new Empleado(codigoEmp, nombreE, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto, cod_departamento);
			            	EmpleadoDAO.insertarEmpleado(session, emple1);
			                
			            	tx.commit();
			            	
			                logger.info("Se ha introducido un empleado en la BD.");
							logger.info("OPCIÓN 1.");
							break;
						case 2:
							System.out.println("Introduzca código del Empleado: ");
							codigoEmp = codigoE.nextInt();
							
							EmpleadoDAO.getEmpleado(session, codigoEmp);
							logger.info("OPCIÓN 2.");
							break;
						case 3:
							System.out.println("Introduzca el código de empleado:");
			    			codigoEmp = codigoE.nextInt();			
			    			System.out.println("Introduzca el nombre del empleado:");
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
			    			cod_departamento = codDep2.nextInt();
			    			
			    			Empleado emple3 = new Empleado(codigoEmp, nombreE, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto, cod_departamento);
			            	EmpleadoDAO.updateEmpleado(session, emple3);
			                
			            	tx.commit();
							
			                logger.info("Se ha actualizado un empleado en la BD.");
							logger.info("OPCIÓN 3.");
							break;
						case 4:
							System.out.println("Introduzca código del empleado: ");
							codigoEmp = codigoE.nextInt();
							
							Empleado emple4 = new Empleado(codigoEmp);
							EmpleadoDAO.deleteEmpleado(session, emple4);
							
							tx.commit();
							
							logger.info("Se ha eliminado un empleado de la BD.");
							logger.info("OPCIÓN 4.");
							break;
						case 0:
							logger.info("SALIDA DEL PROGRAMA.");
							break;
						}

					} while (opcion2 > 1 && opcion2 <= 4);

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
