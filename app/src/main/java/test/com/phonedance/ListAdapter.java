package test.com.phonedance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by droidcafe on 3/13/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ArrayList<String> gestureList;



    public ListAdapter(ArrayList<String> list) {
        gestureList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.textView.setText(gestureList.get(position));
    }


    @Override
    public int getItemCount() {
        return gestureList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);

            textView  = (TextView) itemView.findViewById(R.id.gesture_text);
        }
    }
}
