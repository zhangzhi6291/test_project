package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.bean.PmsProductInfo;

import java.util.List;

public interface SpuService {
    /**
     * 获取商品的spu
     * @param catalog3Id 商品属性值
     * @return  商品集合
     */
    List<PmsProductInfo> spuList(String catalog3Id);

    /**
     * 保存spu属性

     * @return 保存成功与否
     */
    List<PmsBaseSaleAttr> baseSaleAttrList();

    /**
     * 保存商品的属性值
     * @param pmsProductInfo  商品属性值
     * @return 保存成功与否
     */
    String saveSpuInfo(PmsProductInfo pmsProductInfo);
}
