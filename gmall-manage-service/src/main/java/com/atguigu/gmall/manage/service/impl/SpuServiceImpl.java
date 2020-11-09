package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manage.mapper.*;
import com.atguigu.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {
        PmsProductInfo pmsProductInfo1 = new PmsProductInfo();
        pmsProductInfo1.setCatalog3Id(pmsProductInfo.getCatalog3Id());
        pmsProductInfo1.setDescription(pmsProductInfo.getDescription());
        pmsProductInfo1.setProductName(pmsProductInfo.getProductName());
        int insert = pmsProductInfoMapper.insert(pmsProductInfo1);
        if(insert<=0){
            return "false";
        }
        //获取刚刚插入的商品的id
        String id = pmsProductInfo1.getId();
        //获取商品属性的集合
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for(PmsProductSaleAttr pmsProductSaleAttr: pmsProductSaleAttrList){
            pmsProductSaleAttr.setProductId(id);
            //获取商品属性值的集合
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            //获取商品属性值
            for(PmsProductSaleAttrValue pmsProductSaleAttrValue : pmsProductSaleAttrValueList){
                pmsProductSaleAttrValue.setProductId(id);
                int insert1 = pmsProductSaleAttrValueMapper.insert(pmsProductSaleAttrValue);
                if(insert1<=0){
                    return "false";
                }
            }
            int insert1 = pmsProductSaleAttrMapper.insert(pmsProductSaleAttr);
            if(insert1<=0){
                return "fasle";
            }
        }
        return "success";
    }
}
