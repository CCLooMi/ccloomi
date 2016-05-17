package com.ccloomi.web.system.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import com.ccloomi.web.system.constant.TemplateConstant;

import freemarker.cache.TemplateLoader;
/**
 * 类名：DBTemplateLoader
 * 描述：Freemarker数据库模板加载器
 * 作者： Chenxj
 * 日期：2016年4月27日 - 下午5:39:42
 */
public class DBTemplateLoader implements TemplateLoader{

	@Override
	public Object findTemplateSource(String name) throws IOException {
		//根据name从数据库加载Freemarker模板数据
		return TemplateConstant.getTemplateConstantMap().get(name);
	}

	@Override
	public long getLastModified(Object templateSource) {
		return -1;
	}

	@Override
	public Reader getReader(Object templateSource, String encoding) throws IOException {
		return new StringReader((String) templateSource);
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		
	}

}
