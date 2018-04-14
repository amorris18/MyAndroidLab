package com.example.alicia.introandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity implements OnClickListener {

    //declare variables
    private RadioButton btnFah;
    private RadioButton btnCel;
    private RadioButton btnKel;
    private EditText txtInputTemp;
    private TextView lblOutputDegF;
    private TextView lblOutputDegC;
    private TextView lblOutputDegK;
    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //RadioGroup tempGroup = (RadioGroup) findViewById(R.id.tempGroup);
        //tempGroup.setOnCheckedChangeListener(this);

        btnConvert =(Button)  findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.mnuMain:
                startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                return true;
            case R.id.mnuExit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void displayToast(double temperature) {
        if(temperature>50)
            Toast.makeText(this, "Wow it's hot outside!", Toast.LENGTH_LONG).show();
        else if(temperature>20)
            Toast.makeText(this, "Nice weather we are having", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Brrrrr - it's cold out!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        //get the resources for radio buttons, text boxes, and labels
        btnFah = (RadioButton) findViewById(R.id.btnDegF);
        btnCel = (RadioButton) findViewById(R.id.btnDegC);
        btnKel = (RadioButton) findViewById(R.id.btnDegK);
        txtInputTemp = (EditText) findViewById(R.id.txtInputTemp);
        lblOutputDegF = (TextView) findViewById(R.id.lblOutputF);
        lblOutputDegC = (TextView) findViewById(R.id.lblOutputC);
        lblOutputDegK = (TextView) findViewById(R.id.lblOutputK);

        //convert the input temperature to double value
        double temp = Double.parseDouble(String.valueOf(txtInputTemp.getText()));
        double answer=0;

        //If the user chooses Fahrenheit, convert to Celsius and Kelvin and display in those text boxes
        if(btnFah.isChecked()){
            lblOutputDegF.setText(temp+" degrees F");
            lblOutputDegC.setText((Math.round((temp-32)*5/9*100.0)/100.0)+" degrees C");
            lblOutputDegK.setText((Math.round((temp+459.67)*5/9*100.0)/100.0)+" degrees K");
            answer = (temp-32)*5/9;
        }

        //if the user chooses Celsius, convert to Fahrenheit and Kelvin and display in the text boxes
        if(btnCel.isChecked()){
            lblOutputDegF.setText((Math.round(((temp*9)/5+32)*100.0)/100.0)+" degrees F");
            lblOutputDegC.setText(temp+" degrees C");
            lblOutputDegK.setText((Math.round((temp+273.15)*100.0)/100.0)+" degrees K");
            answer = temp;
        }

        //if the user chooses Kelvin, convert to Fahrenheit and Celsius and display in the text boxes
        if(btnKel.isChecked()){
            lblOutputDegF.setText((Math.round((temp*9/5-459.67)*100.0)/100.0)+" degrees F");
            lblOutputDegC.setText((Math.round((temp-273.15)*100.0)/100.0)+" degrees C");
            lblOutputDegK.setText(temp+" degrees K");
            answer = temp-273.15;
        }

        displayToast(answer);
    }
}
