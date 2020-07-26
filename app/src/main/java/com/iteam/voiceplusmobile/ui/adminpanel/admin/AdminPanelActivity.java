package com.iteam.voiceplusmobile.ui.adminpanel.admin;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.iteam.voiceplusmobile.MainActivity;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement.AddItemManagementFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.addneworder.AddNewOrderFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.manageitem.ManageItemFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement.OrderManagementFragment;
import com.iteam.voiceplusmobile.ui.bankpayment.BankPayment;
import com.iteam.voiceplusmobile.ui.chatbox.ChatBoxFragment;
import com.iteam.voiceplusmobile.ui.feedbackreview.FeedBackAdmin;
import com.iteam.voiceplusmobile.ui.home.HomeFragment;
import com.iteam.voiceplusmobile.ui.login.profile.ProfileFragment;
import com.iteam.voiceplusmobile.ui.orders.OrdersFragment;

public class AdminPanelActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    // urls to load navigation header background image
    // and profile image
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_MANAGE_ITEM = "home";
    private static final String TAG_MANAGE_ORDER = "photos";
    private static final String TAG_MANAGE_PROFILE = "movies";
    private static final String TAG_BANk = "notifications";
    private static final String TAG_CHATBOX = "settings";
    private static final String TAG_FEEDBACK = "feedback";
    private static final String TAG_LOGOUT = "logout";
    public static String CURRENT_TAG = TAG_MANAGE_ITEM;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_pane_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
//        txtName = (TextView) navHeader.findViewById(R.id.name);
//        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
//        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
//        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_MANAGE_ITEM;
            loadHomeFragment();
        }
    }


    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                ManageItemFragment manageItemFragment = new ManageItemFragment(new ManageItemFragment.ClickListener() {
                    @Override
                    public void click() {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();

                        AddItemManagementFragment addItemManagementFragment = new AddItemManagementFragment();
                        transaction.replace(R.id.frame, addItemManagementFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
                return manageItemFragment;

            case 1:
                // photos
                OrderManagementFragment orderManagementFragment = new OrderManagementFragment();
                return orderManagementFragment;
            case 2:
                // movies fragment
                ChatBoxFragment chatBoxFragment = new ChatBoxFragment();
                return chatBoxFragment;
            case 3:
                // notifications fragment
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;

            case 4:
                // settings fragment
                BankPayment bankPayment = new BankPayment();
                return bankPayment;

            case 5:
                // notifications fragment
                FeedBackAdmin feedBackAdmin = new FeedBackAdmin();
                return feedBackAdmin;


            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_item:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_MANAGE_ITEM;
                        break;
                    case R.id.nav_order:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_MANAGE_ORDER;
                        break;
                    case R.id.nav_chat:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_CHATBOX;
                        break;
                    case R.id.nav_profile:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_MANAGE_PROFILE;
                        break;
                    case R.id.nav_account:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_BANk;
                        break;
                    case R.id.nav_feedback:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_FEEDBACK;
                        break;
                    case R.id.nav_logout:
                        startActivity(new Intent(AdminPanelActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_MANAGE_ITEM;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }
}