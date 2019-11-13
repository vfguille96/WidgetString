package com.vfguille.widgetstring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spNumEmpl;
    private AutoCompleteTextView autoCompleteTextView;
    private WebView webView;
    private RadioGroup radioGroup;
    private View inclEmpr;
    private View inclPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeComponents();
        initializeActMonth();
        initializeWebView();
        initializeSpinner();
        initializeRgTypeClient();
    }

    private void InitializeComponents() {
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        webView = findViewById(R.id.wvBussiness);
        spNumEmpl = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        inclEmpr = findViewById(R.id.inclBus);
        inclPart = findViewById(R.id.inclPar);
    }

    /*
    Realiza una acción cuando se selecciona un Radio Button.
     */
    private void initializeRgTypeClient() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbParticular:
                        inclEmpr.setVisibility(View.GONE);
                        inclPart.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbEmpresa:
                        inclEmpr.setVisibility(View.VISIBLE);
                        inclPart.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private void initializeSpinner() {
        spNumEmpl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String message;
                if (spNumEmpl.getSelectedItem().equals("0"))
                    message = getString(R.string.zeroE);
                    //Toast.makeText(adapterView.getContext(), "Seleccionado: " + spNumEmpl.getItemAtPosition(i).toString() + " empleado", Toast.LENGTH_LONG).show();
                else
                    message = spNumEmpl.getResources().getQuantityString(R.plurals.num_empl_pl, Integer.parseInt(spNumEmpl.getSelectedItem().toString()), Integer.parseInt(spNumEmpl.getSelectedItem().toString()));
                Toast.makeText(adapterView.getContext(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
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
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, months);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}