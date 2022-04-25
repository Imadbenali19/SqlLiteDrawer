package ma.emsi.sqldrawer.ui.machine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.emsi.sqldrawer.R;
import ma.emsi.sqldrawer.beans.Machine;
import ma.emsi.sqldrawer.beans.Salle;
import ma.emsi.sqldrawer.service.MachineService;
import ma.emsi.sqldrawer.service.SalleService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditMachineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMachineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditMachineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editMachineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditMachineFragment newInstance(String param1, String param2) {
        EditMachineFragment fragment = new EditMachineFragment();
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

    EditText marque;
    EditText ref;
    Spinner salle;
    Button edit;
    MachineService ms;
    SalleService ss;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_edit_machine, container, false);
        marque = v.findViewById(R.id.marqueM);
        ref = v.findViewById(R.id.referenceM);
        salle = v.findViewById(R.id.salleM);
        edit = v.findViewById(R.id.modifier);
        ms = new MachineService(getContext());
        ss = new SalleService(getContext());
        int id = getArguments().getInt("id");
        Machine machine = ms.findById(id);
        marque.setText(machine.getMarque());
        ref.setText(machine.getRefernce());
        marque.setText(machine.getMarque());

        ArrayAdapter<String> adapter;
        List<String> liste = new ArrayList<String>();
        for(Salle salle : ss.findAll()) {
            liste.add(salle.getCode());
        }
        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, liste);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        salle.setAdapter(adapter);
        salle.setSelection(adapter.getPosition(machine.getSalle().getCode()));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ms.update(new Machine(id,
                        marque.getText().toString(),
                        ref.getText().toString(),
                        ss.findByCode(salle.getSelectedItem().toString())));
                Toast.makeText(getContext(), "La machine à été modifier !", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}