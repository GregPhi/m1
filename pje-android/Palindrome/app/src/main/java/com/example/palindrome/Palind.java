package com.example.palindrome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Palind extends AppCompatActivity {

    private static final String PALINDROME = "palindrome";
    private static final String LANGUE = "langue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palind);
        showPalindrome();
    }

    private void showPalindrome(){
        TextView text = (TextView) findViewById(R.id.textView);
        String palin = getIntent().getStringExtra(PALINDROME);
        String l = getIntent().getStringExtra(LANGUE);
        if(l.equals("EN")){
            if(palindrome(palin)){
                text.setText(palin+" est un palindrome !");
            }else{
                text.setText(palin+" n'est pas un palindrome !");
            }
        }else{
            if(palindrome(palin)){
                text.setText(palin+" is a palindrome !");
            }else{
                text.setText(palin+" isn't a palindrome !");
            }
        }
    }

    public boolean palindrome(String palin) {
        boolean isP = new StringBuffer(palin).reverse().toString().equals(palin);
        return isP;
    }
}
