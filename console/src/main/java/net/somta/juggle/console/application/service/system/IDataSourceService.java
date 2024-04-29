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
package net.somta.juggle.console.application.service.system;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.dto.system.DataSourceDTO;
import net.somta.juggle.console.interfaces.param.system.DataSourceAddParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceQueryParam;
import net.somta.juggle.console.interfaces.param.system.DataSourceUpdateParam;

/**
 * @author husong
 * @since 1.2.0
 */
public interface IDataSourceService {

    Boolean addDataSource(DataSourceAddParam dataSourceAddParam);

    void deleteDataSource(Long dataSourceId);

    Boolean updateDataSource(DataSourceUpdateParam dataSourceUpdateParam);

    DataSourceDTO getDataSource(Long dataSourceId);

    PageInfo getDataSourcePageList(DataSourceQueryParam dataSourceQueryParam);
}
