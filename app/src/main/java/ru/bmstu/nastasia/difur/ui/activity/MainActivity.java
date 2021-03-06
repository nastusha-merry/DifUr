package ru.bmstu.nastasia.difur.ui.activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.*;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import ru.bmstu.nastasia.difur.R;
import ru.bmstu.nastasia.difur.ui.fragment.I_simple;
import ru.bmstu.nastasia.difur.ui.fragment.System_simple;

public class MainActivity extends AppCompatActivity {

    public class Requests {
        public static final int REQUEST_CODE = 0;
        public static final int ANSWER_CODE  = 1;
        public static final int MAP_REQ_CODE = 2;
        public static final int MAP_ANSW_CODE = 3;
        public static final String EXTRA_KEY   = "ValueToPrint";
        public static final String ANSWER_KEY  = "string";
    }

    class Names {
        String I_SIMPLE;
        String SYSTEM_SIMPLE;

        Names(Resources resources) {
            I_SIMPLE = resources.getStringArray(R.array.types_1)[0];
            SYSTEM_SIMPLE = resources.getStringArray(R.array.types_system)[0];
        }
    }

    private Toolbar toolbar;
    private int navigationSelectedItem = 1;
    private I_simple fragment_i_simple;
    private System_simple fragment_system_simple;
    private Names names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        names = new Names(getResources());

//        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
//        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        initDrawer();
        initFragments();
        openFragment();
//        new DrawerBuilder()
//                .withActivity(this)
//                .withToolbar(toolbar)
//                .withTranslucentStatusBar(false)
//                .withActionBarDrawerToggle(true)
//                .build();
//        Function f = new Function("f(x, y) = x+y");
//        tv.setText(String.valueOf(f.calculate(1, 2)));
//        Button button_theory =  (Button) findViewById(R.id.button_theory_part);
//        button_theory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                String query = message.getText().toString();
//
//                Log.d("LOOOOl", "\n\n\nPress\n\n\n\n");
//                Intent childActivityIntent = new Intent(MainActivity.this,
//                        ru.bmstu.nastasia.difur.theory.HelloPage.class);
////                        .putExtra(Requests.EXTRA_KEY, query);
//                if (childActivityIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(childActivityIntent, Requests.REQUEST_CODE);
//                }
//            }
//        });

    }

    private void initFragments() {
        fragment_i_simple = new I_simple();
        fragment_system_simple = new System_simple();
    }

    private void initDrawer() {

//        PrimaryDrawerItem users = new PrimaryDrawerItem()
//                .withIdentifier(1)
//                .withName(R.string.users)
//                .withIcon(FontAwesome.Icon.faw_users)
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    mToolbar.setTitle(R.string.users);
//                    navigationSelectedItem = 1;
//                    openFragment();
//
//                    return false;
//                });
//
//        PrimaryDrawerItem favoriteUsers = new PrimaryDrawerItem()
//                .withIdentifier(2)
//                .withName(R.string.favorite_users)
//                .withIcon(FontAwesome.Icon.faw_star2)
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    mToolbar.setTitle(R.string.favorite_users);
//                    navigationSelectedItem = 2;
//                    openFragment();
//
//                    return false;
//                });
//
//        PrimaryDrawerItem dialogs = new PrimaryDrawerItem()
//                .withIdentifier(3)
//                .withName(R.string.dialogs)
//                .withIcon(FontAwesome.Icon.faw_comment2)
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    mToolbar.setTitle(R.string.dialogs);
//                    navigationSelectedItem = 3;
//                    openFragment();
//
//                    return false;
//                });
//
//        PrimaryDrawerItem files = new PrimaryDrawerItem()
//                .withIdentifier(4)
//                .withName(R.string.my_files)
//                .withIcon(FontAwesome.Icon.faw_file2)
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    mToolbar.setTitle(R.string.my_files);
//                    navigationSelectedItem = 4;
//                    openFragment();
//
//                    return false;
//                });

        PrimaryDrawerItem i_simple = new PrimaryDrawerItem()
                .withIdentifier(0)
                .withName(names.I_SIMPLE)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        toolbar.setTitle(names.I_SIMPLE);
                        navigationSelectedItem = 0;
                        openFragment();
                        return false;
                    }
                });

        PrimaryDrawerItem system_simple = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(names.SYSTEM_SIMPLE)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        toolbar.setTitle(names.SYSTEM_SIMPLE);
                        navigationSelectedItem = 1;
                        openFragment();
                        return false;
                    }
                });

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
//                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new SectionDrawerItem().withName("Уравнения первого порядка"),
                        i_simple,
                        new SectionDrawerItem().withName("Системы уравнений"),
                        system_simple
//                        new SectionDrawerItem().withName("Уравнения первого порядка"),
//                        new PrimaryDrawerItem().withName("ДУ с разделяющимися \nпеременными")
//                                .withIdentifier(1),
//                        new PrimaryDrawerItem().withName("ДУ с постоянными коэффициентами"),
//                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withBadge("6").withIdentifier(2),
////                        new SectionDrawerItem().withName(R.string.drawer_item_settings),
//                        new SectionDrawerItem().withName("Уравнения второго порядка"),
//                        new PrimaryDrawerItem().withName(R.string.drawer_item_help),
//                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source),
//                        new DividerDrawerItem(),
//                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withBadge("12+").withIdentifier(1)
                )
                .withSelectedItem(navigationSelectedItem)
                .build();

//        final Drawer drawerResult = new DrawerBuilder()
//                .withActivity(this)
//                .withToolbar(toolbar)
////                .withAccountHeader(mAccountHeader)
//                .withActionBarDrawerToggle(true)
//                .withActionBarDrawerToggleAnimated(true)
//                .addDrawerItems(
//                        myProfile,
////                        users,
////                        favoriteUsers,
////                        dialogs,
////                        files,
//                        new DividerDrawerItem()
////                        websocketItem
//                        , myProfile
//                )
////                .withSelectedItem(navigationSelectedItem)
//                .build();
    }

    private void openFragment() {
        switch (navigationSelectedItem) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_activity_container, fragment_i_simple)
                        .addToBackStack(names.I_SIMPLE)
                        .commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_activity_container, fragment_system_simple)
                        .addToBackStack(names.SYSTEM_SIMPLE)
                        .commit();
                break;
            default:
                Toast.makeText(this,
                        "Fragment with number " + navigationSelectedItem + " does not exists!",
                        Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        openFragment();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        openFragment();
//    }
}
