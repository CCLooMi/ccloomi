package com.ccloomi.web.stock.service.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.web.stock.dao.LdayDao;
import com.ccloomi.web.stock.entity.LdayEntity;
import com.ccloomi.web.stock.service.LdayService;
/**
 * 类名：LdayServiceImp
 * 描述：
 * 作者： Chenxj
 * 日期：2016年3月29日 - 下午1:12:04
 */
@Service("ldayService")
public class LdayServiceImp extends GenericService<LdayEntity> implements LdayService{

	@Autowired
	private LdayDao ldayDao;
	
	@Override
	public void importLdayInfoFromFile(File ldayFile) {
		try {
			InputStreamReader read=new InputStreamReader(new FileInputStream(ldayFile), Charset.forName("GBK"));
			BufferedReader br=new BufferedReader(read);
			String textLine=null;
			String[] headLine=null;
			List<String[]>datalist=new ArrayList<>();
			while((textLine=br.readLine())!=null){
				String[]rs=textLine.split("\t| ");
				System.out.println(rs.length);
				if(rs.length>=7){
					datalist.add(rs);
				}else if(rs.length>1){
					headLine=rs;
				}
			}
			String stockId=headLine[0];
			read.close();
		} catch (Exception e) {
			log.error("日线数据导入出现异常", e);
		}
	}

	@Override
	public void importLdayInfoFromDir(File ldayDir) {
		File[]files=ldayDir.listFiles();
		for(File f:files){
			importLdayInfoFromFile(f);
		}
	}

}
