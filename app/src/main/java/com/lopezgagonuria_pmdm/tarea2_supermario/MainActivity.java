package com.lopezgagonuria_pmdm.tarea2_supermario;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.lopezgagonuria_pmdm.tarea2_supermario.databinding.ActivityMainBinding;

/**
 * Actividad principal de la aplicación que gestiona la navegación y la barra de herramientas.
 * Configura el DrawerLayout, NavigationView y el NavController para proporcionar una experiencia
 * de navegación fluida.
 */
public class MainActivity extends AppCompatActivity {
    // Objeto de ViewBinding para acceder de manera segura a los elementos del diseño
    private ActivityMainBinding binding;

    // Configuración de la barra de aplicaciones
    private AppBarConfiguration appBarConfiguration;

    /**
     * Método principal que se ejecuta al crear la actividad.
     * Configura la interfaz de usuario, el Navigation Drawer y otras funcionalidades.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializa el ViewBinding para acceder a los elementos del diseño de manera segura.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura la Toolbar como la barra de acción de la actividad.
        setSupportActionBar(binding.toolbar);

        // Configura el botón de hamburguesa (menu drawer).
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configura el Navigation Drawer y el NavigationView.
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Configura el NavController para manejar la navegación.
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        // Configura AppBarConfiguration con los fragmentos raíz y el DrawerLayout.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.characterListFragment,  // Fragmento principal.
                R.id.option_language_switch  // Fragmento de ajustes.
        ).setOpenableLayout(drawerLayout).build();

        // Vincula la Toolbar con el NavController.
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        // Configura las acciones al seleccionar elementos del NavigationView.
        binding.navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home_menu) {
                navController.navigate(R.id.characterListFragment);
            } else if (id == R.id.nav_settings_menu) {
                navController.navigate(R.id.option_language_switch);
            }
            // Cierra automáticamente el Drawer después de seleccionar una opción.
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Muestra una Snackbar la primera vez que se abre el menú.
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        boolean isFirstTimeMenu = preferences.getBoolean("isFirstTimeMenu", true);
        if (isFirstTimeMenu) {
            Snackbar.make(binding.getRoot(), getString(R.string.SnackBarText), Snackbar.LENGTH_LONG).show();
            preferences.edit().putBoolean("isFirstTimeMenu", false).apply();
        }
    }

    /**
     * Infla el menú de opciones superior.
     *
     * @param menu Objeto Menu donde se inflará el menú.
     * @return true si el menú se infló correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }

    /**
     * Maneja las selecciones del menú de opciones.
     *
     * @param item Elemento del menú seleccionado.
     * @return true si la acción fue manejada; de lo contrario, delega al super.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_about_menu) {
            // Muestra un AlertDialog con información "Acerca de".
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.about);

            // Infla un diseño personalizado para el AlertDialog.
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_about, null);
            builder.setView(dialogView);

            // Configura el botón de cerrar.
            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss());

            // Muestra el AlertDialog con un fondo personalizado.
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2575FC"))); // Azul marino.
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}