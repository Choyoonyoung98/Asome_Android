package com.example.asome.asome_sourcerequire.Chatting.Etc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * [OUTLINE]
 * 채팅창 하단 이미지 리스트 메뉴 터치 이벤트
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
	private OnItemClickListener listener;
	private GestureDetector gesture_detector;

	public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
		this.listener = listener;

		gesture_detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return true;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

				if (childView != null && RecyclerItemClickListener.this.listener != null) {
					RecyclerItemClickListener.this.listener.onItemLongClick(childView, recyclerView.getChildPosition(childView));
				}
			}
		});
	}

	@Override
	public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
		View childView = view.findChildViewUnder(e.getX(), e.getY());

		if (childView != null && listener != null && gesture_detector.onTouchEvent(e)) {
			listener.onItemClick(childView, view.getChildPosition(childView));
		}

		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
	}

	@Override
	public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

	}

	public interface OnItemClickListener {

		void onItemClick(View view, int position);

		void onItemLongClick(View view, int position);

	}
}