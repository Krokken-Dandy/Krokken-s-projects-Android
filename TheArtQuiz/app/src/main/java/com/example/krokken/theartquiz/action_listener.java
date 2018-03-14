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

    String quizName;
    EditText nameForOrder;
    TextView quizTitle;

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            //quizName();
            return true;
        }
        return false;
    }

    public void quizName() {
        MainActivity mainActivity = new MainActivity();
        nameForOrder = mainActivity.findViewById(R.id.name_input);
        quizTitle = mainActivity.findViewById(R.id.quiz_title);
        quizName = mainActivity.getString(R.string.art_quiz_for) + nameForOrder.getText().toString();
        quizTitle.setText(quizName);
    }
}
