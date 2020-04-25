package com.example.COVID;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
public class ViewHolder extends RecyclerView.ViewHolder{
    // each data item is just a string in this case
    public TextView txtHeader;
    public View layout;

    public ViewHolder(View v) {
        super(v);
        layout = v;
        txtHeader = (TextView) v.findViewById(R.id.firstLine);
    }
}
