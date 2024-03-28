package net.somta.juggle.core.model.node;

public class CodeNode extends FlowNode{

    private LanguageType language;

    private String content;

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum LanguageType {
        Groovy
    }
}
