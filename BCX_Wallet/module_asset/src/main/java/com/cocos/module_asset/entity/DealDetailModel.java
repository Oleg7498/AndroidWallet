package com.cocos.module_asset.entity;

import java.io.Serializable;

/**
 * @author ningkang.guo
 * @Date 2019/4/2
 */
public class DealDetailModel implements Serializable {
    // 转账字段
    public String tx_id;
    public String amount;
    public String deal_type;
    public String from;
    public String to;
    public DealRecordModel.OpBean.MemoBean memo;
    public String block_header;

    public String amountAssetSymbol;
    public String feeAssetSymbol;

    // 合约调用字段
    public String caller;
    public String contract_name;
    public String function_name;
    public String params;

    //nh 资产相关
    public String nh_asset_id;

    // 公共字段
    public String fee;
    public String time;
    public double option;
}
