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
package net.somta.juggle.core.model.node;

import net.somta.juggle.core.model.FillStruct;

import java.util.List;

/**
 * @author husong
 */
public class OpenAiNode extends FlowNode {
    private String product;
    private String apiKey;
    private String model;
    private String prompt;
    private FillStruct inputFillRule;
    private FillStruct outputFillRule;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public FillStruct getInputFillRule() {
        return inputFillRule;
    }

    public void setInputFillRule(FillStruct inputFillRule) {
        this.inputFillRule = inputFillRule;
    }

    public FillStruct getOutputFillRule() {
        return outputFillRule;
    }

    public void setOutputFillRule(FillStruct outputFillRule) {
        this.outputFillRule = outputFillRule;
    }
}
