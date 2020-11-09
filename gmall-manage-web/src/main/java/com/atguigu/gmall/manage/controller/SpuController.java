package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin  //允许跨域
public class SpuController {

        @Reference
        SpuService spuService;

        /**
         * http://127.0.0.1:8091/spuList?catalog3Id=61
         * 获取商品spu集合
         */
        @RequestMapping("/spuList")
        @ResponseBody
       public List<PmsProductInfo> spuList(String catalog3Id){
           return  spuService.spuList(catalog3Id);
       }

    /**
     * 查询销售属性字典表
     * http://127.0.0.1:8091/baseSaleAttrList.
     */
    @RequestMapping("/baseSaleAttrList")
    @ResponseBody
   public List<PmsBaseSaleAttr> baseSaleAttrList(){
        return  spuService.baseSaleAttrList();
    }

    /**保存spu属性值
     *
     * @param pmsProductInfo  商品属性值
     * @return 保存成功与否
     */
    @RequestMapping("/saveSpuInfo")
    @ResponseBody
   public String saveSpuInfo( @RequestBody PmsProductInfo pmsProductInfo){
       return spuService.saveSpuInfo(pmsProductInfo);
   }
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile){
            //将图片存储到分布式存储文件中
            //存储返回来的url路径
        return "";
    }

}
