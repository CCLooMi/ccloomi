package com.ccloomi.web.projManager.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.projManager.bean.ProductBean;
import com.ccloomi.web.projManager.dao.ProductDao;
import com.ccloomi.web.projManager.dao.WhiteListDao;
import com.ccloomi.web.projManager.entity.ProductEntity;
import com.ccloomi.web.projManager.service.ProductService;
import com.ccloomi.web.system.entity.RoleEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ProductServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月25日-下午10:07:44
 */
@Service("productService")
@Transactional
public class ProductServiceImp extends GenericService<ProductEntity> implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private WhiteListDao whiteListDao;
	
	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		return byPage(map, ProductEntity.class,(sm,m)->{
			@SuppressWarnings("unchecked")
			List<RoleEntity>roles=(List<RoleEntity>) m.get("roles");
			List<String>rs=new ArrayList<>();
			List<String>ss=new ArrayList<>();
			for(RoleEntity role:roles){
				rs.add(role.getId());
				ss.add("?");
			}
			sm.SELECT("*")
			.FROM(new ProductEntity(), "p")
			.WHERE("p.accessType='public'")
			.OR("(p.accessType='private' AND p.idUser=?)", m.get("userid"))
			.OR("(p.accessType='protect' AND p.id IN (SELECT wl.idTarget FROM t_cc_white_list wl WHERE wl.type=0 AND wl.idRole IN ("+StringUtil.joinCollectionString(",", ss)+")))");
			sm.addValues(rs);
			});
	}
	
	@Override
	public Object saveProduct(ProductBean p) {
		ProductEntity product=new ProductEntity();
		product.setAccessType(p.getAccessType());
		product.setCode(p.getCode());
		product.setIntroduction(p.getIntroduction());
		product.setName(p.getName());
		product.setProductPIC(p.getProductPIC());
		product.setReleasePIC(p.getReleasePIC());
		product.setTestPIC(p.getTestPIC());
		product.setCreatedDate(new Date());
		product.setCreatedBy(p.getCreatedBy());
		product.setIdUser(p.getIdUser());
		Object id=productDao.save(product);
		Map<String, List<Object>>deleteUpdateAddMap=p.getWhiteListObject();
		whiteListDao.batchUpdateByDeleteUpdateAddMap(deleteUpdateAddMap, id);
		return id;
	}

	@Override
	public int updateProduct(ProductBean p) {
		ProductEntity product=new ProductEntity();
		product.setAccessType(p.getAccessType());
		product.setCode(p.getCode());
		product.setIntroduction(p.getIntroduction());
		product.setName(p.getName());
		product.setProductPIC(p.getProductPIC());
		product.setReleasePIC(p.getReleasePIC());
		product.setTestPIC(p.getTestPIC());
		product.setCreatedDate(new Date());
		product.setCreatedBy(p.getCreatedBy());
		product.setIdUser(p.getIdUser());
		product.setId(p.getId());
		int i=productDao.update(product);
		Map<String, List<Object>>deleteUpdateAddMap=p.getWhiteListObject();
		whiteListDao.batchUpdateByDeleteUpdateAddMap(deleteUpdateAddMap, product.getId());
		return i;
	}
}
