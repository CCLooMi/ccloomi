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
import com.ccloomi.web.stock.dao.MinlineDao;
import com.ccloomi.web.stock.entity.MinlineEntity;
import com.ccloomi.web.stock.service.MinlineService;

/**
 * 类名：MinlineServiceImp
 * 描述：
 * 作者： Chenxj
 * 日期：2016年3月29日 - 下午1:13:18
 */
@Service("minlineService")
public class MinlineServiceImp extends GenericService<MinlineEntity> implements MinlineService{
	
	@Autowired
	private MinlineDao minlineDao;
	
	@Override
	public void importMinlineInfoFromMinlineFile(File minlineFile) {
		try {
			InputStreamReader read=new InputStreamReader(new FileInputStream(minlineFile), Charset.forName("GBK"));
			BufferedReader br=new BufferedReader(read);
			String textLine=null;
			String[] headLine=null;
			List<String[]>datalist=new ArrayList<>();
			while((textLine=br.readLine())!=null){
				String[]rs=textLine.split("\t| ");
				if(rs.length>1&&rs.length<7){
					headLine=rs;
				}else if(rs.length>7){
					datalist.add(rs);
				}
			}
			//查找最大和最小日期
			SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
			sm.SELECT_AS("MIN(m.datetime) ","min")
			.SELECT_AS("MAX(m.datetime) ", "max")
			.FROM(new MinlineEntity(), "m")
			.WHERE("m.idStock=?", headLine[0]);
			List<Map<String, Object>>lm=minlineDao.findBySQLGod(sm);
			Date min=null;
			Date max=null;
			if(lm.size()>0){
				min=(Date) lm.get(0).get("min");
				max=(Date) lm.get(0).get("max");
			}
			//处理数据
			datalist.remove(0);
			List<Object[]>batchArgs=new ArrayList<>();
			for(String[]rs:datalist){
				Object[]args=new Object[9];
				Date date=DateUtil.dateFromString(rs[0]+rs[1]);
				if(min==null||max==null||date.before(min)||date.after(max)){
					args[0]=StringUtil.buildUUID();
					args[1]=headLine[0];
					args[2]=date;
					args[3]=Float.valueOf(rs[2]);
					args[4]=Float.valueOf(rs[3]);
					args[5]=Float.valueOf(rs[4]);
					args[6]=Float.valueOf(rs[5]);
					args[7]=Integer.valueOf(rs[6]);
					args[8]=Double.valueOf(rs[7]);
					batchArgs.add(args);
				}
			}
			//有需要插入的数据
			if(batchArgs.size()>0){
				sm=SQLMakerFactory.getInstance().createMapker();
				sm.INSERT_INTO(new MinlineEntity(), "m")
				.INTO_COLUMNS("id","idStock","datetime","open","high","low","close","trading","turnover");
				sm.setBatchArgs(batchArgs);
				minlineDao.batchUpdateBySQLGod(sm);
			}
			read.close();
		} catch (Exception e) {
			log.error("分钟线导入出现异常", e);
		}
	}

	@Override
	public void importMinlineInfoFromMinlineDir(File minlineDir) {
		File[]files=minlineDir.listFiles();
		for(File f:files){
			importMinlineInfoFromMinlineFile(f);
		}
	}

}
