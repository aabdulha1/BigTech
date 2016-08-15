package work.bigapp.esau.big;

/**
 * Created by abdisalam on 7/26/16.
 */
public class AccountType extends android.support.v7.app.AppCompatActivity {
    private android.widget.EditText inputEmail, inputPassword;
    private android.widget.Button btnSignIn, btnCrew, btnIndividual;
    private android.widget.ProgressBar progressBar;
    private com.google.firebase.auth.FirebaseAuth auth;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounttype);

        //Get Firebase auth instance


        btnCrew = (android.widget.Button) findViewById(work.bigapp.esau.big.R.id.btn_crew);
        btnIndividual = (android.widget.Button) findViewById(R.id.btn_individual);


        btnIndividual.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new android.content.Intent(work.bigapp.esau.big.AccountType.this, work.bigapp.esau.big.SignupActivity.class));
            }
        });

    }
}


