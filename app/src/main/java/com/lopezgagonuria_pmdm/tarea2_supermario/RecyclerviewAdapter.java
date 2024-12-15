package com.lopezgagonuria_pmdm.tarea2_supermario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Adaptador para el RecyclerView que gestiona la lista de personajes.
 * Este adaptador asocia los datos de cada personaje con las vistas correspondientes en la interfaz de usuario.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.PersonajeViewHolder> {

    // Contexto de la aplicación para inflar vistas
    private Context context;

    // Lista de datos de los personajes que se mostrarán en el RecyclerView
    private List<CharacterData> personajeList;

    // Interfaz para manejar el clic en los elementos de la lista
    private OnItemClickListener listener;

    /**
     * Constructor del adaptador.
     *
     * @param context El contexto de la aplicación.
     * @param personajeList La lista de objetos `CharacterData` que se mostrarán en el RecyclerView.
     * @param listener El listener para manejar los clics en los elementos del RecyclerView.
     */
    public RecyclerviewAdapter(Context context, List<CharacterData> personajeList, OnItemClickListener listener) {
        this.context = context;
        this.personajeList = personajeList;
        this.listener = listener;
    }

    /**
     * Infla el layout de cada ítem y crea un ViewHolder para cada uno.
     *
     * @param parent El contenedor en el que se insertará la vista.
     * @param viewType El tipo de vista que se debe crear (generalmente no se usa en este caso).
     * @return Un nuevo objeto `PersonajeViewHolder` con la vista inflada.
     */
    @Override
    public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el layout para cada tarjeta de personaje
        View view = LayoutInflater.from(context).inflate(R.layout.character_cardview, parent, false);
        return new PersonajeViewHolder(view);
    }

    /**
     * Asocia los datos de un personaje a un `ViewHolder`.
     *
     * @param holder El `ViewHolder` que contiene las vistas donde se mostrarán los datos.
     * @param position La posición del elemento en la lista de datos.
     */
    @Override
    public void onBindViewHolder(PersonajeViewHolder holder, int position) {
        // Obtiene el personaje en la posición actual
        CharacterData personaje = personajeList.get(position);

        // Asigna los datos del personaje al `ViewHolder`
        holder.textViewNombre.setText(personaje.getNombre());
        holder.imageViewPersonaje.setImageResource(personaje.getImagen());

        // Configura el evento de clic en el item
        holder.itemView.setOnClickListener(v -> listener.onItemClick(personaje));
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return El tamaño de la lista de personajes.
     */
    @Override
    public int getItemCount() {
        return personajeList.size();
    }

    /**
     * Clase interna `PersonajeViewHolder` que mantiene las vistas para cada ítem del RecyclerView.
     */
    public static class PersonajeViewHolder extends RecyclerView.ViewHolder {
        // Vista de imagen del personaje
        ImageView imageViewPersonaje;

        // Vista de texto para el nombre del personaje
        TextView textViewNombre;

        /**
         * Constructor para el `ViewHolder` que inicializa las vistas.
         *
         * @param itemView La vista que representa un ítem del RecyclerView.
         */
        public PersonajeViewHolder(View itemView) {
            super(itemView);
            imageViewPersonaje = itemView.findViewById(R.id.image_cardview);
            textViewNombre = itemView.findViewById(R.id.text_Cardview);
        }
    }

    /**
     * Interfaz para manejar los clics en los elementos del RecyclerView.
     */
    public interface OnItemClickListener {
        void onItemClick(CharacterData personaje);
    }
}