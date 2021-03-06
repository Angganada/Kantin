package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.model.Resep;

public class InputActivity extends AppCompatActivity {
    EditText etJudul;
    EditText etDeskripsi;
    EditText etBahan;
    EditText etLangkah;
    Resep resep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etJudul = (EditText) findViewById(R.id.editTextNama);
        etDeskripsi = (EditText) findViewById(R.id.editTextDesc);
        etBahan = (EditText) findViewById(R.id.editTextBahan);
        etLangkah = (EditText) findViewById(R.id.editTextLangkah);

        findViewById(R.id.buttonSimpan).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doSave();
                    }
                }
        );

        resep = (Resep) getIntent().getSerializableExtra(MainActivity.RESEP);
        if (resep != null) {
            setTitle("EDIT " + resep.judul);
            fillData();
        } else {
            setTitle("New Resep");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillData() {
        etJudul.setText(resep.judul);
        etDeskripsi.setText(resep.deskripsi);
        etBahan.setText(resep.bahan);
        etLangkah.setText(resep.langkah);
    }

    private void doSave() {
        String judul = etJudul.getText().toString();
        String desc = etDeskripsi.getText().toString();
        String bahan = etBahan.getText().toString();
        String langkah = etLangkah.getText().toString();

        if (isValid(judul, bahan, langkah)) {
            resep = new Resep(judul, desc, bahan, langkah);
            Intent intent = new Intent();
            intent.putExtra(MainActivity.RESEP, resep);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean isValid(String judul, String bahan, String langkah) {
        boolean valid = true;

        if (judul.isEmpty()) { etJudul.setError("Harus diisi"); valid = false; }
        if (bahan.isEmpty()) { etBahan.setError("Harus diisi"); valid = false; }
        if (langkah.isEmpty()) { etLangkah.setError("Harus diisi"); valid = false; }
        return valid;
    }
}
