package com.example.travel.VO;

import com.example.travel.dao.Hodometer;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class GOrderVO {
    private Integer orderId;
    private String orderNumber;
    private String groupNumber;
    private Integer hoId;
    private Integer gId;
    private Integer vId;
    private Integer orderNum;
    private Integer money;
    private String name;
    private String tel;
    private String wx;
    private String orderStatus;
    private String orderProgress;
    private Timestamp createDate;
    private Timestamp updateDate;
    private Date goDate;
    private List<GHodometerVO> gHodometerVOList;
}
