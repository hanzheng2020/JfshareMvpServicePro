package com.jfshare.mvp.server.service;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BaseTermQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
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
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.jfshare.mvp.server.constants.Constant;
import com.jfshare.mvp.server.controller.AdminController;
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
import com.jfshare.mvp.server.model.TbProductUrl;
import com.jfshare.mvp.server.model.TbProductWithBLOBs;
import com.jfshare.mvp.server.thirdinterface.WeChatAppletInterface;
import com.jfshare.mvp.server.utils.DateUtils;
import com.jfshare.mvp.server.utils.FileOpUtil;
import com.jfshare.mvp.server.utils.OSSUtils;
import com.jfshare.mvp.server.utils.SendRequest;

import net.sf.json.JSONObject;

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
	@Autowired
	private PromotionSettingService promotionSettingService;
	
	@Autowired
	private WeChatAppletInterface weChatAppletInterface;
	
	/**
	 * 同步es中的商品信息
	 * @param syncAll 是否全部同步
	 * @param productIds 商品ID
	 */
	public void syncESProduct(boolean syncAll, String... productIds) {
		TbProductExample example = new TbProductExample();
		if (syncAll) {
			esProductRepository.deleteAll();
			example.createCriteria()
				   .andProductIdIn(Arrays.asList(productIds))
				   .andActiveStateEqualTo(Constant.PRODUCT_STATE_ONSELL);
			List<TbProduct> tbproducts =  tbProductDao.selectByExample(example);
			List<ESProduct> esProducts = new ArrayList<>();
			for (TbProduct tbProduct : tbproducts) {
				ESProduct esProduct = new ESProduct(tbProduct.getProductId(), 
						tbProduct.getProductName(), Double.valueOf(tbProduct.getCurPrice()),
						tbProduct.getImgKey().contains(",") ? tbProduct.getImgKey().split(",")[0] : tbProduct.getImgKey());
				esProducts.add(esProduct);
			}
			esProductRepository.saveAll(esProducts);
		} else {
			for (String productId : productIds) {
				if (esProductRepository.existsById(productId)) {
					esProductRepository.deleteById(productId);
				}
				example.createCriteria()
				   .andProductIdEqualTo(productId)
				   .andActiveStateEqualTo(Constant.PRODUCT_STATE_ONSELL);
				List<TbProduct> tbproducts =  tbProductDao.selectByExample(example);
				if (!CollectionUtils.isEmpty(tbproducts)) {
					TbProduct tbProduct = tbproducts.get(0);
					ESProduct esProduct = new ESProduct(tbProduct.getProductId(), 
							tbProduct.getProductName(), Double.valueOf(tbProduct.getCurPrice()),
							tbProduct.getImgKey().contains(",") ? tbProduct.getImgKey().split(",")[0] : tbProduct.getImgKey());
					esProductRepository.save(esProduct);
				}
			}
		}
	}
	
	/**
	 * 查询ES中的商品信息, 模糊查询
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<ESProduct> queryESProduct(String params, int pageIndex, int pageSize) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.should(QueryBuilders.multiMatchQuery(params, "productName"));
		queryBuilder.should(QueryBuilders.wildcardQuery("productId", "*"+params+"*"));
		return esProductRepository.search(queryBuilder, PageRequest.of(pageIndex, pageSize));
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
		
		
        String  content=product.getProductExchange()+product.getProductInstructions();
        String instructions=SendRequest.sendPost(AdminController.url, "title="+product.getProductName()+"&content="+content+"&HTMLFileName=productInstructions"+product.getProductId()+".html");
        logger.info("上传exchangeUrlresult:"+instructions);
        if(StringUtils.isNotEmpty(instructions)) {
	        JSONObject objinstructions =  JSONObject.fromObject(instructions);
	        String instructionsCode = objinstructions.getString("code");
	        String instructionsUrl=objinstructions.getString("url");
	        if("200".equals(instructionsCode)) {
	            tbProductDetail.setProductInstructionsUrl(instructionsUrl);
	        }
        }
        
        String instructionsProductExchange=SendRequest.sendPost(AdminController.url, "title="+product.getProductName()+"&content="+content+"&HTMLFileName=ProductExchange"+product.getProductId()+".html");
        logger.info("上传exchangeUrlresult:"+instructionsProductExchange);
        if(StringUtils.isNotEmpty(instructionsProductExchange)) {
	        JSONObject productExchanges =  JSONObject.fromObject(instructionsProductExchange);
	        String productExchangeCode = productExchanges.getString("code");
	        String productExchangeUrl=productExchanges.getString("url");
	        if("200".equals(productExchangeCode)) {
	            tbProductDetail.setProductInstructionsUrl(productExchangeUrl);
	        }
        }
        
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
	@Transactional
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

        String  content=product.getProductInstructions();
        String instructions=SendRequest.sendPost(AdminController.url, "title="+product.getProductName()+"&content="+content+"&HTMLFileName=productInstructions"+product.getProductId()+".html");
        logger.info("上传exchangeUrlresult:"+instructions);
        if(StringUtils.isNotEmpty(instructions)) {
	        JSONObject objinstructions =  JSONObject.fromObject(instructions);
	        String instructionsCode = objinstructions.getString("code");
	        String instructionsUrl=objinstructions.getString("url");
	        if("200".equals(instructionsCode)) {
	            tbProductDetail.setProductInstructionsUrl(instructionsUrl);
	        }
        }
        content=product.getProductExchange();
        String instructionsProductExchange=SendRequest.sendPost(AdminController.url, "title="+product.getProductName()+"&content="+content+"&HTMLFileName=ProductExchange"+product.getProductId()+".html");
        logger.info("上传exchangeUrlresult:"+instructionsProductExchange);
        if(StringUtils.isNotEmpty(instructionsProductExchange)) {
	        JSONObject productExchanges =  JSONObject.fromObject(instructionsProductExchange);
	        String productExchangeCode = productExchanges.getString("code");
	        String productExchangeUrl=productExchanges.getString("url");
	        if("200".equals(productExchangeCode)) {
	            tbProductDetail.setProductExchangeUrl(productExchangeUrl);
	        }
        }
		TbProductDetailExample example = new TbProductDetailExample();
		example.createCriteria().andDetailKeyEqualTo(product.getProductId());
		int count = tbProductDetailMapper.updateByExampleSelective(tbProductDetail, example);
		int result = 0;
		if (count > 0) {
			result = tbProductDao.updateProduct(tbProductWithBLOBs);
		}
		this.syncESProduct(false, product.getProductId());
		promotionSettingService.updateProductPromotionImg(product.getProductId(), product.getImgKey().contains(",") ? product.getImgKey().split(",")[0] : product.getImgKey());
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
		if(products.size()>0) {
			TbProduct product = products.get(0);
			//处理商品图片格式问题
			StringBuilder sb = new StringBuilder();
			if(product.getImgKey().contains(",")) {
				String[] str = product.getImgKey().split(",");
				for(int i = 0;i < str.length;i ++) {
					if(!StringUtils.isEmpty(str[i])) {
						sb.append(str[i]).append(",");
					}
				}
				String strImg = sb.toString().substring(0, sb.toString().length() - 1);
				product.setImgKey(strImg);
			}
			return product;
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
	
	public int changeProductPrice(String productId, String price) {
		Product product = new Product();
		int count = 0;
		TbProduct productOne = getProductOne(productId);
		if(productOne != null) {
			product.setProductId(productOne.getProductId());
			product.setProductName(productOne.getProductName());
			product.setItemNo(productOne.getItemNo());
			product.setProductHeader(productOne.getProductHeader());
			product.setCurPrice(price);
			product.setOrgPrice(productOne.getOrgPrice());
			product.setPresentexp(productOne.getPresentexp());
			product.setProductStock(productOne.getProductStock());
			product.setActiveState(productOne.getActiveState());
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
	
	public List<TbProduct> queryProduct(){
		TbProductExample example = new TbProductExample();
		example.createCriteria().andActiveStateEqualTo(Constant.PRODUCT_STATE_ONSELL);
		List<TbProduct> productList = tbProductDao.selectByExample(example);
		if(productList != null && productList.size() > 0) {
			return productList;
		}
		return null;
	}
	
	public int addProductUrl(String productId,String imgUrl) {
		TbProductUrl tbProductUrl = new TbProductUrl();
		tbProductUrl.setProductId(productId);
		tbProductUrl.setImgUrl(imgUrl);
		tbProductUrl.setCreateTime(new Date());
		int rows = tbProductDao.addProductUrl(tbProductUrl);
		return rows;
	}
	
	public String querytProductUrl(String productId) {
		List<TbProductUrl> productUrl = tbProductDao.querytProductUrl(productId);
		if(productUrl.size() > 0) {
			return productUrl.get(0).getImgUrl();
		}else {
			return null;
		}
		
	}
	
	 /**
     * 图片缩放
     * @param bytes 字节流
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
     */
	public Image resize(byte[] bytes, int height, int width, boolean bb) {
		Image itemp = null;
		try {
            double ratio = 0; //缩放比例  
            InputStream input = new ByteArrayInputStream(bytes); 
            BufferedImage bi = ImageIO.read(input);
            itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH); 
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemp;
	}
	
	 /**
     * 图片缩放
     * @param url 图片路径
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
     */
	public Image resizeByUrl(URL url, int height, int width, boolean bb) {
		Image itemp = null;
		try {
            double ratio = 0; //缩放比例  
            //InputStream input = new ByteArrayInputStream(bytes); 
            BufferedImage bi = ImageIO.read(url);
            itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH); 
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemp;
	}
	
	//生成并保存图片水印到数据库
	public void markImageByIcon(String productId) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			TbProduct product = this.getProductOne(productId);
			String proImgStr = null;
			if(product.getImgKey().contains(",")) {
				String[] imgStr = product.getImgKey().split(",");
				proImgStr = imgStr[0];
			}else {
				proImgStr = product.getImgKey();
			}
			//获取图片水印的相关信息
			String productName = product.getProductName();
			String price = product.getCurPrice();
			URL url = new URL(proImgStr);
			Image srcImg_2 = this.resizeByUrl(url, 1334, 750, true);
			int width_2 = srcImg_2.getWidth(null);
			int height_2 = srcImg_2.getHeight(null);
			//获取原图片的信息
			//Image srcImg = ImageIO.read(new File("D:\\hz\\222.jpg"));
			URL url_1 = new URL("https://jfsharebucket.oss-cn-beijing.aliyuncs.com/A/222.jpg");
			Image srcImg = ImageIO.read(url_1);

			int width = srcImg.getWidth(null);
			int height = srcImg.getHeight(null);

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);

			byte[] bytes = weChatAppletInterface.createProductQRCode(productId);
			
			//设置水印大小
			Image img = this.resize(bytes, 150, 150, true);

			int width_1 = img.getWidth(null);
			int height_1 = img.getHeight(null);

			float alpha = 1f; // 透明度  1 表示不透明
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.setColor(new Color(220,20,60));
			Font font = new Font("宋体", Font.PLAIN, 35);
			g.setFont(font);
			int x = width - width_1;
			int y = height - height_1;
			
			// 设置水印图片的位置
			g.drawImage(srcImg_2, width - width_2, 0,width_2, 667,null);
			g.drawImage(img, x, y, width_1, height_1, null);
			// 设置文字水印的位置
			g.drawString(productName,150,height/2+100);
			g.drawString("价格：" + price + "元",150,height/2+180);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

			g.dispose();


			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			//InputStream is = new ByteArrayInputStream(os.toString().getBytes("UTF-8"));
			OSSUtils.uploadFile2OssForTemp(is,productId + ".jpg");
			URL imgUrl = OSSUtils.getUrlFromTempDir(productId + ".jpg");
			
			//保存数据库
			List<TbProductUrl> tProductUrlList = tbProductDao.querytProductUrl(productId);
			if(tProductUrlList.size() > 0) {
				TbProductUrl tbProductUrl = tProductUrlList.get(0);
				tbProductUrl.setImgUrl(imgUrl.toString());
				tbProductUrl.setCreateTime(new Date());
				tbProductDao.updateProductUrl(tbProductUrl);
			}else {
				this.addProductUrl(productId, imgUrl.toString());
			}
			System.out.println(imgUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
