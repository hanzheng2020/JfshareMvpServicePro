package com.jfshare.mvp.server.mapper.manual;

import java.util.List;

import com.jfshare.mvp.server.model.TbProductItem;

public interface ManualTbProductItemMapper {
	List<TbProductItem> queryItemList(String itemNo);
}
