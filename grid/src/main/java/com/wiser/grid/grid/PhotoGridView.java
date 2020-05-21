package com.wiser.grid.grid;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wiser.grid.R;
import com.wiser.grid.base.BasePhotoAdapter;
import com.wiser.grid.config.PhotoConfig;
import com.wiser.grid.model.PhotoShowModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wiser
 * 
 *         图片带添加布局展示View
 */
public class PhotoGridView extends RecyclerView implements BasePhotoAdapter.OnPhotoItemClickListener {

	private int									addLayoutId, photoLayoutId, photoResId, deleteResId;

	private int									spanCount				= 4;

	private int									addMode					= BasePhotoAdapter.HEAD;

	private int									maxCounts				= 9;

	private PhotoShowAdapter<PhotoShowModel>	photoAdapter;

	private ArrayList<String> list					= new ArrayList<>();

	private OnPhotoGridListener					onPhotoGridListener;

	public PhotoGridView(@NonNull Context context) {
		super(context);
	}

	public PhotoGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PhotoGridView);
		addLayoutId = ta.getResourceId(R.styleable.PhotoGridView_pgv_addLayoutId, -1);
		photoLayoutId = ta.getResourceId(R.styleable.PhotoGridView_pgv_photoLayoutId, -1);
		photoResId = ta.getResourceId(R.styleable.PhotoGridView_pgv_photoResId, -1);
		deleteResId = ta.getResourceId(R.styleable.PhotoGridView_pgv_photoDeleteResId, -1);
		spanCount = ta.getInt(R.styleable.PhotoGridView_pgv_spanCount, spanCount);
		addMode = ta.getInt(R.styleable.PhotoGridView_pgv_addMode, BasePhotoAdapter.HEAD);
		maxCounts = ta.getInt(R.styleable.PhotoGridView_pgv_maxCounts, maxCounts);

		ta.recycle();

		initData();
	}

	private PhotoConfig photoConfig() {
		if (addLayoutId == -1 || photoLayoutId == -1) throw new InflateException("请设置展示的添加布局或者图片布局ID");
		if (photoResId == -1) throw new InflateException("请设置展示的图片布局内部图片控件ID");
		PhotoConfig photoConfig = new PhotoConfig();
		photoConfig.addLayoutId = addLayoutId;
		photoConfig.photoLayoutId = photoLayoutId;
		photoConfig.photoResId = photoResId;
		photoConfig.deleteResId = deleteResId;
		return photoConfig;
	}

	private void initData() {

		photoConfig();

		setLayoutManager(new GridLayoutManager(getContext(), spanCount));
		setAdapter(photoAdapter = new PhotoShowAdapter<>(getContext(), photoConfig(), addMode, maxCounts));

		photoAdapter.setOnPhotoItemClickListener(this);
		photoAdapter.setItems(new ArrayList<PhotoShowModel>());

	}

	public void setItems(List<PhotoShowModel> list) {
		if (photoAdapter != null) photoAdapter.setItems(list);
	}

	public PhotoShowAdapter<PhotoShowModel> adapter() {
		return photoAdapter;
	}

	public void setOnPhotoGridListener(OnPhotoGridListener onPhotoGridListener) {
		this.onPhotoGridListener = onPhotoGridListener;
	}

	// 设置数据
	public void setPhotoData(ArrayList<String> list) {
		if (list == null) return;
		this.list.addAll(list);
		if (photoAdapter != null) photoAdapter.addList(getModels(list));
	}

	// 删除
	public void delete(int position) {
		if (list == null || adapter() == null) return;
		list.remove(position);
		adapter().delete(position);
	}

	private ArrayList<PhotoShowModel> getModels(ArrayList<String> list) {
		if (list == null || list.size() == 0) return new ArrayList<>();
		ArrayList<PhotoShowModel> models = new ArrayList<>();
		for (String s : list) {
			PhotoShowModel model = new PhotoShowModel();
			model.path = s;
			models.add(model);
		}
		return models;
	}

	public void setMaxCounts(int maxCounts) {
		this.maxCounts = maxCounts;
		if (photoAdapter != null) photoAdapter.maxCounts = maxCounts;
	}

	public int getMaxCounts() {
		return maxCounts;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
    public void onAddPhotoClick(View view, int position) {
		if (onPhotoGridListener != null) onPhotoGridListener.onAddClick(view, position);
	}

	@Override
    public void onItemPhotoClick(View view, int position) {
		if (onPhotoGridListener != null) onPhotoGridListener.onItemClick(view, position);
	}

	@Override
    public void onItemDeleteClick(View view, int position) {
		if (onPhotoGridListener != null) onPhotoGridListener.onDeleteClick(view, position);
	}

	public void onDetach() {
		if (list != null) list.clear();
		list = null;
	}

	public interface OnPhotoGridListener {

		void onAddClick(View view, int position);

		void onItemClick(View view, int position);

		void onDeleteClick(View view, int position);
	}

}
