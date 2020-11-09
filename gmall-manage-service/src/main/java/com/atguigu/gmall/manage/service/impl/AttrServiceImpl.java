package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.atguigu.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        return pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class )
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        //获取商品属性的id
        String id =  pmsBaseAttrInfo.getId();
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
        if (StringUtils.isBlank(id)) {//不存在id进行保存操作
            int insert = pmsBaseAttrInfoMapper.insert(pmsBaseAttrInfo);
            if (insert <= 0) {
                return "fail";
            }
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(id);
                int insert1 = pmsBaseAttrValueMapper.insert(pmsBaseAttrValue);
                if (insert <= 0) {
                    return "fail";
                }
            }
        }else{//存在id进行修改操作
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                Example example = new Example(PmsBaseAttrInfo.class);
                example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
                int insert1 = pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);
                if (insert1 <= 0) {
                    return "fail";
                }
            }
        }

        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return  pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }

    @Override
    public String deleteAttrValue(String id) {
        int i = pmsBaseAttrValueMapper.deleteByPrimaryKey(id);
        if(i<=0){
            return "false";
        }
        return "success";
    }
}
