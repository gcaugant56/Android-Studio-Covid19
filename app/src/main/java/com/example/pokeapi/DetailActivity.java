package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    private Button breturn;
    private TextView totalCase;
    private TextView newCase;
    private TextView totalDead;
    private TextView newDead;
    private TextView totalRecovered;
    private TextView newRecovered;
    private TextView Pays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);
        Intent intent = getIntent();
        totalCase = (TextView) findViewById(R.id.nTotalCase);
        totalRecovered = (TextView) findViewById(R.id.nTotalRecovered);
        totalDead = (TextView) findViewById(R.id.nTotalDeath);
        newCase = (TextView) findViewById(R.id.nNewCase);
        newRecovered = (TextView) findViewById(R.id.nNewRecovered);
        newDead = (TextView) findViewById(R.id.nNewDeath);
        Pays = (TextView) findViewById(R.id.pays);

        totalCase.setText(String.valueOf(intent.getIntExtra("TotalCase",0)));
        newCase.setText(String.valueOf(intent.getIntExtra("NewCase",0)));
        totalDead.setText(String.valueOf(intent.getIntExtra("TotalDead",0)));
        newDead.setText(String.valueOf(intent.getIntExtra("NewDead",0)));
        totalRecovered.setText(String.valueOf(intent.getIntExtra("TotalRecovered",0)));
        newRecovered.setText(String.valueOf(intent.getIntExtra("NewRecovered",0)));
        Pays.setText(intent.getStringExtra("Pays"));


        breturn = (Button) findViewById(R.id.breturn);

        breturn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
