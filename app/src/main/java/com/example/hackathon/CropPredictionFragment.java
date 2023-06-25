package com.example.hackathon;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CropPredictionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CropPredictionFragment() {
        // Required empty public constructor
    }

    public static CropPredictionFragment newInstance(String param1, String param2) {
        CropPredictionFragment fragment = new CropPredictionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private EditText Nitrogen, Phosphorus, Potassium, Temperature, Humidity, pH, Rainfall;
    private Button Predict;
    private TextView ResultTv;
    String url = "https://crop-recommendation-model.onrender.com/predict";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crop_prediction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Nitrogen = view.findViewById(R.id.editTextNitrogen);
        Phosphorus = view.findViewById(R.id.editTextPhosphorus);
        Potassium = view.findViewById(R.id.editTextPotassium);
        Temperature = view.findViewById(R.id.editTextTemperature);
        Humidity = view.findViewById(R.id.editTextHumidity);
        pH = view.findViewById(R.id.editTextPh);
        Rainfall = view.findViewById(R.id.editTextRainfall);
        Predict = view.findViewById(R.id.predictButton);
        ResultTv = view.findViewById(R.id.resultTV);

        Predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("crops");
                                    ResultTv.setText(data);

                                    // Add a delay of 10 seconds before clearing the result


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error response
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("N", Nitrogen.getText().toString());
                        params.put("P", Phosphorus.getText().toString());
                        params.put("K", Potassium.getText().toString());
                        params.put("temperature", Temperature.getText().toString());
                        params.put("humidity", Humidity.getText().toString());
                        params.put("ph", pH.getText().toString());
                        params.put("rainfall", Rainfall.getText().toString());

                        return params;

                    }
                };

                RequestQueue queue = Volley.newRequestQueue(requireContext());
                queue.add(stringRequest);
            }
        });
    }
}
