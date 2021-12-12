package cn.test.addrook.commons.base.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 15:20
 * @Version 1.0
 */

//@ApiModel 是swagger的注解 扫描Pagevo在swagger页面Models显示
@ApiModel(value = "分页视图",description = "分页视图")
public class PageVO<E> implements Serializable {
	private static final long serialVersionUID = -7957357283904427065L;
	@ApiModelProperty(value = "当前页码")
	private Integer pageNum;                    //当前页码
	@ApiModelProperty(value = "每页显示数量")
	private Integer pageSize;                   //每页显示数量
	@ApiModelProperty(value = "分页列表")
	private List<E> list;                       //分页列表
	@ApiModelProperty(value = "总数量")
	private Long totalSize;                     //总数量
	@ApiModelProperty(value = "总页数")
	private Integer totalPages;                    //总页数

	public PageVO() {}

	public PageVO(Integer pageNum, Integer pageSize) {
		if(pageNum != null && pageNum > 0){
			this.pageNum = pageNum;
		}else{
			this.pageNum = 1;
		}

		if(pageSize != null && pageSize >0 ){
			this.pageSize = pageSize;
		}else{
			this.pageSize = 10;
		}

	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
