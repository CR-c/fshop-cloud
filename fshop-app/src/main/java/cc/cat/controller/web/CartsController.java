package cc.cat.controller.web;

import cc.cat.common.R;
import cc.cat.entity.Carts;
import cc.cat.service.CartsService;
import cc.cat.utils.HeadUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Caret;
import java.util.List;

/**
 * (Carts)表控制层
 *
 * @author makejava
 * @since 2024-03-18 00:04:41
 */
@Slf4j
@RestController
@RequestMapping("carts")
public class CartsController {
    /**
     * 服务对象
     */
    @Autowired
    private CartsService cartsService;

    private String userId;

    /**
     * 查询某个用户的购物车信息
     * @return
     */
    @GetMapping("/app/findAllByUserId")
    public R<List<Carts>> app_findAllByUserId(HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);
        if(userId==null||userId.equals("")){
            return R.error("未登录");
        }
        LambdaQueryWrapper<Carts> queryWrapper = new LambdaQueryWrapper<>();
        //根据用户id进行查询
        queryWrapper.eq(Carts::getUserId, userId);
        List<Carts> list = cartsService.list(queryWrapper);
        return R.success(list);
    }




    /**
     * 小程序添加购物车
     *
     * @param carts
     * @param request
     * @return
     */
    @PostMapping("app/addCarts")
    public R<String> app_addCarts(@RequestBody Carts carts, HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);
        carts.setCheckFlag(carts.isCheckFlag());
        //判断是否有商品编号相同的商品在购物车中
        LambdaQueryWrapper<Carts> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Carts::getClothId, carts.getClothId());
        //查询所有商品编号相同的商品
        List<Carts> list = cartsService.list(queryWrapper);
        //遍历集合,找到与carts颜色和尺码都相同的商品
        for (Carts cart : list) {
            //判断颜色和尺码是否相同
            if (cart.getColor().equals(carts.getColor()) && cart.getSize().equals(carts.getSize())) {
                LambdaQueryWrapper<Carts> updQueryWrapper = new LambdaQueryWrapper<>();
                updQueryWrapper.eq(Carts::getColor,cart.getColor());
                updQueryWrapper.eq(Carts::getSize,cart.getSize());
                //更新数据库
                cartsService.update(carts,updQueryWrapper);
                return R.success("添加成功");
            }
        }
        carts.setUserId(userId);
        boolean save = cartsService.save(carts);
        if (save) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 删除购物车的某一个订单
     *
     * @param clothId
     * @param request
     * @return
     */
    @DeleteMapping("app/delCarts/{clothId}")
    public R<String> app_delCart(@PathVariable("clothId") String clothId, @RequestBody Carts carts, HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Carts> queryWrapper = new LambdaQueryWrapper<>();
        //根据用户id和商品id进行查询
        queryWrapper.eq(Carts::getUserId, userId);
        queryWrapper.eq(Carts::getClothId, clothId);
        //根据尺寸和颜色进行查询
        queryWrapper.eq(Carts::getSize,carts.getSize());
        queryWrapper.eq(Carts::getColor,carts.getColor());
        //通过查询结果进行删除
        boolean remove = cartsService.remove(queryWrapper);
        if (remove) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 删除多个购物车订单
     * @param clothIds
     * @param request
     * @return
     */
    @DeleteMapping("app/delCarts")
    public R<String> app_delCarts(@RequestBody String[] clothIds, HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);
        //遍历数组
        for (String clothId : clothIds) {
            LambdaQueryWrapper<Carts> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Carts::getUserId, userId);
            queryWrapper.eq(Carts::getClothId, clothId);
            boolean remove = cartsService.remove(queryWrapper);
            if (!remove) {
                return R.error("删除失败");
            }
        }
        return R.success("删除成功");
    }

    /**
     * 修改单个购物车订单
     * @param carts
     * @param request
     * @return
     */
    @PostMapping("app/updCart")
    public R<String> app_updCart(@RequestBody Carts carts, HttpServletRequest request) {

        userId = HeadUtils.getHeadUserId(request);
        carts.setUserId(userId);
        LambdaQueryWrapper<Carts> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Carts::getUserId, userId);
        queryWrapper.eq(Carts::getColor,carts.getColor());
        queryWrapper.eq(Carts::getSize,carts.getSize());
        boolean update = cartsService.update(carts,queryWrapper);
        if (update) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 修改多个购物车订单
     * @param carts
     * @param request
     * @return
     */
    @PostMapping("app/updCarts")
    public R<String> app_updCarts(@RequestBody Carts[] carts, HttpServletRequest request) {
        userId = HeadUtils.getHeadUserId(request);

        for (Carts cart : carts) {
            LambdaQueryWrapper<Carts> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Carts::getUserId, userId);
            queryWrapper.eq(Carts::getClothId, cart.getClothId());
            boolean update = cartsService.update(cart, queryWrapper);
            if (!update) {
                return R.error("修改失败");
            }
        }
        return R.success("修改成功");
    }

}

