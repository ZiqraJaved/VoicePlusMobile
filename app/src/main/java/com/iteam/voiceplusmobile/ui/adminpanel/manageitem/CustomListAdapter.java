package com.iteam.voiceplusmobile.ui.adminpanel.manageitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.pricing.CustomListDataModel;

import java.util.ArrayList;

/**
 * Created by Sanjeev k Saroj on 28/2/17.
 */

public class CustomListAdapter extends BaseAdapter {

    Context context;
    ArrayList customListDataModelArrayList;
    LayoutInflater layoutInflater = null;

    public CustomListAdapter(Context activity, ArrayList customListDataModelArray) {
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
        public TextView repair_part_model;
        public TextView repairing_part_name;
        ImageView image_view;

    }

    ViewHolder pricing_item = null;


    // this method  is called each time for arraylist data size.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View vi = view;
        final int pos = position;
        if (vi == null) {
            pricing_item = new ViewHolder();
            vi = layoutInflater.inflate(R.layout.item, null);
            pricing_item.image_view = (ImageView) vi.findViewById(R.id.pricing_icon);
            pricing_item.repair_part_model = (TextView) vi.findViewById(R.id.repairing_part_model);
            pricing_item.repairing_part_name = (TextView) vi.findViewById(R.id.repairing_part_name);
            CustomListDataModel customListDataModel = (CustomListDataModel) customListDataModelArrayList.get(pos);

            pricing_item.image_view.setImageResource(customListDataModel.getImage_id());
            pricing_item.repair_part_model.setText(customListDataModel.getPhone_model_name());
            pricing_item.repairing_part_name.setText(customListDataModel.getRepair_part_name());

            vi.setTag(pricing_item);

        } else {

            pricing_item = (ViewHolder) vi.getTag();
        }
        return vi;
    }
}