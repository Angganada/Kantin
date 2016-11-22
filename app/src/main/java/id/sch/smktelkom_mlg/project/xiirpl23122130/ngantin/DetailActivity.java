package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.model.Resep;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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

        Resep resep = (Resep) getIntent().getSerializableExtra(MainActivity.RESEP);
        setTitle(resep.judul);
        TextView tvBahan = (TextView) findViewById(R.id.bahan);
        tvBahan.setText(resep.bahan);
        TextView tvLangkah = (TextView) findViewById(R.id.langkah);
        tvLangkah.setText(resep.langkah);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
