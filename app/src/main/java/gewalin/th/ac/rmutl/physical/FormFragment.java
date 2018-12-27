package gewalin.th.ac.rmutl.physical;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment {

    //    Explicit
    private String genderString, ageString, paralysisitypeString,
            musclepowerString, detailStrig;
    private boolean ageABoolean = true;  // True Not Choose Age


    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Gender Controller
        genderController();


//        Save Controller
        saveController();

//        Age Controller
        ageController();




    }   //Main Method

    private void ageController() {

        final String[] strings = new String[]{"กรุณาเลือกอายุ","0 - 10", "11 - 20", "21 - 30", "31 - 40", "41 - 50", "Over 51"};
        Spinner spinner = getView().findViewById(R.id.spnAge);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ageString = strings[position];
                if (!(position == 0)) {
                    ageABoolean = false;
                } else {
                    ageABoolean = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void genderController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.ragMale:
                        genderString = "0";
                        break;
                    case R.id.ragFemale:
                        genderString = "1";
                        break;

                }

            }
        });
    }

    private void saveController() {
        Button button = getView().findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Get Value Form Edittext
                EditText detailEditText = getView().findViewById(R.id.edtDetail);

                detailStrig = detailEditText.getText().toString().trim();

                MyAlert myAlert = new MyAlert(getActivity());

                RadioButton maleRadioButton = getView().findViewById(R.id.ragMale);
                RadioButton femaleRadioButton = getView().findViewById(R.id.ragFemale);

//                Check Space
                if (detailStrig.isEmpty()) {
//                    Have Space
                    myAlert.nornalDialog("Have Space", "Please Fill Every Blank");
                } else if (!(maleRadioButton.isChecked() || femaleRadioButton.isChecked())) {
//                    Non Choose Gender
                    myAlert.nornalDialog("Non Gender", "Please Choose Male or Female");
                } else if (ageABoolean) {
                    myAlert.nornalDialog("Non Choose Age", "Please Choose Age");
                } else {
                    confimData();
                }


            }   //onClick
        });
    }

    private void confimData() {

        String[] genderStrings = new String[]{"ชาย", "หญิง"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Confirm Data");
        builder.setMessage("Gender ==> " + genderStrings[Integer.parseInt(genderString)] + "\n" +
                "Age ==> " + ageString + "\n" );

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveData();
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void saveData() {

        MyManage myManage = new MyManage(getActivity());
        myManage.addValueToSQLite(genderString, ageString, paralysisitypeString, musclepowerString, detailStrig);

        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

}
