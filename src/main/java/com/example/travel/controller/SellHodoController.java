package com.example.travel.controller;

import com.example.travel.dao.Category;
import com.example.travel.dao.Hodometer;
import com.example.travel.exception.TravelException;
import com.example.travel.form.HodometerForm;
import com.example.travel.service.CategoryService;
import com.example.travel.service.HodometerService;
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
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/sell/hodometer")
public class SellHodoController {
    @Autowired
    private HodometerService hodometerService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 订单列表
     *
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
        Page<Hodometer> hodoDTOPage = hodometerService.findListPage(gId, request);


        map.put("hodoDTOPage", hodoDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
//        orderDTOPage.getTotalPages()
        return new ModelAndView("hodometer/list", map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "hoId", required = false) Integer hoId,
                              Map<String, Object> map) {
        if(hoId!=null) {
            Hodometer hodometer = hodometerService.findOne(hoId);
            map.put("hodometer", hodometer);
        }

        //查询所有的类目
        List<Category> categoryList = categoryService.findList(1);
        map.put("categoryList", categoryList);

        return new ModelAndView("hodometer/index", map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid HodometerForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/hodometer/index");
            return new ModelAndView("common/error", map);
        }
        log.info("form={}",form);

        Hodometer hodometer = new Hodometer();
        try {
            //如果productId为空, 说明是新增（有hoId），否则无
            if (form.getHoId()!=null) {
                hodometer = hodometerService.findOne(form.getHoId());
                BeanUtils.copyProperties(form, hodometer);
            }else{
                hodometer.setHoName(form.getHoName());
                hodometer.setCategory(form.getCategory());
                hodometer.setHoNumUp(form.getHoNumUp());
                hodometer.setHoNumDown(form.getHoNumDown());
                hodometer.setPicture(form.getPicture());
                hodometer.setPrice(form.getPrice());
                hodometer.setHoUrl(form.getHoUrl());
                hodometer.setStrategyUrl(form.getStrategyUrl());
            }

            hodometer.setGId(1);
            hodometer.setProgress("新行程");
            hodometer.setStatus("上架");
            log.info("hodometer={}",hodometer);
            hodometerService.update(hodometer);
        } catch (TravelException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/hodometer/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/hodometer/list?gId=1");
        return new ModelAndView("common/success", map);
    }
}
