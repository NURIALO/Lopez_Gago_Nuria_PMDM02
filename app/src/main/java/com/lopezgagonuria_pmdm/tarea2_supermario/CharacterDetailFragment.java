package com.lopezgagonuria_pmdm.tarea2_supermario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.lopezgagonuria_pmdm.tarea2_supermario.databinding.CharacterDetailFragmentBinding;

/**
 * Fragmento que muestra los detalles de un personaje seleccionado.
 * Este fragmento utiliza ViewBinding para acceder a los elementos de la vista de forma segura.
 */
public class CharacterDetailFragment extends Fragment {

    // Objeto de enlace para acceder a los elementos del diseño de forma segura
    private CharacterDetailFragmentBinding binding;

    /**
     * Método que infla el diseño del fragmento y configura el objeto de enlace (ViewBinding).
     *
     * @param inflater  Objeto LayoutInflater para inflar el diseño del fragmento.
     * @param container Contenedor padre donde se colocará el diseño.
     * @param savedInstanceState Estado guardado del fragmento (si aplica).
     * @return La raíz de la vista inflada.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento y configurar el objeto de enlace
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método llamado después de que la vista del fragmento ha sido creada.
     * Aquí se configura la vista con los datos recibidos a través de los argumentos.
     *
     * @param view               La vista creada para este fragmento.
     * @param savedInstanceState Estado guardado del fragmento (si aplica).
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Verifica si hay argumentos pasados al fragmento
        if (getArguments() != null) {
            // Obtiene los datos del personaje desde los argumentos
            String nombre = getArguments().getString("nombre"); // Nombre del personaje
            int imagen = getArguments().getInt("imagen"); // ID del recurso de imagen del personaje
            String descripcion = getArguments().getString("descripcion"); // Descripción del personaje
            String habilidades = getArguments().getString("habilidades"); // Habilidades del personaje

            // Asigna los datos obtenidos a los elementos de la vista
            binding.nameTextDetail.setText(nombre); // Configura el nombre del personaje
            binding.imageCharacterDetail.setImageResource(imagen); // Configura la imagen del personaje
            binding.descriptionTextDetail.setText(descripcion); // Configura la descripción del personaje
            binding.abilitiesTextDetail.setText(habilidades); // Configura las habilidades del personaje
        }
    }

    /**
     * Método llamado cuando la vista del fragmento se destruye.
     * Libera el objeto de enlace para evitar fugas de memoria.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Libera el objeto de enlace
    }
}