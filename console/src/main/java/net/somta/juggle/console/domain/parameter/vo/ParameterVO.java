package net.somta.juggle.console.domain.parameter.vo;

public class ParameterVO {

    /**
     * 来源类型  api：接口   flow：流程
     */
    private String sourceType;

    private Long sourceId;

    public ParameterVO(String sourceType, Long sourceId) {
        this.sourceType = sourceType;
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }
}
