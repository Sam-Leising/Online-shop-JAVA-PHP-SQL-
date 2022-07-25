package com.example.online_shop.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.online_shop.R;


public class CustomDialogOneEditText extends AppCompatDialogFragment {

    Custom_DialogInterface dl;
    EditText ipAddress;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.custom_dialog_one_edit_text,null);
        ipAddress = view.findViewById(R.id.ipEditText);

        builder.setView(view)
                .setTitle("Update IP Address：")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String text = ipAddress.getText().toString();
                        dl.applyTexts(text);
                    }
                });

//        builder.create().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dl = (Custom_DialogInterface) context;
    }

    public interface Custom_DialogInterface{
        void applyTexts(String ipAddress);
    }
}
