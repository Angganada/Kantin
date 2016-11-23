package id.sch.smktelkom_mlg.project.xiirpl23122130.ngantin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
    IResepAdapter mIResepAdapter;

    public ResepAdapter(Context context, ArrayList<Resep> resepList) {
        this.resepList = resepList;
        mIResepAdapter = (IResepAdapter) context;
    }

    public interface IResepAdapter {
        void doClick(int pos);
        void doEdit(int pos);
        void doDelete(int pos);
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
        Button bEdit;
        ImageButton bDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
            bEdit = (Button) itemView.findViewById(R.id.buttonEdit);
            bDelete = (ImageButton) itemView.findViewById(R.id.buttonDelete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIResepAdapter.doClick(getAdapterPosition());
                }
            });

            bEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIResepAdapter.doEdit(getAdapterPosition());
                }
            });

            bDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIResepAdapter.doDelete(getAdapterPosition());
                }
            });
        }
    }
}
