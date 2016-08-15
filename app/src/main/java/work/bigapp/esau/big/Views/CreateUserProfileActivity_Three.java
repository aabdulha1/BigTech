package work.bigapp.esau.big.Views;

import work.bigapp.esau.big.*;
import work.bigapp.esau.big.Utility.*;
import work.bigapp.esau.big.BusinessObjects.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CreateUserProfileActivity_Three extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_profile_3);

        //BindFields();
        //ConfigureView();
        //BindEvents();
    }

    public void ImgView_NavToCreateProfileStepThree_Click(View view)
    {
        Intent intent = new Intent(this, CreateUserProfileActivity_Two.class);
        startActivity(intent);
    }
}
