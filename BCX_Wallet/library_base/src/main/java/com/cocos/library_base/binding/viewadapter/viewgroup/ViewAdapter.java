package com.cocos.library_base.binding.viewadapter.viewgroup;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by guoningkang on 2017/6/18.
 */
public final class ViewAdapter {

    @BindingAdapter({"itemView", "observableList"})
    public static void addViews(ViewGroup viewGroup, final ItemBinding itemBinding, final ObservableList<IBindingItemViewModel> viewModelList) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews();
            for (IBindingItemViewModel viewModel : viewModelList) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        itemBinding.layoutRes(), viewGroup, true);
                binding.setVariable(itemBinding.variableId(), viewModel);
                viewModel.injecDataBinding(binding);
            }
        }
    }
}

