package id.co.telkom.pesonabeta2;

/**
 * Created by telkom on 8/1/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DestinasiAdapter extends BaseAdapter {

    private Context c;
    private int[] images;
    private String[] judul;
    private String[] view;


    public DestinasiAdapter(Context ctx, int[] images, String[] judul, String[] view) {
        this.c = ctx;
        this.images = images;
        this.judul = judul;
        this.view = view;

    }

    @Override
    public int getCount() {
        return judul.length;
    }

    @Override
    public Object getItem(int i) {
        return judul[i];
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_destinasi, null);
        }

        //GET
        TextView judulDestinasi = convertView.findViewById(R.id.destinasi_judul);
        TextView viewDestinasi = convertView.findViewById(R.id.destinasi_view);
        ImageView imageDestinasi = convertView.findViewById(R.id.destinasi_image);

        judulDestinasi.setText(judul[i]);
        viewDestinasi.setText(view[i]);
        Glide.with(c)
                .load(images[i])
                .centerCrop()
                .into(imageDestinasi);
//        imageDestinasi.setImageResource(images[i]);
        return convertView;
    }
}
