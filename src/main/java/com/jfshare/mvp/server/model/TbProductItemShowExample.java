package com.jfshare.mvp.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbProductItemShowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbProductItemShowExample() {
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

        public Criteria andItemShowNoIsNull() {
            addCriterion("item_show_no is null");
            return (Criteria) this;
        }

        public Criteria andItemShowNoIsNotNull() {
            addCriterion("item_show_no is not null");
            return (Criteria) this;
        }

        public Criteria andItemShowNoEqualTo(Integer value) {
            addCriterion("item_show_no =", value, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoNotEqualTo(Integer value) {
            addCriterion("item_show_no <>", value, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoGreaterThan(Integer value) {
            addCriterion("item_show_no >", value, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_show_no >=", value, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoLessThan(Integer value) {
            addCriterion("item_show_no <", value, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoLessThanOrEqualTo(Integer value) {
            addCriterion("item_show_no <=", value, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoIn(List<Integer> values) {
            addCriterion("item_show_no in", values, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoNotIn(List<Integer> values) {
            addCriterion("item_show_no not in", values, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoBetween(Integer value1, Integer value2) {
            addCriterion("item_show_no between", value1, value2, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowNoNotBetween(Integer value1, Integer value2) {
            addCriterion("item_show_no not between", value1, value2, "itemShowNo");
            return (Criteria) this;
        }

        public Criteria andItemShowDescIsNull() {
            addCriterion("item_show_desc is null");
            return (Criteria) this;
        }

        public Criteria andItemShowDescIsNotNull() {
            addCriterion("item_show_desc is not null");
            return (Criteria) this;
        }

        public Criteria andItemShowDescEqualTo(String value) {
            addCriterion("item_show_desc =", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescNotEqualTo(String value) {
            addCriterion("item_show_desc <>", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescGreaterThan(String value) {
            addCriterion("item_show_desc >", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescGreaterThanOrEqualTo(String value) {
            addCriterion("item_show_desc >=", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescLessThan(String value) {
            addCriterion("item_show_desc <", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescLessThanOrEqualTo(String value) {
            addCriterion("item_show_desc <=", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescLike(String value) {
            addCriterion("item_show_desc like", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescNotLike(String value) {
            addCriterion("item_show_desc not like", value, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescIn(List<String> values) {
            addCriterion("item_show_desc in", values, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescNotIn(List<String> values) {
            addCriterion("item_show_desc not in", values, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescBetween(String value1, String value2) {
            addCriterion("item_show_desc between", value1, value2, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andItemShowDescNotBetween(String value1, String value2) {
            addCriterion("item_show_desc not between", value1, value2, "itemShowDesc");
            return (Criteria) this;
        }

        public Criteria andProductsIsNull() {
            addCriterion("products is null");
            return (Criteria) this;
        }

        public Criteria andProductsIsNotNull() {
            addCriterion("products is not null");
            return (Criteria) this;
        }

        public Criteria andProductsEqualTo(String value) {
            addCriterion("products =", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsNotEqualTo(String value) {
            addCriterion("products <>", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsGreaterThan(String value) {
            addCriterion("products >", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsGreaterThanOrEqualTo(String value) {
            addCriterion("products >=", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsLessThan(String value) {
            addCriterion("products <", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsLessThanOrEqualTo(String value) {
            addCriterion("products <=", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsLike(String value) {
            addCriterion("products like", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsNotLike(String value) {
            addCriterion("products not like", value, "products");
            return (Criteria) this;
        }

        public Criteria andProductsIn(List<String> values) {
            addCriterion("products in", values, "products");
            return (Criteria) this;
        }

        public Criteria andProductsNotIn(List<String> values) {
            addCriterion("products not in", values, "products");
            return (Criteria) this;
        }

        public Criteria andProductsBetween(String value1, String value2) {
            addCriterion("products between", value1, value2, "products");
            return (Criteria) this;
        }

        public Criteria andProductsNotBetween(String value1, String value2) {
            addCriterion("products not between", value1, value2, "products");
            return (Criteria) this;
        }

        public Criteria andAppVersionIsNull() {
            addCriterion("app_version is null");
            return (Criteria) this;
        }

        public Criteria andAppVersionIsNotNull() {
            addCriterion("app_version is not null");
            return (Criteria) this;
        }

        public Criteria andAppVersionEqualTo(String value) {
            addCriterion("app_version =", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotEqualTo(String value) {
            addCriterion("app_version <>", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionGreaterThan(String value) {
            addCriterion("app_version >", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionGreaterThanOrEqualTo(String value) {
            addCriterion("app_version >=", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLessThan(String value) {
            addCriterion("app_version <", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLessThanOrEqualTo(String value) {
            addCriterion("app_version <=", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionLike(String value) {
            addCriterion("app_version like", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotLike(String value) {
            addCriterion("app_version not like", value, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionIn(List<String> values) {
            addCriterion("app_version in", values, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotIn(List<String> values) {
            addCriterion("app_version not in", values, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionBetween(String value1, String value2) {
            addCriterion("app_version between", value1, value2, "appVersion");
            return (Criteria) this;
        }

        public Criteria andAppVersionNotBetween(String value1, String value2) {
            addCriterion("app_version not between", value1, value2, "appVersion");
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