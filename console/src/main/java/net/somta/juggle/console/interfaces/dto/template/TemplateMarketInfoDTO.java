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
package net.somta.juggle.console.interfaces.dto.template;

import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.3
 */
public class TemplateMarketInfoDTO {

    private Long id;

    private String templateName;

    private String templateRemark;

    private Integer priceStatus;

    private Double templatePrice;

    private List<SuiteVO> suiteList;

    private List<SuiteVO> noBuySuiteList;

    private String flowType;


    private String flowContent;

    private String flowInputParameter;


    private String flowOutputParameter;

    private String flowVariable;

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

    public Integer getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    public Double getTemplatePrice() {
        return templatePrice;
    }

    public void setTemplatePrice(Double templatePrice) {
        this.templatePrice = templatePrice;
    }

    public List<SuiteVO> getSuiteList() {
        return suiteList;
    }

    public void setSuiteList(List<SuiteVO> suiteList) {
        this.suiteList = suiteList;
    }

    public List<SuiteVO> getNoBuySuiteList() {
        return noBuySuiteList;
    }

    public void setNoBuySuiteList(List<SuiteVO> noBuySuiteList) {
        this.noBuySuiteList = noBuySuiteList;
    }


    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getFlowContent() {
        return flowContent;
    }

    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent;
    }

    public String getFlowInputParameter() {
        return flowInputParameter;
    }

    public void setFlowInputParameter(String flowInputParameter) {
        this.flowInputParameter = flowInputParameter;
    }

    public String getFlowOutputParameter() {
        return flowOutputParameter;
    }

    public void setFlowOutputParameter(String flowOutputParameter) {
        this.flowOutputParameter = flowOutputParameter;
    }

    public String getFlowVariable() {
        return flowVariable;
    }

    public void setFlowVariable(String flowVariable) {
        this.flowVariable = flowVariable;
    }
}
