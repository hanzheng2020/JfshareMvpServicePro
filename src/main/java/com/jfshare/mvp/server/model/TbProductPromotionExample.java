package com.jfshare.mvp.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbProductPromotionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbProductPromotionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPromotionNoIsNull() {
            addCriterion("promotion_no is null");
            return (Criteria) this;
        }

        public Criteria andPromotionNoIsNotNull() {
            addCriterion("promotion_no is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionNoEqualTo(Integer value) {
            addCriterion("promotion_no =", value, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoNotEqualTo(Integer value) {
            addCriterion("promotion_no <>", value, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoGreaterThan(Integer value) {
            addCriterion("promotion_no >", value, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("promotion_no >=", value, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoLessThan(Integer value) {
            addCriterion("promotion_no <", value, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoLessThanOrEqualTo(Integer value) {
            addCriterion("promotion_no <=", value, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoIn(List<Integer> values) {
            addCriterion("promotion_no in", values, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoNotIn(List<Integer> values) {
            addCriterion("promotion_no not in", values, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoBetween(Integer value1, Integer value2) {
            addCriterion("promotion_no between", value1, value2, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionNoNotBetween(Integer value1, Integer value2) {
            addCriterion("promotion_no not between", value1, value2, "promotionNo");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlIsNull() {
            addCriterion("promotion_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlIsNotNull() {
            addCriterion("promotion_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlEqualTo(String value) {
            addCriterion("promotion_pic_url =", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlNotEqualTo(String value) {
            addCriterion("promotion_pic_url <>", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlGreaterThan(String value) {
            addCriterion("promotion_pic_url >", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("promotion_pic_url >=", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlLessThan(String value) {
            addCriterion("promotion_pic_url <", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlLessThanOrEqualTo(String value) {
            addCriterion("promotion_pic_url <=", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlLike(String value) {
            addCriterion("promotion_pic_url like", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlNotLike(String value) {
            addCriterion("promotion_pic_url not like", value, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlIn(List<String> values) {
            addCriterion("promotion_pic_url in", values, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlNotIn(List<String> values) {
            addCriterion("promotion_pic_url not in", values, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlBetween(String value1, String value2) {
            addCriterion("promotion_pic_url between", value1, value2, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionPicUrlNotBetween(String value1, String value2) {
            addCriterion("promotion_pic_url not between", value1, value2, "promotionPicUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlIsNull() {
            addCriterion("promotion_url is null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlIsNotNull() {
            addCriterion("promotion_url is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlEqualTo(String value) {
            addCriterion("promotion_url =", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotEqualTo(String value) {
            addCriterion("promotion_url <>", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlGreaterThan(String value) {
            addCriterion("promotion_url >", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("promotion_url >=", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlLessThan(String value) {
            addCriterion("promotion_url <", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlLessThanOrEqualTo(String value) {
            addCriterion("promotion_url <=", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlLike(String value) {
            addCriterion("promotion_url like", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotLike(String value) {
            addCriterion("promotion_url not like", value, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlIn(List<String> values) {
            addCriterion("promotion_url in", values, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotIn(List<String> values) {
            addCriterion("promotion_url not in", values, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlBetween(String value1, String value2) {
            addCriterion("promotion_url between", value1, value2, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andPromotionUrlNotBetween(String value1, String value2) {
            addCriterion("promotion_url not between", value1, value2, "promotionUrl");
            return (Criteria) this;
        }

        public Criteria andProductOneIdIsNull() {
            addCriterion("product_one_id is null");
            return (Criteria) this;
        }

        public Criteria andProductOneIdIsNotNull() {
            addCriterion("product_one_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductOneIdEqualTo(String value) {
            addCriterion("product_one_id =", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdNotEqualTo(String value) {
            addCriterion("product_one_id <>", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdGreaterThan(String value) {
            addCriterion("product_one_id >", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_one_id >=", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdLessThan(String value) {
            addCriterion("product_one_id <", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdLessThanOrEqualTo(String value) {
            addCriterion("product_one_id <=", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdLike(String value) {
            addCriterion("product_one_id like", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdNotLike(String value) {
            addCriterion("product_one_id not like", value, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdIn(List<String> values) {
            addCriterion("product_one_id in", values, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdNotIn(List<String> values) {
            addCriterion("product_one_id not in", values, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdBetween(String value1, String value2) {
            addCriterion("product_one_id between", value1, value2, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneIdNotBetween(String value1, String value2) {
            addCriterion("product_one_id not between", value1, value2, "productOneId");
            return (Criteria) this;
        }

        public Criteria andProductOneDescIsNull() {
            addCriterion("product_one_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductOneDescIsNotNull() {
            addCriterion("product_one_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductOneDescEqualTo(String value) {
            addCriterion("product_one_desc =", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescNotEqualTo(String value) {
            addCriterion("product_one_desc <>", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescGreaterThan(String value) {
            addCriterion("product_one_desc >", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_one_desc >=", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescLessThan(String value) {
            addCriterion("product_one_desc <", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescLessThanOrEqualTo(String value) {
            addCriterion("product_one_desc <=", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescLike(String value) {
            addCriterion("product_one_desc like", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescNotLike(String value) {
            addCriterion("product_one_desc not like", value, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescIn(List<String> values) {
            addCriterion("product_one_desc in", values, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescNotIn(List<String> values) {
            addCriterion("product_one_desc not in", values, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescBetween(String value1, String value2) {
            addCriterion("product_one_desc between", value1, value2, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOneDescNotBetween(String value1, String value2) {
            addCriterion("product_one_desc not between", value1, value2, "productOneDesc");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlIsNull() {
            addCriterion("product_one_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlIsNotNull() {
            addCriterion("product_one_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlEqualTo(String value) {
            addCriterion("product_one_pic_url =", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlNotEqualTo(String value) {
            addCriterion("product_one_pic_url <>", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlGreaterThan(String value) {
            addCriterion("product_one_pic_url >", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_one_pic_url >=", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlLessThan(String value) {
            addCriterion("product_one_pic_url <", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlLessThanOrEqualTo(String value) {
            addCriterion("product_one_pic_url <=", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlLike(String value) {
            addCriterion("product_one_pic_url like", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlNotLike(String value) {
            addCriterion("product_one_pic_url not like", value, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlIn(List<String> values) {
            addCriterion("product_one_pic_url in", values, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlNotIn(List<String> values) {
            addCriterion("product_one_pic_url not in", values, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlBetween(String value1, String value2) {
            addCriterion("product_one_pic_url between", value1, value2, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductOnePicUrlNotBetween(String value1, String value2) {
            addCriterion("product_one_pic_url not between", value1, value2, "productOnePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdIsNull() {
            addCriterion("product_two_id is null");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdIsNotNull() {
            addCriterion("product_two_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdEqualTo(String value) {
            addCriterion("product_two_id =", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdNotEqualTo(String value) {
            addCriterion("product_two_id <>", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdGreaterThan(String value) {
            addCriterion("product_two_id >", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_two_id >=", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdLessThan(String value) {
            addCriterion("product_two_id <", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdLessThanOrEqualTo(String value) {
            addCriterion("product_two_id <=", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdLike(String value) {
            addCriterion("product_two_id like", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdNotLike(String value) {
            addCriterion("product_two_id not like", value, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdIn(List<String> values) {
            addCriterion("product_two_id in", values, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdNotIn(List<String> values) {
            addCriterion("product_two_id not in", values, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdBetween(String value1, String value2) {
            addCriterion("product_two_id between", value1, value2, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoIdNotBetween(String value1, String value2) {
            addCriterion("product_two_id not between", value1, value2, "productTwoId");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescIsNull() {
            addCriterion("product_two_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescIsNotNull() {
            addCriterion("product_two_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescEqualTo(String value) {
            addCriterion("product_two_desc =", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescNotEqualTo(String value) {
            addCriterion("product_two_desc <>", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescGreaterThan(String value) {
            addCriterion("product_two_desc >", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_two_desc >=", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescLessThan(String value) {
            addCriterion("product_two_desc <", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescLessThanOrEqualTo(String value) {
            addCriterion("product_two_desc <=", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescLike(String value) {
            addCriterion("product_two_desc like", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescNotLike(String value) {
            addCriterion("product_two_desc not like", value, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescIn(List<String> values) {
            addCriterion("product_two_desc in", values, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescNotIn(List<String> values) {
            addCriterion("product_two_desc not in", values, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescBetween(String value1, String value2) {
            addCriterion("product_two_desc between", value1, value2, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoDescNotBetween(String value1, String value2) {
            addCriterion("product_two_desc not between", value1, value2, "productTwoDesc");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlIsNull() {
            addCriterion("product_two_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlIsNotNull() {
            addCriterion("product_two_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlEqualTo(String value) {
            addCriterion("product_two_pic_url =", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlNotEqualTo(String value) {
            addCriterion("product_two_pic_url <>", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlGreaterThan(String value) {
            addCriterion("product_two_pic_url >", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_two_pic_url >=", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlLessThan(String value) {
            addCriterion("product_two_pic_url <", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlLessThanOrEqualTo(String value) {
            addCriterion("product_two_pic_url <=", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlLike(String value) {
            addCriterion("product_two_pic_url like", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlNotLike(String value) {
            addCriterion("product_two_pic_url not like", value, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlIn(List<String> values) {
            addCriterion("product_two_pic_url in", values, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlNotIn(List<String> values) {
            addCriterion("product_two_pic_url not in", values, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlBetween(String value1, String value2) {
            addCriterion("product_two_pic_url between", value1, value2, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductTwoPicUrlNotBetween(String value1, String value2) {
            addCriterion("product_two_pic_url not between", value1, value2, "productTwoPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdIsNull() {
            addCriterion("product_three_id is null");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdIsNotNull() {
            addCriterion("product_three_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdEqualTo(String value) {
            addCriterion("product_three_id =", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdNotEqualTo(String value) {
            addCriterion("product_three_id <>", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdGreaterThan(String value) {
            addCriterion("product_three_id >", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_three_id >=", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdLessThan(String value) {
            addCriterion("product_three_id <", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdLessThanOrEqualTo(String value) {
            addCriterion("product_three_id <=", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdLike(String value) {
            addCriterion("product_three_id like", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdNotLike(String value) {
            addCriterion("product_three_id not like", value, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdIn(List<String> values) {
            addCriterion("product_three_id in", values, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdNotIn(List<String> values) {
            addCriterion("product_three_id not in", values, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdBetween(String value1, String value2) {
            addCriterion("product_three_id between", value1, value2, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeIdNotBetween(String value1, String value2) {
            addCriterion("product_three_id not between", value1, value2, "productThreeId");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescIsNull() {
            addCriterion("product_three_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescIsNotNull() {
            addCriterion("product_three_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescEqualTo(String value) {
            addCriterion("product_three_desc =", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescNotEqualTo(String value) {
            addCriterion("product_three_desc <>", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescGreaterThan(String value) {
            addCriterion("product_three_desc >", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_three_desc >=", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescLessThan(String value) {
            addCriterion("product_three_desc <", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescLessThanOrEqualTo(String value) {
            addCriterion("product_three_desc <=", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescLike(String value) {
            addCriterion("product_three_desc like", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescNotLike(String value) {
            addCriterion("product_three_desc not like", value, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescIn(List<String> values) {
            addCriterion("product_three_desc in", values, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescNotIn(List<String> values) {
            addCriterion("product_three_desc not in", values, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescBetween(String value1, String value2) {
            addCriterion("product_three_desc between", value1, value2, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreeDescNotBetween(String value1, String value2) {
            addCriterion("product_three_desc not between", value1, value2, "productThreeDesc");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlIsNull() {
            addCriterion("product_three_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlIsNotNull() {
            addCriterion("product_three_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlEqualTo(String value) {
            addCriterion("product_three_pic_url =", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlNotEqualTo(String value) {
            addCriterion("product_three_pic_url <>", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlGreaterThan(String value) {
            addCriterion("product_three_pic_url >", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_three_pic_url >=", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlLessThan(String value) {
            addCriterion("product_three_pic_url <", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlLessThanOrEqualTo(String value) {
            addCriterion("product_three_pic_url <=", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlLike(String value) {
            addCriterion("product_three_pic_url like", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlNotLike(String value) {
            addCriterion("product_three_pic_url not like", value, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlIn(List<String> values) {
            addCriterion("product_three_pic_url in", values, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlNotIn(List<String> values) {
            addCriterion("product_three_pic_url not in", values, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlBetween(String value1, String value2) {
            addCriterion("product_three_pic_url between", value1, value2, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductThreePicUrlNotBetween(String value1, String value2) {
            addCriterion("product_three_pic_url not between", value1, value2, "productThreePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourIdIsNull() {
            addCriterion("product_four_id is null");
            return (Criteria) this;
        }

        public Criteria andProductFourIdIsNotNull() {
            addCriterion("product_four_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductFourIdEqualTo(String value) {
            addCriterion("product_four_id =", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdNotEqualTo(String value) {
            addCriterion("product_four_id <>", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdGreaterThan(String value) {
            addCriterion("product_four_id >", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_four_id >=", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdLessThan(String value) {
            addCriterion("product_four_id <", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdLessThanOrEqualTo(String value) {
            addCriterion("product_four_id <=", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdLike(String value) {
            addCriterion("product_four_id like", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdNotLike(String value) {
            addCriterion("product_four_id not like", value, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdIn(List<String> values) {
            addCriterion("product_four_id in", values, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdNotIn(List<String> values) {
            addCriterion("product_four_id not in", values, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdBetween(String value1, String value2) {
            addCriterion("product_four_id between", value1, value2, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourIdNotBetween(String value1, String value2) {
            addCriterion("product_four_id not between", value1, value2, "productFourId");
            return (Criteria) this;
        }

        public Criteria andProductFourDescIsNull() {
            addCriterion("product_four_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductFourDescIsNotNull() {
            addCriterion("product_four_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductFourDescEqualTo(String value) {
            addCriterion("product_four_desc =", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescNotEqualTo(String value) {
            addCriterion("product_four_desc <>", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescGreaterThan(String value) {
            addCriterion("product_four_desc >", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_four_desc >=", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescLessThan(String value) {
            addCriterion("product_four_desc <", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescLessThanOrEqualTo(String value) {
            addCriterion("product_four_desc <=", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescLike(String value) {
            addCriterion("product_four_desc like", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescNotLike(String value) {
            addCriterion("product_four_desc not like", value, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescIn(List<String> values) {
            addCriterion("product_four_desc in", values, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescNotIn(List<String> values) {
            addCriterion("product_four_desc not in", values, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescBetween(String value1, String value2) {
            addCriterion("product_four_desc between", value1, value2, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourDescNotBetween(String value1, String value2) {
            addCriterion("product_four_desc not between", value1, value2, "productFourDesc");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlIsNull() {
            addCriterion("product_four_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlIsNotNull() {
            addCriterion("product_four_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlEqualTo(String value) {
            addCriterion("product_four_pic_url =", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlNotEqualTo(String value) {
            addCriterion("product_four_pic_url <>", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlGreaterThan(String value) {
            addCriterion("product_four_pic_url >", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_four_pic_url >=", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlLessThan(String value) {
            addCriterion("product_four_pic_url <", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlLessThanOrEqualTo(String value) {
            addCriterion("product_four_pic_url <=", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlLike(String value) {
            addCriterion("product_four_pic_url like", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlNotLike(String value) {
            addCriterion("product_four_pic_url not like", value, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlIn(List<String> values) {
            addCriterion("product_four_pic_url in", values, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlNotIn(List<String> values) {
            addCriterion("product_four_pic_url not in", values, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlBetween(String value1, String value2) {
            addCriterion("product_four_pic_url between", value1, value2, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFourPicUrlNotBetween(String value1, String value2) {
            addCriterion("product_four_pic_url not between", value1, value2, "productFourPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdIsNull() {
            addCriterion("product_five_id is null");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdIsNotNull() {
            addCriterion("product_five_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdEqualTo(String value) {
            addCriterion("product_five_id =", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdNotEqualTo(String value) {
            addCriterion("product_five_id <>", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdGreaterThan(String value) {
            addCriterion("product_five_id >", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_five_id >=", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdLessThan(String value) {
            addCriterion("product_five_id <", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdLessThanOrEqualTo(String value) {
            addCriterion("product_five_id <=", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdLike(String value) {
            addCriterion("product_five_id like", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdNotLike(String value) {
            addCriterion("product_five_id not like", value, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdIn(List<String> values) {
            addCriterion("product_five_id in", values, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdNotIn(List<String> values) {
            addCriterion("product_five_id not in", values, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdBetween(String value1, String value2) {
            addCriterion("product_five_id between", value1, value2, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveIdNotBetween(String value1, String value2) {
            addCriterion("product_five_id not between", value1, value2, "productFiveId");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescIsNull() {
            addCriterion("product_five_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescIsNotNull() {
            addCriterion("product_five_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescEqualTo(String value) {
            addCriterion("product_five_desc =", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescNotEqualTo(String value) {
            addCriterion("product_five_desc <>", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescGreaterThan(String value) {
            addCriterion("product_five_desc >", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_five_desc >=", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescLessThan(String value) {
            addCriterion("product_five_desc <", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescLessThanOrEqualTo(String value) {
            addCriterion("product_five_desc <=", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescLike(String value) {
            addCriterion("product_five_desc like", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescNotLike(String value) {
            addCriterion("product_five_desc not like", value, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescIn(List<String> values) {
            addCriterion("product_five_desc in", values, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescNotIn(List<String> values) {
            addCriterion("product_five_desc not in", values, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescBetween(String value1, String value2) {
            addCriterion("product_five_desc between", value1, value2, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFiveDescNotBetween(String value1, String value2) {
            addCriterion("product_five_desc not between", value1, value2, "productFiveDesc");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlIsNull() {
            addCriterion("product_five_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlIsNotNull() {
            addCriterion("product_five_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlEqualTo(String value) {
            addCriterion("product_five_pic_url =", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlNotEqualTo(String value) {
            addCriterion("product_five_pic_url <>", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlGreaterThan(String value) {
            addCriterion("product_five_pic_url >", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_five_pic_url >=", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlLessThan(String value) {
            addCriterion("product_five_pic_url <", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlLessThanOrEqualTo(String value) {
            addCriterion("product_five_pic_url <=", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlLike(String value) {
            addCriterion("product_five_pic_url like", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlNotLike(String value) {
            addCriterion("product_five_pic_url not like", value, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlIn(List<String> values) {
            addCriterion("product_five_pic_url in", values, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlNotIn(List<String> values) {
            addCriterion("product_five_pic_url not in", values, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlBetween(String value1, String value2) {
            addCriterion("product_five_pic_url between", value1, value2, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductFivePicUrlNotBetween(String value1, String value2) {
            addCriterion("product_five_pic_url not between", value1, value2, "productFivePicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixIdIsNull() {
            addCriterion("product_six_id is null");
            return (Criteria) this;
        }

        public Criteria andProductSixIdIsNotNull() {
            addCriterion("product_six_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductSixIdEqualTo(String value) {
            addCriterion("product_six_id =", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdNotEqualTo(String value) {
            addCriterion("product_six_id <>", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdGreaterThan(String value) {
            addCriterion("product_six_id >", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_six_id >=", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdLessThan(String value) {
            addCriterion("product_six_id <", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdLessThanOrEqualTo(String value) {
            addCriterion("product_six_id <=", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdLike(String value) {
            addCriterion("product_six_id like", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdNotLike(String value) {
            addCriterion("product_six_id not like", value, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdIn(List<String> values) {
            addCriterion("product_six_id in", values, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdNotIn(List<String> values) {
            addCriterion("product_six_id not in", values, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdBetween(String value1, String value2) {
            addCriterion("product_six_id between", value1, value2, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixIdNotBetween(String value1, String value2) {
            addCriterion("product_six_id not between", value1, value2, "productSixId");
            return (Criteria) this;
        }

        public Criteria andProductSixDescIsNull() {
            addCriterion("product_six_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductSixDescIsNotNull() {
            addCriterion("product_six_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductSixDescEqualTo(String value) {
            addCriterion("product_six_desc =", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescNotEqualTo(String value) {
            addCriterion("product_six_desc <>", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescGreaterThan(String value) {
            addCriterion("product_six_desc >", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_six_desc >=", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescLessThan(String value) {
            addCriterion("product_six_desc <", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescLessThanOrEqualTo(String value) {
            addCriterion("product_six_desc <=", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescLike(String value) {
            addCriterion("product_six_desc like", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescNotLike(String value) {
            addCriterion("product_six_desc not like", value, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescIn(List<String> values) {
            addCriterion("product_six_desc in", values, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescNotIn(List<String> values) {
            addCriterion("product_six_desc not in", values, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescBetween(String value1, String value2) {
            addCriterion("product_six_desc between", value1, value2, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixDescNotBetween(String value1, String value2) {
            addCriterion("product_six_desc not between", value1, value2, "productSixDesc");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlIsNull() {
            addCriterion("product_six_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlIsNotNull() {
            addCriterion("product_six_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlEqualTo(String value) {
            addCriterion("product_six_pic_url =", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlNotEqualTo(String value) {
            addCriterion("product_six_pic_url <>", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlGreaterThan(String value) {
            addCriterion("product_six_pic_url >", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_six_pic_url >=", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlLessThan(String value) {
            addCriterion("product_six_pic_url <", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlLessThanOrEqualTo(String value) {
            addCriterion("product_six_pic_url <=", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlLike(String value) {
            addCriterion("product_six_pic_url like", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlNotLike(String value) {
            addCriterion("product_six_pic_url not like", value, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlIn(List<String> values) {
            addCriterion("product_six_pic_url in", values, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlNotIn(List<String> values) {
            addCriterion("product_six_pic_url not in", values, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlBetween(String value1, String value2) {
            addCriterion("product_six_pic_url between", value1, value2, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductSixPicUrlNotBetween(String value1, String value2) {
            addCriterion("product_six_pic_url not between", value1, value2, "productSixPicUrl");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}