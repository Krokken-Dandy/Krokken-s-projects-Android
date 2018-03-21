package com.example.krokken.theartquiz;

import android.widget.TextView;

        import android.content.Context;
        import android.view.KeyEvent;
        import android.view.inputmethod.EditorInfo;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.TextView;

/**
 * Created by Krokken on 3/13/2018.
 */

public class action_listener implements TextView.OnEditorActionListener {

    EditText nameForOrder;
    TextView quizTitle;
    MainActivity mainActivity = new MainActivity();

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
//            nameForOrder = mainActivity.findViewById(R.id.name_input);
//            String theirName = nameForOrder.getText().toString();
               InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            return true;
        }
        return false;
    }

    private void quizName(String quizName) {
        quizTitle = mainActivity.findViewById(R.id.quiz_title);
//        quizName += mainActivity.getString(R.string.art_quiz_for);
        quizTitle.setText(quizName);
    }
}
