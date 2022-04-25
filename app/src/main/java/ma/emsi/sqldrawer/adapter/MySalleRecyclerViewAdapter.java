package ma.emsi.sqldrawer.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ma.emsi.sqldrawer.R;
import ma.emsi.sqldrawer.beans.Salle;
import ma.emsi.sqldrawer.databinding.FragmentMachineItemBinding;
import ma.emsi.sqldrawer.databinding.FragmentSalleItemBinding;
import ma.emsi.sqldrawer.ui.machine.MachineFragment;
import ma.emsi.sqldrawer.ui.machineBySalle.ItemFragment;
import ma.emsi.sqldrawer.ui.salle.AddSalleFragment;
import ma.emsi.sqldrawer.ui.salle.EditSalleFragment;
import ma.emsi.sqldrawer.ui.salle.placeholder.PlaceholderContent.PlaceholderItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySalleRecyclerViewAdapter extends RecyclerView.Adapter<MySalleRecyclerViewAdapter.ViewHolder> {

    private final List<Salle> mValues;
    private Context context;
    FragmentManager fragmentManager;

    public MySalleRecyclerViewAdapter(List<Salle> items,FragmentManager fragmentManager ) {
        mValues = items;
        this.fragmentManager= fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentSalleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.salle = mValues.get(position);
        holder.id.setText(mValues.get(position).getId()+"");
        holder.code.setText(mValues.get(position).getCode()+"");
        holder.libelle.setText(mValues.get(position).getLibelle()+"");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView id;
        public final TextView code;
        public final TextView libelle;
        public Salle salle;
        private ConstraintLayout layout;

        public ViewHolder(FragmentSalleItemBinding binding) {
            super(binding.getRoot());
            layout = binding.salleitem;
            id = binding.idS;
            code = binding.codeS;
            libelle = binding.libelleS;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Bundle bundle = new Bundle();
                    bundle.putInt("id", Integer.parseInt(id.getText().toString()));



                    final AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                            .setMessage("Séléctionnez une action")
                            .setPositiveButton("Modifier", null)
                            .setNegativeButton("Display machines", null)
                            .setNeutralButton("Annuler",null)
                            .show();

                    Button modifier = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button showMachine = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    Button annuler = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);

                    annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    modifier.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Fragment fragment = new EditSalleFragment();
                            fragment.setArguments(bundle);
                            FragmentManager fm = fragmentManager;
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.addSalleFrame, fragment);
                            ft.commit();
                            dialog.dismiss();

                        }
                    });

                    showMachine.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Fragment fragment = new ItemFragment();
                            fragment.setArguments(bundle);
                            FragmentManager fm = fragmentManager;
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.addSalleFrame, fragment);
                            ft.commit();
                            dialog.dismiss();

                        }
                    });





                }
            });


        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}