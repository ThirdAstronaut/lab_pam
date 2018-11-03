package lab.pam.pwr.lab1_pam;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;


        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            recyclerView.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false));

            ItemSnapHelper itemSnapHelper = new ItemSnapHelper(orientation);
            itemSnapHelper.attachToRecyclerView(recyclerView);

        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            ItemSnapHelper itemSnapHelper = new ItemSnapHelper(orientation);
            itemSnapHelper.attachToRecyclerView(recyclerView);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);


//        SnapRecyclerAdapter adapter = new SnapRecyclerAdapter(this, (ArrayList<Photo>) PhotosKeeper.getInstance().getItems(), height, width);
        Adapter adapter = new Adapter(this, (ArrayList<Photo>) PhotosKeeper.getInstance().getItems(), height, width);

        recyclerView.setAdapter(adapter);

    }
}