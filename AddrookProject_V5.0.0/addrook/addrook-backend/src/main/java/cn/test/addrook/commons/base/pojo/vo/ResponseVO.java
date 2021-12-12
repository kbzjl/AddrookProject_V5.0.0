package cn.test.addrook.commons.base.pojo.vo;

import cn.test.addrook.commons.base.pojo.enums.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.reactivestreams.Publisher;

import javax.xml.ws.Response;
import java.io.Serializable;
import java.util.Date;

/**
 * <b>系统视图响应信息</b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 15:42
 * @Version 1.0
 */
@ApiModel(value = "系统视图响应信息",description = "系统视图响应信息")
public class ResponseVO implements Serializable {
	private static final long serialVersionUID = 8177746889874060188L;
	@ApiModelProperty(value = "响应编码")
	private Integer code;               //响应编码
	@ApiModelProperty(value = "响应信息")
	private String message;             //响应信息
	@ApiModelProperty(value = "响应数据")
	private Object  data;               //响应数据

	private ResponseVO(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * <b>获得系统业务处理完成响应成功信息</b>
	 * @param message
	 * @return
	 */
	public static ResponseVO getSuccess(String message){
		return new ResponseVO(ResponseCode.RESPONSE_SUCCESS.getCode(),message,"");
	}
	/**
	 * <b>获得系统业务处理完成响应成功数据</b>
	 * @param data
	 * @return
	 */
	public static ResponseVO getSuccess(Object data){
		return new ResponseVO(ResponseCode.RESPONSE_SUCCESS.getCode(), ResponseCode.RESPONSE_SUCCESS.getRemark(), data);
	}

	/**
	 * <b>获得用户未进系统认证视图信息</b>
	 * @return
	 */
	public static ResponseVO getUnth(){
		return new ResponseVO(ResponseCode.RESPONSE_UNANTH.getCode(),ResponseCode.RESPONSE_UNANTH.getRemark(), "");

	}

	/**
	 * <b>获取系统业务处理逻辑错误信息</b>
	 * @param errorMsg
	 * @return
	 */
	public static ResponseVO getError(String errorMsg){
		return new ResponseVO(ResponseCode.RESPONSE_ERROR.getCode(),errorMsg, "");
	}

	/**
	 * <b>获取系统响应异常信息</b>
	 * @return
	 */
	public static ResponseVO getException(Exception e){
		return new ResponseVO(ResponseCode.RESPONSE_EXCEPTION.getCode(), e.getMessage(), "");

	}

}
