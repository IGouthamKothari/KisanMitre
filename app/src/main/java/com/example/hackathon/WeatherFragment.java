package com.example.hackathon;

import static java.sql.DriverManager.println;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherFragment newInstance(String param1, String param2) {
        WeatherFragment fragment = new WeatherFragment();
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

    private RelativeLayout homeRL;
    private ProgressBar loadingPB;
    private TextView cityNameTV,temperatureTV,conditionTV;
    private RecyclerView weatherRV;
    private TextInputEditText cityEdt;
    private ImageView backIV,iconIV,searchIV;
    private ArrayList<WeatherRVModel> WeatherRVModelArrayList;
    private WeatherRVAdapter weatherRVAdapter;
    private LocationManager locationManager;
    private int PERMISSION_CODE=1;
    private String cityName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeRL=view.findViewById(R.id.RLweather);
        loadingPB=view.findViewById(R.id.progressBarLoading);
        cityNameTV=view.findViewById(R.id.TVCityName);
        temperatureTV=view.findViewById(R.id.TVTemperature);
       conditionTV=view.findViewById(R.id.TVCondition);
        weatherRV=view.findViewById(R.id.RVWeather);
        cityEdt=view.findViewById(R.id.EdtCity);
        backIV=view.findViewById(R.id.bgImage);
        iconIV=view.findViewById(R.id.TVIcon);
        searchIV=view.findViewById(R.id.IVSearch);
        WeatherRVModelArrayList=new ArrayList<>();
        weatherRVAdapter= new WeatherRVAdapter(getContext(),WeatherRVModelArrayList);
        weatherRV.setAdapter(weatherRVAdapter);

        locationManager= (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED &&  ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION )
                != PackageManager.PERMISSION_GRANTED ) {
            // Permission is not granted, request it
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_CODE);
        }

        Location location= locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        cityName= getCityName(location.getLongitude(),location.getLatitude());
        // remove this change if error come
       // cityNameTV.setText(cityName);
        cityNameTV.setText(cityName);
        getWeatherInfo(cityName);


        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city=cityEdt.getText().toString();
                if(city.isEmpty()){
                    println("City is blank");
                    Toast.makeText(getContext(), "Please enter city name", Toast.LENGTH_SHORT).show();
                }else {
                    println("City name is $city");
                    cityNameTV.setText(city);
                    getWeatherInfo(city);
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), "Permission granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Please give the permission", Toast.LENGTH_SHORT).show();
                requireActivity().finish();
            }
        }
    }

    private String getCityName(double longitude, double latitude){

        String cityName= "Not Found";
        Geocoder gcd= new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses=gcd.getFromLocationName(latitude+","+longitude,10);

            for(Address adr : addresses){
                if(adr!=null){
                    String city=adr.getLocality();
                    if(city!=null && !city.equals("")){
                        cityName=city;

                    }else{
                        Log.d("TAG","CITY NOT FOUND");

                    }
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return cityName;
    }



    private void getWeatherInfo(String cityName){
        String url="https://api.weatherapi.com/v1/forecast.json?key=fd173414283e4b4895e130309232304&q="+cityName +"&days=1&aqi=no&alerts=no";
        conditionTV.setText(cityName);
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                homeRL.setVisibility(View.VISIBLE);
                WeatherRVModelArrayList.clear();

                try {
                    String temperature= response.getJSONObject("current").getString("temp_c");
                    temperatureTV.setText(temperature+"°C");
                    int isDay= response.getJSONObject("current").getInt("is_day");
                    String condition=response.getJSONObject("current").getJSONObject("condition").getString("text");
                    String conditionIcon=response.getJSONObject("current").getJSONObject("condition").getString("icon");
                    Picasso.get().load("http:".concat(conditionIcon)).into(iconIV);
                    conditionTV.setText(condition);
                    if (isDay==1){
                        Picasso.get().load("https://images.unsplash.com/photo-1436891620584-47fd0e565afb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1887&q=80").into(backIV);
                    }else {

                        Picasso.get().load("https://images.unsplash.com/photo-1436891620584-47fd0e565afb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1887&q=80").into(backIV);
                    }

                    JSONObject forecastObj=response.getJSONObject("forecast");
                    JSONObject forecastO = forecastObj.getJSONArray("forecastday").getJSONObject(0);
                    JSONArray hourArray= forecastO.getJSONArray("hour");

                    for(int i=0;i<hourArray.length();i++){

                        JSONObject hourObj= hourArray.getJSONObject(i);
                        String time =hourObj.getString("time");
                        String temper =hourObj.getString("temp_c");
                        String img =hourObj.getJSONObject("condition").getString("icon");
                        String wind =hourObj.getString("wind_kph");
                        WeatherRVModelArrayList.add(new WeatherRVModel(time,temper,img,wind) );
                    }
                    weatherRVAdapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Please enter valid city name", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);


    }
}