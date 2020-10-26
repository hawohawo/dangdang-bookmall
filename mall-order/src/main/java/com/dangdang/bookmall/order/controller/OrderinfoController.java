package com.dangdang.bookmall.order.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dangdang.bookmall.order.entity.BookinfoEntity;
import com.dangdang.bookmall.order.feign.ProductFeignService;
import com.dangdang.bookmall.order.service.BookinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.dangdang.bookmall.order.service.OrderinfoService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 20:58:04
 */
@RestController
@RequestMapping("order/orderinfo")
public class OrderinfoController {
    @Autowired
    private OrderinfoService orderinfoService;

    @Autowired
    private BookinfoService bookinfoService;

    //远程调用测试接口
    @Resource
    ProductFeignService productFeignService;

    /**
     * 订单列表
     */
    @GetMapping("/orders")
    //@RequiresPermissions("order:orderinfo:list")
    public R orders(@RequestParam Map<String, Object> params){

        List<OrderinfoEntity> list = orderinfoService.list();
        return R.ok().put("orders", list);
    }

    /**
     * 订单列表筛选（多条件模糊）
     */
    @GetMapping("/findOrders")
    //@RequiresPermissions("order:returninfo:list")
    public R findOrders(@RequestParam Map<String, Object> orderMap){
        String[] conditions = {"code","userPhone","status","timeXd"};
        Object value;
        for (String condition : conditions) {
            if ((value=orderMap.get(condition))==null){
                orderMap.put(condition,"");
            }
        }
        List<OrderinfoEntity> orderinfoEntityList = orderinfoService.findOrders(orderMap);
        return R.ok().put("findOrders", orderinfoEntityList);
    }

    /**
     * 订单详细信息
     */
    @GetMapping("/detail/{id}")
    //@RequiresPermissions("order:orderinfo:list")
    public R detail(@PathVariable Long id){

        OrderinfoEntity orderinfoEntity = orderinfoService.getById(id);
        //获取订单详情
        Map<String,Object> cmap = new HashMap<>();
        cmap.put("order_id",id);
        List<BookinfoEntity> bookinfoEntities = bookinfoService.listByMap(cmap);
        //计算积分
        //远程调用接口,<获取某个书本的积分>
        Integer totalJF = 0;
        for (BookinfoEntity bookinfoEntity : bookinfoEntities) {
            R r = productFeignService.scoreById(Integer.parseInt(bookinfoEntity.getId().toString()));
            Object info = r.get("info");
            int integral = Integer.valueOf(info.toString());
            totalJF+=integral;
        }
        return R.ok().put("order",orderinfoEntity).put("books", bookinfoEntities).put("totalJF",totalJF);
    }


    /**
     * 根据id查订单编号
     */
    @GetMapping("/orderid/{id}")
    //@RequiresPermissions("order:orderinfo:list")
    public R orderid(@PathVariable Long id){

        OrderinfoEntity orderinfoEntity = orderinfoService.getById(id);
        //获取订单编号
        return R.ok().put("code",orderinfoEntity.getCode());
    }

    /**
     * 根据id查订单状态
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @GetMapping("/status/{id}")
    //@RequiresPermissions("order:orderinfo:list")
    public R getStatusById(@PathVariable Long id){

        OrderinfoEntity orderinfoEntity = orderinfoService.getById(id);
        //获取订单编号
        return R.ok().put("status",orderinfoEntity.getStatus());
    }

    /**
     * 前台用
     * 根据状态查询订单
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @GetMapping("/orders/{userId}/{status}")
    //@RequiresPermissions("order:orderinfo:list")
    public R getOrdersByStatus(@PathVariable Long status,@PathVariable Long userId){

        Map<String,Object> map = new HashMap<String,Object>(){
            {
                put("user_id",userId);
                put("status",status);
            }
        };
        List<OrderinfoEntity> orderinfoEntities = orderinfoService.listByMap(map);
        //获取订单
        return R.ok().put("orders",orderinfoEntities);
    }


    /**
     * 新增订单
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderinfo:save")
    public R saveOrder(@RequestBody OrderinfoEntity orderinfo){
        boolean result = orderinfoService.save(orderinfo);
        if(result){
            return R.ok();
        } else
            return R.error(400,"新增订单失败");

    }




    /** ---------------------订单状态修改-------------------------------------------**/

