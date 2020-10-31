package com.dangdang.bookmall.order.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.dangdang.bookmall.order.entity.BookinfoEntity;
import com.dangdang.bookmall.order.entity.RecordEntity;
import com.dangdang.bookmall.order.entity.vo.BookinfoAndPriceVo;
import com.dangdang.bookmall.order.feign.ProductFeignService;
import com.dangdang.bookmall.order.service.BookinfoService;
import com.dangdang.bookmall.order.service.RecordService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.dangdang.bookmall.order.service.OrderinfoService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;

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

    @Autowired
    private RecordService recordService;


    /**
     * =============================后台接口============================
     */


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
     * 订单列表 没分页
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
            int integral  = productFeignService.scoreById(Integer.parseInt(bookinfoEntity.getBookId().toString()));
            integral = integral * bookinfoEntity.getBookNum();
            totalJF+=integral;
        };
        AtomicReference<Double> pricezj = new AtomicReference<>(0.00);
        List<BookinfoAndPriceVo> collect = bookinfoEntities.stream().map((item) -> {
            BookinfoAndPriceVo bookinfoAndPriceVo = new BookinfoAndPriceVo();
            BeanUtils.copyProperties(item, bookinfoAndPriceVo);
            double price = (item.getBookNum().doubleValue()) * item.getBookPrice().doubleValue();
            bookinfoAndPriceVo.setPriceSum(price);
            pricezj.updateAndGet(v -> v + price);
            return bookinfoAndPriceVo;
        }).collect(Collectors.toList());
        //TODO record/records/{orderId} 获取订单操
        List<OrderinfoEntity> orderinfoEntities = new ArrayList<>();
        orderinfoEntities.add(orderinfoEntity);
        return R.ok().put("order",orderinfoEntities).put("books", collect).put("totalJF",totalJF).put("pricezj",pricezj);
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
     * 新增订单
     */
    @PostMapping("/save")
    //@RequiresPermissions("order:orderinfo:save")
    public R saveOrder(@RequestBody OrderinfoEntity orderinfo){
        boolean result = orderinfoService.save(orderinfo);
        if(result){
            return R.ok();
        } else
            return R.error(400,"新增订单失败");
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
     * 商家 修改收货人的信息
     */
    @PostMapping("/updateReceiveAddress")
    public R updateReceiveAddress(@RequestBody OrderinfoEntity orderinfo) {
        if(orderinfo.getStatus()==1){
            orderinfoService.updateById(orderinfo);
            return R.ok();
        }
        else
            return R.error(400,"当前订单状态下无法进行地址的修改");
    }




    /**
     * =======================前台接口=======================
     */

    /**
     * 前台用
     * 根据状态查询订单
     * 4：申请退货，5：待退货，6：拒绝退货，7：退货退款完成，
     * 0：待付款，1：待发货，2：待收货，3：已收货，8：已关闭，9：已取消，10：待评价
     *  提交订单(0) 支付订单(1) 平台发货(2) 确认收货(3) 完成评价(8)
     *  商家对应的订单状态(0.待付款,1.待发货,2.已发货,3.已完成,8.已关闭 9.已关闭 10.已完成)
     *
     *
     *
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
     * 更新订单
     */
    @PostMapping("/deliver")
    public R deliver(@RequestBody OrderinfoEntity orderinfoEntity){
        //1 . 更新物流单号（前段获取）
        //2 . 更新发货时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        orderinfoEntity.setDeliverTime(new Date());
//        orderinfoService.updateById(orderinfoEntity);
        //3.更新订单zt
        OrderinfoEntity orderinfoId = orderinfoService.getById(orderinfoEntity.getId());
        Integer status = orderinfoId.getStatus();
        if(status==1){
            orderinfoEntity.setStatus(2);
            boolean result = orderinfoService.updateById(orderinfoEntity);
            if(result){
                RecordEntity record = new RecordEntity();
                record.setOrderId(orderinfoEntity.getId());
                record.setRemark("订单发货");
                record.setStatus(orderinfoId.getStatus());
                record.setTime(new Date());
                record.setUserId("admin");
                recordService.save(record);
                return R.ok();
            }else
            return R.error(400, "发货失败");
        } else {
            return R.error(400, "当前状态禁止发货");
        }

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


    /**
     * 用户下单
     */



}
