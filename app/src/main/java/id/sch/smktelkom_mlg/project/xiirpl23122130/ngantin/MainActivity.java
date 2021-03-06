package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.adapter.ResepAdapter;
import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.model.Resep;

public class MainActivity extends AppCompatActivity implements ResepAdapter.IResepAdapter {
    public static final String RESEP = "resep";
    private static final int REQUEST_CODE_ADD = 88;
    private static final int REQUEST_CODE_EDIT = 99;
    ArrayList<Resep> mList = new ArrayList<>();
    ResepAdapter mAdapter;
    ArrayList<Resep> mListAll = new ArrayList<>();
    boolean isFiltered;
    ArrayList<Integer> mListMapFilter = new ArrayList<>();
    String mQuery;
    int itemPos;



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
            if (isFiltered) mListAll.add(resep);
            doFilter(mQuery);
            //mAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            Resep resep = (Resep) data.getSerializableExtra(RESEP);
            mList.remove(itemPos);
            if (isFiltered) mListAll.remove(mListMapFilter.get(itemPos).intValue());
            mList.add(itemPos, resep);
            if (isFiltered) mListAll.add(mListMapFilter.get(itemPos), resep);
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

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener()
                {
                    @Override
                    public boolean onQueryTextSubmit(String query)
                    {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText)
                    {
                        mQuery = newText.toLowerCase();
                        doFilter(mQuery);
                        return true;
                    }
                });
        return true;
    }

    private void doFilter(String query)
    {
        if (!isFiltered)
        {
            mListAll.clear();
            mListAll.addAll(mList);
            isFiltered = true;
        }

        mList.clear();
        if (query==null||query.isEmpty())
        {
            mList.addAll(mListAll);
            isFiltered = false;
        }
        else
        {
            mListMapFilter.clear();
            for (int i = 0; i < mListAll.size(); i++) {
                Resep resep = mListAll.get(i);
                if (resep.judul.toLowerCase().contains(query) ||
                        resep.deskripsi.toLowerCase().contains(query) ||
                        resep.bahan.toLowerCase().contains(query) ||
                        resep.langkah.toLowerCase().contains(query))
                {
                    mList.add(resep);
                    mListMapFilter.add(i);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doClick(int pos) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RESEP, mList.get(pos));
        startActivity(intent);
    }

    @Override
    public void doEdit(int pos) {
        itemPos = pos;
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra(RESEP, mList.get(pos));
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    @Override
    public void doDelete(int pos) {
        itemPos = pos;
        final Resep resep = mList.get(pos);
        mList.remove(itemPos);
        mAdapter.notifyDataSetChanged();

        Snackbar.make(findViewById(R.id.fab), resep.judul + " Dihapus", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.add(itemPos, resep);
                mAdapter.notifyDataSetChanged();
            }
        }).show();
    }
}
