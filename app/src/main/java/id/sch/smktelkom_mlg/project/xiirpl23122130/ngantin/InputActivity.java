package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
