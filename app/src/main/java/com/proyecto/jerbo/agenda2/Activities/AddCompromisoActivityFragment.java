package com.proyecto.jerbo.agenda2.Activities;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Fragments.DatePickerFragment;
import com.proyecto.jerbo.agenda2.R;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddCompromisoActivityFragment extends Fragment  {


    public AddCompromisoActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_compromiso, container, false);
    }

}
