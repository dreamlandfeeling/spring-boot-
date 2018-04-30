package com.xin.top.controller;

import com.alibaba.fastjson.JSON;
import com.xin.top.dto.Constant;
import com.xin.top.model.TbItem;
import com.xin.top.model.TbUser;
import com.xin.top.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{

    @GetMapping("/")
    public String order(String ids, Model model, HttpServletRequest request){
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
            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("cartList",orderList);
        }
        model.addAttribute("username",user.getUsername());
        return "/order/order-cart";
    }
}
