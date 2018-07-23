package com.jfshare.mvp.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbJvjindouRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbJvjindouRuleExample() {
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

        public Criteria andGivingRuleIsNull() {
            addCriterion("giving_rule is null");
            return (Criteria) this;
        }

        public Criteria andGivingRuleIsNotNull() {
            addCriterion("giving_rule is not null");
            return (Criteria) this;
        }

        public Criteria andGivingRuleEqualTo(String value) {
            addCriterion("giving_rule =", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleNotEqualTo(String value) {
            addCriterion("giving_rule <>", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleGreaterThan(String value) {
            addCriterion("giving_rule >", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleGreaterThanOrEqualTo(String value) {
            addCriterion("giving_rule >=", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleLessThan(String value) {
            addCriterion("giving_rule <", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleLessThanOrEqualTo(String value) {
            addCriterion("giving_rule <=", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleLike(String value) {
            addCriterion("giving_rule like", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleNotLike(String value) {
            addCriterion("giving_rule not like", value, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleIn(List<String> values) {
            addCriterion("giving_rule in", values, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleNotIn(List<String> values) {
            addCriterion("giving_rule not in", values, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleBetween(String value1, String value2) {
            addCriterion("giving_rule between", value1, value2, "givingRule");
            return (Criteria) this;
        }

        public Criteria andGivingRuleNotBetween(String value1, String value2) {
            addCriterion("giving_rule not between", value1, value2, "givingRule");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinIsNull() {
            addCriterion("random_giving_min is null");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinIsNotNull() {
            addCriterion("random_giving_min is not null");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinEqualTo(Integer value) {
            addCriterion("random_giving_min =", value, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinNotEqualTo(Integer value) {
            addCriterion("random_giving_min <>", value, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinGreaterThan(Integer value) {
            addCriterion("random_giving_min >", value, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("random_giving_min >=", value, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinLessThan(Integer value) {
            addCriterion("random_giving_min <", value, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinLessThanOrEqualTo(Integer value) {
            addCriterion("random_giving_min <=", value, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinIn(List<Integer> values) {
            addCriterion("random_giving_min in", values, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinNotIn(List<Integer> values) {
            addCriterion("random_giving_min not in", values, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinBetween(Integer value1, Integer value2) {
            addCriterion("random_giving_min between", value1, value2, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMinNotBetween(Integer value1, Integer value2) {
            addCriterion("random_giving_min not between", value1, value2, "randomGivingMin");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxIsNull() {
            addCriterion("random_giving_max is null");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxIsNotNull() {
            addCriterion("random_giving_max is not null");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxEqualTo(Integer value) {
            addCriterion("random_giving_max =", value, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxNotEqualTo(Integer value) {
            addCriterion("random_giving_max <>", value, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxGreaterThan(Integer value) {
            addCriterion("random_giving_max >", value, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("random_giving_max >=", value, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxLessThan(Integer value) {
            addCriterion("random_giving_max <", value, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxLessThanOrEqualTo(Integer value) {
            addCriterion("random_giving_max <=", value, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxIn(List<Integer> values) {
            addCriterion("random_giving_max in", values, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxNotIn(List<Integer> values) {
            addCriterion("random_giving_max not in", values, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxBetween(Integer value1, Integer value2) {
            addCriterion("random_giving_max between", value1, value2, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andRandomGivingMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("random_giving_max not between", value1, value2, "randomGivingMax");
            return (Criteria) this;
        }

        public Criteria andFixedGivingIsNull() {
            addCriterion("fixed_giving is null");
            return (Criteria) this;
        }

        public Criteria andFixedGivingIsNotNull() {
            addCriterion("fixed_giving is not null");
            return (Criteria) this;
        }

        public Criteria andFixedGivingEqualTo(Integer value) {
            addCriterion("fixed_giving =", value, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingNotEqualTo(Integer value) {
            addCriterion("fixed_giving <>", value, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingGreaterThan(Integer value) {
            addCriterion("fixed_giving >", value, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingGreaterThanOrEqualTo(Integer value) {
            addCriterion("fixed_giving >=", value, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingLessThan(Integer value) {
            addCriterion("fixed_giving <", value, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingLessThanOrEqualTo(Integer value) {
            addCriterion("fixed_giving <=", value, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingIn(List<Integer> values) {
            addCriterion("fixed_giving in", values, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingNotIn(List<Integer> values) {
            addCriterion("fixed_giving not in", values, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingBetween(Integer value1, Integer value2) {
            addCriterion("fixed_giving between", value1, value2, "fixedGiving");
            return (Criteria) this;
        }

        public Criteria andFixedGivingNotBetween(Integer value1, Integer value2) {
            addCriterion("fixed_giving not between", value1, value2, "fixedGiving");
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