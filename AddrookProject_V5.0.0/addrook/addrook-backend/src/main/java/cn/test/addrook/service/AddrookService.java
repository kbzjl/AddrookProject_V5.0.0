package cn.test.addrook.service;

import cn.test.addrook.commons.base.pojo.vo.PageVO;
import cn.test.addrook.pojo.entity.Addrook;
import cn.test.addrook.pojo.vo.AddrookVO;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 19:59
 * @Version 1.0
 */
public interface AddrookService {
	/**
	 * <b>根据手机号码查询对象视图</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	AddrookVO getByCellphone(String cellphone)throws Exception;

	/**
	 * <b>根据邮箱查询对象视图</b>
	 * @param email
	 * @return
	 * @throws Exception
	 */
	AddrookVO getByEmail(String email)throws Exception;

	/**
	 * <b>保存用户信息</b>
	 * @param addrookVO
	 * @return
	 * @throws Exception
	 */
	boolean saveAddrook(AddrookVO addrookVO)throws Exception;

	/**
	 * <b>查询分页视图</b>
	 * @param pageVO
	 * @param queryVO
	 * @return
	 */
	PageVO<AddrookVO> getBypage(PageVO<AddrookVO> pageVO, AddrookVO queryVO);

	/**
	 * <b>根据主键查询对象</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	AddrookVO getById(Long id)throws Exception;

	/**
	 * <b>修改用户信息</b>
	 * @param addrookVO
	 * @return
	 */
	boolean updateAddrook(AddrookVO addrookVO)throws Exception;

	/**
	 * <b>根据用户主键删除用户信息</b>
	 * @param id
	 * @return
	 */
	boolean deleteById(Long id);
}
