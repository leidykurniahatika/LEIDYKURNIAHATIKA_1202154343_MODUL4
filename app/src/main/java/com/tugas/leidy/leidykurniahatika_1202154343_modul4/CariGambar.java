package com.tugas.leidy.leidykurniahatika_1202154343_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class CariGambar extends AppCompatActivity {

    //menginisialisasi semua variabel
    private EditText editCari;
    private Button buttonCari;
    private ImageView viewCari;
    private ProgressDialog progressDialog;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cari_gambar);

        editCari = (EditText) findViewById(R.id.edit_cari_gambar);
        buttonCari = (Button) findViewById(R.id.button_klik);
        viewCari = (ImageView) findViewById(R.id.view_gambar);

        //Inisialisasi semua komponen yang akan digunakan dan mengambil variabel dari id
    }

    public void CariGambar(View view) {
        text = editCari.getText().toString(); //Mengubah EditText ke dalam bentuk String
        if (text.isEmpty()) {
            Toast.makeText(this, "Masukkan URL gambar terlebih dahulu", Toast.LENGTH_LONG).show();
            //Jika EditText kosong akan memunculkan Toast
        } else {
            new DownloadGambar().execute(text);
            //Jika EditText berisi String maka akan di eksekusi
        }
    }

    private class DownloadGambar extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() { //method sebelum eksekusi
            super.onPreExecute();
            progressDialog = new ProgressDialog(CariGambar.this);
            progressDialog.setTitle("Cari Gambar");
            progressDialog.setMessage("Loading Gambar");
            progressDialog.setIndeterminate(false);
            progressDialog.show(); //menampilkan alert dialog

            //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage()); //untuk menampilkan pesan pada logcat jika terdapat error
                e.printStackTrace();
            }
            return bimage; //untuk mengembalikan variabel bimage

            //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            viewCari.setImageBitmap(bitmap);
            progressDialog.dismiss(); //menghilangkan alert dialog

            //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}
