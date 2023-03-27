package com.szxs.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.szxs.dto.OrderByOrderNoDto;
import com.szxs.entity.DmOrder;
import com.szxs.entity.DmSchedulerSeat;
import com.szxs.entity.DmTrade;
import com.szxs.feign.OrderClient;
import com.szxs.feign.PayClient;
import com.szxs.service.PayService;
import com.szxs.util.AlipayConfig;
import com.szxs.util.EmptyUtil;
import com.szxs.vo.OrderByOrderNoVo;
import com.szxs.vo.PayOrderNoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayClient payClient;
    @Autowired
    private AlipayConfig alipayConfig;
    @Autowired
    private OrderClient orderClient;

    public boolean syncVerifyResult(Map<String, String[]> requesParms) throws Exception {
        Map<String,String> parms = new HashMap<String,String>();
        StringBuilder sb = new StringBuilder();
        for (Iterator iter = requesParms.keySet().iterator(); iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requesParms.get(name);
            String valueStr = "";
            for (int i =0;i < values.length;i++){
                valueStr = (i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + ",");

            }
            sb.append(name+"="+valueStr+"&");
            parms.put(name,valueStr);
        }
        System.out.println(sb.toString());

        boolean verify_result = AlipaySignature.rsaCheckV1(parms,alipayConfig.getAlipay_public_key(),alipayConfig.getCharset(),alipayConfig.getSign_type());
        return verify_result;
    }

    public boolean saveTrade(DmTrade dmTrade) {
        if (EmptyUtil.isNotEmpty(dmTrade)){
            if (payClient.savePay(dmTrade)){
                return true;
            }
        }
        return false;
    }

    public PayOrderNoVo queryPayOrderNo(String orderNo) {
        PayOrderNoVo payOrderNoVo = null;
        if (EmptyUtil.isNotEmpty(orderNo)){
            payOrderNoVo = payClient.payOrderNo(orderNo);
            payOrderNoVo.setCode("10000");
            payOrderNoVo.setMsg("Success");

            AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),alipayConfig.getApp_id(),
                    alipayConfig.getMerchant_private_key(),alipayConfig.getFormat(),
                    alipayConfig.getCharset(),alipayConfig.getAlipay_public_key(),alipayConfig.getSign_type());

            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            request.setReturnUrl(alipayConfig.getReturn_url());
            request.setNotifyUrl(alipayConfig.getNotify_url());

            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
            String voOrderNo = payOrderNoVo.getOrderNo();
            double totalAmount = payOrderNoVo.getTotalAmount();
            String itemName = payOrderNoVo.getItemName();
            model.setOutTradeNo(voOrderNo);
            model.setTotalAmount(String.valueOf(totalAmount));
            model.setSubject(itemName);
            request.setBizModel(model);

            try {
                AlipayTradePrecreateResponse response = alipayClient.execute(request);

                if (response.isSuccess()){
                    BeanUtils.copyProperties(response,payOrderNoVo);
                }
            }catch (AlipayApiException e){
                e.printStackTrace();
            }
        }
        return payOrderNoVo;
    }

    public boolean updateOrderType(DmOrder dmOrder) {
        if (payClient.updateOrderType(dmOrder)){
            return true;
        }
        return false;
    }

    public DmOrder queryOrder(String orderNo) {

        if (EmptyUtil.isNotEmpty(orderNo)){
            DmOrder dmOrder = payClient.queryOrder(orderNo);
            return dmOrder;
        }
        throw new RuntimeException("查无此订单号！");
    }

    public boolean updateSeatStatus(DmSchedulerSeat dmSchedulerSeat) {
        if (payClient.updateSeatStatus(dmSchedulerSeat)){
            return true;
        }
        return false;
    }


}
