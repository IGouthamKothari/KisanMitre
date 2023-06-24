package com.example.hackathon;

import android.os.Bundle;
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

public class MandiFragment extends Fragment {

    private String selectedState, selectedDistrict, selectedCrop; // Variables to hold the values of selected State, District, and Crop
    private TextView tvStateSpinner, tvDistrictSpinner, tvCropSpinner; // TextViews to show the errors
    private Spinner stateSpinner, districtSpinner, cropSpinner; // Spinners
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter, cropAdapter; // Adapters for the spinners

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
                selectedCrop = cropSpinner.getSelectedItem().toString(); // Obtain the selected Crop
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}
