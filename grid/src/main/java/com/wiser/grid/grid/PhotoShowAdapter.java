package com.wiser.grid.grid;

import android.content.Context;
import android.view.ViewGroup;

import com.wiser.grid.base.BasePhotoAdapter;
import com.wiser.grid.base.BasePhotoHolder;
import com.wiser.grid.config.PhotoConfig;
import com.wiser.grid.edit.PhotoHolder;


/**
 * @author Wiser
 * @param <T>
 *            数据
 *
 *            图片带添加图片布局适配器
 */
public class PhotoShowAdapter<T> extends BasePhotoAdapter<T, BasePhotoHolder> {

	private PhotoConfig photoConfig;

	public PhotoShowAdapter(Context context, PhotoConfig photoConfig) {
		super(context);
		this.photoConfig = photoConfig;
	}

	public PhotoShowAdapter(Context context, PhotoConfig photoConfig, int type, int maxCounts) {
		super(context, type, maxCounts);
		this.photoConfig = photoConfig;
		this.maxCounts = maxCounts;
	}

	@Override
    public BasePhotoHolder newViewHolder(ViewGroup viewGroup, int type) {
		if (type == HEAD || type == END) return new PhotoAddHolder(inflate(photoConfig.addLayoutId, viewGroup));
		else return new PhotoHolder(inflate(photoConfig.photoLayoutId, viewGroup),photoConfig);
	}

}
