package cn.blinkdagger.tipview.sample;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import cn.blinkdagger.tipview.TipMenuView;


public class TipMenuViewFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private TipMenuView tipMenuView;
    private PopupWindow mTipMenuWindow;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_tip_menu_view_layout, container, false);
        floatingActionButton = rootView.findViewById(R.id.tip_menu_view_fab);
        tipMenuView = rootView.findViewById(R.id.tip_menu_view);
        initView();
        return rootView;
    }

    private void initView(){
        tipMenuView.setItems("我的搜藏","我的评论","我的浏览");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOperateTipMenu();
            }
        });
    }

    private void showOperateTipMenu() {
        if (mTipMenuWindow == null) {
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_tip_menu_view,null);
            TipMenuView tipMenuView = (TipMenuView) contentView.findViewById(R.id.tip_menu);
            tipMenuView.setItems("我的搜藏","我的评论","我的浏览");
            tipMenuView.setOnItemClickListener(new TipMenuView.OnItemClickListener() {
                @Override
                public void onItemClick(String name, int position) {
                    if (mTipMenuWindow != null && mTipMenuWindow.isShowing()) {
                        mTipMenuWindow.dismiss();
                    }
                }
            });
            mTipMenuWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mTipMenuWindow.setContentView(contentView);
            mTipMenuWindow.setBackgroundDrawable(new BitmapDrawable());
            mTipMenuWindow.setFocusable(false);
            mTipMenuWindow.setOutsideTouchable(false);
        }
        if (!mTipMenuWindow.isShowing()) {
            int[] location = new int[2];
            floatingActionButton.getLocationOnScreen(location);
            // 计算popupwindow的高度
            mTipMenuWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int popHeight = mTipMenuWindow.getContentView().getMeasuredHeight();
            int popWidth = mTipMenuWindow.getContentView().getMeasuredWidth();
            // 显示在正上方
            mTipMenuWindow.showAtLocation(floatingActionButton, Gravity.NO_GRAVITY,
                    location[0] + (floatingActionButton.getWidth() - popWidth) / 2, location[1] - popHeight);
        } else {
            mTipMenuWindow.dismiss();
        }
    }

}
