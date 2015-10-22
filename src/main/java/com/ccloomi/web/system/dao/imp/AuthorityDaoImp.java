package com.ccloomi.web.system.dao.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.dao.AbstractDao;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.web.system.dao.AuthorityDao;
import com.ccloomi.web.system.entity.AuthorityEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：AuthorityDaoImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月5日-下午4:27:39
 */
@Service("authorityDao")
public class AuthorityDaoImp extends AbstractDao<AuthorityEntity> implements AuthorityDao{
	
	@Override
	public List<Map<String, Object>> getAllAuthorityVisEdges() {
//		String sql="SELECT a.id AS 'from',a.pid AS 'to' FROM sys_authority a WHERE a.pid !=''";
		SQLMaker sm=new SQLMaker();
		sm.SELECT_AS("a.id", "from")
		.SELECT_AS("a.pid", "to")
		.FROM(new AuthorityEntity(), "a")
		.WHERE("a.pid!=''");
		return findBySQLGod(sm);
	}

	@Override
	public List<Map<String, Object>> getAllAuthorityVisNodes() {
//		String sql="SELECT id,name AS 'label','authority' AS 'group',url FROM sys_authority";
		SQLMaker sm=new SQLMaker();
		sm.SELECT("a.id")
		.SELECT_AS("a.name", "label")
		.SELECT_AS("a.authority", "group")
		.FROM(new AuthorityEntity(), "a");
		return findBySQLGod(sm);
	}

	@Override
	public int[] batchDeletePid(Collection<? extends Object> authorityids) {
		String sql="UPDATE sys_authority SET pid=NULL WHERE id=?";
		List<Object[]>batchArgs=new ArrayList<Object[]>();
		for(Object authorityid:authorityids){
			Object[]args={authorityid};
			batchArgs.add(args);
		}
		return jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	@Override
	public int[] batchAddPid(Collection<? extends Object[]> pids_authorityids) {
		String sql="UPDATE sys_authority SET pid=? WHERE id=?";
		List<Object[]>batchArgs=new ArrayList<Object[]>();
		for(Object[]os:pids_authorityids){
			batchArgs.add(os);
		}
		return jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
}
