package com.xin.top.controller;

import com.alibaba.fastjson.JSON;
import com.xin.top.dto.Constant;
import com.xin.top.dto.OrderInfo;
import com.xin.top.dto.Result;
import com.xin.top.model.*;
import com.xin.top.service.OrderService;
import com.xin.top.utils.CookieUtils;
import com.xin.top.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String order(@ModelAttribute("ids") String ids, Model model, HttpServletRequest request){
        TbUser user = getUser(request);
        String cart = redisTemplate.opsForValue().get(Constant.REDIS_CART + user.getId());
        List orderList=new ArrayList<TbItem>();
        if(StringUtils.isNotBlank(cart)){
            List<TbItem> cartList= (List<TbItem>) JSON.parseArray(cart,TbItem.class);
            int totalPrice=0;
            String[] split = ids.split(",");
            for (TbItem tbItem : cartList) {
                for (String id : split) {
                    if(String.valueOf(tbItem.getId().longValue()).equals(id)){
                        orderList.add(tbItem);
                        totalPrice += tbItem.getPrice()*tbItem.getNum();
                    }
                }

            }
            Result address = orderService.findUserAddress(user.getId());
            List<TbUserShipping> addrList = (List<TbUserShipping>) address.getData();
            model.addAttribute("addrList",addrList);
            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("cartList",orderList);
        }
        model.addAttribute("username",user.getUsername());
        return "/order/order-cart";
    }

    /**
     * 生成订单和相关信息,成功后清除购物车
     * @param orderInfo
     * @param model
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/")
    public String addOrder(OrderInfo orderInfo, Model model, HttpServletRequest request, HttpServletResponse response){
        TbUser user = getUser(request);
        TbOrder order = orderInfo.getOrder();
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        Result result = orderService.createOrder(orderInfo);
        if(result.getStatus()==200){
            redisTemplate.delete(Constant.REDIS_CART+user.getId());
        }
        TbOrder TbOrder = (TbOrder) result.getData();
        model.addAttribute("orderId",TbOrder.getOrderId());
        model.addAttribute("payment",TbOrder.getPayment());
        model.addAttribute("username",user.getUsername());
        return "/order/success";
    }


}
