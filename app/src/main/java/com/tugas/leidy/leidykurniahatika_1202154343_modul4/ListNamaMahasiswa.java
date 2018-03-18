package com.tugas.leidy.leidykurniahatika_1202154343_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNamaMahasiswa extends AppCompatActivity {
    //Menginisialisasi array dan semua komponen yang akan digunakan
    private ListView mListView;
    private ProgressBar mProgressBar;
    private String[] mUsers = {
            "Achmad Taufik Rizki", "Aliyya Nur Ramdhania", "Anandika Nur Iman", "Annisa Ayu Wahdini Fatimah",
            "Ardi Tri Yunansyah", "Damar Auriga Daniswara", "Epifanio Juang Victorius", "Hakim Rizki Pratama",
            "Jaysyurahman", "Julian Dwi Nugraha", "Leidy Kurnia Hatika", "Miftah Fajar Asy'ari",
            "Muhammad Irfan Luthfi", "Mochamad Riski", "Mohammad Zakky Fauzan", "One Tika Suryati",
            "Tengku Maisha Shahrani"};

    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_nama_mahasiswa);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView); //Mengambil variabel dari id listView
        mListView.setVisibility(View.GONE); //List tidak akan ditampilkan sebelum button di klik
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //Inisialisasi semua komponen yang akan digunakan dan melakukan setAdapter terhadap ListView dengan menggunakan Array

    }

    public void MulaiCari(View view) { //method untuk memulai melakukan pencarian nama mahasiswa
        mAddItemToListView = new AddItemToListView(); //mengisi list nama pada class AddItemToListView
        mAddItemToListView.execute(); //Untuk eksekusi method ketika Button diklik
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter; //menginisialisasi array adapter
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaMahasiswa.this);

        //Deklarasi semua komponen yang akan digunakan

        @Override
        protected void onPreExecute() {

            //Method untuk melakukan eksekusi sebelum method onPostExecute dijalankan
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //for progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");  //judul progress dialog
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0); //membuat progress dimulai dari nol

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAddItemToListView.cancel(true); //Agar button cancel bisa di klik
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss();
                }
            });
            mProgressDialog.show(); //menampilkan progress dialog
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : mUsers) { //menjalankan proses di background dan memanggil array
                publishProgress(item);
                try {
                    Thread.sleep(100); //untuk foreground selama 100ms
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                }
            }
            return null;

            //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]); //memanggil adapter dan memulai array dengan value dimulai dari nol
            Integer current_status = (int) ((counter / (float) mUsers.length) * 100);
            mProgressBar.setProgress(current_status);
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;

            //Method ini digunakan untuk menghitung presentase progress dialog
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE); //untuk menampilkan progress bar
            mProgressDialog.dismiss(); //untuk menghilangkan progress dialog
            mListView.setVisibility(View.VISIBLE); // agar list ditampilkan

            //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}
