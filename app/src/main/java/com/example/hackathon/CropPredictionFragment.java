package com.example.hackathon;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        // Add TextWatchers for each EditText
        Nitrogen.addTextChangedListener(createTextWatcher(Nitrogen, 0, 140, "Enter a value between 0 and 140"));
        Phosphorus.addTextChangedListener(createTextWatcher(Phosphorus, 5, 145, "Enter a value between 5 and 145"));
        Potassium.addTextChangedListener(createTextWatcher(Potassium, 5, 205, "Enter a value between 5 and 205"));
        Temperature.addTextChangedListener(createTextWatcher(Temperature, 8, 43, "Enter a value between 8 and 43"));
        Humidity.addTextChangedListener(createTextWatcher(Humidity, 0, 100, "Enter a value between 0 and 100"));
        pH.addTextChangedListener(createTextWatcher(pH, 1, 14, "Enter a value between 1 and 14"));
        Rainfall.addTextChangedListener(createTextWatcher(Rainfall, 0, 300, "Enter a value between 0 and 300"));

        Predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidInput()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String data = jsonObject.getString("crops");
                                        ResultTv.setText(data);
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
                } else {
                    Toast.makeText(requireContext(), "Please enter correct values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private TextWatcher createTextWatcher(final EditText editText, final float minValue, final float maxValue, final String errorMessage) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    float value = Float.parseFloat(s.toString());
                    if (value < minValue || value > maxValue) {
                        editText.setError(errorMessage);
                    } else {
                        editText.setError(null);
                    }
                } catch (NumberFormatException e) {
                    editText.setError("Invalid input");
                }
            }
        };
    }

    private boolean isValidInput() {
        try {
            float n = Float.parseFloat(Nitrogen.getText().toString());
            float p = Float.parseFloat(Phosphorus.getText().toString());
            float k = Float.parseFloat(Potassium.getText().toString());
            float temp = Float.parseFloat(Temperature.getText().toString());
            float humidity = Float.parseFloat(Humidity.getText().toString());
            float ph = Float.parseFloat(pH.getText().toString());
            float rainfall = Float.parseFloat(Rainfall.getText().toString());

            if (n < 0 || n > 140) {
                return false;
            }

            if (p < 5 || p > 145) {
                return false;
            }

            if (k < 5 || k > 205) {
                return false;
            }

            if (temp < 8 || temp > 43) {
                return false;
            }

            if (humidity < 0 || humidity > 100) {
                return false;
            }

            if (ph < 1 || ph > 14) {
                return false;
            }

            if (rainfall < 0 || rainfall > 300) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}