package com.example.travel.handle;

import com.example.travel.VO.ResultVO;
import com.example.travel.exception.TravelException;
import com.example.travel.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e){
        if(e instanceof TravelException){
            TravelException travelException=(TravelException) e;
            return ResultUtil.error(travelException.getCode(),travelException.getMessage());
        }else {
            System.out.print(e);
            return ResultUtil.error(-1, "未知错误");
        }
    }


}
