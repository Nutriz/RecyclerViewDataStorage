package fr.jgully.recyclerviewdatastorage;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class NewPeopleDialogFragment extends DialogFragment {

    public interface OnNewPeopleCreated {
        void onCreated(People people);
    }

    public NewPeopleDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static NewPeopleDialogFragment newInstance(int nextPeopleNumber) {
        NewPeopleDialogFragment frag = new NewPeopleDialogFragment();
        Bundle args = new Bundle();
        args.putInt("nextPeopleNumber", nextPeopleNumber);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int nextPeopleNumber = getArguments().getInt("nextPeopleNumber");

        final View view = LayoutInflater.from(getContext()).inflate(R.layout.new_people_dialog, null);
        ((EditText) view.findViewById(R.id.name_edit_text)).setText("People " + nextPeopleNumber);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("Add new people")
                .setView(view)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        People people = createPeople(view);
                        ((OnNewPeopleCreated) getActivity()).onCreated(people);
                    }
                })
                .setNegativeButton("Cancel", null);

        return alertDialogBuilder.create();
    }

    private People createPeople(View view) {
        String name = ((EditText) view.findViewById(R.id.name_edit_text)).getText().toString();
        int age = Integer.parseInt(((EditText) view.findViewById(R.id.age_edit_text)).getText().toString());
        People.Sex sex = ((RadioGroup) view.findViewById(R.id.sex_radio_group)).getCheckedRadioButtonId() == R.id.male_sex_radio ? People.Sex.MALE : People.Sex.FEMALE;
        boolean loveAndroid = ((Switch) view.findViewById(R.id.love_android_switch)).isChecked();
        return new People(name, age, sex, loveAndroid);
    }
}
