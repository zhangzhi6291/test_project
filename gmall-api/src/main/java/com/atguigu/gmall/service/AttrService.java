package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;

import java.util.List;

public interface AttrService {
    /**
     * 根据三级属性查询
     * @param catalog3Id
     * @return
     */
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    /**
     * 增加属性值
     * @param pmsBaseAttrInfo
     * @return
     */
    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     * 获取属性值
     * @param attrId 属性
     * @return 属性值集合
     */
    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    /**
     * 删除属性值
     * @param id 属性值id
     * @return  删除成功与否
     */
     String deleteAttrValue(String id);
}
