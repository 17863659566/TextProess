package com.example.android.fragments;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HeadlinesFragment extends ListFragment {
	OnHeadlineSelectedListener mCallback;

	public interface OnHeadlineSelectedListener {
		public void onArticleSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("HeadlinesFragment", "onCreate()");
		int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1
				: android.R.layout.simple_list_item_1;
		setListAdapter(new ArrayAdapter<String>(getActivity(), layout,
				Ipsum.Headlines));
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("HeadlinesFragment", "onStart()");
		if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("HeadlinesFragment", "onPause()");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.i("HeadlinesFragment", "onResume()");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.i("HeadlinesFragment", "onStop()");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("HeadlinesFragment", "onDestroy()");
	}
	@Override
	public void onDetach() {
		super.onDetach();
		Log.i("HeadlinesFragment", "onDetach()");
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("HeadlinesFragment", "onActivityCreated()");
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i("HeadlinesFragment", "onAttach()");
		try {
			mCallback = (OnHeadlineSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("HeadlinesFragment", "onCreateView()");
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallback.onArticleSelected(position);
		getListView().setItemChecked(position, true);
	}
}
