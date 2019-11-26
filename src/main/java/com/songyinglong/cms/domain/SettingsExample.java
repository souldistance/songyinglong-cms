package com.songyinglong.cms.domain;

import java.util.ArrayList;
import java.util.List;

public class SettingsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SettingsExample() {
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

        public Criteria andSiteDomainIsNull() {
            addCriterion("site_domain is null");
            return (Criteria) this;
        }

        public Criteria andSiteDomainIsNotNull() {
            addCriterion("site_domain is not null");
            return (Criteria) this;
        }

        public Criteria andSiteDomainEqualTo(String value) {
            addCriterion("site_domain =", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainNotEqualTo(String value) {
            addCriterion("site_domain <>", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainGreaterThan(String value) {
            addCriterion("site_domain >", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainGreaterThanOrEqualTo(String value) {
            addCriterion("site_domain >=", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainLessThan(String value) {
            addCriterion("site_domain <", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainLessThanOrEqualTo(String value) {
            addCriterion("site_domain <=", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainLike(String value) {
            addCriterion("site_domain like", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainNotLike(String value) {
            addCriterion("site_domain not like", value, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainIn(List<String> values) {
            addCriterion("site_domain in", values, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainNotIn(List<String> values) {
            addCriterion("site_domain not in", values, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainBetween(String value1, String value2) {
            addCriterion("site_domain between", value1, value2, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteDomainNotBetween(String value1, String value2) {
            addCriterion("site_domain not between", value1, value2, "siteDomain");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNull() {
            addCriterion("site_name is null");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNotNull() {
            addCriterion("site_name is not null");
            return (Criteria) this;
        }

        public Criteria andSiteNameEqualTo(String value) {
            addCriterion("site_name =", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotEqualTo(String value) {
            addCriterion("site_name <>", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThan(String value) {
            addCriterion("site_name >", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("site_name >=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThan(String value) {
            addCriterion("site_name <", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThanOrEqualTo(String value) {
            addCriterion("site_name <=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLike(String value) {
            addCriterion("site_name like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotLike(String value) {
            addCriterion("site_name not like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameIn(List<String> values) {
            addCriterion("site_name in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotIn(List<String> values) {
            addCriterion("site_name not in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameBetween(String value1, String value2) {
            addCriterion("site_name between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotBetween(String value1, String value2) {
            addCriterion("site_name not between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeIsNull() {
            addCriterion("article_list_size is null");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeIsNotNull() {
            addCriterion("article_list_size is not null");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeEqualTo(Integer value) {
            addCriterion("article_list_size =", value, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeNotEqualTo(Integer value) {
            addCriterion("article_list_size <>", value, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeGreaterThan(Integer value) {
            addCriterion("article_list_size >", value, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_list_size >=", value, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeLessThan(Integer value) {
            addCriterion("article_list_size <", value, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeLessThanOrEqualTo(Integer value) {
            addCriterion("article_list_size <=", value, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeIn(List<Integer> values) {
            addCriterion("article_list_size in", values, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeNotIn(List<Integer> values) {
            addCriterion("article_list_size not in", values, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeBetween(Integer value1, Integer value2) {
            addCriterion("article_list_size between", value1, value2, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andArticleListSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("article_list_size not between", value1, value2, "articleListSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeIsNull() {
            addCriterion("slide_size is null");
            return (Criteria) this;
        }

        public Criteria andSlideSizeIsNotNull() {
            addCriterion("slide_size is not null");
            return (Criteria) this;
        }

        public Criteria andSlideSizeEqualTo(Integer value) {
            addCriterion("slide_size =", value, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeNotEqualTo(Integer value) {
            addCriterion("slide_size <>", value, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeGreaterThan(Integer value) {
            addCriterion("slide_size >", value, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_size >=", value, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeLessThan(Integer value) {
            addCriterion("slide_size <", value, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeLessThanOrEqualTo(Integer value) {
            addCriterion("slide_size <=", value, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeIn(List<Integer> values) {
            addCriterion("slide_size in", values, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeNotIn(List<Integer> values) {
            addCriterion("slide_size not in", values, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeBetween(Integer value1, Integer value2) {
            addCriterion("slide_size between", value1, value2, "slideSize");
            return (Criteria) this;
        }

        public Criteria andSlideSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_size not between", value1, value2, "slideSize");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIsNull() {
            addCriterion("admin_username is null");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIsNotNull() {
            addCriterion("admin_username is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameEqualTo(String value) {
            addCriterion("admin_username =", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotEqualTo(String value) {
            addCriterion("admin_username <>", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameGreaterThan(String value) {
            addCriterion("admin_username >", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_username >=", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLessThan(String value) {
            addCriterion("admin_username <", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLessThanOrEqualTo(String value) {
            addCriterion("admin_username <=", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLike(String value) {
            addCriterion("admin_username like", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotLike(String value) {
            addCriterion("admin_username not like", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIn(List<String> values) {
            addCriterion("admin_username in", values, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotIn(List<String> values) {
            addCriterion("admin_username not in", values, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameBetween(String value1, String value2) {
            addCriterion("admin_username between", value1, value2, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotBetween(String value1, String value2) {
            addCriterion("admin_username not between", value1, value2, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordIsNull() {
            addCriterion("admin_password is null");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordIsNotNull() {
            addCriterion("admin_password is not null");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordEqualTo(String value) {
            addCriterion("admin_password =", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordNotEqualTo(String value) {
            addCriterion("admin_password <>", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordGreaterThan(String value) {
            addCriterion("admin_password >", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("admin_password >=", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordLessThan(String value) {
            addCriterion("admin_password <", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordLessThanOrEqualTo(String value) {
            addCriterion("admin_password <=", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordLike(String value) {
            addCriterion("admin_password like", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordNotLike(String value) {
            addCriterion("admin_password not like", value, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordIn(List<String> values) {
            addCriterion("admin_password in", values, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordNotIn(List<String> values) {
            addCriterion("admin_password not in", values, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordBetween(String value1, String value2) {
            addCriterion("admin_password between", value1, value2, "adminPassword");
            return (Criteria) this;
        }

        public Criteria andAdminPasswordNotBetween(String value1, String value2) {
            addCriterion("admin_password not between", value1, value2, "adminPassword");
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