    /**
     * 用户
     * 修改订单状态（待付款0-待发货1）
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateDFH")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateDFH(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==0){
            orderinfo.setStatus(1);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "付款失败");
        } else {
            return R.error(400, "付款失败");
        }
    }

    /**
     * 商家
     * 修改订单状态（待发货1-待收货2）
     * 0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateDSH")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateDSH(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==1){
            orderinfo.setStatus(2);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "发货失败");
        } else {
            return R.error(400, "发货失败");
        }
    }

    /**
     * 用户
     * 修改订单状态（待收货2-已收货3）
     * 0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateYSH")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateYSH(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==2){
            orderinfo.setStatus(3);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "确认收货失败");
        } else {
            return R.error(400, "确认收货失败");
        }
    }

    /**
     * 用户
     * 修改订单状态（申请退货），待付款和已收货的情况下才可以申请
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateSQTH")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateSQTH(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==0 || status==3){
            orderinfo.setStatus(4);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "申请退货退款失败");
        } else {
            return R.error(400, "申请退货退款失败");
        }
    }

    /**
     * 商家
     * 修改订单状态（商家同意退货）
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateDTH")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateDTH(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==4){
            orderinfo.setStatus(5);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "同意退货退款失败");
        } else {
            return R.error(400, "同意退货退款失败");
        }
    }

    /**
     * 商家
     * 修改订单状态（商家拒绝退货）
     * 0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateJJTH")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateJJTH(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==4){
            orderinfo.setStatus(6);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "拒绝退货退款失败");
        } else {
            return R.error(400, "拒绝退货退款失败");
        }
    }

    /**
     * 商家
     * 修改订单状态（退款退货完成），商家操作
     * 0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateTHWC")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateTHWC(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==5){
            orderinfo.setStatus(7);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "退货退款完成失败");
        } else {
            return R.error(400, "退货退款完成失败");
        }
    }

    /**
     * 用户
     * 修改订单状态（订单评价），处于已收货状态或者商家拒绝退货才可评价
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updatePJ")
    //@RequiresPermissions("order:orderinfo:update")
    public R updatePJ(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==3 || status==6){
            orderinfo.setStatus(10);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "确认收货失败");
        } else {
            return R.error(400, "确认收货失败");
        }
    }

    /**
     * 用户
     * 修改订单状态（取消订单），处于待付款状态才可取消
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateQXDD")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateQXDD(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==0){
            orderinfo.setStatus(9);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "申请退货退款失败");
        } else {
            return R.error(400, "申请退货退款失败");
        }
    }

    /**
     * 系统自动
     * 修改订单状态（订单关闭），处于评价完成后或者拒绝退货或者退货退款完成后才可取消
     *0：待付款，1：待发货，2：待收货，3：已收货，4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，8：已关闭，9：已取消，10：待评价
     */
    @PostMapping("/updateGBDD")
    //@RequiresPermissions("order:orderinfo:update")
    public R updateGBDD(@RequestBody OrderinfoEntity orderinfo){
        orderinfo = orderinfoService.getById(orderinfo.getId());
        Integer status = orderinfo.getStatus();
        if(status==10 || status==7 ){
            orderinfo.setStatus(8);
            boolean result = orderinfoService.updateById(orderinfo);
            if(result){
                return R.ok();
            }
            return R.error(400, "订单完成失败");
        } else {
            return R.error(400, "订单完成失败");
        }
    }






    /**
     * 远程调用接口测试
     * for ： 订单服务（this） -> 商品服务
     * detail ： 输出订单信息 和 商品的信息
     */

    @GetMapping("/books")
    public R test(){
        OrderinfoEntity orderinfoEntity = new OrderinfoEntity();
        orderinfoEntity.setId(1L);
        orderinfoEntity.setCode("1erf-we25-de4c-f78d");
        R book = productFeignService.setAndGetBook();
        return R.ok().put("orderinfo",orderinfoEntity).put("book",book.get("book"));

    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderinfo:info")
    public R info(@PathVariable("id") Long id){
		OrderinfoEntity orderinfo = orderinfoService.getById(id);

        return R.ok().put("orderinfo", orderinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderinfo:save")
    public R save(@RequestBody OrderinfoEntity orderinfo){
		orderinfoService.save(orderinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderinfo:update")
    public R update(@RequestBody OrderinfoEntity orderinfo){
		orderinfoService.updateById(orderinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderinfo:delete")
    public R delete(@RequestBody Long[] ids){
		orderinfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }



}
