package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.R;
import id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.model.Resep;

/**
 * Created by WildanZ on 22/11/2016.
 */
public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ViewHolder> {
    ArrayList<Resep> resepList;

    public ResepAdapter(ArrayList<Resep> resepList) {
        this.resepList = resepList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resep resep = resepList.get(position);
        holder.tvJudul.setText(resep.judul);
        holder.tvDeskripsi.setText(resep.deskripsi);
    }

    @Override
    public int getItemCount() {
        if (resepList != null)
            return resepList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        TextView tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
        }
    }
}
