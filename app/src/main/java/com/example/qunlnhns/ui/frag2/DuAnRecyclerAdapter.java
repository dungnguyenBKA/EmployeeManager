package com.example.qunlnhns.ui.frag2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qunlnhns.R;
import com.example.qunlnhns.db.DbDAManager;
import com.example.qunlnhns.db.DbEmployeeManager;
import com.example.qunlnhns.model.DuAn;

import java.util.ArrayList;

public class DuAnRecyclerAdapter extends RecyclerView.Adapter<DuAnRecyclerAdapter.ViewHolder> {

    private ArrayList<DuAn> duAnArrayList;
    private Context context;

    public DuAnRecyclerAdapter(ArrayList<DuAn> duAnArrayList) {
        this.duAnArrayList = duAnArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_du_an_rv, parent, false);

        if(context == null) context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DuAn duAn = duAnArrayList.get(position);

        holder.duAn.setText(duAn.getTenDA());
        holder.maDA.setText(duAn.getMaDA());

        int soNguoi = DbEmployeeManager.getInstance(context).getCountEmployeeEachProject(duAn.getMaDA());
        holder.soNguoi.setText("Số người tham gia: "+ soNguoi);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbDAManager.getInstance(context).deleteRow(duAn.getMaDA());

                duAnArrayList.remove(duAn);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return duAnArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView maDA, duAn, soNguoi;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            maDA = itemView.findViewById(R.id.ma_du_an);
            duAn = itemView.findViewById(R.id.ten_du_an);
            soNguoi = itemView.findViewById(R.id.du_an_nguoi);
            delete = itemView.findViewById(R.id.delete_du_an);
        }
    }

    public void updateArrayListDuAn(ArrayList<DuAn> duAnArrayList){
        this.duAnArrayList = duAnArrayList;
        notifyDataSetChanged();
    }
}
