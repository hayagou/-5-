package com.hayagou.hjkim_order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Menu a, b, c;
    HashMap<Menu, Integer> menuMap;
    HashMap<Menu, Integer> saleMap;
    Button btnA, btnB, btnC;
    TextView txtA, txtB, txtC, txtRun, txtConfirm, txtSale;
    DecimalFormat decimalFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        InitializeView();
        SetListener();
    }


    private void init() {
        a = new Menu("돼지국밥", 5000);
        b = new Menu("라면", 2000);
        c = new Menu("낙지볶음", 8000);
        menuMap = new HashMap<Menu, Integer>();
        saleMap = new HashMap<Menu, Integer>();
        menuMap.put(a, 0);
        menuMap.put(b, 0);
        menuMap.put(c, 0);
        saleMap.put(a, 0);
        saleMap.put(b, 0);
        saleMap.put(c, 0);
        decimalFormat = new DecimalFormat("###,###");
    }

    public void InitializeView() {
        btnA = findViewById(R.id.numA);
        btnB = findViewById(R.id.numB);
        btnC = findViewById(R.id.numC);
        txtA = findViewById(R.id.priceA);
        txtB = findViewById(R.id.priceB);
        txtC = findViewById(R.id.priceC);
        txtRun = findViewById(R.id.run);
        txtConfirm = findViewById(R.id.confirm);
        txtSale = findViewById(R.id.sale);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.increaseA:
                if(menuMap.get(a)==5){
                    Toast.makeText(getApplicationContext(),"Sorry, Max Number is 5", Toast.LENGTH_SHORT).show();
                    break;
                }
                menuMap.put(a, menuMap.get(a)+1);
                break;
            case R.id.increaseB:
                if(menuMap.get(b)==5){
                    Toast.makeText(getApplicationContext(),"Sorry, Max Number is 5", Toast.LENGTH_SHORT).show();
                    break;
                }
                menuMap.put(b, menuMap.get(b)+1);
                break;
            case R.id.increaseC:
                if(menuMap.get(c)==5){
                    Toast.makeText(getApplicationContext(),"Sorry, Max Number is 5", Toast.LENGTH_SHORT).show();
                    break;
                }
                menuMap.put(c, menuMap.get(c)+1);
                break;
            case R.id.reduceA:
                if(menuMap.get(a)==0){
                    Toast.makeText(getApplicationContext(),"Sorry, Min Number is 0", Toast.LENGTH_SHORT).show();
                    break;
                }
                menuMap.put(a, menuMap.get(a)-1);
                break;
            case R.id.reduceB:
                if(menuMap.get(b)==0){
                    Toast.makeText(getApplicationContext(),"Sorry, Min Number is 0", Toast.LENGTH_SHORT).show();
                    break;
                }
                menuMap.put(b, menuMap.get(b)-1);
                break;
            case R.id.reduceC:
                if(menuMap.get(c)==0){
                    Toast.makeText(getApplicationContext(),"Sorry, Min Number is 0", Toast.LENGTH_SHORT).show();
                    break;
                }
                menuMap.put(c, menuMap.get(c)-1);
                break;
            case R.id.run:
                run();
                break;
        }
        reView();
    }

    public void reView(){
        btnA.setText(Integer.toString(menuMap.get(a)));
        txtA.setText(decimalFormat.format(menuMap.get(a)*a.getPrice()));
        btnB.setText(Integer.toString(menuMap.get(b)));
        txtB.setText(decimalFormat.format(menuMap.get(b)*b.getPrice()));
        btnC.setText(Integer.toString(menuMap.get(c)));
        txtC.setText(decimalFormat.format(menuMap.get(c)*c.getPrice()));

    }
    public void SetListener() {
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        txtA.setOnClickListener(this);
        txtB.setOnClickListener(this);
        txtC.setOnClickListener(this);
        txtRun.setOnClickListener(this);
    }

    public void run(){
        txtConfirm.setText(a.getName() +"  " +  Integer.toString(menuMap.get(a))+"개   " + decimalFormat.format(menuMap.get(a)*a.getPrice())+"원\n" +
                b.getName() +"  " +  Integer.toString(menuMap.get(b))+"개   " + decimalFormat.format(menuMap.get(b)*b.getPrice())+"원\n" +
                c.getName() +"  " +  Integer.toString(menuMap.get(c))+"개   " + decimalFormat.format(menuMap.get(c)*c.getPrice())+"원\n"
               );
        saleMap.put(a,saleMap.get(a)+menuMap.get(a));
        saleMap.put(b,saleMap.get(b)+menuMap.get(b));
        saleMap.put(c,saleMap.get(c)+menuMap.get(c));
        txtSale.setText(a.getName()+"   " + saleMap.get(a) + "개 \n" + b.getName()+"   " + saleMap.get(b) + "개 \n" +c.getName()+"   " + saleMap.get(c) + "개 \n"
                + "총계 : " + decimalFormat.format(a.getPrice()*saleMap.get(a)+b.getPrice()*saleMap.get(b)+c.getPrice()*saleMap.get(c))+"원");
        menuMap.put(a, 0);
        menuMap.put(b, 0);
        menuMap.put(c, 0);
        reView();
    }

}
