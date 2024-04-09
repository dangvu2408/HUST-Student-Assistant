package com.example.app_01;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class Toeic_Dialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.toeic_dialog, null);
        builder.setView(view); //setview khi bat su kien

//        LinearLayout linearLayout = view.findViewById(R.id.linear_dialog);
//        linearLayout.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.linearlayout_background)); //set background cho dialog
        AlertDialog dialog1 = builder.create();
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.linearlayout_background);



        Button cancel = view.findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog1;
    }
}

