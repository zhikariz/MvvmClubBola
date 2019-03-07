package id.legible.codelabs.helmi.osg3clubbola.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import id.legible.codelabs.helmi.osg3clubbola.BuildConfig;
import id.legible.codelabs.helmi.osg3clubbola.R;

public class MyRatingView extends LinearLayout {
    private boolean ratingOnGoing = false;
    private Context mContext;
    private int mNumStars = 5;
    private int mRating = 0;

    public MyRatingView(Context context) {
        super(context);

        init(context, null);
    }

    public MyRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public MyRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyRatingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);

        final TypedArray styleAttributesArray = context.obtainStyledAttributes(attrs, R.styleable.MyRatingView);
        mNumStars = styleAttributesArray.getInteger(R.styleable.MyRatingView_numStars, mNumStars);
        mRating = styleAttributesArray.getInteger(R.styleable.MyRatingView_rating, mRating);

        styleAttributesArray.recycle();

        addRatingStars();
        updateViewAfterRatingChanged(mRating - 1);
    }

    private CheckBox getRatingView() {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CheckBox checkBox = (CheckBox) inflater.inflate(R.layout.rating_star_item, this, false);

        checkBox.setId(getChildCount());
        checkBox.setOnCheckedChangeListener(onCheckChangeListener);

        return checkBox;
    }

    private void addRatingStars() {
        if(mNumStars != 0) {
            for (int i = 0; i < mNumStars; i++) {
                addView(getRatingView());
            }
        }
    }

    private void updateViewAfterRatingChanged(int checkedPosition) {
        this.mRating = checkedPosition + 1;
        ratingOnGoing = true;
        if(checkedPosition < getChildCount()) {
            for(int i = 0; i < getChildCount(); i++) {
                ((CheckBox) getChildAt(i)).setChecked(i <= checkedPosition);
            }
        }
        ratingOnGoing = false;
    }

    private final CompoundButton.OnCheckedChangeListener onCheckChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @SuppressLint("ResourceType")
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(ratingOnGoing) {
                return;
            }

            if(buttonView.getId() >= 0 && buttonView.getId() <= getChildCount() - 1) {
                updateViewAfterRatingChanged(buttonView.getId());
            }

        }
    };
}
