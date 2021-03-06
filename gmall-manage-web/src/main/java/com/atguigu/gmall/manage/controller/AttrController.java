package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    /**
     *  http://127.0.0.1:8091/attrInfoList?catalog3Id=61
     */
    @RequestMapping("/attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        return  attrService.attrInfoList(catalog3Id);
    }

    /**
     * 增加属性值
     * @param pmsBaseAttrInfo
     * @return
     */
    @RequestMapping("/saveAttrInfo")
    @ResponseBody
   public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        String s = attrService.saveAttrInfo(pmsBaseAttrInfo);
        return s;
    }

    /**
     * 获取属性值
     * @param attrId
     * @return
     */
    @RequestMapping("/getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        return attrService.getAttrValueList(attrId);
    }

    @RequestMapping("/deleteAttrValue")
    @ResponseBody
    public String deleteAttrValue(String id){
        return attrService.deleteAttrValue(id);
    }

}
