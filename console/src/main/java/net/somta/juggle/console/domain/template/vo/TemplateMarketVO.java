package net.somta.juggle.console.domain.template.vo;


import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.3
 */
public class TemplateMarketVO {
    private Long id;

    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板描述
     */
    private String templateRemark;

    /**
     * 是否推荐
     */
    private Boolean recommend;

    /**
     * 模特使用到的套件列表
     */
    private List<SuiteVO> suiteList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateRemark() {
        return templateRemark;
    }

    public void setTemplateRemark(String templateRemark) {
        this.templateRemark = templateRemark;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public List<SuiteVO> getSuiteList() {
        return suiteList;
    }

    public void setSuiteList(List<SuiteVO> suiteList) {
        this.suiteList = suiteList;
    }
}
