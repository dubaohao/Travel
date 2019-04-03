package com.example.travel.controller;

import com.example.travel.dao.Category;
import com.example.travel.dao.Hodometer;
import com.example.travel.dao.TGroup;
import com.example.travel.exception.TravelException;
import com.example.travel.form.HodometerForm;
import com.example.travel.service.CategoryService;
import com.example.travel.service.GroupServcice;
import com.example.travel.service.HodometerService;
import com.example.travel.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/sell/group")
public class SellGroupController {
    @Autowired
    private GroupServcice groupServcice;
    @Autowired
    private HodometerService hodometerService;

    /**
     * 订单列表
     * @param page 第几页, 从1页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam("gId") Integer gId,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<TGroup> groupDTOPage = groupServcice.findGUserGroupPage(gId,request);
        System.out.println(groupDTOPage);


        map.put("groupDTOPage", groupDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
//        orderDTOPage.getTotalPages()
        return new ModelAndView("group/list", map);
    }
    /*
     * @Author dubaohao
     * @Description //详情页
     * @Date 15:50 2018/11/26
     * @Param
     * @return
     **/
    @GetMapping("/detail")
    public ModelAndView detial(@RequestParam("groupNumber") String groupNumber){
        TGroup tGroup = groupServcice.findGroupNumber(groupNumber);
        System.out.println(tGroup);
        Hodometer hodometer = hodometerService.findOne(tGroup.getHoId());

        Map<String,Object>map = new HashMap<String,Object>();
        map.put("Hodometer",hodometer);
        map.put("GroupDetail",tGroup);
        log.info("map={}",map);
        return new ModelAndView("group/detail",map);
    }


}
