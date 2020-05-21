package com.wiser.grid.grid;

import android.view.View;

import androidx.annotation.NonNull;

import com.wiser.grid.base.BasePhotoHolder;
import com.wiser.grid.model.PhotoShowModel;

/**
 * @author Wiser
 *
 *         添加布局
 */
public class PhotoAddHolder extends BasePhotoHolder<PhotoShowModel> {

	public PhotoAddHolder(@NonNull View itemView) {
		super(itemView);
	}

	@Override
    public void bindData(PhotoShowModel model, final int position) {
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (adapter().getOnPhotoItemClickListener() != null) adapter().getOnPhotoItemClickListener().onAddPhotoClick(itemView, position);
			}
		});
	}
}
