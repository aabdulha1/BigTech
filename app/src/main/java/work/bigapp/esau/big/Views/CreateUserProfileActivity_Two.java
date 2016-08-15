package work.bigapp.esau.big.Views;

import work.bigapp.esau.big.*;
import work.bigapp.esau.big.Utility.*;
import work.bigapp.esau.big.BusinessObjects.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CreateUserProfileActivity_Two extends AppCompatActivity
{
    //region Constructor and Initialization

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_profile_2);

        BindFields();
        ConfigureView();
        BindEvents();
    }

    private void BindFields(){ }

    private void ConfigureView(){ }

    private void BindEvents(){ }

    //endregion Constructor and Initialization

    public void ImgView_NavToCreateProfileStepOne_Click(View view)
    {
        Intent intent = new Intent(this, CreateUserProfileActivity_One.class);
        startActivity(intent);
    }

    public void ImgView_NavToCreateProfileStepThree_Click(View view)
    {
        Intent intent = new Intent(this, CreateUserProfileActivity_Three.class);
        startActivity(intent);
    }
}
