package com.jfshare.mvp.server.finagle.server;

import com.google.common.collect.ImmutableMap;

public final class Constants4Brain {

    private Constants4Brain() {
    }

    public static final String Jfshare_addressServ="AddressServ";
    public static final String Jfshare_AftersaleServ="AfterSaleServ";
    public static final String Jfshare_baseTemplateServ="BaseTemplateServ";
    public static final String Jfshare_BonusPointServ="BonusPointServ";
    public static final String Jfshare_brandServ="BrandServ";
    public static final String Jfshare_buyerServ="BuyerServ";
    public static final String Jfshare_cartServ="CartServ";
    public static final String Jfshare_commonServ="CommonServ";
    public static final String Jfshare_expressServ="ExpressServ";
    public static final String Jfshare_fileForCardServ="FileForCardServ";
    public static final String Jfshare_fileNameMappedServ="FileNameMappedServ";
    public static final String Jfshare_managerServ="ManagerServ";
    public static final String Jfshare_MessageServ="MessageServ";
    public static final String Jfshare_orderServ="OrderServ";
    public static final String Jfshare_payServ="PayServ";
    public static final String Jfshare_productServ="ProductServ";
    public static final String Jfshare_ScoreCardServ="ScoreCardServ";
    public static final String Jfshare_ScoreServ="ScoreServ";
    public static final String Jfshare_sellerServ="SellerServ";
    public static final String Jfshare_stockServ="StockServ";
    public static final String Jfshare_subjectServ="SubjectServ";
    public static final String Jfshare_thirdProductServ="ThirdProductServ";
    public static final String Jfshare_ThirdPayServ="ThirdPayServ";
    public static final String Jfshare_tradeServ="TradeServ";

    /**
     * 每个服务器，注册在zk时候的路径
     */
    public static final ImmutableMap<String, String> SERVICE_MAP =
            ImmutableMap.<String, String>builder()
                    .put(Jfshare_addressServ, "/jfshare/addressServ")
                    .put(Jfshare_AftersaleServ, "/jfshare/AftersaleServ")
                    .put(Jfshare_baseTemplateServ, "/jfshare/baseTemplateServ")
                    .put(Jfshare_BonusPointServ, "/jfshare/BonusPointServ")
                    .put(Jfshare_brandServ, "/jfshare/brandServ")
                    .put(Jfshare_buyerServ, "/jfshare/buyerServ")
                    .put(Jfshare_cartServ, "/jfshare/cartServ")
                    .put(Jfshare_commonServ, "/jfshare/commonServ")
                    .put(Jfshare_expressServ, "/jfshare/expressServ")
                    .put(Jfshare_fileForCardServ, "/jfshare/fileForCardServ")
                    .put(Jfshare_fileNameMappedServ, "/jfshare/fileNameMappedServ")
                    .put(Jfshare_managerServ, "/jfshare/managerServ")
                    .put(Jfshare_MessageServ, "/jfshare/MessageServ")
                    .put(Jfshare_orderServ, "/jfshare/orderServ")
                    .put(Jfshare_payServ, "/jfshare/payServ")
                    .put(Jfshare_productServ, "/jfshare/productServ")
                    .put(Jfshare_ScoreCardServ, "/jfshare/CardServ")
                    .put(Jfshare_ScoreServ, "/jfshare/ScoreServ")
                    .put(Jfshare_sellerServ, "/jfshare/sellerServ")
                    .put(Jfshare_stockServ, "/jfshare/stockServ")
                    .put(Jfshare_subjectServ, "/jfshare/subjectServ")
                    .put(Jfshare_thirdProductServ, "/jfshare/thirdProductServ")
                    .put(Jfshare_ThirdPayServ, "/jfshare/ThirdPayServ")
                    .put(Jfshare_tradeServ, "/jfshare/tradeServ")
                    .build();

    /**  每个服务对应的端口 ## */
    public static final ImmutableMap<String, String> SERVICE_IP_PORT =
            ImmutableMap.<String, String>builder()
                    .put(Jfshare_productServ, "1980")
                    .put(Jfshare_brandServ, "1981")
                    .put(Jfshare_subjectServ, "1982")
                    .put(Jfshare_stockServ, "1983")
                    .put(Jfshare_commonServ, "1984")
                    .put(Jfshare_addressServ, "1985")
                    .put(Jfshare_orderServ, "1986")
                    .put(Jfshare_tradeServ, "1987")
                    .put(Jfshare_managerServ, "1988")
                    .put(Jfshare_expressServ, "1989")
                    .put(Jfshare_buyerServ, "1990")
                    .put(Jfshare_sellerServ, "1991")
                    .put(Jfshare_cartServ, "1992")
                    .put(Jfshare_payServ, "1993")
                    .put(Jfshare_MessageServ, "2001")
                    .put(Jfshare_ScoreServ, "2002")
                    .put(Jfshare_AftersaleServ, "2003")
                    .put(Jfshare_baseTemplateServ, "2004")
                    .put(Jfshare_ScoreCardServ, "2005")
                    .put(Jfshare_fileForCardServ, "2006")
                    .put(Jfshare_thirdProductServ, "2007")
                    .put(Jfshare_ThirdPayServ, "2007")
                    .put(Jfshare_BonusPointServ, "2010")
                    .put(Jfshare_fileNameMappedServ, "19890")
                    .build();


}
