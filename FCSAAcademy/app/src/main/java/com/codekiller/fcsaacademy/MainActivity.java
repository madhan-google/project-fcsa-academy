package com.codekiller.fcsaacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.codekiller.fcsaacademy.Fragments.AboutFragment;
import com.codekiller.fcsaacademy.Fragments.ContactFragment;
import com.codekiller.fcsaacademy.Fragments.CourseFragment;
import com.codekiller.fcsaacademy.Fragments.EbookFragment;
import com.codekiller.fcsaacademy.Fragments.HomeFragment;
import com.codekiller.fcsaacademy.Fragments.PhotosFragment;
import com.codekiller.fcsaacademy.Fragments.RegistrationFragment;
import com.codekiller.fcsaacademy.Utils.UtilClass;
import com.google.android.material.navigation.NavigationView;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;

//fcsapbt@gmail.com
//fcsapbt1234
public class MainActivity extends AppCompatActivity {

    UtilClass utilClass;
    SNavigationDrawer navigationDrawer;
    ArrayList<MenuItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        navigationDrawer = findViewById(R.id.drawer_layout);

        utilClass = new UtilClass(this);
        list = new ArrayList<>();

        list.add(new MenuItem("Home", R.drawable.bg5));
        list.add(new MenuItem("About Us", R.drawable.bg3));
        list.add(new MenuItem("Registration", R.drawable.bg1));
        list.add(new MenuItem("Courses", R.drawable.bg3));
        list.add(new MenuItem("eBooks", R.drawable.bg6));
        list.add(new MenuItem("Contact", R.drawable.bg4));
        list.add(new MenuItem("Photo Session", R.drawable.bg4));

        loadFragment(new HomeFragment(this));
        navigationDrawer.setMenuItemList(list);
        navigationDrawer.setAppbarTitleTV("Home");
        navigationDrawer.setAppbarColor(R.color.purple_500);
        navigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position){
                    case 0:
                        loadFragment(new HomeFragment(MainActivity.this));
                        break;
                    case 1:
                        loadFragment(new AboutFragment(MainActivity.this));
                        break;
                    case 2:
                        loadFragment(new RegistrationFragment(MainActivity.this));
                        break;
                    case 3:
                        loadFragment(new CourseFragment(MainActivity.this));
                        break;
                    case 4:
                        loadFragment(new EbookFragment(MainActivity.this));
                        break;
                    case 5:
                        loadFragment(new ContactFragment(MainActivity.this));
                        break;
                    case 6:
                        loadFragment(new PhotosFragment(MainActivity.this));
                        break;
                }
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
//        transaction.attach(fragment);
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        loadFragment(new HomeFragment(this));
    }
}