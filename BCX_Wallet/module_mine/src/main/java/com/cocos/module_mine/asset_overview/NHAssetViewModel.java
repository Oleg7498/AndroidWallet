package com.cocos.module_mine.asset_overview;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import com.cocos.bcx_sdk.bcx_api.CocosBcxApiWrapper;
import com.cocos.bcx_sdk.bcx_callback.IBcxCallBack;
import com.cocos.library_base.base.BaseViewModel;
import com.cocos.library_base.utils.AccountHelperUtils;
import com.cocos.library_base.utils.singleton.GsonSingleInstance;
import com.cocos.library_base.utils.singleton.MainHandler;
import com.cocos.module_mine.BR;
import com.cocos.module_mine.R;
import com.cocos.module_mine.entity.PropAssetModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author ningkang.guo
 * @Date 2019/2/25
 */
public class NHAssetViewModel extends BaseViewModel {

    public NHAssetViewModel(@NonNull Application application) {
        super(application);
    }


    public ObservableInt emptyViewVisible = new ObservableInt(View.GONE);

    public ObservableInt recyclerViewVisible = new ObservableInt(View.VISIBLE);

    public ObservableList<NHAssetItemViewModel> observableList = new ObservableArrayList<>();

    public ItemBinding<NHAssetItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.module_mine_item_prop_assets);

    public final BindingRecyclerViewAdapter<NHAssetItemViewModel> adapter = new BindingRecyclerViewAdapter<>();

    public void requestPropAssetsListData() {
        String accountName = AccountHelperUtils.getCurrentAccountName();
        List<String> wordView = new ArrayList<>();
        observableList.clear();
        CocosBcxApiWrapper.getBcxInstance().list_account_nh_asset(accountName, wordView, 1, 100, new IBcxCallBack() {
            @Override
            public void onReceiveValue(final String s) {
                final PropAssetModel propAssetModel = GsonSingleInstance.getGsonInstance().fromJson(s, PropAssetModel.class);
                MainHandler.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        if (!propAssetModel.isSuccess() || propAssetModel.data.size() <= 0) {
                            emptyViewVisible.set(View.VISIBLE);
                            recyclerViewVisible.set(View.GONE);
                            return;
                        }
                        for (PropAssetModel.PropAssetModelBean assetModelBean : propAssetModel.data) {
                            NHAssetItemViewModel itemViewModel = new NHAssetItemViewModel(NHAssetViewModel.this, assetModelBean);
                            observableList.add(itemViewModel);
                            emptyViewVisible.set(View.GONE);
                            recyclerViewVisible.set(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }
}