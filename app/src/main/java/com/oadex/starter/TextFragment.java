package com.oadex.starter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TextFragment extends Fragment {
    private Button uploadButton;
    private TextView storyTextView;
    private TextView cameraTextView;

    private OnFragmentInteractionListener mListener;

    public TextFragment() {
        // Required empty public constructor
    }


    public static TextFragment newInstance(Bundle bundle) {
        TextFragment fragment = new TextFragment();
        Bundle args = bundle;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        uploadButton = view.findViewById(R.id.upload);
        storyTextView = view.findViewById(R.id.caption_text);
        cameraTextView = view.findViewById(R.id.camera_text);
        cameraTextView.requestFocus();
        uploadButton.setOnClickListener(uploadListerner);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    public View.OnClickListener uploadListerner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(storyTextView, "picture successfully uploaded..\n Thanks for blowing the whistle", Snackbar.LENGTH_LONG)
                    .setAction("Ok", null)
                    .show();


        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
