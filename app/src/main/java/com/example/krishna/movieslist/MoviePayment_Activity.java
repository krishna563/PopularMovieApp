package com.example.krishna.movieslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MoviePayment_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviepayment);
        Button btn_amount=(Button)findViewById(R.id.Btn_pay);

        btn_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoviePayment_Activity.this, Payment_Activity.class);
                startActivity(intent);
            }
        });
    }

}
