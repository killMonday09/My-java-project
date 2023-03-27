package com.szxs.controller;

import com.szxs.entity.DmOrder;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmTrade;
import com.szxs.service.PayService;
import com.szxs.util.AlxConstant;
import com.szxs.util.DmwConstant;
import com.szxs.util.EmptyUtil;
import com.szxs.util.VoUtil;
import com.szxs.vo.PayOrderNoVo;
import com.szxs.vo.RespDataVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@Log4j2
public class PayController {
    @Autowired
    private PayService payService;

    @GetMapping("/api/v/alipay/pay/{orderNo}")
    public PayOrderNoVo pay(@PathVariable String orderNo){
        if (EmptyUtil.isNotEmpty(orderNo)){
            PayOrderNoVo payOrderNoVo = payService.queryPayOrderNo(orderNo);
            return payOrderNoVo;
        }
        throw  new RuntimeException("支付异常！");
    }

    @RequestMapping(value = "/notify",method = RequestMethod.POST)
    @ResponseBody
    public String alinotify(HttpServletRequest req,HttpServletResponse resp) throws Exception{
        System.out.println("notify---------------------begin");

        try {
            Map requestParams = req.getParameterMap();
            req.setCharacterEncoding("UTF-8");

            String out_trade_no = req.getParameter("out_trade_no");
            String trade_no = req.getParameter("trade_no");

            String trade_status = req.getParameter("trade_status");
            System.out.println("notify-------out_trade_no:"+out_trade_no+",trade_no"+trade_no+",trade_status:"+trade_status);
            boolean verify_result = payService.syncVerifyResult(requestParams);
            System.out.println("notify------------------------verify_result:"+verify_result);
            if (verify_result){
//                orderService.updateOrder(new Order(out_trade_no,2,trade_no));
                payService.updateOrderType(new DmOrder(out_trade_no,trade_no,AlxConstant.Common.TWO,new Date()));
                payService.updateSeatStatus(new DmSchedulerSeat(out_trade_no,AlxConstant.Common.Three,new Date()));
                //查询订单号信息
                DmOrder dmOrder = payService.queryOrder(out_trade_no);
                DmTrade dmTrade = new DmTrade();
                dmTrade.setId(String.valueOf(dmOrder.getId()));
                dmTrade.setTradeNo(trade_no);
                dmTrade.setOrderNo(out_trade_no);
                dmTrade.setPayMethod(AlxConstant.Common.TWO);
                dmTrade.setAmount(dmOrder.getTotalAmount());
                dmTrade.setCreatedTime(dmOrder.getCreatedTime());
                dmTrade.setUpdatedTime(dmOrder.getUpdatedTime());
                payService.saveTrade(dmTrade);
                return "success";
            }else {
                return "fail";
            }
        }catch (Exception e){
            return "fail";
        }
    }

}
