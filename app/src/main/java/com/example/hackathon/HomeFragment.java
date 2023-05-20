package com.example.hackathon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<tools> toolsArrayList;
    private int[] imageResourceId;
    private String[] toolsTitle;
    private String[] toolsRent;
    private RecyclerView recyclerview;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitilaize();
        recyclerview= view.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        MyAdapter myAdapter=new MyAdapter(getContext(),toolsArrayList);
        recyclerview.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    public void dataInitilaize(){

        toolsArrayList=new ArrayList<>();

        toolsTitle= new String[]{
                getString(R.string.tool1),
                getString(R.string.tool2),
                getString(R.string.tool3),
                getString(R.string.tool4),
                getString(R.string.tool5),
                "Rotavator",
                "Seed drill",
                "Pesticide spray",
                "Cultivator",

        };

        toolsRent= new String[]{
                getString(R.string.tool1rent),
                getString(R.string.tool2rent),
                getString(R.string.tool3rent),
                getString(R.string.tool4rent),
                getString(R.string.tool5rent),
                "150/day",
                "150/day",
                "50/day",
                "100/day",


        };

        imageResourceId=new int[]{
                R.drawable.tractor,
                R.drawable.plough,
                R.drawable.harrow,
                R.drawable.harvester,
                R.drawable.shovel,
                R.drawable.rotavator,
                R.drawable.seeddrill,
                R.drawable.spray,
                R.drawable.cultivator,



        };

        for (int i=0; i<toolsTitle.length; i++){

            tools tools =new tools(toolsTitle[i],toolsRent[i],imageResourceId[i]);
            toolsArrayList.add(tools);


        }

    }
}