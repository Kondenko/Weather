package com.aleksandersh.weather.features.main;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.aleksandersh.weather.R;
import com.aleksandersh.weather.features.about.AboutFragment;
import com.aleksandersh.weather.features.settings.SettingsFragment;
import com.aleksandersh.weather.features.weather.presentation.WeatherFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "WeatherActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        // Toggle обеспечивает совместную работу DrawerLayout с ActionBar
        toggle = createDrawerToggle();
        drawer.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
            // При запуске приложения необходимо загрузить стандартный фрагмент
            selectNavigationMenuItem(R.id.nav_weather_fragment);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Синхронизация индикатора с состоянием DrawerLayout
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Новая конфигурация передается Toggle, хотя я не совсем понял зачем это здесь
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_weather_fragment:
                replaceFragment(WeatherFragment.newInstance());
                break;
            case R.id.nav_settings_fragment:
                replaceFragment(SettingsFragment.newInstance());
                break;
            case R.id.nav_about_fragment:
                replaceFragment(AboutFragment.newInstance());
                break;
            default:
                throw new IllegalArgumentException("Illegal menu id");
        }
        // Установка нового заголовка, для начала хватит названия пункта меню
        setTitle(item.getTitle());
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /**
     * Собирает новый экземпляр {@link ActionBarDrawerToggle}.
     *
     * @return Новый экземпляр.
     */
    protected ActionBarDrawerToggle createDrawerToggle() {
        return new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
    }

    /**
     * Заменяет фрагмент в контейнере.
     *
     * @param fragment Новый фрагмент.
     */
    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    protected void setupToolbar() {
        float toolbarElevation = this.getResources().getDimension(R.dimen.all_elevation_toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setElevation(toolbar, toolbarElevation);
    }

    /**
     * Переключает пункт в панели навигации.
     *
     * @param itemId Id ресурса выбираемого пункта меню.
     * @throws IllegalArgumentException Исключение возникает, если пункт с таким Id не найден .
     */
    private void selectNavigationMenuItem(@IdRes int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        if (item != null) {
            onNavigationItemSelected(item);
            item.setChecked(true);
        } else {
            throw new IllegalArgumentException(String.format(Locale.getDefault(), "Navigation menu item with id %d not found", id));
        }
    }

}
