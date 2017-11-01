package com.oadex.starter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class FragmentTwo extends Fragment {
    public final static int REQUEST_CODE = 1;

    private ImageView imageView;
    private Button cameraButton;
    private Bitmap bitmap;

    private OnFragmentInteractionListener mListener;



    public FragmentTwo() {
        // Required empty public constructor
    }

    public static FragmentTwo newInstance() {
        FragmentTwo fragment = new FragmentTwo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       return inflater.inflate(R.layout.fragment_two, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState){
        cameraButton = (Button)view.findViewById(R.id.cameraButton);
        imageView = (ImageView)view.findViewById(R.id.imagePreview);
        cameraButton.setOnClickListener(pictureListener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            this.bitmap = bitmap;
            imageView.setImageBitmap(bitmap);
            cameraButton.setText("Next");
            cameraButton.setOnClickListener(addCaptionListener);
        }
        else
        {
            Snackbar.make(imageView, "no picture taken", Snackbar.LENGTH_LONG)
                    .setAction("Ok", null)
                    .show();
        }

    }

    public View.OnClickListener pictureListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    public View.OnClickListener addCaptionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("picture", bitmap);
            mListener.addCaption(TextFragment.newInstance(), bundle);
        }
    };

    public interface OnFragmentInteractionListener {
        void addCaption(Fragment fragment,Bundle bundle );
    }

}
