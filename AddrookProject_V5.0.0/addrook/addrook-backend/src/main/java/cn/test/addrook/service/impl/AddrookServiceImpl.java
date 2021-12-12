package cn.test.addrook.service.impl;

import cn.test.addrook.commons.base.pojo.vo.PageVO;
import cn.test.addrook.commons.base.util.RedisUtil;
import cn.test.addrook.commons.util.PojoMapper;
import cn.test.addrook.dao.AddrookDao;
import cn.test.addrook.pojo.entity.Addrook;
import cn.test.addrook.pojo.vo.AddrookVO;
import cn.test.addrook.service.AddrookService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 19:59
 * @Version 1.0
 */
@Service("addrookService")
public class AddrookServiceImpl implements AddrookService {
	@Autowired
	private AddrookDao addrookDao;
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * <b>根据手机号码查询对象视图</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	@Override
	public AddrookVO getByCellphone(String cellphone) throws Exception {
		//首先从Redis中查询 如果Redis中有返回addrookVO 如果没有就去mySqls数据库中查
		AddrookVO addrookVO = (AddrookVO) redisUtil.findFromRedis(cellphone,AddrookVO.class);
		if(addrookVO == null){
			// 使用数据持久层进行查询 使用MyBatis-plus 的crud提供的方法
			//创建查询对象QueryWapper
			QueryWrapper<Addrook> queryWrapper = new QueryWrapper<Addrook>();
			queryWrapper.eq("cellphone", cellphone);
			//进行查询
			Addrook addrook = addrookDao.selectOne(queryWrapper);
			//将实体对象转为视图对象
			addrookVO = PojoMapper.INSTANCE.parseToVO(addrook);
			//将视图对象在存入Redis中下次查询就存在了
			redisUtil.saveToRedis(cellphone, addrookVO);
			//返回视图对象
			return addrookVO;
		}
		return addrookVO;
	}

	/**
	 * <b>据邮箱地址查询对象视图</b>
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@Override
	public AddrookVO getByEmail(String email) throws Exception {
		//首先从Redis中查询 如果Redis中有返回addrookVO 如果没有就去mySqls数据库中查
		AddrookVO addrookVO = (AddrookVO) redisUtil.findFromRedis(email,AddrookVO.class);
		if(addrookVO == null){
			//创建查询对象QueryWapper
			QueryWrapper<Addrook> queryWrapper = new QueryWrapper<Addrook>();
			queryWrapper.eq("email", email);
			//进行查询
			Addrook addrook = addrookDao.selectOne(queryWrapper);
			//将实体对象转为视图对象
			addrookVO = PojoMapper.INSTANCE.parseToVO(addrook);
			//保存到redis中
			redisUtil.saveToRedis(email, addrookVO);
			return addrookVO;
		}
		return addrookVO;
	}

	/**
	 * <b>保存用户信息</b>
	 * @param addrookVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean saveAddrook(AddrookVO addrookVO) throws Exception {
		int count = addrookDao.insert(PojoMapper.INSTANCE.parseToEntity(addrookVO));
		if(count > 0){
			return true;
		}
		return false;
	}

	/**
	 * <b>查询分页视图</b>
	 * @param pageVO
	 * @param queryVO
	 * @return
	 */
	@Override
	public PageVO<AddrookVO> getBypage(PageVO<AddrookVO> pageVO, AddrookVO queryVO) {
		//创建查询对象  queryWrapper是mybatis plus中实现查询的对象封装操作类 Entity 对象封装操作类
		QueryWrapper<Addrook> queryWrapper = new QueryWrapper<Addrook>(PojoMapper.INSTANCE.parseToEntity(queryVO));
		//开启过滤器
		PageHelper.startPage(pageVO.getPageNum(),pageVO.getPageSize());
		//进行列表查询
		List<Addrook> entityList = addrookDao.selectList(queryWrapper);
		//创建PgeInfo 对象
		PageInfo<Addrook> pageInfo = new PageInfo<Addrook>(entityList);
		//提取数据
		pageVO.setList(PojoMapper.INSTANCE.parseToVOList(pageInfo.getList()));
		pageVO.setTotalSize(pageInfo.getTotal());
		pageVO.setTotalPages(pageInfo.getPages());
		return pageVO;
	}

	/**
	 * <b>根据主键查询对象</b>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public AddrookVO getById(Long id) throws Exception {
		Addrook addrook = addrookDao.selectById(id);
		return PojoMapper.INSTANCE.parseToVO(addrook);
	}

	/**
	 * <b>修改用户信息</b>
	 * @param addrookVO
	 * @return
	 */
	@Override
	public boolean updateAddrook(AddrookVO addrookVO) throws Exception {
		//通过id修改
		int count = addrookDao.updateById(PojoMapper.INSTANCE.parseToEntity(addrookVO));
		if (count > 0){
			return true;
		}
		return false;
	}

	/**
	 * <b>根据用户主键删除用户信息</b>
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteById(Long id) {
		int count = addrookDao.deleteById(id);
		if(count > 0){
			return true;
		}
		return false;
	}
}
