package lab.pam.pwr.lab1_pam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
       // Photo modelObject = PhotosKeeper.getInstance().getItems().get(mPosition);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.images, collection, false);

       ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Glide.with(mContext).load(PhotosKeeper.getInstance().getItems().get(position).getResourceId()).into(imageView);

    //    imageView.setImageResource(PhotosKeeper.getInstance().getItems().get(mPosition).getResourceId());

        collection.addView(itemView);
        return itemView;
        /*public Object instantiateItem(ViewGroup container, int position) {
 View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
 imageView.setImageResource(mResources[position]);

container.addView(itemView);

return itemView;
 }*/
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return PhotosKeeper.getInstance().getItems().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Photo customPagerEnum = PhotosKeeper.getInstance().getItems().get(position);

        return mContext.getString(customPagerEnum.getResourceId());
    }




}/*class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ModelObject customPagerEnum = ModelObject.values()[position];

        return mContext.getString(customPagerEnum.getTitleResId());
    }

}
*/