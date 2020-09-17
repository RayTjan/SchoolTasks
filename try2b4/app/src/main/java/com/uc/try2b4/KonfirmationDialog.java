package com.uc.try2b4;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class KonfirmationDialog extends AppCompatDialogFragment {
    ArrayList<items> arr;
    int poss;

    public KonfirmationDialog(ArrayList<items> itemlist, int position) {
        arr = itemlist;
        poss = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder confirm = new AlertDialog.Builder(getActivity())
                .setMessage("Ae you sure you want to delete " + arr.get(poss).getMtext1()+ " ?")
                .setTitle("ALERT")
                .setNegativeButton("NO",null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent delete = new Intent(getActivity(), MainActivity.class);
                        arr.remove(poss);
                        delete.putExtra("what2",arr);
                        delete.putExtra("pos",poss);
                        Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                        loadingDialog.startLoadingDialog();
                        startActivity(delete);
                    }
                });
        return confirm.create();
    }
}
