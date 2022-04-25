package ma.emsi.sqldrawer.adapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ma.emsi.sqldrawer.R;
import ma.emsi.sqldrawer.beans.Machine;
import ma.emsi.sqldrawer.databinding.FragmentMachineItemBinding;
import ma.emsi.sqldrawer.ui.machine.EditMachineFragment;
import ma.emsi.sqldrawer.ui.machine.placeholder.PlaceholderContent.PlaceholderItem;
import ma.emsi.sqldrawer.ui.salle.EditSalleFragment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MymachineRecyclerViewAdapter extends RecyclerView.Adapter<MymachineRecyclerViewAdapter.ViewHolder> {

    private final List<Machine> mValues;
    private FragmentManager fragmentManager;
    public MymachineRecyclerViewAdapter(List<Machine> items , FragmentManager fragmentManager) {
        mValues = items;
        this.fragmentManager =  fragmentManager;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentMachineItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.machine = mValues.get(position);
        holder.id.setText(mValues.get(position).getId()+"");
        holder.marque.setText(mValues.get(position).getMarque()+"");
        holder.reference.setText(mValues.get(position).getRefernce()+"");
        holder.salle.setText(mValues.get(position).getSalle().getLibelle()+"");

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView marque,reference,salle,id;
        public PlaceholderItem mItem;
        public Machine machine;
        private ConstraintLayout layout;

        public ViewHolder(FragmentMachineItemBinding binding) {
            super(binding.getRoot());
            layout = binding.machineitem;
            id = binding.idM;
            marque = binding.marqueM;
            reference = binding.referenceM;
            salle = binding.salleM;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", Integer.parseInt(id.getText().toString()));
                    Fragment fragment = new EditMachineFragment();
                    fragment.setArguments(bundle);
                    FragmentManager fm = fragmentManager;
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.addMachineFrame, fragment);
                    ft.commit();
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}