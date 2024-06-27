package com.example.quickmaths;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quickmaths.database.CalculBaseHelper;
import com.example.quickmaths.database.ScoreDao;
import com.example.quickmaths.entities.Score;

import org.jetbrains.annotations.Nullable;

public class CustomDialogFragment extends DialogFragment {
    private static final String ARG_SCORE = "argScore";

    // Method to create new instance of CustomDialogFragment with arguments
    public static CustomDialogFragment newInstance(int argScore) {
        CustomDialogFragment fragment = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, argScore);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.activity_loose, null);
        builder.setView(view);

        int score = getArguments().getInt(ARG_SCORE);
        TextView textMsg = view.findViewById(R.id.textMsg);
        TextView nameInput = view.findViewById(R.id.NameInput);
        Button buttonOK = view.findViewById(R.id.backButton);

        buttonOK.setEnabled(false);
        textMsg.setText(textMsg
                .getText()
                .toString()
                .replace("%SCORE%",String.valueOf(score)));

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                buttonOK.setEnabled(!nameInput.getText().toString().equals(""));
            }
        });

        // Setup any custom logic or listeners here
        buttonOK.setOnClickListener(v -> {
            // Handle button click
            String input = nameInput.getText().toString();
            ScoreDao dbScore = new ScoreDao(new CalculBaseHelper(requireContext(),"dbQuickMaths",1));

            Score EntityScore = new Score();
            EntityScore.setName(input);
            EntityScore.setScore(score);
            dbScore.create(EntityScore);
            dismiss(); // Close the dialog
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
        });

        return builder.create();
    }
}
