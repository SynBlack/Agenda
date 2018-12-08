package org.iesalandalus.programacion.agenda;

import javax.naming.OperationNotSupportedException;

/**
 * @author Emanuel Martínez Lonardi
 *
 */
public class Agenda {

	private static final int MAX_CONTACTOS = 100;
	private int numContactos;
	private Contacto[] contactos = null;

	public Contacto[] getContactos() {

		return this.contactos;
	}

	public int getNumContactos() {
		return numContactos;

	}

	public void anadir(Contacto contacto) {
		int indice;
		try {
			indice = buscarPrimerIndiceComprobandoExistencia(contacto);
			if (indiceNoSuperaTamano(indice)) {
				this.contactos[indice] = contacto;
			} else {
				System.out.println("El array está lleno");
			}
		} catch (OperationNotSupportedException e) {
			System.out.println("Operación  no soportada");
			e.getMessage();
		}
	}

	private int buscarPrimerIndiceComprobandoExistencia(Contacto existeContacto) throws OperationNotSupportedException {
		int indiceLibre = 0;
		for (Contacto contacto : contactos) {
			indiceLibre++;
			if (contacto.equals(existeContacto)) {
				throw new OperationNotSupportedException("Ya existe un contacto con ese nombre.");
			}
		}
		return indiceLibre;
	}

	private boolean indiceNoSuperaTamano(int indice) {
		boolean noSuperaTamano = false;
		if (indice < MAX_CONTACTOS) {
			noSuperaTamano = true;
		} else {
			noSuperaTamano = false;
		}
		return noSuperaTamano;
	}

	public Contacto buscar(String contacto) {
		int indice;
		Contacto encontrado = null;
		indice = buscarIndiceCliente(contacto);
		if (indice < MAX_CONTACTOS + 1) {
			encontrado = contactos[indice];
			System.out.println("Se encontró el contacto: " + contacto);
			return encontrado;
		} else {
			System.out.println("No se encontró el contacto");
		}
		return encontrado;
	}

	private int buscarIndiceCliente(String cliente) {
		int indice = 0;
		for (Contacto contacto : contactos) {
			if (contacto.getNombre().equals(cliente)) {
				return indice;
			}
			++indice;
		}
		return MAX_CONTACTOS + 1;
	}

	public void borrar(String contacto) throws OperationNotSupportedException {

		int indice;
		indice = buscarIndiceCliente(contacto);
		if (indice < MAX_CONTACTOS + 1) {
			contactos[indice] = null;
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("El contacto a borrar no existe.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		for (int i = posicion; posicion < MAX_CONTACTOS; i++) {
			contactos[i] = contactos[i + 1];
		}
	}
}
