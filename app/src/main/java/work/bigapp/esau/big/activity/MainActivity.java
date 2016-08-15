package work.bigapp.esau.big.activity;

public class MainActivity extends android.support.v7.app.AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{

    private android.widget.Button btnChangeEmail, btnChangePassword, btnSendResetEmail, btnRemoveUser,
            changeEmail, changePassword, sendEmail, remove, signOut;

    private android.widget.EditText oldEmail, newEmail, password, newPassword;
    private android.widget.ProgressBar progressBar;
    private com.google.firebase.auth.FirebaseAuth.AuthStateListener authListener;
    private com.google.firebase.auth.FirebaseAuth auth;

    private android.app.ProgressDialog mAuthProgressDialog;
    /* The login button for Facebook */

    private android.widget.ListView mDrawerList;
    private android.widget.ArrayAdapter<String> mAdapter;

    private com.firebase.client.Firebase mFirebaseRef;
    private com.firebase.client.AuthData mAuthData;


    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private android.support.v4.widget.DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    private android.support.v7.widget.Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(work.bigapp.esau.big.R.layout.activity_main);

        //tool-bar
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(work.bigapp.esau.big.R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(work.bigapp.esau.big.R.id.fragment_navigation_drawer);
        drawerFragment.setUp(work.bigapp.esau.big.R.id.fragment_navigation_drawer, (android.support.v4.widget.DrawerLayout) findViewById(work.bigapp.esau.big.R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

        com.google.firebase.storage.FirebaseStorage storage = com.google.firebase.storage.FirebaseStorage.getInstance();
        // Create a storage reference from our app
        com.google.firebase.storage.StorageReference storageRef = storage.getReferenceFromUrl("gs://bigtechnology-e531d.appspot.com");



        /*getSupportActionBar().setTitle(work.bigapp.esau.big.R.string.welcome_title);
        getSupportActionBar().setSubtitle(R.string.my_tb_subtitle);
        getSupportActionBar().setIcon(work.bigapp.esau.big.R.drawable.ic_action_name);*/
// Get access to the custom title view


//drawer


        // Display icon in the toolbar
       /* getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/


        //get firebase auth instance
        auth = com.google.firebase.auth.FirebaseAuth.getInstance();
        // file = android.net.Uri.fromFile(new java.io.File("path/to/mountains.jpg"));
        //get current user
        final com.google.firebase.auth.FirebaseUser user = com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser();

        authListener = new com.google.firebase.auth.FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@android.support.annotation.NonNull com.google.firebase.auth.FirebaseAuth firebaseAuth) {
                com.google.firebase.auth.FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new android.content.Intent(MainActivity.this, work.bigapp.esau.big.LoginActivity.class));
                    finish();
                }
            }
        };















        btnChangeEmail = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.change_email_button);
        btnChangePassword = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.change_password_button);
        btnSendResetEmail = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.sending_pass_reset_button);
        btnRemoveUser = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.remove_user_button);
        changeEmail = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.changeEmail);
        changePassword = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.changePass);
        sendEmail = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.send);
        remove = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.remove);
        signOut = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.sign_out);

        oldEmail = (android.widget.EditText) findViewById(work.bigapp.esau.big.R.id.old_email);
        newEmail = (android.widget.EditText) findViewById(work.bigapp.esau.big.R.id.new_email);
        password = (android.widget.EditText) findViewById(work.bigapp.esau.big.R.id.password);
        newPassword = (android.widget.EditText) findViewById(work.bigapp.esau.big.R.id.newPassword);

        oldEmail.setVisibility(android.view.View.GONE);
        newEmail.setVisibility(android.view.View.GONE);
        password.setVisibility(android.view.View.GONE);
        newPassword.setVisibility(android.view.View.GONE);
        changeEmail.setVisibility(android.view.View.GONE);
        changePassword.setVisibility(android.view.View.GONE);
        sendEmail.setVisibility(android.view.View.GONE);
        remove.setVisibility(android.view.View.GONE);

        progressBar = (android.widget.ProgressBar) findViewById(work.bigapp.esau.big.R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(android.view.View.GONE);
        }

        btnChangeEmail.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                oldEmail.setVisibility(android.view.View.GONE);
                newEmail.setVisibility(android.view.View.VISIBLE);
                password.setVisibility(android.view.View.GONE);
                newPassword.setVisibility(android.view.View.GONE);
                changeEmail.setVisibility(android.view.View.VISIBLE);
                changePassword.setVisibility(android.view.View.GONE);
                sendEmail.setVisibility(android.view.View.GONE);
                remove.setVisibility(android.view.View.GONE);
            }
        });

        changeEmail.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                progressBar.setVisibility(android.view.View.VISIBLE);
                if (user != null && !newEmail.getText().toString().trim().equals("")) {
                    user.updateEmail(newEmail.getText().toString().trim())
                            .addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        android.widget.Toast.makeText(MainActivity.this, "Email address is updated. Please sign in with new email id!", android.widget.Toast.LENGTH_LONG).show();
                                        signOut();
                                        progressBar.setVisibility(android.view.View.GONE);
                                    } else {
                                        android.widget.Toast.makeText(MainActivity.this, "Failed to update email!", android.widget.Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(android.view.View.GONE);
                                    }
                                }
                            });
                } else if (newEmail.getText().toString().trim().equals("")) {
                    newEmail.setError("Enter email");
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }
        });

        btnChangePassword.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                oldEmail.setVisibility(android.view.View.GONE);
                newEmail.setVisibility(android.view.View.GONE);
                password.setVisibility(android.view.View.GONE);
                newPassword.setVisibility(android.view.View.VISIBLE);
                changeEmail.setVisibility(android.view.View.GONE);
                changePassword.setVisibility(android.view.View.VISIBLE);
                sendEmail.setVisibility(android.view.View.GONE);
                remove.setVisibility(android.view.View.GONE);
            }
        });

        changePassword.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                progressBar.setVisibility(android.view.View.VISIBLE);
                if (user != null && !newPassword.getText().toString().trim().equals("")) {
                    if (newPassword.getText().toString().trim().length() < 6) {
                        newPassword.setError("Password too short, enter minimum 6 characters");
                        progressBar.setVisibility(android.view.View.GONE);
                    } else {
                        user.updatePassword(newPassword.getText().toString().trim())
                                .addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            android.widget.Toast.makeText(MainActivity.this, "Password is updated, sign in with new password!", android.widget.Toast.LENGTH_SHORT).show();
                                            signOut();
                                            progressBar.setVisibility(android.view.View.GONE);
                                        } else {
                                            android.widget.Toast.makeText(MainActivity.this, "Failed to update password!", android.widget.Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(android.view.View.GONE);
                                        }
                                    }
                                });
                    }
                } else if (newPassword.getText().toString().trim().equals("")) {
                    newPassword.setError("Enter password");
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }
        });

        btnSendResetEmail.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                oldEmail.setVisibility(android.view.View.VISIBLE);
                newEmail.setVisibility(android.view.View.GONE);
                password.setVisibility(android.view.View.GONE);
                newPassword.setVisibility(android.view.View.GONE);
                changeEmail.setVisibility(android.view.View.GONE);
                changePassword.setVisibility(android.view.View.GONE);
                sendEmail.setVisibility(android.view.View.VISIBLE);
                remove.setVisibility(android.view.View.GONE);
            }
        });

        sendEmail.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                progressBar.setVisibility(android.view.View.VISIBLE);
                if (!oldEmail.getText().toString().trim().equals("")) {
                    auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
                            .addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        android.widget.Toast.makeText(MainActivity.this, "Reset password email is sent!", android.widget.Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(android.view.View.GONE);
                                    } else {
                                        android.widget.Toast.makeText(MainActivity.this, "Failed to send reset email!", android.widget.Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(android.view.View.GONE);
                                    }
                                }
                            });
                } else {
                    oldEmail.setError("Enter email");
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }
        });

        btnRemoveUser.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                progressBar.setVisibility(android.view.View.VISIBLE);
                if (user != null) {
                    user.delete()
                            .addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        android.widget.Toast.makeText(MainActivity.this, "Your profile is deleted:( Create a account now!", android.widget.Toast.LENGTH_SHORT).show();
                                        startActivity(new android.content.Intent(MainActivity.this, work.bigapp.esau.big.SignupActivity.class));
                                        finish();
                                        progressBar.setVisibility(android.view.View.GONE);
                                    } else {
                                        android.widget.Toast.makeText(MainActivity.this, "Failed to delete your account!", android.widget.Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(android.view.View.GONE);
                                    }
                                }
                            });
                }
            }
        });

        signOut.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                signOut();
            }
        });

    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(work.bigapp.esau.big.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == work.bigapp.esau.big.R.id.action_settings) {
            return true;
        }

        if(id == work.bigapp.esau.big.R.id.action_search){
            android.widget.Toast.makeText(getApplicationContext(), "Search action is selected!", android.widget.Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(android.view.View view, int position) {
        displayView(position);
    }
    private void displayView(int position) {
        android.support.v4.app.Fragment fragment = null;
        String title = getString(work.bigapp.esau.big.R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(work.bigapp.esau.big.R.string.title_home);
                break;
            case 1:
                fragment = new FriendsFragment();
                title = getString(work.bigapp.esau.big.R.string.title_friends);
                break;
            case 2:
                fragment = new MessagesFragment();
                title = getString(work.bigapp.esau.big.R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(work.bigapp.esau.big.R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
    }









