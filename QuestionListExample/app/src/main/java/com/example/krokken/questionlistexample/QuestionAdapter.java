package com.example.krokken.questionlistexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionAdapter extends BaseAdapter {

    private Context context;
    private List<GroupItem> LIST_GROUP;
    public QuestionAdapter(Context context, List<GroupItem> LIST_GROUP){
        this.context = context;
        this.LIST_GROUP = LIST_GROUP;
    }

    @Override
    public int getCount() {
        return LIST_GROUP.size();
    }

    @Override
    public Object getItem(int position) {
        return LIST_GROUP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.layout_group, parent, false);
        }
        GroupItem groupItem = LIST_GROUP.get(position);
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group);
        tv_group.setText(groupItem.getGroup_name());
        LinearLayout ll_questions = (LinearLayout) convertView.findViewById(R.id.ll_questions);

        ll_questions.removeAllViews();
        for (int i = 0; i<groupItem.getLIST_QUESTION().size(); i++){

            final QuestionItem questionItem = groupItem.getLIST_QUESTION().get(i);
            LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View question_view = inflater1.inflate(R.layout.layout_question, null);
            TextView tv_question_name = (TextView) question_view.findViewById(R.id.tv_question_name);
            tv_question_name.setText(questionItem.getQuestionName());

            RadioGroup radioGroup = (RadioGroup) question_view.findViewById(R.id.radio_group);
            if (questionItem.getAnswer() == -1){
                radioGroup.clearCheck();
            }else {
                ((RadioButton)radioGroup.getChildAt(questionItem.getAnswer())).setChecked(true);
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId){
                        case R.id.radio_button1 :
                            questionItem.setAnswer(0);
                            break;
                        case R.id.radio_button2 :
                            questionItem.setAnswer(1);
                            break;
                        case R.id.radio_button3 :
                            questionItem.setAnswer(2);
                            break;
                        case R.id.radio_button4 :
                            questionItem.setAnswer(3);
                            break;
                    }
//                    notifyDataSetChanged();
                }
            });
            ll_questions.addView(question_view);
        }

        return convertView;
    }
}