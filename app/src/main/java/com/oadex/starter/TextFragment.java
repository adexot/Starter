package com.oadex.starter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.oadex.starter.model.DataBaseHelper;
import com.oadex.starter.model.Starter;
import com.oadex.starter.model.Util;

public class TextFragment extends Fragment {
    private Button uploadButton;
    private TextView title;
    private TextView description;

    private OnFragmentInteractionListener mListener;

    public TextFragment() {
        // Required empty public constructor
    }


    public static TextFragment newInstance() {
        TextFragment fragment = new TextFragment();
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
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        title.requestFocus();
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

            Bitmap bitmap = getArguments().getParcelable("picture");
            String video = null;
            String title1 = title.getText().toString();
            String description1 = description.getText().toString();
            String picture = Util.encodePicture(bitmap);
            Starter item = new Starter(title1, description1, "", "", video);
            DataBaseHelper helper = new DataBaseHelper(getContext());
            helper.insert(item);
            helper.close();
            Snackbar.make(description, "picture successfully uploaded", Snackbar.LENGTH_LONG)
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
