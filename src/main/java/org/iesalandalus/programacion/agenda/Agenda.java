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
}
