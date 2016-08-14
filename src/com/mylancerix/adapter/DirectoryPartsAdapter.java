package com.mylancerix.adapter;

import java.util.List;

import com.mylancerix.R;
import com.mylancerix.pojo.DirectoryParts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DirectoryPartsAdapter extends BaseAdapter {

	private List<DirectoryParts> list;
	private LayoutInflater layoutInflater;

	public DirectoryPartsAdapter(Context context, List<DirectoryParts> list) {
		super();
		this.list = list;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = layoutInflater.inflate(R.layout.item_directory_layout,
					parent, false);
		}

		DirectoryParts directoryParts = getDirectoryParts(position);
		TextView textView = (TextView) view.findViewById(R.id.directoryView);
		textView.setText(directoryParts.getName());

		return view;
	}

	private DirectoryParts getDirectoryParts(int position) {
		return (DirectoryParts) getItem(position);
	}

}
