package work.bigapp.esau.big;
import work.bigapp.esau.big.Views.*;

/**
 * Created by abdisalam on 7/4/16.
 */

public class LoginActivity extends android.support.v7.app.AppCompatActivity {

    private android.widget.EditText inputEmail, inputPassword;
    private com.google.firebase.auth.FirebaseAuth auth;
    private android.widget.ProgressBar progressBar;
    private android.widget.Button btnSignup, btnLogin, btnReset;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = com.google.firebase.auth.FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new android.content.Intent(LoginActivity.this, work.bigapp.esau.big.activity.MainActivity.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputEmail = (android.widget.EditText) findViewById(R.id.email);
        inputPassword = (android.widget.EditText) findViewById(R.id.password);
        progressBar = (android.widget.ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (android.widget.Button) findViewById(R.id.btn_signup);
        btnLogin = (android.widget.Button) findViewById(R.id.btn_login);
        btnReset = (android.widget.Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = com.google.firebase.auth.FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new android.content.Intent(v.getContext(), CreateUserProfileActivity_One.class));
            }
        });

        btnReset.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new android.content.Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (android.text.TextUtils.isEmpty(email)) {
                    android.widget.Toast.makeText(getApplicationContext(), "Enter email address!", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                if (android.text.TextUtils.isEmpty(password)) {
                    android.widget.Toast.makeText(getApplicationContext(), "Enter password!", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(android.view.View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(android.view.View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        android.widget.Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), android.widget.Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    android.content.Intent intent = new android.content.Intent(LoginActivity.this, work.bigapp.esau.big.activity.MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}