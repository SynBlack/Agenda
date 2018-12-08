package org.iesalandalus.programacion.agenda;

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

	
}
