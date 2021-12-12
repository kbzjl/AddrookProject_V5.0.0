package cn.test.addrook.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import cn.test.addrook.commons.base.controller.BaseController;
import cn.test.addrook.commons.base.pojo.vo.PageVO;
import cn.test.addrook.commons.base.pojo.vo.ResponseVO;
import cn.test.addrook.commons.util.ValidationUtil;
import cn.test.addrook.pojo.entity.Addrook;
import cn.test.addrook.pojo.vo.AddrookVO;
import cn.test.addrook.service.AddrookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 20:03
 * @Version 1.0
 */
@Api(value = "个人通信录 - 控制层类",tags = "个人通讯录 - 交互接口")
@RestController("addrookController")
@RequestMapping("/addrook")
public class AddrookController extends BaseController {
	@Resource(name = "addrookService")
	private AddrookService addrookService;

	/**
	 * <b>校验该手机号能否使用</b>
	 * @param cellphone
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "效验该手机号码能否使用",tags = "效验该手机号码能否使用",httpMethod = "POST")
	@PostMapping("/check/cellphone")
	public ResponseVO checkCellphoneCanUse(String cellphone,Long id) throws Exception{
		//使用手机号码查询对象信息
		AddrookVO addrookVO = addrookService.getByCellphone(cellphone);
		//判断对象是否存在
		if (addrookVO == null){
			//如果对象不存在name可用
			return ResponseVO.getSuccess("该手机号码可用");
		}else{
			//如果对象存在 那么判断该对象的主键和所给定的主键是否相同 如果相同，那么可用
			if(addrookVO.getId().equals(id)){
				return ResponseVO.getSuccess("该手机号码可用");
			}
		}
		//否则不可以
		return ResponseVO.getError("该手机号码已被使用");
	}

	/**
	 * <b>校验电子邮箱是否可以用</b>
	 * @param email
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "效验该邮箱是否能使用",tags = "效验该邮箱是否能使用",httpMethod = "POST")
	@PostMapping("/check/email")
	public ResponseVO checkEmailCanUse(String email,Long id)throws  Exception{
		//使用邮箱地址查询对象信息
		AddrookVO addrookVO = addrookService.getByEmail(email);
		//判断对象是否存在
		if (addrookVO == null){
			//如果对象不存在name可用
			return ResponseVO.getSuccess("该邮箱地址可用");
		}else{
			//如果对象存在 那么判断该对象的主键和所给定的主键是否相同 如果相同，那么可用
			if(addrookVO.getId().equals(id)){
				return ResponseVO.getSuccess("该邮箱地址可用");
			}
		}
		//否则不可以
		return ResponseVO.getError("该电子邮箱已被使用");
	}

	/**
	 * <b>保存用户信息</b>
	 * @param addrookVO
	 * @return
	 */
	@ApiOperation(value = "保存用户信息",tags = "保存用户信息",httpMethod = "POST")
	@PostMapping("/save")
	public ResponseVO saveAddrook(@RequestBody AddrookVO addrookVO)throws Exception{
		//效验用户所填写的信息是否有效
		if(StrUtil.isNotBlank(addrookVO.getName()) &&
				ValidationUtil.isCellphone(addrookVO.getCellphone()) &&
				ValidationUtil.isPassword(addrookVO.getPassword())){
		//判断用户所给定的手机号码是否可以
			AddrookVO cellphoneVO = addrookService.getByCellphone(addrookVO.getCellphone());
			if(cellphoneVO != null){
				//手机号码被占用
				return ResponseVO.getError("该手机号码已被注册");
			}
			if(addrookVO.getEmail() != null && ValidationUtil.isEmail(addrookVO.getEmail())){

				AddrookVO emailVO = addrookService.getByEmail(addrookVO.getEmail());
				if (emailVO != null){
					return ResponseVO.getError("该电子邮件已被占用");
				}
			}
			//对登录密码进行加密
			addrookVO.setPassword(MD5.create().digestHex(addrookVO.getPassword()));
			//进行保存
			boolean isSuccess = addrookService.saveAddrook(addrookVO);
			if(isSuccess){
				return ResponseVO.getSuccess("保存成功");
			}else{
				return ResponseVO.getError("保存失败");
			}
		}
		return ResponseVO.getError("请填写有效的手机号码");
	}

