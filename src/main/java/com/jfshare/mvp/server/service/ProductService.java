package com.jfshare.mvp.server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BaseTermQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RegexpQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.index.search.MultiMatchQuery.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.dao.TbProductDao;
import com.jfshare.mvp.server.dao.TbProductItemDao;
import com.jfshare.mvp.server.elasticsearch.ESProduct;
import com.jfshare.mvp.server.elasticsearch.repository.ESProductRepository;
import com.jfshare.mvp.server.mapper.TbProductDetailMapper;
import com.jfshare.mvp.server.model.Product;
import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProduct;
import com.jfshare.mvp.server.model.TbProductDetailExample;
import com.jfshare.mvp.server.model.TbProductDetailWithBLOBs;
import com.jfshare.mvp.server.model.TbProductExample;
import com.jfshare.mvp.server.model.TbProductItem;
import com.jfshare.mvp.server.model.TbProductSurvey;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;
import com.jfshare.mvp.server.utils.DateUtils;
import com.jfshare.mvp.server.utils.FileOpUtil;

@Service
public class ProductService {
	private final static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private TbProductDao tbProductDao;
	@Autowired
	private TbProductDetailMapper tbProductDetailMapper;
	@Autowired
	private TbProductItemDao tbProductItemDao;
	@Autowired
	private ProductDetailService productDetailService;
	@Autowired
	private ESProductRepository esProductRepository;
	
	/**
	 * 同步es中的商品信息
	 * @param syncAll 是否全部同步
	 * @param productIds 商品ID
	 */
	public void syncESProduct(boolean syncAll, String... productIds) {
		if (syncAll) {
			esProductRepository.deleteAll();
		} else {
			for (String productId : productIds) {
				esProductRepository.deleteById(productId);
			}
		}
		TbProductExample example = new TbProductExample();
		example.createCriteria()
			   .andProductIdIn(Arrays.asList(productIds))
			   .andActiveStateEqualTo(Constant.PRODUCT_STATE_ONSELL);
		List<TbProduct> tbproducts =  tbProductDao.selectByExample(example);
		List<ESProduct> esProducts = new ArrayList<>();
		for (TbProduct tbProduct : tbproducts) {
			ESProduct esProduct = new ESProduct(tbProduct.getProductId(), 
					tbProduct.getProductName(), Double.valueOf(tbProduct.getCurPrice()));
			esProducts.add(esProduct);
		}
		esProductRepository.saveAll(esProducts);
	}
	
