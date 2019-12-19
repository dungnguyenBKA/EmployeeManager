package com.example.qunlnhns.ui.frag2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qunlnhns.R;
import com.example.qunlnhns.db.DbDAManager;
import com.example.qunlnhns.model.DuAn;

import java.util.Objects;

public class AddDAFrag extends Fragment {

    private TextView save;
    private ImageView close;
    private EditText edTenDuAn, edMaDA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_du_an, container, false);

        initView(view);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add and close

                if(edTenDuAn.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "Tên dự án không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(edMaDA.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "Tên dự án không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                DbDAManager.getInstance(getContext()).addRow(new DuAn(edMaDA.getText().toString().trim(), edTenDuAn.getText().toString().trim(), 0));
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // close frag
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void initView(View view) {
        save = view.findViewById(R.id.save_DA);
        close = view.findViewById(R.id.close_da);

        edTenDuAn = view.findViewById(R.id.adding_ten_du_an);
        edMaDA = view.findViewById(R.id.adding_ma_du_an);
    }
}
