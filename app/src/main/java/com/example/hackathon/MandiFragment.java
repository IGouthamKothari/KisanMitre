package com.example.hackathon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MandiFragment extends Fragment {

    private String selectedState, selectedDistrict, selectedCrop; // Variables to hold the values of selected State, District, and Crop
    private TextView tvStateSpinner, tvDistrictSpinner, tvCropSpinner; // TextViews to show the errors
    private Spinner stateSpinner, districtSpinner, cropSpinner; // Spinners
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter, cropAdapter;
    private TextView tvMinPrice, tvMaxPrice,tvMandi;// Adapters for the spinners

    public MandiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mandi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvMinPrice = view.findViewById(R.id.minPriceTV);
        tvMaxPrice = view.findViewById(R.id.maxPriceTV);
        tvMandi=view.findViewById(R.id.mandiTextView);

        // State Spinner Initialization
        stateSpinner = view.findViewById(R.id.spinner_indian_states);
        tvStateSpinner = view.findViewById(R.id.textView_indian_states);

        // Populate ArrayAdapter using string array and a spinner layout that we will define
        stateAdapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.array_indian_states, R.layout.spinner_layout);

        // Specify the layout to use when the list of choices appear
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stateSpinner.setAdapter(stateAdapter); // Set the adapter to the spinner to populate the State Spinner

        // District Spinner Initialization
        districtSpinner = view.findViewById(R.id.spinner_indian_districts);
        tvDistrictSpinner = view.findViewById(R.id.textView_indian_districts);


        // When any item of the stateSpinner is selected
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedState = stateSpinner.getSelectedItem().toString(); // Obtain the selected State
                String state = selectedState;

                if (!selectedState.equals("Select Your State")) {
                    switch (selectedState) {
                        case "Andhra Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_andhra_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Arunachal Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_arunachal_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Assam":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_assam_districts, R.layout.spinner_layout);
                            break;
                        case "Bihar":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_bihar_districts, R.layout.spinner_layout);
                            break;
                        case "Chhattisgarh":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_chhattisgarh_districts, R.layout.spinner_layout);
                            break;
                        case "Goa":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_goa_districts, R.layout.spinner_layout);
                            break;
                        case "Gujarat":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_gujarat_districts, R.layout.spinner_layout);
                            break;
                        case "Haryana":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_haryana_districts, R.layout.spinner_layout);
                            break;
                        case "Himachal Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_himachal_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Jharkhand":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_jharkhand_districts, R.layout.spinner_layout);
                            break;
                        case "Karnataka":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_karnataka_districts, R.layout.spinner_layout);
                            break;
                        case "Kerala":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_kerala_districts, R.layout.spinner_layout);
                            break;
                        case "Madhya Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_madhya_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Maharashtra":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_maharashtra_districts, R.layout.spinner_layout);
                            break;
                        case "Manipur":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_manipur_districts, R.layout.spinner_layout);
                            break;
                        case "Meghalaya":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_meghalaya_districts, R.layout.spinner_layout);
                            break;
                        case "Mizoram":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_mizoram_districts, R.layout.spinner_layout);
                            break;
                        case "Nagaland":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_nagaland_districts, R.layout.spinner_layout);
                            break;
                        case "Odisha":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_odisha_districts, R.layout.spinner_layout);
                            break;
                        case "Punjab":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_punjab_districts, R.layout.spinner_layout);
                            break;
                        case "Rajasthan":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_rajasthan_districts, R.layout.spinner_layout);
                            break;
                        case "Sikkim":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_sikkim_districts, R.layout.spinner_layout);
                            break;
                        case "Tamil Nadu":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_tamil_nadu_districts, R.layout.spinner_layout);
                            break;
                        case "Telangana":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_telangana_districts, R.layout.spinner_layout);
                            break;
                        case "Tripura":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_tripura_districts, R.layout.spinner_layout);
                            break;
                        case "Uttar Pradesh":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_uttar_pradesh_districts, R.layout.spinner_layout);
                            break;
                        case "Uttarakhand":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_uttarakhand_districts, R.layout.spinner_layout);
                            break;
                        case "West Bengal":
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_west_bengal_districts, R.layout.spinner_layout);
                            break;
                        default:
                            districtAdapter = ArrayAdapter.createFromResource(requireContext(),
                                    R.array.array_default_districts, R.layout.spinner_layout);
                            break;
                    }

                    // Specify the layout to use when
                    districtSpinner.setAdapter(districtAdapter); // Set the adapter to the spinner to populate the District Spinner

                    tvDistrictSpinner.setVisibility(View.VISIBLE); // Make the District TextView visible
                    districtSpinner.setVisibility(View.VISIBLE); // Make the District Spinner visible
                } else {
                    tvDistrictSpinner.setVisibility(View.GONE); // Make the District TextView gone
                    districtSpinner.setVisibility(View.GONE); // Make the District Spinner gone
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // When any item of the districtSpinner is selected
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDistrict = districtSpinner.getSelectedItem().toString(); // Obtain the selected District
                String district = selectedDistrict;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Button click event
        Button btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedState != null && selectedDistrict != null) {
                    // Perform the search operation with selectedState and selectedDistrict
                    Toast.makeText(requireContext(), "Searching for Mandi in " + selectedDistrict + ", " + selectedState, Toast.LENGTH_SHORT).show();
                    getCropInfo(selectedState, selectedDistrict, selectedCrop);
                } else {
                    // Show error message if State or District is not selected
                    Toast.makeText(requireContext(), "Please select State and District", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cropSpinner = view.findViewById(R.id.spinner_crop_selection);
        tvCropSpinner = view.findViewById(R.id.textView_crop_selection);

        // Populate ArrayAdapter using string array and a spinner layout that we will define
        cropAdapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.array_crops, R.layout.spinner_layout);

        // Specify the layout to use when the list of choices appear
        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cropSpinner.setAdapter(cropAdapter); // Set the adapter to the spinner to populate the Crop Spinner

        // When any item of the cropSpinner is selected
        cropSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCrop = cropSpinner.getSelectedItem().toString();// Obtain the selected Crop
                String crop = selectedCrop;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


    }

    private void getCropInfo(String state, String district, String crop) {
        String url = "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&filters%5Bstate%5D=" + state + "&filters%5Bdistrict%5D=" + district + "&filters%5Bcommodity%5D=" + crop;

        // Create a Volley request queue
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        // Create a JSON object request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray recordsArray = response.getJSONArray("records");

                    if (recordsArray.length() > 0) {
                        JSONObject record = recordsArray.getJSONObject(0);
                        String minPrice = record.getString("min_price");
                        String maxPrice = record.getString("max_price");
                        String mandi=record.getString("market");
                        requireActivity().runOnUiThread(() -> {
                            tvMinPrice.setVisibility(View.VISIBLE);
                            tvMaxPrice.setVisibility(View.VISIBLE);
                            tvMandi.setVisibility(View.VISIBLE);
                            tvMinPrice.setText("Min Price: " + minPrice);
                            tvMaxPrice.setText("Max Price: " + maxPrice);
                            tvMandi.setText("Mandi: "+ mandi);
                            Toast.makeText(requireContext(), "Min Price: " + minPrice + ", Max Price: " + maxPrice, Toast.LENGTH_SHORT).show();
                            // TODO: Update the UI elements with the min and max prices
                        });

                        // Delay the execution of code for 10 seconds
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Update the UI with the min and max prices
                                updateMinAndMaxPrices(minPrice, maxPrice, mandi);
                            }
                        }, 10000); // 10 seconds delay
                    } else {
                        requireActivity().runOnUiThread(() -> {
                            // Hide the UI elements for min and max prices
                            tvMinPrice.setVisibility(View.GONE);
                            tvMaxPrice.setVisibility(View.GONE);
                            Toast.makeText(requireContext(), "No records found", Toast.LENGTH_SHORT).show();
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    requireActivity().runOnUiThread(() -> {
                        // Hide the UI elements for min and max prices
                        tvMinPrice.setVisibility(View.GONE);
                        tvMaxPrice.setVisibility(View.GONE);
                        Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                requireActivity().runOnUiThread(() -> {
                    // Hide the UI elements for min and max prices
                    tvMinPrice.setVisibility(View.GONE);
                    tvMaxPrice.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show();
                });
            }
        });

        // Add the request to the Volley request queue
        queue.add(request);
    }

    private void updateMinAndMaxPrices(String minPrice, String maxPrice, String mandi) {
        tvMinPrice.setText("Min Price: " + minPrice);
        tvMaxPrice.setText("Max Price: " + maxPrice);
        tvMandi.setText("Mandi: " + mandi);


    }
}
