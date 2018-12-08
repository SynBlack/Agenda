package org.iesalandalus.programacion.agenda;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Emanuel Martínez Lonardi
 *
 */
public class Contacto {
	private static final String ER_TELEFONO = "[69][0-9]{8}";;
	private static final String ER_CORREO = "[^@]{1,}@[a-zA-Z]{1,}\\.[a-zA-Z]{1,5}";;
	private String nombre;
	private String telefono;
	private String correo;

	public String getNombre() {
        return this.nombre;
    }
     private void setNombre(String nombre) {
        boolean creado = false;
        if (this.nombre != null) {
            creado = true;
        }
        if (nombre != null && nombre != "" && creado != true) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre de un contacto no puede ser nulo o vacío.");
        }
    }
     public String getTelefono() {
        return this.telefono;
    }
     public void setTelefono(String telefono) {
         if (telefono != null && telefono != "") {
            if (Pattern.matches(ER_TELEFONO, telefono)) {
                this.telefono = telefono;
            } else {
                throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
            }
        } else {
            throw new IllegalArgumentException("El teléfono de un contacto no puede ser nulo o vacío.");
        }
    }
     public String getCorreo() {
        return this.correo;
    }
     public void setCorreo(String correo) {
         if (correo != null && correo != "") {
            if (Pattern.matches(ER_CORREO, correo)) {
                this.correo = correo;
            } else {
                throw new IllegalArgumentException("El correo no tiene un formato válido.");
            }
        } else {
            throw new IllegalArgumentException("El correo de un contacto no puede ser nulo o vacío.");
        }
    }
     
     @Override
     public String toString() {
          return nombre + " [" + telefono + ", " + correo + "]";
     }
      @Override
     public int hashCode() {
         int hash = 7;
         hash = 61 * hash + Objects.hashCode(this.nombre);
         hash = 61 * hash + Objects.hashCode(this.telefono);
         hash = 61 * hash + Objects.hashCode(this.correo);
         return hash;
     }
      @Override
     public boolean equals(Object obj) {
         if (this == obj) {
             return true;
         }
         if (obj == null) {
             return false;
         }
         if (getClass() != obj.getClass()) {
             return false;
         }
         final Contacto other = (Contacto) obj;
         if (!Objects.equals(this.nombre, other.nombre)) {
             return false;
         }
         if (!Objects.equals(this.telefono, other.telefono)) {
             return false;
         }
         if (!Objects.equals(this.correo, other.correo)) {
             return false;
         }
         return true;
     }
}
