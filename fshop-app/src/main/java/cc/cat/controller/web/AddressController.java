package cc.cat.controller.web;

import cc.cat.entity.Address;
import cc.cat.service.AddressService;
import cc.cat.common.R;
import cc.cat.utils.HeadUtils;
import cc.cat.utils.HttpClientUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (Address)表控制层
 *
 * @author makejava
 * @since 2023-12-13 17:21:46
 */
@Slf4j
@RestController
@RequestMapping("address")
public class AddressController {
    /**
     * 服务对象
     */
    @Autowired
    private AddressService addressService;

    private String userId;

    /**
     * 增加地址
     * @param address
     * @param request
     * @return
     */
    @PostMapping(value = "/web/addAddress",produces = "application/json;charset=UTF-8")
    public R<String> w_addAddress(@RequestBody Address address,HttpServletRequest request){
        //通过请求头进行获取用户id
        userId = HeadUtils.getHeadUserId(request);
        address.setUserId(userId);
        //存入地址全
        address.setAddress(address.getProvince()+address.getCity()+address.getArea()+address.getStress());
        boolean save = addressService.save(address);
        if(save){
            return R.success("添加成功");
        }
        return R.error("添加失败");
    }

    /**
     * 删除用户地址
     * @param addressId
     * @param request
     * @return
     */
    @DeleteMapping("/web/deleteAddress/{addressId}")
    public R<String> w_deleteAddress(@PathVariable("addressId") String addressId,HttpServletRequest request){
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Address> addressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据地址id查询
        addressLambdaQueryWrapper.eq(Address::getAddressId,addressId);
        //根据用户id查询
        addressLambdaQueryWrapper.eq(Address::getUserId,userId);
        //删除
        boolean remove = addressService.remove(addressLambdaQueryWrapper);
        if(remove){
            return R.success("删除成功");
        }
        return R.error("删除失败");
    }


    /**
     * 修改地址
     * @param address
     * @param request
     * @return
     */
    @PostMapping("/web/updateAddress")
    public R<String> w_updateAddress(@RequestBody Address address,HttpServletRequest request){
        //通过请求头进行获取用户id
        userId = HeadUtils.getHeadUserId(request);
        address.setUserId(userId);
        //存入地址全
        address.setAddress(address.getProvince()+address.getCity()+address.getArea()+address.getStress());

        //根据地址id查询

        LambdaQueryWrapper<Address> addressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressLambdaQueryWrapper.eq(Address::getAddressId,address.getAddressId());
        addressLambdaQueryWrapper.eq(Address::getUserId,userId);
        //修改
        boolean save = addressService.saveOrUpdate(address,addressLambdaQueryWrapper);
//        boolean save = addressService.update(address,addressLambdaQueryWrapper);
        if(save){
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }



    /**
     * 查询用户的地址信息
     * @param request
     * @return
     */
    @GetMapping("/app/addressInfo")
    public R<List<Address>> addressInfo(HttpServletRequest request){
        userId = HeadUtils.getHeadUserId(request);
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, userId);
        List<Address> list = addressService.list(queryWrapper);
        return R.success(list);
    }

    /**
     * 分页查询用户地址信息
     */
    @GetMapping("/app/addressPage")
    public R<Page> addressPage(int page, int pageSize, HttpServletRequest request){
        userId = HeadUtils.getHeadUserId(request);
        Page<Address> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Address::getUserId, userId);
        Page<Address> pageAddress = addressService.page(pageInfo, queryWrapper);
        return R.success(pageAddress);
    }

    /**
     * 通过地址id查询地址信息(
     * @param id
     * @return
     */
    @GetMapping("/app/addressInfo/{id}")
    public R<Address> address(@PathVariable("id") Integer id, HttpServletRequest request){
        String userId = HeadUtils.getHeadUserId(request);

        LambdaQueryWrapper<Address> addressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //增加地址id判断
        addressLambdaQueryWrapper.eq(Address::getAddressId,id);
        //增加用户id判断
        addressLambdaQueryWrapper.eq(Address::getUserId,userId);
        Address address = addressService.getOne(addressLambdaQueryWrapper);
        if(address!=null){
            return R.success(address);
        }
        return R.error("地址不存在");
    }


}

