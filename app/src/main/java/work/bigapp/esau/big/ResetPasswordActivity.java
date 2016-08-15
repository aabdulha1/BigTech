package work.bigapp.esau.big;

/**
 * Created by abdisalam on 7/4/16.
 */

public class ResetPasswordActivity extends android.support.v7.app.AppCompatActivity {

    private android.widget.EditText inputEmail;
    private android.widget.Button btnReset, btnBack;
    private com.google.firebase.auth.FirebaseAuth auth;
    private android.widget.ProgressBar progressBar;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = (android.widget.EditText) findViewById(R.id.email);
        btnReset = (android.widget.Button) findViewById(R.id.btn_reset_password);
        btnBack = (android.widget.Button) findViewById(R.id.btn_back);
        progressBar = (android.widget.ProgressBar) findViewById(R.id.progressBar);

        auth = com.google.firebase.auth.FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                String email = inputEmail.getText().toString().trim();

                if (android.text.TextUtils.isEmpty(email)) {
                    android.widget.Toast.makeText(getApplication(), "Enter your registered email id", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(android.view.View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<Void> task) {
                                if (task.isSuccessful()) {
                                    android.widget.Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", android.widget.Toast.LENGTH_SHORT).show();
                                } else {
                                    android.widget.Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", android.widget.Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(android.view.View.GONE);
                            }
                        });
            }
        });
    }

}