package ma.emsi.sqldrawer.ui.salle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ma.emsi.sqldrawer.R;
import ma.emsi.sqldrawer.beans.Salle;
import ma.emsi.sqldrawer.service.SalleService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditSalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditSalleFragment extends Fragment {
    EditText code,libelle;
    Button edit;
    SalleService ss;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditSalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editSalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditSalleFragment newInstance(String param1, String param2) {
        EditSalleFragment fragment = new EditSalleFragment();
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
        View v =  inflater.inflate(R.layout.fragment_edit_salle, container, false);
        code = v.findViewById(R.id.codeM);
        libelle = v.findViewById(R.id.libelleM);
        edit = v.findViewById(R.id.modifier);
        ss = new SalleService(getContext());
        int id = getArguments().getInt("id");
        Log.d("TAGID", id+" ");
        Salle salle = ss.findById(id);
        code.setText(salle.getCode());
        libelle.setText(salle.getLibelle());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ss.update(new Salle(id,code.getText().toString(),libelle.getText().toString()));
                Toast.makeText(getContext(), "La Salle à été modifier !", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}