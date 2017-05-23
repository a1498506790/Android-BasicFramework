package com.airsaid.android_basicframework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airsaid.android_basicframework.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vlonjat Gashi (vlonjatg)
 */
public class StatusLayout extends RelativeLayout {

    private static final String TAG_LOADING = "StatusLayout.TAG_LOADING";
    private static final String TAG_EMPTY = "StatusLayout.TAG_EMPTY";
    private static final String TAG_ERROR = "StatusLayout.TAG_ERROR";

    final String CONTENT = "type_content";
    final String LOADING = "type_loading";
    final String EMPTY = "type_empty";
    final String ERROR = "type_error";

    LayoutInflater inflater;
    View view;
    LayoutParams layoutParams;
    Drawable currentBackground;

    List<View> contentViews = new ArrayList<>();

    RelativeLayout loadingStateRelativeLayout;
    ProgressBar loadingStateProgressBar;

    RelativeLayout emptyStateRelativeLayout;
    ImageView emptyStateImageView;
    TextView emptyStateTitleTextView;
    TextView emptyStateContentTextView;

    RelativeLayout errorStateRelativeLayout;
    ImageView errorStateImageView;
    TextView errorStateTitleTextView;
    TextView errorStateContentTextView;
    Button errorStateButton;

    int loadingStateProgressBarWidth;
    int loadingStateProgressBarHeight;
    int loadingStateBackgroundColor;

    int emptyStateImageWidth;
    int emptyStateImageHeight;
    int emptyStateTitleTextSize;
    int emptyStateContentTextSize;
    int emptyStateTitleTextColor;
    int emptyStateContentTextColor;
    int emptyStateBackgroundColor;

    int errorStateImageWidth;
    int errorStateImageHeight;
    int errorStateTitleTextSize;
    int errorStateContentTextSize;
    int errorStateTitleTextColor;
    int errorStateContentTextColor;
    int errorStateButtonTextColor;
    int errorStateBackgroundColor;

    private String state = CONTENT;

    public StatusLayout(Context context) {
        super(context);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StatusLayout);

