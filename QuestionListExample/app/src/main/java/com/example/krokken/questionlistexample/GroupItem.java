package com.example.krokken.questionlistexample;

import java.util.List;

public class GroupItem {
    private String group_id;
    private String group_name;
    private List<QuestionItem> LIST_QUESTION;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<QuestionItem> getLIST_QUESTION() {
        return LIST_QUESTION;
    }

    public void setLIST_QUESTION(List<QuestionItem> LIST_QUESTION) {
        this.LIST_QUESTION = LIST_QUESTION;
    }
}