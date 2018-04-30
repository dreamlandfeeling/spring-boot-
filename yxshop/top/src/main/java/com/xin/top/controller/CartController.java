package com.xin.top.controller;

import com.alibaba.fastjson.JSON;
import com.xin.top.dto.Constant;
import com.xin.top.dto.Result;
import com.xin.top.model.TbItem;
import com.xin.top.model.TbUser;
import com.xin.top.service.ItemService;
import com.xin.top.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String cartList(Model model, HttpServletRequest request){
        TbUser user = getUser(request);
        String cart = redisTemplate.opsForValue().get(Constant.REDIS_CART + user.getId());
        if(StringUtils.isNotBlank(cart)){
            List<TbItem> cartList= (List<TbItem>)JSON.parseArray(cart,TbItem.class);
            int totalPrice=0;
            for (TbItem tbItem : cartList) {
                totalPrice += tbItem.getPrice()*tbItem.getNum();
            }
            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("cartList",cartList);
        }
        model.addAttribute("username",user.getUsername());

        return "/cart/cart";
    }

    @PostMapping("/")
    public String addItemToCart(long productId,int num,Model model, HttpServletRequest request){
        TbUser user = getUser(request);
        //if(user==null){
        //    String requestURL = request.getHeader("Referer");
        //    System.err.println(requestURL);
        //    return "redirect:/user/login?redirect="+requestURL;
        //}
        addItemToCart(productId,num,request);
        model.addAttribute("username",user.getUsername());
        return "/cart/cartSuccess";
    }

    /**
     * 更新购物车商品数量
     * @param id
     * @param num
     * @param request
     * @return
     */
    @ResponseBody
    @PutMapping("/{id}")
    public String updateItemToCart(@PathVariable long id ,int num, HttpServletRequest request){
        TbUser user = getUser(request);
        String cart = redisTemplate.opsForValue().get(Constant.REDIS_CART + user.getId());
        if(StringUtils.isNotBlank(cart)){
            List<TbItem> cartList= (List<TbItem>)JSON.parseArray(cart,TbItem.class);
            for (TbItem tbItem : cartList) {
                if(tbItem.getId().longValue()==id){
                    tbItem.setNum(num);
                    break;
                }
            }
            redisTemplate.opsForValue().set(Constant.REDIS_CART+user.getId(),JSON.toJSONString(cartList));
        }
        return "";
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public String deleteItemToCart(@PathVariable long id , HttpServletRequest request){
        TbUser user = getUser(request);
        String cart = redisTemplate.opsForValue().get(Constant.REDIS_CART + user.getId());
        if(StringUtils.isNotBlank(cart)){
            List<TbItem> cartList = (List<TbItem>)JSON.parseArray(cart,TbItem.class);
            Iterator<TbItem> iterator = cartList.iterator();
            while(iterator.hasNext()){
                TbItem tbItem = iterator.next();
                if(tbItem.getId().longValue()==id){
                    iterator.remove();
                    break;
                }
            }
            redisTemplate.opsForValue().set(Constant.REDIS_CART+user.getId(),JSON.toJSONString(cartList));
        }
        return "";
    }

    /**
     * 添加商品到购物车,有重复商品就更新数量
     * @param productId
     * @param num
     * @param request
     */
    public void addItemToCart(long productId,int num,HttpServletRequest request){
        TbUser user = getUser(request);
        List<TbItem> cartList = new ArrayList<>();
        String cart = redisTemplate.opsForValue().get(Constant.REDIS_CART + user.getId());
        Result item = itemService.findItemByPrimaryKeyToCart(productId,num);
        TbItem tbItem = (TbItem)item.getData();
        if(StringUtils.isNotBlank(cart)){
            cartList = (List<TbItem>)JSON.parseArray(cart,TbItem.class);
            for (TbItem temp : cartList) {
                if(temp.getId().longValue() == tbItem.getId().longValue()){
                    temp.setNum(temp.getNum()+num);
                    redisTemplate.opsForValue().set(Constant.REDIS_CART+user.getId(),JSON.toJSONString(cartList));
                    return ;
                }
            }
        }
        cartList.add(tbItem);
        redisTemplate.opsForValue().set(Constant.REDIS_CART+user.getId(),JSON.toJSONString(cartList));
    }
}