	/**
	 * <b>用户登录</b>
	 * @param cellphone
	 * @param password
	 * @return
	 */
	@ApiOperation(value = "用户登录",tags = "用户登录",httpMethod = "POST")
	@PostMapping("/login")
	public ResponseVO loginAddrook(String cellphone,String password) throws Exception {
		//判断用户是否填写了有效的登录信息
		if (ValidationUtil.isCellphone(cellphone) && ValidationUtil.isPassword(password)){
			//根据手机号码查询对象信息
			AddrookVO addrookVO = addrookService.getByCellphone(cellphone);
			if (addrookVO != null){
				//对密码进行加密
				password = MD5.create().digestHex(password);
				//进行登录  获取的密码相同
				if (addrookVO.getPassword().equals(password)){
					//登录成功
					return ResponseVO.getSuccess("登录成功");
				}
			}
			return ResponseVO.getError("手机号码或密码错误");
		}
		return ResponseVO.getError("登录失败");
	}

	/**
	 * <b>根据查询视图进行分页查询</b>
	 * @param pageNum
	 * @param pageSize
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据查询视图进行分页查询",tags = "根据查询视图进行分页查询",httpMethod = "POST")
	@PostMapping("/page/{pageNum}/{pageSize}")
	public ResponseVO queryPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize,
	                            @RequestBody AddrookVO queryVO)throws Exception{
		//创建查询对象
		PageVO<AddrookVO> pageVO = new PageVO<AddrookVO>(pageNum,pageSize);
		//进行分页查询 获取分页查询对象
		pageVO = addrookService.getBypage(pageVO,queryVO);
		//返回成功的分页视图
		return ResponseVO.getSuccess(pageVO);
	}

	/**
	 * <b>根据主键查询用户信息</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据主键查询用户信息",tags = "根据主键查询用户信息",httpMethod = "POST")
	@PostMapping("/id{id}")
	public ResponseVO queryById(@PathVariable("id") Long id)throws Exception{
		return ResponseVO.getSuccess(addrookService.getById(id));
	}

	/**
	 * <b>修改用户信息</b>
	 * @param addrookVO
	 * @return
	 */
	@ApiOperation(value = "修改用户信息",tags = "修改用户信息",httpMethod = "POST")
	@PostMapping("/update")
	public ResponseVO updateAddrook(@RequestBody AddrookVO addrookVO)throws Exception{
		//判断所需要的数据是否有效
		if (StrUtil.isNotBlank(addrookVO.getName()) &&
				ValidationUtil.isCellphone(addrookVO.getCellphone()) &&
				ValidationUtil.isEmail(addrookVO.getEmail())){
			//校验手机号码是否重复
			AddrookVO cellphoneVO = addrookService.getByCellphone(addrookVO.getCellphone());
			//如果手机号码不为空 且 id也不相等 说明此时获取的信息不正确
			if (cellphoneVO != null && ! cellphoneVO.getId().equals(addrookVO.getId())) {
				return ResponseVO.getError("该手机已被注册");
			}
			//效验电子邮件是否重复
			AddrookVO emailVO = addrookService.getByEmail(addrookVO.getEmail());
			if (emailVO != null && !emailVO.getId().equals(addrookVO.getId())) {
					return ResponseVO.getError("该邮箱已被注册");
			}
				//进行保存
			boolean isSuccess = addrookService.updateAddrook(addrookVO);
			//如果结果为true 修改成功
			if (isSuccess){
					return ResponseVO.getSuccess("修改成功");
				} else {
					return ResponseVO.getError("修改失败");
				}
		}
		return ResponseVO.getError("用户信息修改失败");
	}

	/**
	 * <b>删除当前用户信息</b>
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "删除当前用户信息",tags = "删除当前用户信息",httpMethod = "GET")
	@GetMapping("/delete")
	public ResponseVO deleteById(Long id)throws Exception{
		boolean isSuccess = addrookService.deleteById(id);
		if(isSuccess){
			return ResponseVO.getSuccess("删除成功");
		}
		return ResponseVO.getError("用户信息删除失败");
	}

	/**
	 * <b>退出当前登录用户</b>
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "退出当前登录用户",tags = "退出当前登录用户",httpMethod = "GET")
	@GetMapping("/logout")
	public ResponseVO logout()throws Exception{
		return ResponseVO.getError("用户退出失败");
	}

}
