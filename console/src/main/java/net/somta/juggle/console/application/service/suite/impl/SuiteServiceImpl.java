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
package net.somta.juggle.console.application.service.suite.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.suite.ISuiteAssembler;
import net.somta.juggle.console.application.service.suite.ISuiteService;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteQueryVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteDTO;
import net.somta.juggle.console.interfaces.param.suite.SuiteAddParam;
import net.somta.juggle.console.interfaces.param.suite.SuiteQueryParam;
import net.somta.juggle.console.interfaces.param.suite.SuiteUpdateParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 * @since 1.1.1
 */
@Service
public class SuiteServiceImpl implements ISuiteService {
    private final ISuiteRepository suiteRepository;

    public SuiteServiceImpl(ISuiteRepository suiteRepository) {
        this.suiteRepository = suiteRepository;
    }

    @Override
    public void addSuite(SuiteAddParam suiteAddParam) {
        SuiteEntity suiteEntity = ISuiteAssembler.IMPL.paramToEntity(suiteAddParam);
        suiteRepository.addSuite(suiteEntity);
    }

    @Override
    public void updateSuite(SuiteUpdateParam suiteUpdateParam) {
        SuiteEntity suiteEntity = ISuiteAssembler.IMPL.paramToEntity(suiteUpdateParam);
        suiteRepository.updateSuite(suiteEntity);
    }

    @Override
    public void deleteSuite(Long suiteId) {
        suiteRepository.deleteSuiteById(suiteId);
    }

    @Override
    public PageInfo getSuitePageList(SuiteQueryParam suiteQueryParam) {
        Page<SuiteDTO> page = PageHelper.startPage(suiteQueryParam.getPageNum(), suiteQueryParam.getPageSize());
        SuiteQueryVO suiteQueryVo = new SuiteQueryVO();
        suiteQueryVo.setSuiteName(suiteQueryParam.getSuiteName());
        List<SuiteVO> suiteVoList = suiteRepository.querySuiteList(suiteQueryVo);
        List<SuiteDTO> suiteList = ISuiteAssembler.IMPL.voListToDtoList(suiteVoList);
        PageInfo pageInfo = new PageInfo(suiteList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public List<SuiteDTO> getAllDomainList() {
        List<SuiteVO> suiteVoList = suiteRepository.querySuiteList(null);
        List<SuiteDTO> suiteList = ISuiteAssembler.IMPL.voListToDtoList(suiteVoList);
        return suiteList;
    }
}
