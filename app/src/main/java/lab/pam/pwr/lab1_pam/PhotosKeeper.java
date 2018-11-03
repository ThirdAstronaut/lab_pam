package lab.pam.pwr.lab1_pam;

import java.util.ArrayList;
import java.util.List;

public class PhotosKeeper {
    private static PhotosKeeper instance;
    private List<Photo> items;

    private PhotosKeeper() {
        fillList();
    }

    public static PhotosKeeper getInstance() {
        if (instance == null) {
            instance = new PhotosKeeper();
        }
        return instance;
    }

    private void fillList() {
        items = new ArrayList<>();
        items.add(new Photo("Sample1", R.drawable.sample1));
        items.add(new Photo("Sample2", R.drawable.sample2));
        items.add(new Photo("Sample3", R.drawable.sample3));
        items.add(new Photo("Sample4", R.drawable.sample4));
        items.add(new Photo("Sample5", R.drawable.sample5));
        items.add(new Photo("Sample6", R.drawable.sample6));
        items.add(new Photo("Sample7", R.drawable.sample7));
        items.add(new Photo("Sample8", R.drawable.sample8));
        items.add(new Photo("Sample9", R.drawable.sample9));
        items.add(new Photo("Sample10", R.drawable.sample10));
        items.add(new Photo("Sample11", R.drawable.sample11));
        items.add(new Photo("Sample12", R.drawable.sample12));
    }

    public List<Photo> getItems() {
        return items;
    }
}
