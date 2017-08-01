package id.co.telkom.pesonabeta2.fragment;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.co.telkom.pesonabeta2.CustomAdapter;
import id.co.telkom.pesonabeta2.DestinasiItem;
import id.co.telkom.pesonabeta2.ExpandableHeightGridView;
import id.co.telkom.pesonabeta2.GridViewAdapter;
import id.co.telkom.pesonabeta2.R;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import id.co.telkom.pesonabeta2.ImageItem;
/**
 * Created by telkom on 8/1/17.
 */

public class destinasiFragment extends DialogFragment{
    GridView gv;

    String[] judul = {"Yogyakarta", "Bali", "Pulau Komodo", "Gili Trawangan" , "Surabaya", "Jakarta","Bandung","Medan","Palangkaraya", "Pulau Seribu"};
    String[] view = {"115 Views","310 Views","110 Views","200 Views", "450 Views","110 Views","319 Views", "250 Views","100 Views","210 Views"};
    int[] images = {R.drawable.destinasi_1, R.drawable.destinasi_2, R.drawable.destinasi_3, R.drawable.destinasi_4, R.drawable.destinasi_5,
    R.drawable.destinasi_6, R.drawable.destinasi_7, R.drawable.destinasi_8, R.drawable.destinasi_9, R.drawable.destinasi_10};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_destinasi, null);
        gv=(GridView) rootView.findViewById(R.id.gridview_destinasi);

        /*getDialog().setTitle("Destinasi");*/
        CustomAdapter adapter = new CustomAdapter(getActivity(), images, judul, view);

        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {

                Toast.makeText(getActivity(), judul[pos], Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
