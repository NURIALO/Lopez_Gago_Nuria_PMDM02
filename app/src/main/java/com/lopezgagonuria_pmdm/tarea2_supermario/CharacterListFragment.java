package com.lopezgagonuria_pmdm.tarea2_supermario;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.lopezgagonuria_pmdm.tarea2_supermario.databinding.RecyclerviewFragmentBinding;
import java.util.ArrayList;
import java.util.List;
/**
 * Fragmento encargado de mostrar una lista de personajes en un RecyclerView.
 * Implementa la interfaz OnItemClickListener para manejar los eventos de clic en los elementos de la lista.
 */
public class CharacterListFragment extends Fragment implements RecyclerviewAdapter.OnItemClickListener {
    // ViewBinding para acceder a los elementos de la vista de forma segura
    private RecyclerviewFragmentBinding binding;
    // Adaptador para el RecyclerView
    private RecyclerviewAdapter adapter;
    // Lista de datos de los personajes
    private List<CharacterData> personajeList;
    /**
     * Constructor vacío requerido para el fragmento.
     */
    public CharacterListFragment() {
        // Constructor vacío
    }
    /**
     * Método que infla la vista del fragmento y configura el objeto de enlace (ViewBinding).
     *
     * @param inflater  Objeto LayoutInflater para inflar el diseño.
     * @param container Contenedor padre donde se colocará el diseño.
     * @param savedInstanceState Estado guardado del fragmento (si aplica).
     * @return La raíz de la vista inflada.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento y configurar el objeto de enlace
        binding = RecyclerviewFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    /**
     * Método llamado después de que la vista del fragmento ha sido creada.
     * Configura el RecyclerView y carga la lista de personajes.
     *
     * @param view               La vista creada para este fragmento.
     * @param savedInstanceState Estado guardado del fragmento (si aplica).
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Configurar el RecyclerView con un LinearLayoutManager
        binding.characterRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inicializar la lista de personajes
        personajeList = new ArrayList<>();
        personajeList.add(new CharacterData("MARIO", R.drawable.mario, requireContext().getString(R.string.MarioDescription), requireContext().getString(R.string.MarioAbilities)));
        personajeList.add(new CharacterData("LUIGI", R.drawable.luigi, requireContext().getString(R.string.LuigiDescription), requireContext().getString(R.string.LuigiAbilities)));
        personajeList.add(new CharacterData("PEACH", R.drawable.peach, requireContext().getString(R.string.PeachDescription), requireContext().getString(R.string.PeachAbilities)));
        personajeList.add(new CharacterData("TOAD", R.drawable.toad, requireContext().getString(R.string.ToadDescription), requireContext().getString(R.string.ToadAbilities)));
        personajeList.add(new CharacterData("BOWSER", R.drawable.bowser, requireContext().getString(R.string.BowserDesciption), requireContext().getString(R.string.BowserAbilities)));
        personajeList.add(new CharacterData("PAKKUN", R.drawable.pakkun, requireContext().getString(R.string.PakkunDescription), requireContext().getString(R.string.PakkunAbilities)));
        personajeList.add(new CharacterData("YOSHI", R.drawable.yoshi, requireContext().getString(R.string.YoshiDescription), requireContext().getString(R.string.YoshiAbilities)));
        personajeList.add(new CharacterData("KOOPA", R.drawable.koopa, requireContext().getString(R.string.KoopaDescription), requireContext().getString(R.string.KoopaAbilities)));
        // Configurar el adaptador del RecyclerView
        adapter = new RecyclerviewAdapter(getContext(), personajeList, this);
        binding.characterRecyclerview.setAdapter(adapter);
    }
    /**
     * Método que se ejecuta cuando se hace clic en un elemento del RecyclerView.
     * Muestra un mensaje (Toast) con el nombre del personaje seleccionado
     * y navega al fragmento de detalles del personaje.
     *
     * @param personaje El objeto CharacterData correspondiente al elemento clicado.
     */
    @Override
    public void onItemClick(CharacterData personaje) {
        // Mostrar un Toast con el nombre del personaje seleccionado
        Toast.makeText(getContext(), getString(R.string.toastText) + " " + personaje.getNombre(), Toast.LENGTH_SHORT).show();
        // Crear un Bundle con los datos del personaje
        Bundle args = new Bundle();
        args.putString("nombre", personaje.getNombre());
        args.putInt("imagen", personaje.getImagen());
        args.putString("descripcion", personaje.getDescripcion());
        args.putString("habilidades", personaje.getHabilidades());
        // Navegar al fragmento de detalles del personaje con los datos
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_characterListFragment_to_characterDetailFragment, args);
    }
}
