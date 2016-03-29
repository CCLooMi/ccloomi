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
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.DateUtil;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.stock.dao.LdayDao;
import com.ccloomi.web.stock.entity.LdayEntity;
import com.ccloomi.web.stock.entity.MinlineEntity;
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
			datalist.remove(0);
			List<Object[]>batchArgs=new ArrayList<>();
			for(String[]rs:datalist){
				Object[]args=new Object[9];
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.SELECT("COUNT(*)")
				.FROM(new LdayEntity(), "l")
				.WHERE("l.date=?", DateUtil.dateFromString(rs[0]))
				.AND("l.idStock=?", headLine[0]);
				long l=ldayDao.countBySQLGod(sm);
				if(l<1){
					args[0]=StringUtil.buildUUID();
					args[1]=headLine[0];
					args[2]=DateUtil.dateFromString(rs[0]);
					args[3]=Float.valueOf(rs[1]);
					args[4]=Float.valueOf(rs[2]);
					args[5]=Float.valueOf(rs[3]);
					args[6]=Float.valueOf(rs[4]);
					args[7]=Integer.valueOf(rs[5]);
					args[8]=Double.valueOf(rs[6]);
					batchArgs.add(args);
				}
			}
			SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
			sm.INSERT_INTO(new MinlineEntity(), "m")
			.INTO_COLUMNS("id","idStock","date","open","high","low","close","trading","turnover");
			sm.setBatchArgs(batchArgs);
			ldayDao.batchUpdateBySQLGod(sm);
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
