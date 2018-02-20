package com.example.daniel.lightalive;

import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Daniel on 30/01/2018.
 *
 * Classe que guarda informações da lâmpada.
 *
 * Cada lâmpada tem seu próprio id, nome, botão e texto.
 */

public class Lamp {
    private int id;
    private String nome;

    public Switch switchButton;
    public TextView switchText;


    public Lamp(int id) {
        this.id = id;
        nome = String.valueOf(id);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
