package com.example.qunlnhns.ui.frag3;

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
import com.example.qunlnhns.db.DbPbManager;
import com.example.qunlnhns.model.PhongBan;

import java.util.Objects;

public class AddPBFrag extends Fragment {

    private TextView save;
    private ImageView close;
    private EditText edMaPB, edTenPB, edDiaChiPB, edSDT_PB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_phong_ban, container, false);

        initView(view);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add and close

                if(edTenPB.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "Tên phòng ban không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(edMaPB.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "Mã phòng ban không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                DbPbManager.getInstance(getContext()).addRow(new PhongBan(edMaPB.getText().toString().trim(),
                        edTenPB.getText().toString().trim(),
                        edDiaChiPB.getText().toString().trim(), 0));


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
        save = view.findViewById(R.id.save_pb);
        close = view.findViewById(R.id.close_pb);

        edTenPB = view.findViewById(R.id.adding_ten_pb);
        edMaPB = view.findViewById(R.id.adding_ma_pb);
        edDiaChiPB = view.findViewById(R.id.adding_diachi_pb);
    }
}
