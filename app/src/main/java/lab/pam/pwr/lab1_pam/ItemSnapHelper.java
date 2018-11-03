package lab.pam.pwr.lab1_pam;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import org.jetbrains.annotations.NotNull;

public final class ItemSnapHelper extends LinearSnapHelper {
    private Context context;
    private OrientationHelper helper;
    private Scroller scroller;
    private int maxScrollDistance;
    private static final float MILLISECONDS_PER_INCH = 100.0F;
    private static final int MAX_SCROLL_ON_FLING_DURATION_MS = 1000;
    private int orientation;
    public ItemSnapHelper(int orientation) {
        this.orientation = orientation;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        if (recyclerView != null) {
            this.context = recyclerView.getContext();
            this.scroller = new Scroller(this.context,(new DecelerateInterpolator()));
        } else {
            this.scroller = null;
            this.context = null;
        }

        super.attachToRecyclerView(recyclerView);
    }

    @Nullable
    public View findSnapView(@Nullable RecyclerView.LayoutManager layoutManager) {
        return this.findFirstView(layoutManager, this.helper(layoutManager));
    }

    @NotNull
    public int[] calculateDistanceToFinalSnap(@NotNull RecyclerView.LayoutManager layoutManager, @NotNull View targetView) {
        if(orientation == Configuration.ORIENTATION_LANDSCAPE)
            return new int[]{this.distanceToStart(targetView, this.helper(layoutManager)), 0};

        else
        return new int[]{0, this.distanceToStart(targetView, this.helper(layoutManager))};
    }

    @NotNull
    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        int[] out = new int[2];
        OrientationHelper var10000 = this.helper;
        if (this.helper != null) {
            if (this.maxScrollDistance == 0) {
                this.maxScrollDistance = (var10000.getEndAfterPadding() - var10000.getStartAfterPadding()) / 2;
            }

            Scroller var5 = this.scroller;
            if (this.scroller != null) {
                var5.fling(0, 0, velocityX, velocityY, -this.maxScrollDistance, this.maxScrollDistance, 0, 0);
            }

            out[0] = this.scroller != null ? this.scroller.getFinalX() : 0;
            out[1] = this.scroller != null ? this.scroller.getFinalY() : 0;
            return out;
        } else {
            return out;
        }
    }

    @Nullable
    protected RecyclerView.SmoothScroller createScroller(@Nullable final RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return super.createScroller(layoutManager);
        } else {
            Context var10000 = this.context;
            if (this.context != null) {
                final Context context = var10000;
                return (new LinearSmoothScroller(context) {
                    protected void onTargetFound(@NotNull View targetView, @NotNull RecyclerView.State state, @NotNull Action action) {
                        int[] snapDistance = ItemSnapHelper.this.calculateDistanceToFinalSnap(layoutManager, targetView);
                        int dx = snapDistance[0];
                        int dy = snapDistance[1];
                        int dt = this.calculateTimeForDeceleration(Math.abs(dx));
                        int time = Math.max(1, Math.min(1000, dt));
                        action.update(dx, dy, time, (Interpolator)this.mDecelerateInterpolator);
                    }

                    protected float calculateSpeedPerPixel(@NotNull DisplayMetrics displayMetrics) {
                        return 100.0F / (float)displayMetrics.densityDpi;
                    }
                });
            } else {
                return null;
            }
        }
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        int childStart = helper.getDecoratedStart(targetView);
        int containerStart = helper.getStartAfterPadding();
        return childStart - containerStart;
    }

    private View findFirstView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        if (layoutManager == null) {
            return null;
        } else {
            int childCount = layoutManager.getChildCount();
            if (childCount == 0) {
                return null;
            } else {
                int absClosest = Integer.MAX_VALUE;
                View closestView = null;
                int start = helper.getStartAfterPadding();
                int i = 0;

                for(; i < childCount; ++i) {
                    View child = layoutManager.getChildAt(i);
                    int childStart = helper.getDecoratedStart(child);
                    int absDistanceToStart = Math.abs(childStart - start);
                    if (absDistanceToStart < absClosest) {
                        absClosest = absDistanceToStart;
                        closestView = child;
                    }
                }

                return closestView;
            }
        }
    }

    private OrientationHelper helper(RecyclerView.LayoutManager layoutManager) {
        if ( Configuration.ORIENTATION_LANDSCAPE == orientation) {
            this.helper = OrientationHelper.createHorizontalHelper(layoutManager);
        } else if(/*this.helper == null ||  */Configuration.ORIENTATION_PORTRAIT == orientation) {
            this.helper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.helper;
    }

}
