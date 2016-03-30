package com.ccloomi.web.stock.service.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.DateUtil;
import com.ccloomi.core.util.StringUtil;
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
				if(rs.length>=7){
					datalist.add(rs);
				}else if(rs.length>1){
					headLine=rs;
				}
			}
			//查找最大和最小日期
			SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
			sm.SELECT_AS("MIN(l.date) ","min")
			.SELECT_AS("MAX(l.date) ", "max")
			.FROM(new LdayEntity(), "l")
			.WHERE("l.idStock=?", headLine[0]);
			List<Map<String, Object>>lm=ldayDao.findBySQLGod(sm);
			Date min=null;
			Date max=null;
			if(lm.size()>0){
				min=(Date) lm.get(0).get("min");
				max=(Date) lm.get(0).get("max");
			}
			datalist.remove(0);
			List<Object[]>batchArgs=new ArrayList<>();
			for(String[]rs:datalist){
				Object[]args=new Object[9];
				Date date=DateUtil.dateFromString(rs[0]);
				if(min==null||max==null||date.before(min)||date.after(max)){
					args[0]=StringUtil.buildUUID();
					args[1]=headLine[0];
					args[2]=date;
					args[3]=Float.valueOf(rs[1]);
					args[4]=Float.valueOf(rs[2]);
					args[5]=Float.valueOf(rs[3]);
					args[6]=Float.valueOf(rs[4]);
					args[7]=Long.valueOf(rs[5]);
					args[8]=Double.valueOf(rs[6]);
					batchArgs.add(args);
				}
			}
			if(batchArgs.size()>0){
				sm.clean()
				.INSERT_INTO(new LdayEntity(), "l")
				.INTO_COLUMNS("id","idStock","date","open","high","low","close","trading","turnover");
				sm.setBatchArgs(batchArgs);
				ldayDao.batchUpdateBySQLGod(sm);
			}
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
