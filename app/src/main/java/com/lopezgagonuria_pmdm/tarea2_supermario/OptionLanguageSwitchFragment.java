package com.lopezgagonuria_pmdm.tarea2_supermario;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import java.util.Locale;

/**
 * Fragmento para cambiar el idioma de la aplicación.
 * Proporciona un `SwitchCompat` para alternar entre inglés y español, y guarda la selección en `SharedPreferences`.
 */
public class OptionLanguageSwitchFragment extends Fragment {
    private SwitchCompat switchLanguage;

    /**
     * Constructor vacío requerido para los fragmentos.
     */
    public OptionLanguageSwitchFragment() {
        // Constructor vacío requerido
    }

    /**
     * Infla la vista para este fragmento y configura los componentes de la interfaz.
     *
     * @param inflater           Objeto LayoutInflater para inflar las vistas.
     * @param container          Contenedor principal para el fragmento.
     * @param savedInstanceState Estado previamente guardado (si existe).
     * @return Vista inflada con todos los elementos inicializados.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño del fragmento.
        View view = inflater.inflate(R.layout.option_language_switch, container, false);

        // Inicializa el Switch para cambiar el idioma.
        switchLanguage = view.findViewById(R.id.option_language_switch);

        // Obtiene el estado actual del idioma desde SharedPreferences.
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        boolean isSpanish = sharedPreferences.getBoolean("isSpanish", false); // Por defecto, el idioma será inglés.
        switchLanguage.setChecked(isSpanish);

        // Configura el comportamiento del Switch cuando cambia de estado.
        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (isChecked) {
                editor.putBoolean("isSpanish", true); // Guardar selección de idioma español.
                setLocale("es"); // Cambiar idioma a español.
            } else {
                editor.putBoolean("isSpanish", false); // Guardar selección de idioma inglés.
                setLocale("en"); // Cambiar idioma a inglés.
            }
            editor.apply();

            // Muestra un mensaje de confirmación.
            Toast.makeText(requireContext(), R.string.languajeChange, Toast.LENGTH_SHORT).show();

            // Reinicia la actividad para aplicar los cambios.
            requireActivity().recreate();
        });

        // Configura el botón para regresar al fragmento principal.
        Button backButton = view.findViewById(R.id.btn_back_switch);
        backButton.setOnClickListener(v -> {
            // Usa NavController para navegar al fragmento principal.
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_switchFragment_to_mainFragment);
        });

        // Cambia el título de la Toolbar cuando este fragmento esté visible.
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.getSupportActionBar().setTitle(R.string.languageOptions);
        }

        return view;
    }

    /**
     * Cambia el idioma de la aplicación según el código de idioma proporcionado.
     *
     * @param languageCode Código del idioma (por ejemplo, "es" para español, "en" para inglés).
     */
    private void setLocale(String languageCode) {
        // Configura el idioma de la aplicación.
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        requireActivity().getResources().updateConfiguration(config, requireActivity().getResources().getDisplayMetrics());

        // ContextWrapper para mayor compatibilidad en Android 7+.
        Context context = requireActivity().createConfigurationContext(config);
        requireActivity().getBaseContext().getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}