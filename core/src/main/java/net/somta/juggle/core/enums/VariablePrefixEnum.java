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
 * @author Gavin
 * @since 1.0.0
 */

public enum VariablePrefixEnum {
    INPUT_VARIABLE_PREFIX("input_","入参变量前缀"),
    OUTPUT_VARIABLE_PREFIX("output_","出参变量前缀");

    private String code;
    private String description;

    VariablePrefixEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }
}