        //Loading state attrs
        loadingStateProgressBarWidth =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_loadingProgressBarWidth, 108);

        loadingStateProgressBarHeight =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_loadingProgressBarHeight, 108);

        loadingStateBackgroundColor =
                typedArray.getColor(R.styleable.StatusLayout_loadingBackgroundColor, Color.TRANSPARENT);

        //Empty state attrs
        emptyStateImageWidth =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_emptyImageWidth, 308);

        emptyStateImageHeight =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_emptyImageHeight, 308);

        emptyStateTitleTextSize =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_emptyTitleTextSize, 15);

        emptyStateContentTextSize =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_emptyContentTextSize, 14);

        emptyStateTitleTextColor =
                typedArray.getColor(R.styleable.StatusLayout_emptyTitleTextColor, Color.BLACK);

        emptyStateContentTextColor =
                typedArray.getColor(R.styleable.StatusLayout_emptyContentTextColor, Color.BLACK);

        emptyStateBackgroundColor =
                typedArray.getColor(R.styleable.StatusLayout_emptyBackgroundColor, Color.TRANSPARENT);

        //Error state attrs
        errorStateImageWidth =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_errorImageWidth, 308);

        errorStateImageHeight =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_errorImageHeight, 308);

        errorStateTitleTextSize =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_errorTitleTextSize, 15);

        errorStateContentTextSize =
                typedArray.getDimensionPixelSize(R.styleable.StatusLayout_errorContentTextSize, 14);

        errorStateTitleTextColor =
                typedArray.getColor(R.styleable.StatusLayout_errorTitleTextColor, Color.BLACK);

        errorStateContentTextColor =
                typedArray.getColor(R.styleable.StatusLayout_errorContentTextColor, Color.BLACK);

        errorStateButtonTextColor =
                typedArray.getColor(R.styleable.StatusLayout_errorButtonTextColor, Color.BLACK);

        errorStateBackgroundColor =
                typedArray.getColor(R.styleable.StatusLayout_errorBackgroundColor, Color.TRANSPARENT);

        typedArray.recycle();

        currentBackground = this.getBackground();
    }

    @Override
    public void addView(@NonNull View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (child.getTag() == null || (!child.getTag().equals(TAG_LOADING) &&
                !child.getTag().equals(TAG_EMPTY) && !child.getTag().equals(TAG_ERROR))) {

//            contentViews.add(child);
            contentViews.addAll(getAllChildren(child));
        }
    }


    /**
     * save all of the direct and  indirect children views
     *
     * @param
     */
    private List<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            List<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        } else {
            if (((ViewGroup) v).getChildCount() == 0) {
                List<View> viewArrayList = new ArrayList<>();
                viewArrayList.add(v);
                return viewArrayList;
            } else {
                List<View> result = new ArrayList<>();

                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {

                    View child = vg.getChildAt(i);

                    List<View> viewArrayList = new ArrayList<>();
                    viewArrayList.add(v);
                    viewArrayList.addAll(getAllChildren(child));

                    result.addAll(viewArrayList);
                }
                return result;
            }
        }
    }

    /**
     * Hide all other states and show content
     */
    public void showContent() {
        switchState(CONTENT, null, null, null, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Hide all other states and show content
     *
     * @param skipIds Ids of views not to show
     */
    public void showContent(List<Integer> skipIds) {
        switchState(CONTENT, null, null, null, null, null, skipIds);
    }

    /**
     * Hide content and show the progress bar
     */
    public void showLoading() {
        switchState(LOADING, null, null, null, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Hide content and show the progress bar
     *
     * @param skipIds Ids of views to not hide
     */
    public void showLoading(List<Integer> skipIds) {
        switchState(LOADING, null, null, null, null, null, skipIds);
    }

    /**
     * Show empty view when there are not data to show
     */
    public void showEmpty(){
        switchState(EMPTY, null, null, null, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Show empty view when there are not data to show
     *
     * @param emptyImageDrawable Drawable to show
     * @param emptyTextTitle     Title of the empty view to show
     * @param emptyTextContent   Content of the empty view to show
     */
    public void showEmpty(Drawable emptyImageDrawable, String emptyTextTitle, String emptyTextContent) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Show empty view when there are not data to show
     *
     * @param emptyImageDrawable Drawable to show
     * @param emptyTextTitle     Title of the empty view to show
     * @param emptyTextContent   Content of the empty view to show
     * @param skipIds            Ids of views to not hide
     */
    public void showEmpty(Drawable emptyImageDrawable, String emptyTextTitle, String emptyTextContent, List<Integer> skipIds) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, skipIds);
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again
     */
    public void showError(View.OnClickListener onClickListener){
        switchState(ERROR, null, null, null, null, onClickListener, Collections.<Integer>emptyList());
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again
     *
     * @param errorImageDrawable Drawable to show
     * @param errorTextTitle     Title of the error view to show
     * @param errorTextContent   Content of the error view to show
     * @param errorButtonText    Text on the error view button to show
     * @param onClickListener    Listener of the error view button
     */
    public void showError(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, View.OnClickListener onClickListener) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, Collections.<Integer>emptyList());
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again
     *
     * @param errorImageDrawable Drawable to show
     * @param errorTextTitle     Title of the error view to show
     * @param errorTextContent   Content of the error view to show
     * @param errorButtonText    Text on the error view button to show
     * @param onClickListener    Listener of the error view button
     * @param skipIds            Ids of views to not hide
     */
    public void showError(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, View.OnClickListener onClickListener, List<Integer> skipIds) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds);
    }

    /**
     * Get which state is set
     *
     * @return State
     */
    public String getState() {
        return state;
    }

    /**
     * Check if content is shown
     *
     * @return boolean
     */
    public boolean isContent() {
        return state.equals(CONTENT);
    }

    /**
     * Check if loading state is shown
     *
     * @return boolean
     */
    public boolean isLoading() {
        return state.equals(LOADING);
    }

    /**
     * Check if empty state is shown
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return state.equals(EMPTY);
    }

    /**
     * Check if error state is shown
     *
     * @return boolean
     */
    public boolean isError() {
        return state.equals(ERROR);
    }

    private void switchState(String state, Drawable drawable, String errorText, String errorTextContent,
                             String errorButtonText, View.OnClickListener onClickListener, List<Integer> skipIds) {
        this.state = state;

        switch (state) {
            case CONTENT:
                //Hide all state views to display content
                hideLoadingView();
                hideEmptyView();
                hideErrorView();

                setContentVisibility(true, skipIds);
                break;
            case LOADING:
                hideEmptyView();
                hideErrorView();

                setLoadingView();
                setContentVisibility(false, skipIds);
                break;
            case EMPTY:
                hideLoadingView();
                hideErrorView();

                setEmptyView();
                if(drawable != null)  emptyStateImageView.setImageDrawable(drawable);
                if(errorText != null)   emptyStateTitleTextView.setText(errorText);
                if(errorTextContent != null)   emptyStateContentTextView.setText(errorTextContent);
                setContentVisibility(false, skipIds);
                break;
            case ERROR:
                hideLoadingView();
                hideEmptyView();

                setErrorView();
                if(drawable != null)  errorStateImageView.setImageDrawable(drawable);
                if(errorText != null)   errorStateTitleTextView.setText(errorText);
                if(errorTextContent != null)   errorStateContentTextView.setText(errorTextContent);
                if(errorButtonText != null)   errorStateButton.setText(errorButtonText);
                errorStateButton.setOnClickListener(onClickListener);
                setContentVisibility(false, skipIds);
                break;
        }
    }

    private void setLoadingView() {
        if (loadingStateRelativeLayout == null) {
            view = inflater.inflate(R.layout.layout_status_loading_view, null);
            loadingStateRelativeLayout = (RelativeLayout) view.findViewById(R.id.loadingStateRelativeLayout);
            loadingStateRelativeLayout.setTag(TAG_LOADING);

            loadingStateProgressBar = (ProgressBar) view.findViewById(R.id.loadingStateProgressBar);

            loadingStateProgressBar.getLayoutParams().width = loadingStateProgressBarWidth;
            loadingStateProgressBar.getLayoutParams().height = loadingStateProgressBarHeight;
            loadingStateProgressBar.requestLayout();

            //Set background color if not TRANSPARENT
            if (loadingStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundColor(loadingStateBackgroundColor);
            }

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);

            addView(loadingStateRelativeLayout, layoutParams);
        } else {
            loadingStateRelativeLayout.setVisibility(VISIBLE);
        }
    }

    private void setEmptyView() {
        if (emptyStateRelativeLayout == null) {
            view = inflater.inflate(R.layout.layout_status_empty_view, null);
            emptyStateRelativeLayout = (RelativeLayout) view.findViewById(R.id.emptyStateRelativeLayout);
            emptyStateRelativeLayout.setTag(TAG_EMPTY);

            emptyStateImageView = (ImageView) view.findViewById(R.id.emptyStateImageView);
            emptyStateTitleTextView = (TextView) view.findViewById(R.id.emptyStateTitleTextView);
            emptyStateContentTextView = (TextView) view.findViewById(R.id.emptyStateContentTextView);

            //Set empty state image width and height
            emptyStateImageView.getLayoutParams().width = emptyStateImageWidth;
            emptyStateImageView.getLayoutParams().height = emptyStateImageHeight;
            emptyStateImageView.requestLayout();

            emptyStateTitleTextView.setTextSize(emptyStateTitleTextSize);
            emptyStateContentTextView.setTextSize(emptyStateContentTextSize);
            emptyStateTitleTextView.setTextColor(emptyStateTitleTextColor);
            emptyStateContentTextView.setTextColor(emptyStateContentTextColor);

            //Set background color if not TRANSPARENT
            if (emptyStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundColor(emptyStateBackgroundColor);
            }

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);

            addView(emptyStateRelativeLayout, layoutParams);
        } else {
            emptyStateRelativeLayout.setVisibility(VISIBLE);
        }
    }

    private void setErrorView() {
        if (errorStateRelativeLayout == null) {
            view = inflater.inflate(R.layout.layout_status_error_view, null);
            errorStateRelativeLayout = (RelativeLayout) view.findViewById(R.id.errorStateRelativeLayout);
            errorStateRelativeLayout.setTag(TAG_ERROR);

            errorStateImageView = (ImageView) view.findViewById(R.id.errorStateImageView);
            errorStateTitleTextView = (TextView) view.findViewById(R.id.errorStateTitleTextView);
            errorStateContentTextView = (TextView) view.findViewById(R.id.errorStateContentTextView);
            errorStateButton = (Button) view.findViewById(R.id.errorStateButton);

            //Set error state image width and height
            errorStateImageView.getLayoutParams().width = errorStateImageWidth;
            errorStateImageView.getLayoutParams().height = errorStateImageHeight;
            errorStateImageView.requestLayout();

            errorStateTitleTextView.setTextSize(errorStateTitleTextSize);
            errorStateContentTextView.setTextSize(errorStateContentTextSize);
            errorStateTitleTextView.setTextColor(errorStateTitleTextColor);
            errorStateContentTextView.setTextColor(errorStateContentTextColor);
            errorStateButton.setTextColor(errorStateButtonTextColor);

            //Set background color if not TRANSPARENT
            if (errorStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundColor(errorStateBackgroundColor);
            }

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);

            addView(errorStateRelativeLayout, layoutParams);
        } else {
            errorStateRelativeLayout.setVisibility(VISIBLE);
        }
    }

    /**
     * set children visibility of StatusLayout
     *
     * @param visible
     * @param skipIds
     */
    private void setContentVisibility(boolean visible, List<Integer> skipIds) {
        boolean parentVisibleFlag = false;
        for (View v : contentViews) {
            // when visible is true,which means set content Layout, the skipIds component need to be hide
            if (visible) {
                if (skipIds.contains(v.getId())) {
                    v.setVisibility(View.INVISIBLE);
                }
            }
            if (!skipIds.contains(v.getId())) {
                v.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }

            //set parent visible flag to be visible when one child want to be visible
            if (v.getVisibility() == View.VISIBLE) {
                parentVisibleFlag = true;
            }
            //set  all of the parent to be visible
            if(parentVisibleFlag &&(contentViews.get(contentViews.size()-1)==v)){
                setParentToVisible(v);
            }
            int visibility = v.getVisibility();
        }
    }

    public void setParentToVisible(View v) {
        ViewParent mParent = v.getParent();
        if (mParent != null && (mParent instanceof ViewGroup)) {
            ((ViewGroup) mParent).setVisibility(View.VISIBLE);
            if (mParent.getParent() != null) {
                setParentToVisible((View) mParent);
            }
        }
    }


    private void hideLoadingView() {
        if (loadingStateRelativeLayout != null) {
            loadingStateRelativeLayout.setVisibility(GONE);

            //Restore the background color if not TRANSPARENT
            if (loadingStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundDrawable(currentBackground);
            }
        }
    }

    private void hideEmptyView() {
        if (emptyStateRelativeLayout != null) {
            emptyStateRelativeLayout.setVisibility(GONE);

            //Restore the background color if not TRANSPARENT
            if (emptyStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundDrawable(currentBackground);
            }
        }
    }

    private void hideErrorView() {
        if (errorStateRelativeLayout != null) {
            errorStateRelativeLayout.setVisibility(GONE);

            //Restore the background color if not TRANSPARENT
            if (errorStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundDrawable(currentBackground);
            }

        }
    }
}