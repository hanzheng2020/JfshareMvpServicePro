package com.jfshare.mvp.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbProductExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(Integer value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(Integer value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(Integer value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(Integer value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(Integer value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<Integer> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<Integer> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(Integer value1, Integer value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNull() {
            addCriterion("subject_id is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Integer value) {
            addCriterion("subject_id =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Integer value) {
            addCriterion("subject_id <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Integer value) {
            addCriterion("subject_id >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("subject_id >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Integer value) {
            addCriterion("subject_id <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("subject_id <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Integer> values) {
            addCriterion("subject_id in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Integer> values) {
            addCriterion("subject_id not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Integer value1, Integer value2) {
            addCriterion("subject_id between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("subject_id not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andCurPriceIsNull() {
            addCriterion("cur_price is null");
            return (Criteria) this;
        }

        public Criteria andCurPriceIsNotNull() {
            addCriterion("cur_price is not null");
            return (Criteria) this;
        }

        public Criteria andCurPriceEqualTo(Integer value) {
            addCriterion("cur_price =", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceNotEqualTo(Integer value) {
            addCriterion("cur_price <>", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceGreaterThan(Integer value) {
            addCriterion("cur_price >", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("cur_price >=", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceLessThan(Integer value) {
            addCriterion("cur_price <", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceLessThanOrEqualTo(Integer value) {
            addCriterion("cur_price <=", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceIn(List<Integer> values) {
            addCriterion("cur_price in", values, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceNotIn(List<Integer> values) {
            addCriterion("cur_price not in", values, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceBetween(Integer value1, Integer value2) {
            addCriterion("cur_price between", value1, value2, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("cur_price not between", value1, value2, "curPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceIsNull() {
            addCriterion("org_price is null");
            return (Criteria) this;
        }

        public Criteria andOrgPriceIsNotNull() {
            addCriterion("org_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPriceEqualTo(Integer value) {
            addCriterion("org_price =", value, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceNotEqualTo(Integer value) {
            addCriterion("org_price <>", value, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceGreaterThan(Integer value) {
            addCriterion("org_price >", value, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_price >=", value, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceLessThan(Integer value) {
            addCriterion("org_price <", value, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceLessThanOrEqualTo(Integer value) {
            addCriterion("org_price <=", value, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceIn(List<Integer> values) {
            addCriterion("org_price in", values, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceNotIn(List<Integer> values) {
            addCriterion("org_price not in", values, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceBetween(Integer value1, Integer value2) {
            addCriterion("org_price between", value1, value2, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andOrgPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("org_price not between", value1, value2, "orgPrice");
            return (Criteria) this;
        }

        public Criteria andPresentexpIsNull() {
            addCriterion("presentexp is null");
            return (Criteria) this;
        }

        public Criteria andPresentexpIsNotNull() {
            addCriterion("presentexp is not null");
            return (Criteria) this;
        }

        public Criteria andPresentexpEqualTo(Integer value) {
            addCriterion("presentexp =", value, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpNotEqualTo(Integer value) {
            addCriterion("presentexp <>", value, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpGreaterThan(Integer value) {
            addCriterion("presentexp >", value, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpGreaterThanOrEqualTo(Integer value) {
            addCriterion("presentexp >=", value, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpLessThan(Integer value) {
            addCriterion("presentexp <", value, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpLessThanOrEqualTo(Integer value) {
            addCriterion("presentexp <=", value, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpIn(List<Integer> values) {
            addCriterion("presentexp in", values, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpNotIn(List<Integer> values) {
            addCriterion("presentexp not in", values, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpBetween(Integer value1, Integer value2) {
            addCriterion("presentexp between", value1, value2, "presentexp");
            return (Criteria) this;
        }

        public Criteria andPresentexpNotBetween(Integer value1, Integer value2) {
            addCriterion("presentexp not between", value1, value2, "presentexp");
            return (Criteria) this;
        }

        public Criteria andImgKeyIsNull() {
            addCriterion("img_key is null");
            return (Criteria) this;
        }

        public Criteria andImgKeyIsNotNull() {
            addCriterion("img_key is not null");
            return (Criteria) this;
        }

        public Criteria andImgKeyEqualTo(String value) {
            addCriterion("img_key =", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyNotEqualTo(String value) {
            addCriterion("img_key <>", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyGreaterThan(String value) {
            addCriterion("img_key >", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyGreaterThanOrEqualTo(String value) {
            addCriterion("img_key >=", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyLessThan(String value) {
            addCriterion("img_key <", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyLessThanOrEqualTo(String value) {
            addCriterion("img_key <=", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyLike(String value) {
            addCriterion("img_key like", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyNotLike(String value) {
            addCriterion("img_key not like", value, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyIn(List<String> values) {
            addCriterion("img_key in", values, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyNotIn(List<String> values) {
            addCriterion("img_key not in", values, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyBetween(String value1, String value2) {
            addCriterion("img_key between", value1, value2, "imgKey");
            return (Criteria) this;
        }

        public Criteria andImgKeyNotBetween(String value1, String value2) {
            addCriterion("img_key not between", value1, value2, "imgKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyIsNull() {
            addCriterion("detail_key is null");
            return (Criteria) this;
        }

        public Criteria andDetailKeyIsNotNull() {
            addCriterion("detail_key is not null");
            return (Criteria) this;
        }

        public Criteria andDetailKeyEqualTo(String value) {
            addCriterion("detail_key =", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotEqualTo(String value) {
            addCriterion("detail_key <>", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyGreaterThan(String value) {
            addCriterion("detail_key >", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyGreaterThanOrEqualTo(String value) {
            addCriterion("detail_key >=", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyLessThan(String value) {
            addCriterion("detail_key <", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyLessThanOrEqualTo(String value) {
            addCriterion("detail_key <=", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyLike(String value) {
            addCriterion("detail_key like", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotLike(String value) {
            addCriterion("detail_key not like", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyIn(List<String> values) {
            addCriterion("detail_key in", values, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotIn(List<String> values) {
            addCriterion("detail_key not in", values, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyBetween(String value1, String value2) {
            addCriterion("detail_key between", value1, value2, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotBetween(String value1, String value2) {
            addCriterion("detail_key not between", value1, value2, "detailKey");
            return (Criteria) this;
        }

        public Criteria andActiveStateIsNull() {
            addCriterion("active_state is null");
            return (Criteria) this;
        }

        public Criteria andActiveStateIsNotNull() {
            addCriterion("active_state is not null");
            return (Criteria) this;
        }

        public Criteria andActiveStateEqualTo(Integer value) {
            addCriterion("active_state =", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotEqualTo(Integer value) {
            addCriterion("active_state <>", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateGreaterThan(Integer value) {
            addCriterion("active_state >", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_state >=", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateLessThan(Integer value) {
            addCriterion("active_state <", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateLessThanOrEqualTo(Integer value) {
            addCriterion("active_state <=", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateIn(List<Integer> values) {
            addCriterion("active_state in", values, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotIn(List<Integer> values) {
            addCriterion("active_state not in", values, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateBetween(Integer value1, Integer value2) {
            addCriterion("active_state between", value1, value2, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotBetween(Integer value1, Integer value2) {
            addCriterion("active_state not between", value1, value2, "activeState");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeIsNull() {
            addCriterion("last_soldout_time is null");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeIsNotNull() {
            addCriterion("last_soldout_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeEqualTo(Date value) {
            addCriterion("last_soldout_time =", value, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeNotEqualTo(Date value) {
            addCriterion("last_soldout_time <>", value, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeGreaterThan(Date value) {
            addCriterion("last_soldout_time >", value, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_soldout_time >=", value, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeLessThan(Date value) {
            addCriterion("last_soldout_time <", value, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_soldout_time <=", value, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeIn(List<Date> values) {
            addCriterion("last_soldout_time in", values, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeNotIn(List<Date> values) {
            addCriterion("last_soldout_time not in", values, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeBetween(Date value1, Date value2) {
            addCriterion("last_soldout_time between", value1, value2, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastSoldoutTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_soldout_time not between", value1, value2, "lastSoldoutTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeIsNull() {
            addCriterion("last_putaway_time is null");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeIsNotNull() {
            addCriterion("last_putaway_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeEqualTo(Date value) {
            addCriterion("last_putaway_time =", value, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeNotEqualTo(Date value) {
            addCriterion("last_putaway_time <>", value, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeGreaterThan(Date value) {
            addCriterion("last_putaway_time >", value, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_putaway_time >=", value, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeLessThan(Date value) {
            addCriterion("last_putaway_time <", value, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_putaway_time <=", value, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeIn(List<Date> values) {
            addCriterion("last_putaway_time in", values, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeNotIn(List<Date> values) {
            addCriterion("last_putaway_time not in", values, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeBetween(Date value1, Date value2) {
            addCriterion("last_putaway_time between", value1, value2, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastPutawayTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_putaway_time not between", value1, value2, "lastPutawayTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdIsNull() {
            addCriterion("last_update_id is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdIsNotNull() {
            addCriterion("last_update_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdEqualTo(Integer value) {
            addCriterion("last_update_id =", value, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdNotEqualTo(Integer value) {
            addCriterion("last_update_id <>", value, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdGreaterThan(Integer value) {
            addCriterion("last_update_id >", value, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_update_id >=", value, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdLessThan(Integer value) {
            addCriterion("last_update_id <", value, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_update_id <=", value, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdIn(List<Integer> values) {
            addCriterion("last_update_id in", values, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdNotIn(List<Integer> values) {
            addCriterion("last_update_id not in", values, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdBetween(Integer value1, Integer value2) {
            addCriterion("last_update_id between", value1, value2, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_update_id not between", value1, value2, "lastUpdateId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyIsNull() {
            addCriterion("third_party_identify is null");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyIsNotNull() {
            addCriterion("third_party_identify is not null");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyEqualTo(Integer value) {
            addCriterion("third_party_identify =", value, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyNotEqualTo(Integer value) {
            addCriterion("third_party_identify <>", value, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyGreaterThan(Integer value) {
            addCriterion("third_party_identify >", value, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_party_identify >=", value, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyLessThan(Integer value) {
            addCriterion("third_party_identify <", value, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyLessThanOrEqualTo(Integer value) {
            addCriterion("third_party_identify <=", value, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyIn(List<Integer> values) {
            addCriterion("third_party_identify in", values, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyNotIn(List<Integer> values) {
            addCriterion("third_party_identify not in", values, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyBetween(Integer value1, Integer value2) {
            addCriterion("third_party_identify between", value1, value2, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyIdentifyNotBetween(Integer value1, Integer value2) {
            addCriterion("third_party_identify not between", value1, value2, "thirdPartyIdentify");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdIsNull() {
            addCriterion("third_party_product_id is null");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdIsNotNull() {
            addCriterion("third_party_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdEqualTo(String value) {
            addCriterion("third_party_product_id =", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdNotEqualTo(String value) {
            addCriterion("third_party_product_id <>", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdGreaterThan(String value) {
            addCriterion("third_party_product_id >", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("third_party_product_id >=", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdLessThan(String value) {
            addCriterion("third_party_product_id <", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdLessThanOrEqualTo(String value) {
            addCriterion("third_party_product_id <=", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdLike(String value) {
            addCriterion("third_party_product_id like", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdNotLike(String value) {
            addCriterion("third_party_product_id not like", value, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdIn(List<String> values) {
            addCriterion("third_party_product_id in", values, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdNotIn(List<String> values) {
            addCriterion("third_party_product_id not in", values, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdBetween(String value1, String value2) {
            addCriterion("third_party_product_id between", value1, value2, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andThirdPartyProductIdNotBetween(String value1, String value2) {
            addCriterion("third_party_product_id not between", value1, value2, "thirdPartyProductId");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsIsNull() {
            addCriterion("storehouse_ids is null");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsIsNotNull() {
            addCriterion("storehouse_ids is not null");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsEqualTo(String value) {
            addCriterion("storehouse_ids =", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsNotEqualTo(String value) {
            addCriterion("storehouse_ids <>", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsGreaterThan(String value) {
            addCriterion("storehouse_ids >", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsGreaterThanOrEqualTo(String value) {
            addCriterion("storehouse_ids >=", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsLessThan(String value) {
            addCriterion("storehouse_ids <", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsLessThanOrEqualTo(String value) {
            addCriterion("storehouse_ids <=", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsLike(String value) {
            addCriterion("storehouse_ids like", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsNotLike(String value) {
            addCriterion("storehouse_ids not like", value, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsIn(List<String> values) {
            addCriterion("storehouse_ids in", values, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsNotIn(List<String> values) {
            addCriterion("storehouse_ids not in", values, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsBetween(String value1, String value2) {
            addCriterion("storehouse_ids between", value1, value2, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andStorehouseIdsNotBetween(String value1, String value2) {
            addCriterion("storehouse_ids not between", value1, value2, "storehouseIds");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
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