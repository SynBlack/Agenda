package org.iesalandalus.programacion.agenda;

import java.util.regex.Pattern;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	private static final String ERROR = "ERROR! Vuelva a intentarlo";
	private static final String EXITO = "La operación se realizo con ÉXITO";
	private static Agenda agenda = null;

	public MainApp() {
	}

	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("Programa para gestionar una agenda de contactos");
		agenda = new Agenda();
		mostrarMenu();
		ejecutarOpcion(elegirOpcion());
	}

	private static void mostrarMenu() {
		System.out.println("Menú:");
		System.out.println("1. Añadir contacto");
		System.out.println("2. Buscar contacto");
		System.out.println("3. Borrar contacto");
		System.out.println("4. Mostrar contactos");
		System.out.println("5. Mostrar menú");
		System.out.println("0. Salir");
	}

	private static int elegirOpcion() {
		System.out.println("Elija una opción: ");
		return Entrada.entero();
	}

	private static void ejecutarOpcion(int opcion) throws OperationNotSupportedException {
		switch (opcion) {
		case 0:
			System.exit(0);
			break;
		case 1:
			anadirContacto();
			break;
		case 2:
			buscarContacto();
			break;
		case 3:
			borrarContacto();
			break;
		case 4:
			listarAgenda();
			break;
		case 5:
			mostrarMenu();
			elegirOpcion();
			break;
		default:
			System.out.println(ERROR);
			elegirOpcion();
		}
	}

	private static void anadirContacto() throws OperationNotSupportedException {
		agenda.anadir(comprobarContactoAnadir());
		ejecutarOpcion(elegirOpcion());
	}

	private static void buscarContacto() throws OperationNotSupportedException {
		String nombre;
		System.out.println("Inserte el nombre a buscar: ");
		nombre = Entrada.cadena();
		agenda.buscar(nombre);
		ejecutarOpcion(elegirOpcion());
	}

	private static void borrarContacto() throws OperationNotSupportedException {
		String nombre;
		System.out.println("Inserte el nombre a borrar: ");
		nombre = Entrada.cadena();
		boolean encontrado = false;
		Contacto[] contactos = agenda.getContactos();
		try {
			for (Contacto contacto : contactos) {
				if (contacto != null) {
					if (contacto.getNombre().equals(nombre)) {
						encontrado = true;
						agenda.borrar(nombre);
					}
				}
			}
		} catch (OperationNotSupportedException e) {
			e.getMessage();
		}
		if (encontrado == false) {
			System.out.println(ERROR);
		} else {
			System.out.println(EXITO);
		}
		ejecutarOpcion(elegirOpcion());
	}

	private static void listarAgenda() throws OperationNotSupportedException {
		Contacto[] contactos = agenda.getContactos();
		int posicion = 1;
		int indice = 0;
		boolean listaVacia = true;
		for (Contacto contacto : contactos) {
			if (contacto != null) {
				listaVacia = false;
				++indice;
			}
		}
		if (listaVacia == false) {
			System.out.println("---------- AGENDA ------------");
			System.out.println("Lista de contactos: ");
			for (Contacto contacto : contactos) {
				if (contacto != null) {
					System.out.println(posicion + ". " + contacto.getNombre() + " - " + contacto.getTelefono() + " - "
							+ contacto.getCorreo());
					++posicion;
				}
			}
		} else {
			System.out.println("No existen contactos en esta Agenda");
		}
		ejecutarOpcion(elegirOpcion());
	}

	private static Contacto comprobarContactoAnadir() throws OperationNotSupportedException {
		String ER_TELEFONO = "[69][0-9]{8}";
		String ER_CORREO = "[^@]{1,}@[a-zA-Z]{1,}\\.[a-zA-Z]{1,5}";
		boolean valido;
		Contacto contacto = null;
		String nombre, telefono, correo;
		System.out.println("Inserte Nombre y Apellidos");
		nombre = Entrada.cadena();
		System.out.println("Inserte el Teléfono");
		telefono = Entrada.cadena();
		System.out.println("Inserte el Correo");
		correo = Entrada.cadena();
		if (nombre != null && nombre != "") {
			valido = true;
		} else if (Pattern.matches(ER_TELEFONO, telefono)) {
			valido = true;
		} else if (Pattern.matches(ER_CORREO, correo)) {
			valido = true;
		} else {
			valido = false;
		}
		if (valido) {
			contacto = new Contacto(nombre, telefono, correo);
			return contacto;
		} else {
			System.out.println("ERROR! Contacto incorrecto");
			ejecutarOpcion(elegirOpcion());
		}
		return contacto;
	}
}
