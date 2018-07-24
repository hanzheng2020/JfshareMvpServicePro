package com.jfshare.mvp.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbProductDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbProductDetailExample() {
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

        public Criteria andProductDetailIsNull() {
            addCriterion("product_detail is null");
            return (Criteria) this;
        }

        public Criteria andProductDetailIsNotNull() {
            addCriterion("product_detail is not null");
            return (Criteria) this;
        }

        public Criteria andProductDetailEqualTo(String value) {
            addCriterion("product_detail =", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotEqualTo(String value) {
            addCriterion("product_detail <>", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailGreaterThan(String value) {
            addCriterion("product_detail >", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailGreaterThanOrEqualTo(String value) {
            addCriterion("product_detail >=", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLessThan(String value) {
            addCriterion("product_detail <", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLessThanOrEqualTo(String value) {
            addCriterion("product_detail <=", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLike(String value) {
            addCriterion("product_detail like", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotLike(String value) {
            addCriterion("product_detail not like", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailIn(List<String> values) {
            addCriterion("product_detail in", values, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotIn(List<String> values) {
            addCriterion("product_detail not in", values, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailBetween(String value1, String value2) {
            addCriterion("product_detail between", value1, value2, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotBetween(String value1, String value2) {
            addCriterion("product_detail not between", value1, value2, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsIsNull() {
            addCriterion("product_instructions is null");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsIsNotNull() {
            addCriterion("product_instructions is not null");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsEqualTo(String value) {
            addCriterion("product_instructions =", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsNotEqualTo(String value) {
            addCriterion("product_instructions <>", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsGreaterThan(String value) {
            addCriterion("product_instructions >", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsGreaterThanOrEqualTo(String value) {
            addCriterion("product_instructions >=", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsLessThan(String value) {
            addCriterion("product_instructions <", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsLessThanOrEqualTo(String value) {
            addCriterion("product_instructions <=", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsLike(String value) {
            addCriterion("product_instructions like", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsNotLike(String value) {
            addCriterion("product_instructions not like", value, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsIn(List<String> values) {
            addCriterion("product_instructions in", values, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsNotIn(List<String> values) {
            addCriterion("product_instructions not in", values, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsBetween(String value1, String value2) {
            addCriterion("product_instructions between", value1, value2, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductInstructionsNotBetween(String value1, String value2) {
            addCriterion("product_instructions not between", value1, value2, "productInstructions");
            return (Criteria) this;
        }

        public Criteria andProductExchangeIsNull() {
            addCriterion("product_exchange is null");
            return (Criteria) this;
        }

        public Criteria andProductExchangeIsNotNull() {
            addCriterion("product_exchange is not null");
            return (Criteria) this;
        }

        public Criteria andProductExchangeEqualTo(String value) {
            addCriterion("product_exchange =", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeNotEqualTo(String value) {
            addCriterion("product_exchange <>", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeGreaterThan(String value) {
            addCriterion("product_exchange >", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeGreaterThanOrEqualTo(String value) {
            addCriterion("product_exchange >=", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeLessThan(String value) {
            addCriterion("product_exchange <", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeLessThanOrEqualTo(String value) {
            addCriterion("product_exchange <=", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeLike(String value) {
            addCriterion("product_exchange like", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeNotLike(String value) {
            addCriterion("product_exchange not like", value, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeIn(List<String> values) {
            addCriterion("product_exchange in", values, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeNotIn(List<String> values) {
            addCriterion("product_exchange not in", values, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeBetween(String value1, String value2) {
            addCriterion("product_exchange between", value1, value2, "productExchange");
            return (Criteria) this;
        }

        public Criteria andProductExchangeNotBetween(String value1, String value2) {
            addCriterion("product_exchange not between", value1, value2, "productExchange");
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