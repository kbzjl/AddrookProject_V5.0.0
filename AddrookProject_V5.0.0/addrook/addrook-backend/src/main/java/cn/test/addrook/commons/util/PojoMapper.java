package cn.test.addrook.commons.util;

import cn.test.addrook.pojo.entity.Addrook;
import cn.test.addrook.pojo.vo.AddrookVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 18:08
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface PojoMapper {
	PojoMapper INSTANCE = Mappers.getMapper(PojoMapper.class);

	/**
	 * <b>将视图对象转为实体对象</b>
	 * @param vo
	 * @return
	 */
	Addrook parseToEntity(AddrookVO vo);

	/**
	 * <b>将实体对象转为视图对象</b>
	 * @param Entity
	 * @return
	 */
	AddrookVO parseToVO(Addrook Entity);

	/**
	 * <b>将实体对象转为视图列表</b>
	 * @param entityList
	 * @return
	 */
	List<AddrookVO> parseToVOList(List<Addrook> entityList);

}
