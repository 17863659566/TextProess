package com.example.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleFragment extends Fragment {
	final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		Log.i("ArticleFragment", "onCreateView()");
		return inflater.inflate(R.layout.article_view, container, false);
	}

	@Override
	public void onStart() {
		 super.onStart();
		 Log.i("ArticleFragment", "onStart()");
		Bundle args = getArguments();
		if(args != null) {
			updateArticleView(args.getInt(ARG_POSITION));
		}else  if(mCurrentPosition != -1){
			updateArticleView(mCurrentPosition);
		}
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		 Log.i("ArticleFragment", "onActivityCreated()");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("ArticleFragment", "onCreate()");
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("ArticleFragment", "onStop()");
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("ArticleFragment", "onResume()");
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("ArticleFragment", "onPause()");
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("ArticleFragment", "onDestroy()");
	}
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.i("ArticleFragment", "onDetach()");
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.i("ArticleFragment", "onDestroyView()");
	}
	public void updateArticleView(int position) {
		TextView article = (TextView) getActivity().findViewById(R.id.article);
		article.setText(Ipsum.Articles[position]);
		mCurrentPosition = position;
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt(ARG_POSITION, mCurrentPosition);
	}
}
