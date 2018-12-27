package gewalin.th.ac.rmutl.physical;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Show View
        showView();

    }  // Main Method

    private void showView() {
        TextView genderTextView = getView().findViewById(R.id.txtGender);
        TextView ageTextView = getView().findViewById(R.id.txtAge);
        TextView praralysistypeTextView = getView().findViewById(R.id.txtPraralysisType);
        TextView musclepowerTextView = getView().findViewById(R.id.txtMusclePower);
        TextView detailTextView = getView().findViewById(R.id.txtDetail);

        SQLiteDatabase sqLiteDatabase = getActivity().openOrCreateDatabase(MyOpenHelper.database_name, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        cursor.moveToFirst();


        int indexGender = Integer.parseInt(cursor.getString(1));
        genderTextView.setText(findGender(indexGender));

        ageTextView.setText(cursor.getString(2));

        praralysistypeTextView.setText(cursor.getString(3));
        musclepowerTextView.setText(cursor.getString(4));
        detailTextView.setText(cursor.getString(5));
    }

    private String findGender(int indexGender) {

        String result = "";
        String[] strings = new String[]{"ชาย", "หญิง"};

        result = "Gender ==> " + strings[indexGender];

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

}
