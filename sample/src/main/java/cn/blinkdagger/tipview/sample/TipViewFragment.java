package cn.blinkdagger.tipview.sample;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import cn.blinkdagger.tipview.TipView;


public class TipViewFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private TipView tipView;
    private PopupWindow mTipWindow;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_tip_view_layout, container, false);
        floatingActionButton = rootView.findViewById(R.id.tipview_fab);
        tipView = rootView.findViewById(R.id.tip_view);

        initView();

        return rootView;
    }



    private void initView(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOperateTipMenu();
            }
        });
    }

    private void showOperateTipMenu() {
        if (mTipWindow == null) {
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_tip_view, null);
            mTipWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mTipWindow.setContentView(contentView);
            mTipWindow.setBackgroundDrawable(new BitmapDrawable());
            mTipWindow.setFocusable(false);
            mTipWindow.setOutsideTouchable(false);
        }
        if (!mTipWindow.isShowing()) {
            int[] location = new int[2];
            floatingActionButton.getLocationOnScreen(location);
            // 计算popupwindow的高度
            mTipWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int popHeight = mTipWindow.getContentView().getMeasuredHeight();
            int popWidth = mTipWindow.getContentView().getMeasuredWidth();
            // 显示在正上方
            mTipWindow.showAtLocation(floatingActionButton, Gravity.NO_GRAVITY,
                    location[0] + (floatingActionButton.getWidth() - popWidth) / 2, location[1] - popHeight);
        } else {
            mTipWindow.dismiss();
        }
    }


}
