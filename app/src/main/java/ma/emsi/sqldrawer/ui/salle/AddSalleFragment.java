package ma.emsi.sqldrawer.ui.salle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ma.emsi.sqldrawer.R;
import ma.emsi.sqldrawer.beans.Salle;
import ma.emsi.sqldrawer.service.SalleService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AddSalleFragment extends Fragment {
    EditText code,libelle;
    Button add;
    SalleService ss;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddSalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addSalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSalleFragment newInstance(String param1, String param2) {
        AddSalleFragment fragment = new AddSalleFragment();
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
        View v =  inflater.inflate(R.layout.fragment_add_salle, container, false);

        code = v.findViewById(R.id.code);
        libelle = v.findViewById(R.id.libelle);
        add = v.findViewById(R.id.ajouter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ss = new SalleService(getContext());
                ss.add(new Salle(code.getText().toString(),libelle.getText().toString()));
                Fragment fragment = new SalleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.addSalleFrame, fragment);
                fragmentTransaction.commit();
            }
        });


        return v;
    }
}