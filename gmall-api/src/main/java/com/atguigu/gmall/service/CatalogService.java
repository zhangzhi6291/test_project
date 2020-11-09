package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.bean.PmsBaseCatalog2;
import com.atguigu.gmall.bean.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {
    /**
     * 查询一级
     * @return 一级目录的集合
     */
    public List<PmsBaseCatalog1> getCatalog1();

    /**
     * 根据目录1去查询目录2
     * @return 二级目录
     */
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    /**
     * 二级目录去查询3级目录
     * @param catalog2Id 二级目录
     * @return  三级目录集合
     */
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
