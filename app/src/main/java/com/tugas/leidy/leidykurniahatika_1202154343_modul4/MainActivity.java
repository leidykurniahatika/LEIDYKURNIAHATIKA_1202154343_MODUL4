package com.tugas.leidy.leidykurniahatika_1202154343_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void List_Nama(View view) {
        Intent intent = new Intent(MainActivity.this, ListNamaMahasiswa.class); //intent untuk mengarahkan ke list nama mahasiswa
        startActivity(intent);
    }

    public void Cari_Gambar(View view) {
        Intent intent = new Intent(MainActivity.this, CariGambar.class); //inten untuk mengarahkan ke pencarian gambar
        startActivity(intent);
    }
}
