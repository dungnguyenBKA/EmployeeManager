package com.example.qunlnhns.ui.frag3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qunlnhns.R;
import com.example.qunlnhns.model.PhongBan;

import java.util.ArrayList;

public class PbRecyclerAdapter extends RecyclerView.Adapter<PbRecyclerAdapter.ViewHolder> {
    private ArrayList<PhongBan> phongBanArrayList;
    private Context context;

    public PbRecyclerAdapter(ArrayList<PhongBan> phongBanArrayList) {
        this.phongBanArrayList = phongBanArrayList;
    }

    @NonNull
    @Override
    public PbRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong_ban_rv, parent, false);

        if(context == null) context = parent.getContext();
        return new PbRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PbRecyclerAdapter.ViewHolder holder, int position) {
        PhongBan phongBan = phongBanArrayList.get(position);

        holder.maPB.setText(phongBan.getMaPb());
        holder.tenPB.setText(phongBan.getTenPb());
        holder.diachi.setText(phongBan.getDiachiPb());
    }

    @Override
    public int getItemCount() {
        return phongBanArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView maPB, tenPB, diachi, sdt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            maPB = itemView.findViewById(R.id.ma_phong_ban);
            tenPB = itemView.findViewById(R.id.ten_phong_ban);
            diachi = itemView.findViewById(R.id.dia_chi_pb);
        }
    }

    public void updateArrayListPhongBan(ArrayList<PhongBan> phongBanArrayList){
        this.phongBanArrayList = phongBanArrayList;
        notifyDataSetChanged();
    }
}
