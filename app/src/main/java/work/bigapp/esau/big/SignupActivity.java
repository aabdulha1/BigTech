package work.bigapp.esau.big;

/**
 * Created by abdisalam on 7/4/16.
 */

public class SignupActivity extends android.support.v7.app.AppCompatActivity {

    private android.widget.EditText inputEmail, inputPassword;
    private android.widget.Button btnSignIn, btnSignUp, btnResetPassword;
    private android.widget.ProgressBar progressBar;
    private com.google.firebase.auth.FirebaseAuth auth;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = com.google.firebase.auth.FirebaseAuth.getInstance();

        btnSignIn = (android.widget.Button) findViewById(R.id.sign_in_button);
        btnSignUp = (android.widget.Button) findViewById(R.id.sign_up_button);
        inputEmail = (android.widget.EditText) findViewById(R.id.email);
        inputPassword = (android.widget.EditText) findViewById(R.id.password);
        progressBar = (android.widget.ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (android.widget.Button) findViewById(R.id.btn_reset_password);


        btnResetPassword.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new android.content.Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new android.content.Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (android.text.TextUtils.isEmpty(email)) {
                    android.widget.Toast.makeText(getApplicationContext(), "Enter email address!", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                if (android.text.TextUtils.isEmpty(password)) {
                    android.widget.Toast.makeText(getApplicationContext(), "Enter password!", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    android.widget.Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(android.view.View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> task) {
                                android.widget.Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), android.widget.Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(android.view.View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    android.widget.Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            android.widget.Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new android.content.Intent(SignupActivity.this, work.bigapp.esau.big.activity.MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(android.view.View.GONE);
    }
}
