package com.example.qunlnhns.ui.frag3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qunlnhns.R;
import com.example.qunlnhns.activities.mainAcitivity.MainActivity;
import com.example.qunlnhns.db.DbPbManager;
import com.example.qunlnhns.model.PhongBan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag3 extends Fragment {

    private RecyclerView rv;
    private EditText search;
    private FloatingActionButton fab;
    private PbRecyclerAdapter adapter;
    private ArrayList<PhongBan> phongBanArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        initView(view);

        phongBanArrayList = DbPbManager.getInstance(getContext()).getAllPhongBan();
        adapter = new PbRecyclerAdapter(phongBanArrayList);
        rv.setAdapter(adapter);

        /* search */
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // search
                phongBanArrayList = DbPbManager.getInstance(getContext()).getSearchPhongBan(s.toString());
                adapter.updateArrayListPhongBan(phongBanArrayList);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add phong ban
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null){
                    mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AddPBFrag())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.phong_ban_rv);
        search = view.findViewById(R.id.search_phong_ban);
        fab = view.findViewById(R.id.floatingActionButton_phong_ban);
    }
}
