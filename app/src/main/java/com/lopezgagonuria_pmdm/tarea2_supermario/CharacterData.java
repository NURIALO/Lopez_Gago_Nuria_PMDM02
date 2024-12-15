package com.lopezgagonuria_pmdm.tarea2_supermario;

/**
 * Clase que representa los datos de un personaje en la aplicación.
 * Contiene información como el nombre, la imagen, la descripción y las habilidades del personaje.
 */
public class CharacterData {

    // Nombre del personaje
    private String nombre;

    // ID del recurso de la imagen asociada al personaje (almacenado en drawable)
    private int imagen;

    // Descripción del personaje (historia, características, etc.)
    private String descripcion;

    // Lista o resumen de las habilidades del personaje
    private String habilidades;

    /**
     * Constructor de la clase CharacterData.
     * Permite inicializar un objeto con toda la información requerida de un personaje.
     *
     * @param nombre      Nombre del personaje.
     * @param imagen      ID del recurso de la imagen del personaje.
     * @param descripcion Descripción detallada del personaje.
     * @param habilidades Resumen de las habilidades del personaje.
     */
    public CharacterData(String nombre, int imagen, String descripcion, String habilidades) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.habilidades = habilidades;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return Nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el ID del recurso de la imagen asociada al personaje.
     * Este ID se utiliza para referenciar la imagen desde la carpeta drawable.
     *
     * @return ID del recurso de la imagen del personaje.
     */
    public int getImagen() {
        return imagen;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return Descripción del personaje.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Habilidades del personaje en formato de texto.
     */
    public String getHabilidades() {
        return habilidades;
    }
}