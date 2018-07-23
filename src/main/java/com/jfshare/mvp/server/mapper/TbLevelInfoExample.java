package com.jfshare.mvp.server.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbLevelInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbLevelInfoExample() {
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

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userid");
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

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andLevleIsNull() {
            addCriterion("levle is null");
            return (Criteria) this;
        }

        public Criteria andLevleIsNotNull() {
            addCriterion("levle is not null");
            return (Criteria) this;
        }

        public Criteria andLevleEqualTo(String value) {
            addCriterion("levle =", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleNotEqualTo(String value) {
            addCriterion("levle <>", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleGreaterThan(String value) {
            addCriterion("levle >", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleGreaterThanOrEqualTo(String value) {
            addCriterion("levle >=", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleLessThan(String value) {
            addCriterion("levle <", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleLessThanOrEqualTo(String value) {
            addCriterion("levle <=", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleLike(String value) {
            addCriterion("levle like", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleNotLike(String value) {
            addCriterion("levle not like", value, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleIn(List<String> values) {
            addCriterion("levle in", values, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleNotIn(List<String> values) {
            addCriterion("levle not in", values, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleBetween(String value1, String value2) {
            addCriterion("levle between", value1, value2, "levle");
            return (Criteria) this;
        }

        public Criteria andLevleNotBetween(String value1, String value2) {
            addCriterion("levle not between", value1, value2, "levle");
            return (Criteria) this;
        }

        public Criteria andJvjindouIsNull() {
            addCriterion("jvjindou is null");
            return (Criteria) this;
        }

        public Criteria andJvjindouIsNotNull() {
            addCriterion("jvjindou is not null");
            return (Criteria) this;
        }

        public Criteria andJvjindouEqualTo(Integer value) {
            addCriterion("jvjindou =", value, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouNotEqualTo(Integer value) {
            addCriterion("jvjindou <>", value, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouGreaterThan(Integer value) {
            addCriterion("jvjindou >", value, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouGreaterThanOrEqualTo(Integer value) {
            addCriterion("jvjindou >=", value, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouLessThan(Integer value) {
            addCriterion("jvjindou <", value, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouLessThanOrEqualTo(Integer value) {
            addCriterion("jvjindou <=", value, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouIn(List<Integer> values) {
            addCriterion("jvjindou in", values, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouNotIn(List<Integer> values) {
            addCriterion("jvjindou not in", values, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouBetween(Integer value1, Integer value2) {
            addCriterion("jvjindou between", value1, value2, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andJvjindouNotBetween(Integer value1, Integer value2) {
            addCriterion("jvjindou not between", value1, value2, "jvjindou");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondIsNull() {
            addCriterion("levle_beyond is null");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondIsNotNull() {
            addCriterion("levle_beyond is not null");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondEqualTo(Integer value) {
            addCriterion("levle_beyond =", value, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondNotEqualTo(Integer value) {
            addCriterion("levle_beyond <>", value, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondGreaterThan(Integer value) {
            addCriterion("levle_beyond >", value, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondGreaterThanOrEqualTo(Integer value) {
            addCriterion("levle_beyond >=", value, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondLessThan(Integer value) {
            addCriterion("levle_beyond <", value, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondLessThanOrEqualTo(Integer value) {
            addCriterion("levle_beyond <=", value, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondIn(List<Integer> values) {
            addCriterion("levle_beyond in", values, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondNotIn(List<Integer> values) {
            addCriterion("levle_beyond not in", values, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondBetween(Integer value1, Integer value2) {
            addCriterion("levle_beyond between", value1, value2, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andLevleBeyondNotBetween(Integer value1, Integer value2) {
            addCriterion("levle_beyond not between", value1, value2, "levleBeyond");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouIsNull() {
            addCriterion("real_jvjindou is null");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouIsNotNull() {
            addCriterion("real_jvjindou is not null");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouEqualTo(Integer value) {
            addCriterion("real_jvjindou =", value, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouNotEqualTo(Integer value) {
            addCriterion("real_jvjindou <>", value, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouGreaterThan(Integer value) {
            addCriterion("real_jvjindou >", value, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_jvjindou >=", value, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouLessThan(Integer value) {
            addCriterion("real_jvjindou <", value, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouLessThanOrEqualTo(Integer value) {
            addCriterion("real_jvjindou <=", value, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouIn(List<Integer> values) {
            addCriterion("real_jvjindou in", values, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouNotIn(List<Integer> values) {
            addCriterion("real_jvjindou not in", values, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouBetween(Integer value1, Integer value2) {
            addCriterion("real_jvjindou between", value1, value2, "realJvjindou");
            return (Criteria) this;
        }

        public Criteria andRealJvjindouNotBetween(Integer value1, Integer value2) {
            addCriterion("real_jvjindou not between", value1, value2, "realJvjindou");
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