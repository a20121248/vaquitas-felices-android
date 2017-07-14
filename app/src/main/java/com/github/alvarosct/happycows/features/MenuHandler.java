package com.github.alvarosct.happycows.features;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.happycows.FlavorMethods;
import com.github.alvarosct.happycows.PreferenceManager;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.User;
import com.github.alvarosct.happycows.features.main.BlankActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android-Dev on 07/06/2017.
 */

public class MenuHandler implements NavigationView.OnNavigationItemSelectedListener {

    private static MenuHandler INSTANCE;
    private static List<DrawerItem> listSections = new ArrayList<>();
    private View navLayout;
    private FrameLayout fl_holder;

    public static MenuHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuHandler();
        }
        return INSTANCE;
    }

    static {
        FlavorMethods.setupDrawerItems();
    }

    private MenuHandler() {

    }

    public static void openApp(Activity activity) {
        openApp(activity, 0);

//        User user = PreferenceManager.getInstance(activity).getUserInfo();
//        if (user != null){
//            openApp(activity, user.getId());
//        } else {
//            UtilMethods.showToast("No hay un usuario registrado");
//        }
    }

    public static void openApp(Activity activity, int userId) {
//        List<Integer> sections = AppDatabase.getInstance().moduleUserModel().getSectionsByUser(userId);
//
//        for (DrawerItem drawerItem : listSections) {
//            if ((drawerItem.resourceId == 1) && sections.contains(drawerItem.id)) {
//                Intent intent =  new Intent(activity, drawerItem.activityClazz);
//                activity.startActivity(intent);
//                activity.finish();
//                return;
//            }
//        }
    }

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView tvName;
    private TextView tvEmail;
    private Activity activity;

    public View getView(final Activity activity, @LayoutRes int layoutId) {
        View content = activity.getLayoutInflater().inflate(layoutId, null);


        LayoutInflater inflater = activity.getLayoutInflater();
        navLayout = inflater.inflate(R.layout.nav_layout, null);
        drawerLayout = (DrawerLayout) navLayout.findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) navLayout.findViewById(R.id.nav_view);
        fl_holder = (FrameLayout) navLayout.findViewById(R.id.fl_holder);

        View headerView = inflater.inflate(R.layout.nav_header_main, null);
        tvName = (TextView) headerView.findViewById(R.id.tv_name);
        tvEmail = (TextView) headerView.findViewById(R.id.tv_email);

        User user = PreferenceManager.getInstance(activity).getUserInfo();

        tvName.setText(user.getNombres() + " " + user.getApellidos());
        tvEmail.setText(user.getNombreUsuario());
        View holder = headerView.findViewById(R.id.holder);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, MainMenuActivity.class));
                activity.finish();
            }
        });

        navigationView.addHeaderView(headerView);
        navigationView.setNavigationItemSelectedListener(this);

        fl_holder.removeAllViews();
        fl_holder.addView(content);
//        drawerLayout.addView(content,0);

        if (navLayout.getParent() != null) {
            ((ViewGroup) navLayout.getParent()).removeView(navLayout);
        }
        return navLayout;
    }

    public void initialize(Activity activity, Toolbar toolbar) {
        this.activity = activity;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawerLayout, toolbar,
                R.string.material_drawer_open, R.string.material_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    public static class DrawerItem {
        private int id;
        private int menuId;
        private int resourceId;
        private Class activityClazz;

        public DrawerItem(int id, int menuId, int resourceId, Class activityClazz) {
            this.id = id;
            this.menuId = menuId;
            this.resourceId = resourceId;
            this.activityClazz = activityClazz;
            listSections.add(this);
        }
    }

    public Intent pickIntent(Activity activity, int resourceId, int menuId) {

        for (DrawerItem drawerItem : listSections) {
            if (drawerItem.id == 1000 && drawerItem.menuId == menuId) {
                return new Intent(activity, drawerItem.activityClazz);
            }

            if ((drawerItem.resourceId == resourceId) && (drawerItem.menuId == menuId)) {
                Intent intent = new Intent(activity, drawerItem.activityClazz);
                intent.putExtra(Constants.BUNDLE_RESOURCE_ID, drawerItem.resourceId);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                return intent;
            }
        }
        return new Intent(activity, BlankActivity.class);
    }


    public boolean onBackPressed() {

        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int menuItemId = menuItem.getItemId();

//
//        if (menuItem.getItemId() == R.id.nav_logout){
//            new DialogCustom(activity,
//                    "¡Atención!", "¿Estás seguro que deseas cerrar sesión?",
//                    new DialogCustom.ButtonBehaviour("Si", new DialogCustom.IButton() {
//                        @Override
//                        public void onButtonClick() {
//
//                            PreferenceManager.getInstance(activity).removeUser();
//                            Intent i = new Intent(activity, LoginActivity.class);
//                            activity.startActivityForResult(i, Constants.INTENT_SYNC_ACTIVITY);
//                            activity.finish();
//
//                        }
//                    }), "No").show();
//            return true;
//        }

        Intent intent = pickIntent(activity, 1, menuItemId);
        if (intent != null) {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.fade_in_2, R.anim.fade_out_2);
            activity.finish();
        } else {
//            TODO: SHOULD NOT HAPPEN
            throw new RuntimeException("SHOULD NOT HAPPEN");
        }

//        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
