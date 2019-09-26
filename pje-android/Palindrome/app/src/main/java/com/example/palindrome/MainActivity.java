package com.example.palindrome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String PALINDROME = "palindrome";
    private static final String LANGUE = "langue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testPalindrome(View view){
        // Create an Intent to start the second activity
        Intent palindromeIntent = new Intent(this, Palind.class);

        EditText text = (EditText)  findViewById(R.id.editText);
        String palin ="";
        if(text.toString() != null){
            palin = text.getText().toString();
        }

        Button bLang = findViewById(R.id.buttonLanguage);
        String langue = bLang.getText().toString();

        palindromeIntent.putExtra(PALINDROME,palin);
        palindromeIntent.putExtra(LANGUE,langue);

        // Start the new activity.
        startActivity(palindromeIntent);
    }

    public void englishFrench(View view){
        Button bClear = findViewById(R.id.buttonClear);
        Button bSub = findViewById(R.id.buttonSubmit);
        Button bLang = findViewById(R.id.buttonLanguage);
        if(bLang.getText().equals("FR")){
            bClear.setText("Effacer");
            bSub.setText("Envoyer");
            bLang.setText("EN");
        }else {
            bClear.setText("Clean");
            bSub.setText("Submit");
            bLang.setText("FR");
        }
    }

    public void clear(View view){
        EditText text = findViewById(R.id.editText);
        text.setText("");
    }
}
