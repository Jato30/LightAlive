package com.example.daniel.lightalive;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Lamp> arrayLampadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // TODO: Recebe a quantidade de lampadas e as cria
        for(int i = 0; i < 1; i++) {
            arrayLampadas.add(new Lamp(i));

            // Set informações default
            arrayLampadas.get(i).switchButton = (Switch) findViewById(R.id.switch1);
            arrayLampadas.get(i).switchText = (TextView) findViewById(R.id.textView);
            arrayLampadas.get(i).switchText.setText("\"" + arrayLampadas.get(i).getNome() + "\" está desligada.");

        }


        // Alterar nome - longClick
        for(int i = 0; i < arrayLampadas.size(); i++) {
            arrayLampadas.get(i).switchButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    LampSettings(arrayLampadas.get(0));

                    return false;
                }
            });

            arrayLampadas.get(i).switchText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    LampSettings(arrayLampadas.get(0));

                    return false;
                }
            });

        }
    }


    private void LampSettings(final Lamp lampada){
        final EditText campoNome = new EditText(this);
        campoNome.setText(lampada.getNome());
        campoNome.setHint("Nome da lâmpada " + lampada.getNome());
        // 1. Instantiate an AlertDialog.Builder with its constructor
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle("Editar lâmpada");
        builder.setMessage("Lâmpada: " + lampada.getNome());
        builder.setView(campoNome);


// Add the buttons
        builder.setNegativeButton("Restaurar padrão", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                campoNome.setText(String.valueOf(lampada.getId()));
                builder.setView(campoNome);


                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Deseja restaurar as definições padrão para esta lâmpada?")
                        .setCancelable(false)
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayLampadas.get(lampada.getId()).setNome(String.valueOf(lampada.getId()));
                                // TODO: Salvar novas informações


                                Toast.makeText(MainActivity.this, "Alterações aplicadas com sucesso.", Toast.LENGTH_LONG).show();
                            }
                        }).show();


            }
        });
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Tem certeza que deseja alterar estas informações?")
                        .setCancelable(false)
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){


                                String novoNome = campoNome.getText().toString();
                                arrayLampadas.get(lampada.getId()).setNome(novoNome);
                                // TODO: Salvar novas informações


                                Toast.makeText(MainActivity.this, "Lâmpada \"" + lampada.getNome() + "\" atualizada com sucesso.", Toast.LENGTH_LONG).show();

                            }
                        }).show();
            }
        });
        builder.setNeutralButton("Cancelar", null);


// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public void ChangeState(View view){
        boolean isChecked = ((Switch)view).isChecked();

        if(isChecked){
            arrayLampadas.get(0).switchText.setText("\"" + arrayLampadas.get(0).getNome() + "\" está ligada");
            Toast.makeText(MainActivity.this, "\"" + arrayLampadas.get(0).getNome() + "\" ligada.", Toast.LENGTH_LONG).show();
        }
        else {
            arrayLampadas.get(0).switchText.setText("\"" + arrayLampadas.get(0).getNome() + "\" está desligada");
            Toast.makeText(MainActivity.this, "\"" + arrayLampadas.get(0).getNome() + "\" desligada.", Toast.LENGTH_LONG).show();
        }
    }
}
