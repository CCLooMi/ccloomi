package com.ccloomi.core.constant;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：HttpResponseStatus
 * 类 描 述：HTTP状态码
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月26日-下午9:01:00
 */
public class HttpResponseStatus {
	/** 继续 */
	public static final int SC_CONTINUE=100;
	/** 转换协议 */
	public static final int SC_SWITCHING_PROTOCOLS=101;
	/** 正常 */
	public static final int SC_OK=200;
	/** 已创建 */
	public static final int SC_CREATED=201;
	/** 接受 */
	public static final int SC_ACCEPTED=202;
	/** 非官方信息 */
	public static final int SC_NON_AUTHORITATIVE_INFORMATION=203;
	/** 无内容 */
	public static final int SC_NO_CONTENT=204;
	/** 重置内容 */
	public static final int SC_RESET_CONTENT=205;
	/** 局部内容 */
	public static final int SC_PARTIAL_CONTENT=206;
	/** 请求的文档可以在多个地方找到，并将在返回的文档中列出来。如果服务器有首选设置，首选项将会被列于定位响应头信息中。 */
	public static final int SC_MULTIPLE_CHOICES=300;
	/** 请求的文档在别的地方；文档新的URL会在定位响应头信息中给出。浏览器会自动连接到新的URL。 */
	public static final int SC_MOVED_PERMANENTLY=301;
	/** 找到 */
	public static final int SC_MOVED_TEMPORARILY=302;
	/** 参见其他信息 */
	public static final int SC_SEE_OTHER=303;
	/** 为修正 */
	public static final int SC_NOT_MODIFIED=304;
	/** 使用代理 */
	public static final int SC_USE_PROXY=305;
	/** 临时重定向 */
	public static final int SC_TEMPORARY_REDIRECT=307;
	/** 错误请求 */
	public static final int SC_BAD_REQUEST=400;
	/** 未授权 */
	public static final int SC_UNAUTHORIZED=401;
	/** 禁止 */
	public static final int SC_FORBIDDEN=403;
	/** 未找到 */
	public static final int SC_NOT_FOUND=404;
	/** 方法未允许 */
	public static final int SC_METHOD_NOT_ALLOWED=405;
	/** 无法访问 */
	public static final int SC_NOT_ACCEPTABLE=406;
	/** 代理服务器认证要求 */
	public static final int SC_PROXY_AUTHENTICATION_REQUIRED=407;
	/** 请求超时 */
	public static final int SC_REQUEST_TIMEOUT=408;
	/** 冲突 */
	public static final int SC_CONFLICT=409;
	/** 已经不存在 */
	public static final int SC_GONE=410;
	/** 需要数据长度 */
	public static final int SC_LENGTH_REQUIRED=411;
	/** 先决条件错误 */
	public static final int SC_PRECONDITION_FAILED=412;
	/** 请求实体过大 */
	public static final int SC_REQUEST_ENTITY_TOO_LARGE=413;
	/** 请求URI过长 */
	public static final int SC_REQUEST_URI_TOO_LONG=414;
	/** 不支持的媒体格式 */
	public static final int SC_UNSUPPORTED_MEDIA_TYPE=415;
	/** 请求范围无法满足 */
	public static final int SC_REQUESTED_RANGE_NOT_SATISFIABLE=416;
	/** 期望失败 */
	public static final int SC_EXPECTATION_FAILED=417;
	/** 内部服务器错误 */
	public static final int SC_INTERNAL_SERVER_ERROR=500;
	/** 未实现 */
	public static final int SC_NOT_IMPLEMENTED=501;
	/** 错误的网关 */
	public static final int SC_BAD_GATEWAY=502;
	/** 服务无法获得 */
	public static final int SC_SERVICE_UNAVAILABLE=503;
	/** 网关超时 */
	public static final int SC_GATEWAY_TIMEOUT=504;
	/**不支持的 HTTP 版本*/
	public static final int SC_HTTP_VERSION_NOT_SUPPORTED=505;
}
