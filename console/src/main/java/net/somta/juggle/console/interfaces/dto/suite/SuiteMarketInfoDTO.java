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
package net.somta.juggle.console.interfaces.dto.suite;

import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketApiVO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.1
 */
public class SuiteMarketInfoDTO {
    private Long id;
    private String suiteCode;
    private String suiteName;
    private String suiteImage;
    private String suiteVersion;
    private String suiteDesc;
    private Integer priceStatus;
    private Double suitePrice;
    private String suiteHelpDocJson;

    private Boolean installStatus = false;

    private List<SuiteMarketApiVO> apiList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuiteCode() {
        return suiteCode;
    }

    public void setSuiteCode(String suiteCode) {
        this.suiteCode = suiteCode;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getSuiteImage() {
        return suiteImage;
    }

    public void setSuiteImage(String suiteImage) {
        this.suiteImage = suiteImage;
    }

    public String getSuiteVersion() {
        return suiteVersion;
    }

    public void setSuiteVersion(String suiteVersion) {
        this.suiteVersion = suiteVersion;
    }

    public String getSuiteDesc() {
        return suiteDesc;
    }

    public void setSuiteDesc(String suiteDesc) {
        this.suiteDesc = suiteDesc;
    }

    public Integer getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    public Double getSuitePrice() {
        return suitePrice;
    }

    public void setSuitePrice(Double suitePrice) {
        this.suitePrice = suitePrice;
    }

    public String getSuiteHelpDocJson() {
        return suiteHelpDocJson;
    }

    public void setSuiteHelpDocJson(String suiteHelpDocJson) {
        this.suiteHelpDocJson = suiteHelpDocJson;
    }

    public Boolean getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(Boolean installStatus) {
        this.installStatus = installStatus;
    }

    public List<SuiteMarketApiVO> getApiList() {
        return apiList;
    }

    public void setApiList(List<SuiteMarketApiVO> apiList) {
        this.apiList = apiList;
    }
}
