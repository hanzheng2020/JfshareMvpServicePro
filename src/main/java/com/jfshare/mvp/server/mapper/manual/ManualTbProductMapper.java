package com.jfshare.mvp.server.mapper.manual;

import java.util.List;

import com.jfshare.mvp.server.model.ProductSurveyQueryParam;
import com.jfshare.mvp.server.model.TbProductSurvey;

public interface ManualTbProductMapper {
	List<TbProductSurvey> productSurveyQuery(ProductSurveyQueryParam param);
}
