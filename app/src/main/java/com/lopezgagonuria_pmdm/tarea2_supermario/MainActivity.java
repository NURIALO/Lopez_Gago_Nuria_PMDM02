package com.lopezgagonuria_pmdm.tarea2_supermario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.lopezgagonuria_pmdm.tarea2_supermario.databinding.ActivityMainBinding;
import java.util.Locale;

/**
 * MainActivity gestiona la interfaz de usuario principal, configurando la barra de navegación lateral,
 * la barra de herramientas (Toolbar), la gestión de idiomas y otras funciones relacionadas con la interfaz.
 */
public class MainActivity extends AppCompatActivity {

    // Instancia de ViewBinding para acceder de forma segura a los elementos de la vista.
    private ActivityMainBinding binding;

    // Objeto para manejar la configuración de la barra de aplicaciones y el DrawerLayout.
    private AppBarConfiguration appBarConfiguration;

    /**
     * Método que se ejecuta cuando se crea la actividad. Configura la interfaz de usuario,
     * el DrawerLayout, la Toolbar, y el idioma seleccionado.
     *
     * @param savedInstanceState Información previamente guardada sobre el estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Aplica el idioma guardado antes de inflar las vistas.
        applySavedLocale();

        super.onCreate(savedInstanceState);

        // Inicializa el ViewBinding para acceder a los elementos del diseño de forma más segura.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura la Toolbar como barra de acción de la actividad.
        setSupportActionBar(binding.toolbar);

        // Configura el botón de hamburguesa para el DrawerLayout.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configura el DrawerLayout y el NavigationView.
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Configura el NavController para gestionar la navegación entre fragmentos.
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        // Configura AppBarConfiguration para gestionar el DrawerLayout y los fragmentos raíz.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.characterListFragment, // Fragmento principal.
                R.id.option_language_switch // Fragmento de ajustes.
        ).setOpenableLayout(drawerLayout).build();

        // Vincula la Toolbar con el NavController para gestionar la navegación.
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        // Configura las acciones de los elementos del NavigationView.
        binding.navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home_menu) {
                navController.navigate(R.id.characterListFragment); // Navegar al fragmento principal.
            } else if (id == R.id.nav_settings_menu) {
                navController.navigate(R.id.option_language_switch); // Navegar al fragmento de ajustes.
            }
            // Cierra automáticamente el Drawer después de seleccionar una opción.
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Cambiar el tamaño y color de los textos de los ítems del menú.
        Menu menu = navigationView.getMenu();

        // Accede a los ítems del menú y cambia su estilo.
        MenuItem homeMenuItem = menu.findItem(R.id.nav_home_menu);
        MenuItem settingsMenuItem = menu.findItem(R.id.nav_settings_menu);

        // Encuentra los TextViews correspondientes a los ítems y cambia su estilo.
        View homeActionView = homeMenuItem.getActionView();
        if (homeActionView instanceof TextView) {
            TextView homeTextView = (TextView) homeActionView;
            homeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30); // Ajusta el tamaño del texto a 30sp
            homeTextView.setTextColor(getResources().getColor(R.color.azulMarino)); // Cambia el color
        }

        View settingsActionView = settingsMenuItem.getActionView();
        if (settingsActionView instanceof TextView) {
            TextView settingsTextView = (TextView) settingsActionView;
            settingsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30); // Ajusta el tamaño del texto a 30sp
            settingsTextView.setTextColor(getResources().getColor(R.color.azulMarino)); // Cambia el color
        }

        /**
         * Muestra una Snackbar la primera vez que se abre la aplicación.
         */
        Snackbar.make(binding.drawerLayout, getString(R.string.SnackBarText), Snackbar.LENGTH_LONG).show();
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        boolean isFirstTimeMenu = preferences.getBoolean("isFirstTimeMenu", true);

        if (isFirstTimeMenu) {
            // Mostrar la Snackbar con el texto desde strings.xml
            Snackbar.make(binding.getRoot(), getString(R.string.SnackBarText), Snackbar.LENGTH_LONG).show();

            // Actualizar el estado en SharedPreferences para que no se vuelva a mostrar.
            preferences.edit().putBoolean("isFirstTimeMenu", false).apply();
        }
    }

    /**
     * Infla el menú de opciones superior en la Toolbar.
     *
     * @param menu Objeto Menu donde se inflará el menú.
     * @return true si el menú se infló correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu); // Infla el menú de "Acerca de".
        return true;
    }

    /**
     * Maneja las selecciones de elementos en el menú de opciones superior.
     *
     * @param item Elemento seleccionado del menú.
     * @return true si la acción fue manejada, false si no.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            if (item.getItemId() == R.id.nav_about_menu) {
            // Construye un AlertDialog para mostrar información "Acerca de".
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.about); // Título del diálogo.

            // Infla un diseño personalizado para el diálogo.
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_about, null);
            builder.setView(dialogView);

            // Agrega un botón para cerrar el diálogo.
            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss());

            // Configura y muestra el diálogo con fondo azul marino
                // He tenido que hacerlo así porque con los estilos no consegui que me funcionara y se cerraba la app
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2575FC"))); // Azul marino.
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Aplica el idioma guardado en las preferencias antes de inflar las vistas.
     */
    private void applySavedLocale() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        boolean isSpanish = sharedPreferences.getBoolean("isSpanish", false);
        setLocale(isSpanish ? "es" : "en"); // Establece el idioma basado en las preferencias.
    }

    /**
     * Cambia el idioma de la aplicación según el código de idioma proporcionado.
     *
     * @param languageCode Código del idioma (por ejemplo, "es" para español, "en" para inglés).
     */
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}