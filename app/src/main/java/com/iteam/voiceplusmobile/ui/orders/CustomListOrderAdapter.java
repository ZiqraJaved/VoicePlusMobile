package com.iteam.voiceplusmobile.ui.orders;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteam.voiceplusmobile.R;

import java.util.ArrayList;

public class CustomListOrderAdapter extends BaseAdapter {

    Context context;
    ArrayList customListDataModelArrayList;
    LayoutInflater layoutInflater = null;

    public CustomListOrderAdapter(Context activity, ArrayList customListDataModelArray) {
        this.context = activity;
        this.customListDataModelArrayList = customListDataModelArray;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return customListDataModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return customListDataModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder {
        public TextView order_part_model;
        public TextView order_part_name;
        public TextView order_status;
        ImageView order_item_icon;

    }

    ViewHolder order_details = null;


    // this method  is called each time for arraylist data size.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View vi = view;
        final int pos = position;
        if (vi == null) {
            order_details = new ViewHolder();
            vi = layoutInflater.inflate(R.layout.order_item, null);
            order_details.order_item_icon = (ImageView) vi.findViewById(R.id.order_item_icon);
            order_details.order_part_model = (TextView) vi.findViewById(R.id.order_part_model);
            order_details.order_part_name = (TextView) vi.findViewById(R.id.order_part_name);
            order_details.order_status = (TextView) vi.findViewById(R.id.order_status);

            CustomListOrderModel customListOrderModel = (CustomListOrderModel) customListDataModelArrayList.get(pos);

//            order_details.image_view.setImageResource(customListOrderModel.getImage_id());
            order_details.order_part_model.setText(customListOrderModel.getMobile_model());
            order_details.order_part_name.setText(customListOrderModel.getMobile_fault());
            order_details.order_status.setText(customListOrderModel.getOrder_status());

            vi.setTag(order_details);

        } else {

            order_details =(ViewHolder) vi.getTag();
        }
        return vi;
    }
}