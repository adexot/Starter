package com.oadex.starter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import static android.app.Activity.RESULT_OK;


public class FragmentOne extends Fragment {

    public final static int REQUEST_CODE = 2;

    VideoView videoPreview;
    private Button videoButton;

    private OnFragmentInteractionListener mListener;


    public FragmentOne() {
        // Required empty public constructor
    }

    public static FragmentOne newInstance() {
        FragmentOne fragment = new FragmentOne();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        videoButton = view.findViewById(R.id.videoButton);
        videoPreview = view.findViewById(R.id.videoPreview);
        videoButton.setOnClickListener(videoListener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentOne.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            Uri videoUri = data.getData();

            videoPreview.setVideoURI(videoUri);
            videoPreview.setVisibility(View.VISIBLE);
            videoPreview.setZOrderOnTop(true);
            videoPreview.start();
            videoButton.setText("Next");
            videoButton.setOnClickListener(addCaptionListener);
        }
        else
        {
            Snackbar.make(videoPreview, "no video taken", Snackbar.LENGTH_LONG)
                    .setAction("Ok", null)
                    .show();
        }

    }

    public View.OnClickListener videoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getActivity().getPackageManager()) != null ){
                startActivityForResult(intent, REQUEST_CODE);
            }
            else{
                Snackbar.make(videoPreview, "no video service", Snackbar.LENGTH_LONG)
                        .setAction("Ok", null)
                        .show();
            }
        }
    };

    public View.OnClickListener addCaptionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mListener.addCaption(TextFragment.newInstance(), null);
        }
    };

    public interface OnFragmentInteractionListener {
        void addCaption(Fragment fragment,Bundle bundle );
    }


}
