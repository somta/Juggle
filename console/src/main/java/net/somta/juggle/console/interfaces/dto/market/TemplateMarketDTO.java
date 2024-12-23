/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.interfaces.dto.market;

import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.3
 */
public class TemplateMarketDTO {
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
