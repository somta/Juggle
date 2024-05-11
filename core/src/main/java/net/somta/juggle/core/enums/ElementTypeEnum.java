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
package net.somta.juggle.core.enums;

/**
 * 流程元素类型枚举类
 * @author husong
 * @since 1.0.0
 **/
public enum ElementTypeEnum {
    /**
     * 开始节点
     */
    START,
    /**
     * 结束节点
     */
    END,
    /**
     * 方法节点
     */
    METHOD,
    /**
     * 代码节点
     */
    CODE,
    /**
     * mysql节点
     */
    MYSQL,
    /**
     * AI节点
     */
    AI,
    /**
     * 判断节点
     */
    CONDITION;
}
