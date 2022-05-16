package ma.enset.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bplus,bmoins,betoile,bdiv,bc,begal,bpoint;
    private EditText resultat;
    private double numero;
    private boolean clicOperateur = false;
    private boolean update = false;
    private String operateur = "";
    private List list;
    private ArrayAdapter arrayAdapter;
    private double oldvalue;
    private double newvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0 = (Button) findViewById(R.id.button0);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);
        bplus = (Button) findViewById(R.id.buttonPlus);
        bmoins = (Button) findViewById(R.id.buttonMoins);
        betoile = (Button) findViewById(R.id.buttonMultiplier);
        bdiv = (Button) findViewById(R.id.buttonDivision);
        bc = (Button) findViewById(R.id.buttonC);
        begal = (Button) findViewById(R.id.buttonEgal);
        bpoint = (Button) findViewById(R.id.buttonPoint);
        resultat = (EditText) findViewById(R.id.EditText01);
        ListView listView = findViewById(R.id.listview);
        list = new ArrayList();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        bplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plusClick();
            }
        });
        bmoins.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moinsClick();
            }
        });
        betoile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                etoileClick();
            }
        });
        bdiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                divClick();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetClick();
            }
        });
        begal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                egalClick();
            }
        });
        bpoint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick(".");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("0");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("9");
            }
        });
    }
    public void chiffreClick(String str) {
        //String name = resultat.getText().toString();
        if(update){
            update = false;
        }else{
            if(!resultat.getText().equals("0"))
                str = resultat.getText() + str;
        }
        resultat.setText(str);
        newvalue = Double.valueOf(resultat.getText().toString()).doubleValue();
    }
    public void plusClick(){
        if(clicOperateur){
            calcul();
            resultat.setText(String.valueOf(numero));
        }else{
            numero = Double.valueOf(resultat.getText().toString()).doubleValue();
            clicOperateur = true;
        }
        oldvalue=Double.valueOf(resultat.getText().toString()).doubleValue();
        operateur = "+";
        update = true;
    }
    public void moinsClick(){
        if(clicOperateur){
            calcul();
            resultat.setText(String.valueOf(numero));
        }else{
            numero = Double.valueOf(resultat.getText().toString()).doubleValue();
            clicOperateur = true;
        }
        oldvalue=Double.valueOf(resultat.getText().toString()).doubleValue();
        operateur = "-";
        update = true;
    }
    //voici la méthode qui est  exécutée lorsqu'on clique sur le bouton *
    public void etoileClick(){
        if(clicOperateur){
            calcul();
            resultat.setText(String.valueOf(numero));
        }else{
            numero = Double.valueOf(resultat.getText().toString()).doubleValue();
            clicOperateur = true;
        }
        oldvalue=Double.valueOf(resultat.getText().toString()).doubleValue();
        operateur = "*";
        update = true;
    }
    public void divClick(){
        if(clicOperateur){
            calcul();
            resultat.setText(String.valueOf(numero));
        }else{
            numero = Double.valueOf(resultat.getText().toString()).doubleValue();
            clicOperateur = true;
        }
        oldvalue=Double.valueOf(resultat.getText().toString()).doubleValue();
        operateur = "/";
        update = true;
    }
    //voici la méthode qui est  exécutée lorsqu'on clique sur le bouton =
    public void egalClick(){
        calcul();
        list.add(oldvalue+" "+operateur+" "+newvalue+"="+String.valueOf(numero));
        arrayAdapter.notifyDataSetChanged();
        update = true;
        clicOperateur = false;
    }
    //voici la méthode qui est  exécutée lorsque l'on clique sur le bouton C
    public void resetClick(){
        clicOperateur = false;
        update = true;
        numero = 0;
        operateur = "";
        resultat.setText("");
    }
    // la méthode qui fait le calcul
    private void calcul(){
        if(operateur.equals("+")){
            numero = numero + Double.valueOf(resultat.getText().toString()).doubleValue();
            resultat.setText(String.valueOf(numero));
        }
        if(operateur.equals("-")){
            numero = numero - Double.valueOf(resultat.getText().toString()).doubleValue();
            resultat.setText(String.valueOf(numero));
        }
        if(operateur.equals("*")){
            numero = numero * Double.valueOf(resultat.getText().toString()).doubleValue();
            resultat.setText(String.valueOf(numero));
        }
        if(operateur.equals("/")){
            try{
                numero = numero / Double.valueOf(resultat.getText().toString()).doubleValue();
                resultat.setText(String.valueOf(numero));
            }catch(ArithmeticException e){
                resultat.setText("0");
            }
        }
    }
}