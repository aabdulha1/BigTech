package work.bigapp.esau.big.Views;

import work.bigapp.esau.big.*;
import work.bigapp.esau.big.Utility.*;
import work.bigapp.esau.big.BusinessObjects.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.util.Patterns;

public class CreateUserProfileActivity_One extends AppCompatActivity
{
    //region Members

    Toolbar ActionBar;

    EditText EdtTxt_FirstName;
    EditText EdtTxt_LastName;
    EditText EdtTxt_CompanyName;
    EditText EdtTxt_ZipCode;
    EditText EdtTxt_Phone;
    EditText EdtTxt_Email;

    ImageView ImgView_NavToLoginScreen;
    ImageView ImgView_NavToCreateProfileStepTwo;

    //endregion

    //region Constructor and Initialization

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_profile_1);

        BindFields();
        ConfigureView();
        BindEvents();
    }

    // Grab references
    private void BindFields()
    {
        EdtTxt_FirstName = (EditText) findViewById(R.id.EdtTxt_FirstName);
        EdtTxt_LastName = (EditText) findViewById(R.id.EdtTxt_LastName);
        EdtTxt_CompanyName = (EditText) findViewById(R.id.EdtTxt_CompanyName);
        EdtTxt_ZipCode = (EditText) findViewById(R.id.EdtTxt_ZipCode);
        EdtTxt_Phone = (EditText) findViewById(R.id.EdtTxt_Phone);
        EdtTxt_Email = (EditText) findViewById(R.id.EdtTxt_Email);

        ImgView_NavToLoginScreen = (ImageView) findViewById(R.id.ImgView_NavToLoginScreen);
        ImgView_NavToCreateProfileStepTwo = (ImageView) findViewById(R.id.ImgView_NavToCreateProfileStepTwo);
    }

    private void ConfigureView()
    {
        // Set the Toolbar as the new Actionbar
        Toolbar NewActionBar = (Toolbar) findViewById(R.id.Toolbar_Main);
        setSupportActionBar(NewActionBar);

        // Set Hints
        EdtTxt_FirstName.setHint(EdtTxt_FirstName.getHint() + " *");
        EdtTxt_LastName.setHint(EdtTxt_LastName.getHint() + " *");
        EdtTxt_CompanyName.setHint(EdtTxt_CompanyName.getHint() + " (Optional)");
        EdtTxt_ZipCode.setHint(EdtTxt_ZipCode.getHint() + " *");
        EdtTxt_Phone.setHint(EdtTxt_Phone.getHint() + " *");
        EdtTxt_Email.setHint(EdtTxt_Email.getHint() + " *");

        // region Populate Registration Data

        if (User.GetInstance().NewUser)
        {
            if (!InputValidator.GetInstance().IsNullOrEmpty(User.GetInstance().FirstName))
            {
                EdtTxt_FirstName.setText(User.GetInstance().FirstName);
            }

            if (!InputValidator.GetInstance().IsNullOrEmpty(User.GetInstance().LastName))
            {
                EdtTxt_LastName.setText(User.GetInstance().LastName);
            }

            if (!InputValidator.GetInstance().IsNullOrEmpty(User.GetInstance().CompanyName))
            {
                EdtTxt_CompanyName.setText(User.GetInstance().CompanyName);
            }

            if (!InputValidator.GetInstance().IsNullOrEmpty(User.GetInstance().ZipCode))
            {
                EdtTxt_ZipCode.setText(User.GetInstance().ZipCode);
            }

            if (!InputValidator.GetInstance().IsNullOrEmpty(User.GetInstance().Phone))
            {
                EdtTxt_Phone.setText(User.GetInstance().Phone);
            }

            if (!InputValidator.GetInstance().IsNullOrEmpty(User.GetInstance().Email))
            {
                EdtTxt_Email.setText(User.GetInstance().Email);
            }
        }

        //endregion Populate Registration Data
    }

    private void BindEvents()
    {
        //region EditText FirstName Events

        EdtTxt_FirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                User.GetInstance().FirstName = EdtTxt_FirstName.getText().toString();
                Validate();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        //endregion EditText FirstName Events

        //region EditText LastName Events

        EdtTxt_LastName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                User.GetInstance().LastName = EdtTxt_LastName.getText().toString();
                Validate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //endregion EditText LastName Events

        //region EditText CompanyName Events

        EdtTxt_CompanyName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                User.GetInstance().CompanyName = EdtTxt_CompanyName.getText().toString();
                Validate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //endregion EditText CompanyName Events

        //region EditText ZipCode Events

        EdtTxt_ZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                User.GetInstance().ZipCode = EdtTxt_ZipCode.getText().toString();
                Validate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //endregion EditText ZipCode Events

        //region EditText Phone Events

        EdtTxt_Phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                User.GetInstance().Phone = EdtTxt_Phone.getText().toString();
                Validate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //endregion EditText Phone Events

        // region EditText Email Events

        EdtTxt_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                CharSequence EmailInput = EdtTxt_Email.getText().toString();
                if (Patterns.EMAIL_ADDRESS.matcher(EmailInput).matches())
                {
                    User.GetInstance().Email = EmailInput.toString();
                    EdtTxt_Email.setSelectAllOnFocus(false);
                }
                else
                {
                    EdtTxt_Email.setSelectAllOnFocus(true);
                    User.GetInstance().Email = null;
                }

                Validate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //endregion EditText Email Events

        // region ImageView NavToLoginScreen Events

        ImgView_NavToLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                User.Clear();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // endregion ImageView NavToLoginScreen Events

        //region ImageView NavToCreateProfileStepTwo Events

        ImgView_NavToCreateProfileStepTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), CreateUserProfileActivity_Two.class);
                startActivity(intent);
            }
        });

        //endregion ImageView NavToCreateProfileStepTwo Events
    }

    //endregion Constructor and Initialization

    //region Methods

    private void Validate()
    {
        String[] InputArray = new String[]{User.GetInstance().FirstName, User.GetInstance().LastName, User.GetInstance().ZipCode, User.GetInstance().Phone, User.GetInstance().Email };

        for (String Item : InputArray){
            if (InputValidator.GetInstance().IsNullOrEmpty(Item))
            {
                ImgView_NavToCreateProfileStepTwo.setImageDrawable(getResources().getDrawable(R.drawable.nav_arrow_right_inactive));
                ImgView_NavToCreateProfileStepTwo.setClickable(false);
                return;
            }
        }

        ImgView_NavToCreateProfileStepTwo.setImageDrawable(getResources().getDrawable(R.drawable.nav_arrow_right_active));
        ImgView_NavToCreateProfileStepTwo.setClickable(true);
    }

    //endregion Methods
}