	/**
	 * 查询ES中的商品信息, 模糊查询
	 * @param productName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<ESProduct> queryESProduct(String productName, int pageIndex, int pageSize) {
		
		return esProductRepository.search(QueryBuilders.queryStringQuery(productName), PageRequest.of(pageIndex, pageSize));
	}

	//根据条件搜索商品信息
	public List<TbProductSurvey> productSurveyQuery(String param,Integer itemNo,Integer activeState, Integer curpage,
			Integer percount) {
		ProductSurveyQueryParam productParam = new ProductSurveyQueryParam();
		if (!StringUtils.isEmpty(param)) {
			productParam.setParam(param);
		}
		if(itemNo >= 0) {
			productParam.setItemNo(itemNo);
		}
		if(activeState >= 0) {
			productParam.setActiveState(activeState);
		}
		PageHelper.startPage(curpage, percount);
		//处理图片  列表只返回一张图片
		List<TbProductSurvey> productSurveyQuery = tbProductDao.productSurveyQuery(productParam);
		for (TbProductSurvey tbProductSurvey : productSurveyQuery) {
			String imgKey = tbProductSurvey.getImgKey();
			if(!StringUtils.isEmpty(imgKey) && imgKey.contains(",")) {
				tbProductSurvey.setImgKey(imgKey.split(",")[0]);
			}
		}	
		return productSurveyQuery;
	}

	//新增商品
	public int addProduct(Product product) {
		TbProductWithBLOBs tbProductWithBLOBs = new TbProductWithBLOBs();
		tbProductWithBLOBs.setProductId(product.getProductId());
		tbProductWithBLOBs.setProductName(product.getProductName());
		tbProductWithBLOBs.setSellerId(product.getSellerId());
		tbProductWithBLOBs.setItemNo(product.getItemNo());
		tbProductWithBLOBs.setProductHeader(product.getProductHeader());
		tbProductWithBLOBs.setCurPrice(product.getCurPrice());
		tbProductWithBLOBs.setOrgPrice(product.getOrgPrice());
		tbProductWithBLOBs.setPresentexp(product.getPresentexp());
		tbProductWithBLOBs.setProductStock(product.getProductStock());
		tbProductWithBLOBs.setActiveState(product.getActiveState());
		tbProductWithBLOBs.setImgKey(product.getImgKey());
		tbProductWithBLOBs.setCreateTime(new Date());
		// 商品使用说明和商品兑换说明使用product_detail表更新
		TbProductDetailWithBLOBs tbProductDetail = new TbProductDetailWithBLOBs();
		tbProductDetail.setDetailKey(product.getProductId());
		tbProductDetail.setProductInstructions(product.getProductInstructions());
		tbProductDetail.setProductExchange(product.getProductExchange());
		tbProductDetail.setCreateTime(new Date());
		//tbProductDetail.setUpdateTime(new Date());
		int count = tbProductDetailMapper.insert(tbProductDetail);
		int result = 0;
		if (count > 0) {
			result = tbProductDao.addProduct(tbProductWithBLOBs);
		}
		return result;
	}

	//删除商品
	public int deleteProduct(String productId) {
		int result = 0;
		if (productId.contains(",")) {
			String[] productIdStr = productId.split(",");
			for(int i = 0;i < productIdStr.length;i++) {
				if(!StringUtils.isEmpty(productIdStr[i])) {
					TbProductDetailExample example = new TbProductDetailExample();
					example.createCriteria().andDetailKeyEqualTo(productIdStr[i]);
					int count = tbProductDetailMapper.deleteByExample(example);
					if (count > 0) {
						result = tbProductDao.deleteProduct(productIdStr[i]);
					}
				}
			}
		}else {
			TbProductDetailExample example = new TbProductDetailExample();
			example.createCriteria().andDetailKeyEqualTo(productId);
			int count = tbProductDetailMapper.deleteByExample(example);
			if (count > 0) {
				result = tbProductDao.deleteProduct(productId);
			}
		}
		return result;
	}

	//更新商品
	public int updateProduct(Product product) {
		TbProductWithBLOBs tbProductWithBLOBs = new TbProductWithBLOBs();
		tbProductWithBLOBs.setProductId(product.getProductId());
		tbProductWithBLOBs.setProductName(product.getProductName());
		tbProductWithBLOBs.setSellerId(product.getSellerId());
		tbProductWithBLOBs.setItemNo(product.getItemNo());
		tbProductWithBLOBs.setProductHeader(product.getProductHeader());
		tbProductWithBLOBs.setCurPrice(product.getCurPrice());
		tbProductWithBLOBs.setOrgPrice(product.getOrgPrice());
		tbProductWithBLOBs.setPresentexp(product.getPresentexp());
		tbProductWithBLOBs.setProductStock(product.getProductStock());
		Integer activeState = product.getActiveState();
		tbProductWithBLOBs.setActiveState(activeState);
		if(activeState == Constant.PRODUCT_STATE_ONSELL) {
			tbProductWithBLOBs.setLastPutawayTime(new Date());
		}else {
			tbProductWithBLOBs.setLastSoldoutTime(new Date());
		}
		tbProductWithBLOBs.setImgKey(product.getImgKey());
		// 更新商品详情表
		TbProductDetailWithBLOBs tbProductDetail = new TbProductDetailWithBLOBs();
		tbProductDetail.setDetailKey(product.getProductId());
		tbProductDetail.setProductInstructions(product.getProductInstructions());
		tbProductDetail.setProductExchange(product.getProductExchange());
		// tbProductDetail.setCreateTime(new Date());
		tbProductDetail.setUpdateTime(new Date());
		TbProductDetailExample example = new TbProductDetailExample();
		example.createCriteria().andDetailKeyEqualTo(product.getProductId());
		int count = tbProductDetailMapper.updateByExampleSelective(tbProductDetail, example);
		int result = 0;
		if (count > 0) {
			result = tbProductDao.updateProduct(tbProductWithBLOBs);
		}
		this.syncESProduct(false, product.getProductId());
		return result;
	}
	
	//商品导出execl表格
	public String exportProduct(String param,Integer itemNo,Integer activeState) {
		 String path = "";
		 try {
			List<TbProductSurvey> productList = productSurveyQuery(param,itemNo,activeState,1,1000);
			 if(productList != null && productList.size() > 0) {
				 byte[] files = FileOpUtil.getExportProduct(productList);
				 path = FileOpUtil.uploadFile(files, DateUtils.dateToStr(new Date(), Constant.FORMAT_DEFAULT_MIN) + ".xls");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return path;
	}
	
	/**
	 * 
	 * 根据商品id来搜索商品
	 * @param productId
	 * @return
	 */
	public TbProduct getProductOne(String productId) {
		TbProductExample example = new TbProductExample();
		example.createCriteria().andProductIdEqualTo(productId);
		List<TbProduct> products =  tbProductDao.selectByExample(example);
		if(products!=null && products.size()>0) {
			return products.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 * 根据类目id来搜索商品
	 * @param itemNo
	 * @return
	 */
	public List<TbProductSurvey> queryProductByItemNo(Integer itemNo) {
		List<TbProductSurvey> productList = new ArrayList<TbProductSurvey>();
		List<TbProductItem> tbProductItems = tbProductItemDao.queryItemList(itemNo+"");
		logger.info("tbProductItems : " + tbProductItems.size());
		ProductSurveyQueryParam productParam = new ProductSurveyQueryParam();
		productParam.setActiveState(0);
		productParam.setActiveState(Constant.PRODUCT_STATE_ONSELL);//只查询已上架的商品
		if(tbProductItems.size() > 0) {
			for (TbProductItem tbProductItem : tbProductItems) {
				logger.info("ItemNo : " + tbProductItem.getItemNo());
				productParam.setItemNo(Integer.parseInt(tbProductItem.getItemNo()));
				List<TbProductSurvey> productSurveyQuery = tbProductDao.productSurveyQuery(productParam);
				logger.info("productSurveyQuery : " + productSurveyQuery);
				for (TbProductSurvey tbProductSurvey : productSurveyQuery) {
					productList.add(tbProductSurvey);
				}
			}
		}
		//处理图片  列表只返回一张图片
		for (TbProductSurvey tbProductSurvey : productList) {
			String imgKey = tbProductSurvey.getImgKey();
			if(!StringUtils.isEmpty(imgKey) && imgKey.contains(",")) {
				tbProductSurvey.setImgKey(imgKey.split(",")[0]);
			}
		}	
		return productList;
	}
	
	public int changeProductState(String productId,Integer activeState) {
		Product product = new Product();
		int count = 0;
		TbProduct productOne = getProductOne(productId);
		if(productOne != null) {
			product.setProductId(productOne.getProductId());
			product.setProductName(productOne.getProductName());
			product.setItemNo(productOne.getItemNo());
			product.setProductHeader(productOne.getProductHeader());
			product.setCurPrice(productOne.getCurPrice());
			product.setOrgPrice(productOne.getOrgPrice());
			product.setPresentexp(productOne.getPresentexp());
			product.setProductStock(productOne.getProductStock());
			product.setActiveState(activeState);
			product.setImgKey(productOne.getImgKey());
			List<TbProductDetailWithBLOBs> productDetails =  productDetailService.selectByExample(productId);
			if(productDetails!=null&&productDetails.size()>0) {
				product.setProductInstructions(productDetails.get(0).getProductInstructions());
				product.setProductExchange(productDetails.get(0).getProductExchange());
			}
			count = updateProduct(product);
		}
		this.syncESProduct(false, product.getProductId());
		return count;
	}
}
