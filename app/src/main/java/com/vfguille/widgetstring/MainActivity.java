package com.vfguille.widgetstring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spNumEmpl;
    private AutoCompleteTextView autoCompleteTextView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        webView = findViewById(R.id.wvBussiness);
        spNumEmpl = findViewById(R.id.spinner);
        initializeActMonth();
        initializeWebView();
        initializeSpinner();
    }

    private void initializeSpinner() {
        spNumEmpl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(adapterView.getContext(), "Seleccionado: " + spNumEmpl.getItemAtPosition(i).toString() + " empleado", Toast.LENGTH_LONG).show();
                String message = spNumEmpl.getResources().getQuantityString(R.plurals.num_empl_pl, Integer.parseInt(spNumEmpl.getSelectedItem().toString()));
                Toast.makeText(adapterView.getContext(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initializeWebView() {
        webView.loadData(getResources().getString(R.string.wbInfo), "text/html", "UTF-8");
    }

    /*
    Inicializa el componente y eventos
     */
    private void initializeActMonth() {
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,  months);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}
