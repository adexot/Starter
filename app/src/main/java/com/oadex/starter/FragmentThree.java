package com.oadex.starter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oadex.starter.model.DataBaseHelper;
import com.oadex.starter.model.Starter;
import com.oadex.starter.model.StarterAdapter;

import java.util.List;

public class FragmentThree extends Fragment
{
    private RecyclerView recyclerView;
    private List<Starter> list;
    private StarterAdapter adapter;
    private DataBaseHelper database;

    public FragmentThree()
    {
        // Required empty public constructor
    }

    public static FragmentThree newInstance() {
        FragmentThree fragment = new FragmentThree();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DataBaseHelper(getContext());
        list = database.getAll();
        adapter = new StarterAdapter(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_three, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layout);

        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
