package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.adapter.ResepAdapter;
import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.model.Resep;

public class MainActivity extends AppCompatActivity implements ResepAdapter.IResepAdapter {
    public static final String RESEP = "resep";
    private static final int REQUEST_CODE_ADD = 88;
    private static final int REQUEST_CODE_EDIT = 99;
    ArrayList<Resep> mList = new ArrayList<>();
    ResepAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    goAdd();
                }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ResepAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);

        fillData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            Resep resep = (Resep) data.getSerializableExtra(RESEP);
            mList.add(resep);
            mAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            Resep resep = (Resep) data.getSerializableExtra(RESEP);
            mList.remove(itemPos);
            mList.add(itemPos, resep);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void goAdd() {
        startActivityForResult(new Intent(this, InputActivity.class), REQUEST_CODE_ADD);
    }

    private void fillData() {
        Resources resources = getResources();
        String [] arJudul = resources.getStringArray(R.array.judul);
        String [] arDeskripsi = resources.getStringArray(R.array.desc);
        String [] arBahan = resources.getStringArray(R.array.bahan);
        String [] arLangkah = resources.getStringArray(R.array.langkah);


        for (int i = 0; i < arJudul.length; i++) {
            mList.add(new Resep(arJudul[i], arDeskripsi[i], arBahan[i], arLangkah[i]));
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doClick(int pos) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESEP, mList.get(pos));
        startActivity(intent);
    }

    int itemPos;

    @Override
    public void doEdit(int pos) {
        itemPos = pos;
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra(RESEP, mList.get(pos));
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    @Override
    public void doDelete(int pos) {

    }
}
