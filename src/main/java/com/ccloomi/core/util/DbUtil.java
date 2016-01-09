package com.ccloomi.core.util;

import java.util.ArrayList;
import java.util.List;

import com.ccloomi.web.dbManager.entity.ColumnsEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DbUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月9日-上午11:48:38
 */
public class DbUtil {
	public static String getJavaType(ColumnsEntity column){
		if("bit".equalsIgnoreCase(column.getData_type())){
			if(column.getCharacter_maximum_length().longValueExact()==1){
				return "Boolean";
			}else{
				return "byte[]";
			}
		}else if("tinyint".equalsIgnoreCase(column.getData_type())){
			return "Integer";
		}else if("bool".equalsIgnoreCase(column.getData_type())
				||"boollean".equalsIgnoreCase(column.getData_type())){
			return "Boolean";
		}else if("smallint".equalsIgnoreCase(column.getData_type())){
			return "Integer";
		}else if("mediumint".equalsIgnoreCase(column.getData_type())){
			return "Integer";
		}else if("int".equalsIgnoreCase(column.getData_type())
				||"integer".equalsIgnoreCase(column.getData_type())){
			return "Integer";
		}else if("bigint".equalsIgnoreCase(column.getData_type())){
			return "Long";
		}else if("real".equalsIgnoreCase(column.getData_type())){
			return "Float";
		}else if("double".equalsIgnoreCase(column.getData_type())){
			return "Double";
		}else if("float".equalsIgnoreCase(column.getData_type())){
			return "Float";
		}else if("decimal".equalsIgnoreCase(column.getData_type())){
			return "BigDecimal";
		}else if("date".equalsIgnoreCase(column.getData_type())){
			return "Date";
		}else if("datetime".equalsIgnoreCase(column.getData_type())){
			return "Date";
		}else if("timestamp".equalsIgnoreCase(column.getData_type())){
			return "Timestamp";
		}else if("time".equalsIgnoreCase(column.getData_type())){
			return "Time";
		}else if("year".equalsIgnoreCase(column.getData_type())){
			return "String";
		}else if("char".equalsIgnoreCase(column.getData_type())){
			if("binary".equalsIgnoreCase(column.getCharacter_set_name())){
				return "byte[]";
			}else{
				return "String";
			}
		}else if("varchar".equalsIgnoreCase(column.getData_type())){
			if("binary".equalsIgnoreCase(column.getCharacter_set_name())){
				return "byte[]";
			}else{
				return "String";
			}
		}else if("binary".equalsIgnoreCase(column.getData_type())){
			return "byte[]";
		}else if("varbinary".equalsIgnoreCase(column.getData_type())){
			return "byte[]";
		}else if("tinyblob".equalsIgnoreCase(column.getData_type())){
			return "byte[]";
		}else if("blob".equalsIgnoreCase(column.getData_type())){
			return "byte[]";
		}else if("text".equalsIgnoreCase(column.getData_type())){
			return "String";
		}else if("mediumblog".equalsIgnoreCase(column.getData_type())){
			return "byte[]";
		}else if("mediumtext".equalsIgnoreCase(column.getData_type())){
			return "String";
		}else if("longblob".equalsIgnoreCase(column.getData_type())){
			return "byte[]";
		}else if("longtext".equalsIgnoreCase(column.getData_type())){
			return "String";
		}else if("enum".equalsIgnoreCase(column.getData_type())){
			return "String";
		}else if("set".equalsIgnoreCase(column.getData_type())){
			return "String";
		}else if("point".equalsIgnoreCase(column.getData_type())){
			return "Point";
		}else if("linestring".equalsIgnoreCase(column.getData_type())){
			return "Linestring";
		}else if("polygon".equalsIgnoreCase(column.getData_type())){
			return "Polygon";
		}else if("geometry".equalsIgnoreCase(column.getData_type())){
			return "Geometry";
		}else if("multipoint".equalsIgnoreCase(column.getData_type())){
			return "Multipoint";
		}else if("multilinestring".equalsIgnoreCase(column.getData_type())){
			return "Multilinestring";
		}else if("multipolygon".equalsIgnoreCase(column.getData_type())){
			return "Multipolygon";
		}else if("geometrycollection".equalsIgnoreCase(column.getData_type())){
			return "Geometrycollection";
		}else{
			throw new RuntimeException("未知的数据库类型");
		}
	}
	public static List<String> convertColumns2Properties(List<ColumnsEntity>columns,ColumnsFilter filter){
		List<String>ls=new ArrayList<>();
		for(ColumnsEntity column:columns){
			filter.doFilter(column);
			ls.add("/**"+column.getColumn_comment()+"*/");
			ls.add("private "+DbUtil.getJavaType(column)+" "+column.getColumn_name()+";");
		}
		return ls;
	}
}
